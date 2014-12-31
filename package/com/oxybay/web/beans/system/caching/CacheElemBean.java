/**
 * CacheBean.java
 * 17/feb/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.caching;

public class CacheElemBean {

	/* key */
	private String key = "";
	/* release time */
	private long releaseTime = 0;
	/* valid time */
	private long validTime = CacheBean.DEFAULT_EXPIRETIME;
	/* object */
	private Object object = null;
	/* byte size */
	private long size = 0;
	
	
	
	/**
	 * Constructor
	 * @param key
	 * @param validTime
	 * @param object
	 */
	public CacheElemBean(String key, long validTime, Object object) {
		this.key = key;
		this.validTime = validTime;
		this.object = object;
		this.releaseTime = System.currentTimeMillis();
	}




	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}




	/**
	 * @return the releaseTime
	 */
	public long getReleaseTime() {
		return releaseTime;
	}




	/**
	 * @return the validTime
	 */
	public long getValidTime() {
		return validTime;
	}




	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}
	
	
	/**
	 * Is Expired
	 * @return
	 */
	public boolean isExpired(){
		return (System.currentTimeMillis()-this.releaseTime)>this.validTime;
	}




	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}




	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	
}
