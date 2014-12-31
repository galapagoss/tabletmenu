/**
 * Resources class
 * Loads connections to all system resources needed, specifically the
 * Database and File System
 */

package com.oxybay.web.resources;

import java.io.FileInputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.oxybay.web.beans.system.caching.CacheBean;
import com.oxybay.web.beans.system.resources.ResourcesInfo;
import com.oxybay.web.resources.connection.ConnectionManager;
import com.oxybay.web.resources.keys.ConfigKeys;
import com.oxybay.web.resources.keys.VarKeys;
import com.oxybay.web.resources.utils.Utils;

public class Resources {

	/* single instance resource object */
  private static Resources _instance = new Resources();
  /* logging */
  private Logger log = Logger.getLogger(getClass().getName());
  /* reference to Connection Pool  */
  private ConnectionManager db = null;
  /* Properties file */
  private Properties properties = new Properties();
  /* servlet context */
  private ServletContext servletContext = null;
  /* data info */
  private ResourcesInfo info = null;
    
  
  /**
   * Empty Constructor
   */
  public Resources() {}
  
  
  /**
   * <p>
   * Setup the resource object: get initialization parameters and
   * </p>
   * </p>
   * @param ctx Servlet context for the web application
   */
  public void setup(ServletContext ctx) {
  
		this.servletContext = ctx;
		String serverName = "";
    try { serverName = java.net.InetAddress.getLocalHost().getHostName();} catch (java.net.UnknownHostException e) { Utils.trace(e);	}
    this.info = new ResourcesInfo(serverName, ctx.getRealPath("").replace("\\","/"));
    
		log.info("************** RELOAD "+ConfigKeys.SITE_NAME+" ****************");

		/* Config Params */
    loadConfigParams();
    
    // db init
    db = new ConnectionManager(log);
    
    // Custom routines
    ResourceCustom.initRoutines(this);
    
    info.startingCompleted();
  }


  /**
   * <p> Load configuration params</p>
   */
  private void loadConfigParams() {
  	log.info(" .... Load Application settings ..... ");
  	try {
  		properties.load(new FileInputStream(info.getBaseDir() + ConfigKeys.CONFIG_PATH_FILE));
  		// Cache config
  		loadConfigCacheParams();
  		// Custom Params
  		ResourceCustom.loadConfigProperties(properties);
  	} catch (Exception e) {
  		log.error("Error to load application settings: " + e.toString());
  	}
  }
  
  
  /**
   * Load Cache configuration
   */
  private void loadConfigCacheParams() {
  	boolean activeCache = false;
  	try { activeCache = Boolean.parseBoolean(properties.getProperty(ConfigKeys.CONFIG_PARAM_CACHE_ACTIVATE)); } catch (Exception e) {}
  	if (activeCache) {
  		log.info("--... Cache activated ...--");
  		long expireTime = 0;
  		try { expireTime = Long.parseLong(properties.getProperty(ConfigKeys.CONFIG_PARAM_CACHE_EXPIRETIME));} catch(Exception e) {}
  		servletContext.setAttribute(VarKeys.APP_CACHE, new CacheBean(expireTime*1000));
  	}
  }
  
  /**
   * <p>
   * Gets an instance of the Resources object
   * </p>
   * @return the instance of the Resources object
   */
  public static Resources getInstance() {
    return _instance;
  }

	
  /**
	 * @return the log
	 */
	public Logger getLog() {
		return log;
	}


	/**
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}


	/**
	 * @return the servletContext
	 */
	public ServletContext getServletContext() {
		return servletContext;
	}


	/**
	 * @return the db
	 */
	public ConnectionManager getDb() {
		return db;
	}


	/**
	 * @return the info
	 */
	public ResourcesInfo getInfo() {
		return info;
	}

	
}