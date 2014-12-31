/**
 * BaseBsn.java
 * 17/apr/2012
 * @author Gaetano
 * <p>Base Business class extended by all other business classes: exposes basic facilities like DB connection retrieval</p>
 */


package com.oxybay.web.business.system.root;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import com.oxybay.web.beans.system.caching.CacheBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.resources.Resources;
import com.oxybay.web.resources.keys.ConfigKeys;
import com.oxybay.web.resources.keys.VarKeys;
import com.oxybay.web.resources.utils.Utils;

public class BaseBsn {
	
	/* data info */
	private BsnDataInfo data;
  /* current cache key */
  private String cacheKey = "";
  /* force no cache */
  public boolean forceNoCache = false;
  /* cache refer */
  private CacheBean cache = null;

  
  /**
	 * Constructor
	 */
	public BaseBsn(BsnDataInfo data) {
		this.data = data!=null ? data : new BsnDataInfo(Resources.getInstance(), Logger.getLogger(this.getClass()));  
		cache = (CacheBean)this.data.getResources().getServletContext().getAttribute(VarKeys.APP_CACHE);
	}
	
	/**
	 * Log an Error
	 * @param e
	 */
	protected void traceError(Exception e) {
		this.data.getLog().error(Utils.trace(e));
	}
	
	/**
	 * @param ps
	 * @param e
	 */
	protected void traceQueryError(PreparedStatement ps, Exception e) {
		this.data.getLog().error("QUERY = "+ps);
		this.data.getLog().error(Utils.trace(e));
	}

	/**
	 * Debug Message
	 * @param message
	 */
	protected void debug(String message) {
		this.data.getLog().info(message);
	}
	
	/**
	 * Destroy
	 */
	public void destroy() {
		BaseBsn.releaseConnections(data);
	}
	
	/**
	 * Release all Connections
	 */
	public static void releaseConnections(BsnDataInfo dataInfo) {
		dataInfo.getResources().getDb().releaseDBConnections(dataInfo.getConnections());
	}
	
	
	/**
	 * Get a db Connection
	 */
	protected Connection getConnection(int db) throws Exception {
    return data.getConnection(db,1);
	}
	
	
	/**
	 * Get a connection to Main DB
	 * @return
	 * @throws Exception
	 */
	protected Connection getConnection() throws Exception {
    return data.getConnection(ConfigKeys.JDBC_DB_MAIN,1);
	}
	
	
	
	/**
	 * Get Cached Object
	 * @param filters
	 * @return
	 */
	protected Object getCached(String filters) {
		if (forceNoCache)
			return null;
		this.cacheKey = Utils.getClassMethod(3)+"#"+filters;
		return (cache != null) ? cache.getData(this.cacheKey) : null; 
	}
	
	/**
	 * Get Cached Object
	 * @return
	 */
	protected Object getCached() {
		if (forceNoCache)
			return null;
		this.cacheKey = Utils.getClassMethod(3);
		return (cache != null) ? cache.getData(this.cacheKey) : null;
	}
	

	/**
	 * Cache Object
	 * @param obj
	 * @param validTime
	 * @return
	 */
	protected Object caching(Object obj, long validTime) {
		if (!forceNoCache && cache!=null && this.cacheKey!=null) {
			cache.setData(this.cacheKey,obj,validTime);
		}
		return obj;
	}
	
	/**
	 * Cache Object
	 * @param obj
	 * @return
	 */
	protected Object caching(Object obj) {
		if (!forceNoCache && cache!=null && this.cacheKey!=null) {
			cache.setData(this.cacheKey,obj,0);
		}
		return obj;
	}
	

	/**
	 * @return the data
	 */
	public BsnDataInfo getData() {
		return data;
	}
	
}
