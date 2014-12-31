/**
 * CacheBean.java
 * 17/feb/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.caching;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.oxybay.web.beans.system.caching.monitor.CacheMonitorBean;
import com.oxybay.web.beans.system.caching.monitor.CacheMonitorElemBean;

public class CacheBean {

	public final static long 		DEFAULT_EXPIRETIME 						= 5*60*1000; // 5 minutes

	/* cache map */
	private ConcurrentHashMap<String, CacheElemBean> cacheMap = null;
	/* default valid time */
	private long defaultExpireTime = DEFAULT_EXPIRETIME;
	

	/* memory usage (approx) */
	private long memoryUsage = 0;
	/* requests count*/
	private long reqCounts = 0;
	/* requests successfully*/
	private long reqOk = 0;
	
	/**
	 * Constructor 
	 */
	public CacheBean() {
		this.cacheMap = new ConcurrentHashMap<String, CacheElemBean>();
	}


	/**
	 * Get Monitor Data
	 * @return
	 */
	public CacheMonitorBean getMonitorData() {
		ArrayList<CacheMonitorElemBean> elems = new ArrayList<CacheMonitorElemBean>();
		try { 
			for(Entry<String, CacheElemBean> item: this.cacheMap.entrySet())
				elems.add(new CacheMonitorElemBean(item.getValue()));
		} catch(Exception e) {}
		return new CacheMonitorBean(this.reqCounts, this.reqOk, getHitRate(), elems);
	}
	
	/**
	 * Constructor 
	 */
	public CacheBean(long defaulExpireTime) {
		this.cacheMap = new ConcurrentHashMap<String, CacheElemBean>();
		this.defaultExpireTime = defaulExpireTime;
	}
	
	/**
	 * Get Cached Object
	 * @param key
	 * @return
	 */
	public Object getData(String key) {
		this.reqCounts++;
		CacheElemBean elem = this.cacheMap.get(key);
		if (elem!=null && !elem.isExpired()) {
			this.reqOk++;
			return elem.getObject();
		}
		return null;
	}
	
	/**
	 * Save object in cache
	 * @param elem
	 */
	public void setData(String key, Object obj, long expireTime) {
		if (key!=null & obj!=null) 
			this.cacheMap.put(key, new CacheElemBean(key, (expireTime>0 ? expireTime : this.defaultExpireTime), obj));
		//debug();
	}

	
	/**
	 * Map Snapshot for debug 
	 */
	public void debug() {
		Logger log = Logger.getLogger(this.getClass());
		log.info("*********** MAP CACHE SNAPSHOT **********************************************************");
		log.info("Memory Usage:"+memoryUsage+"   Hit Rate:"+getHitRate());
		for(Iterator<CacheElemBean> i = this.cacheMap.values().iterator(); i.hasNext();) {
			CacheElemBean elem = i.next();
			log.info(""+elem.getKey()+"\t\t\t"+elem.getObject().hashCode()+"\t\t\t"+elem.getReleaseTime()+"\t\t\t"+elem.getValidTime());
		}
	}
	
	/**
	 * Get Values Iterator
	 * @return
	 */
	public Iterator<CacheElemBean> getIterator() {
		return this.cacheMap.values().iterator();
	}
	
	/**
	 * Remove Cached Object
	 * @param key
	 */
	public void removeData(String key) {
		this.cacheMap.remove(key);
	}
	
	/**
	 * @return the hitRate
	 */
	public float getHitRate() {
		return (float)this.reqOk/this.reqCounts;
	}
	
}
