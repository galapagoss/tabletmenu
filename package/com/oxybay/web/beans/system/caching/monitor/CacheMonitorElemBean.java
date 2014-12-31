/**
 * CacheMonitorElemBean.java
 * 24/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.caching.monitor;

import java.util.Date;

import com.oxybay.web.beans.system.caching.CacheElemBean;

public class CacheMonitorElemBean {
	
	private static final int LIMIT_CLASSSTRING			= 30;
	private static final int LIMIT_METHODSTRING			= 40;
	private static final int LIMIT_PARAMSSTRING			= 50;

	/* method */
	private String method = "";
	/* params */
	private String params = "";
	/* creation date */
	private Date creation = null;
	/* expire date */
	private Date expire = null;
	/* object class */
	private String objClass = "";
	
	
	
	/**
	 * Constructor
	 */
	public CacheMonitorElemBean(CacheElemBean elem) {
		this.method = limitClass(elem.getKey().replaceAll("(#(.*))",""),LIMIT_METHODSTRING);
		this.params = limitString(elem.getKey().replaceAll("((.*)#)",""),LIMIT_PARAMSSTRING);
		this.creation = new Date(elem.getReleaseTime());
		this.expire = new Date(elem.getReleaseTime()+elem.getValidTime());
		this.objClass = limitClass(elem.getObject().getClass().getName(),LIMIT_CLASSSTRING);
	}
	
	
	/**
	 * Limit Class String
	 * @param s
	 * @param limit
	 * @return
	 */
	private String limitClass(String s, int limit) {
		String res = "";
		String[] split = s.split("\\.");
		if (split.length>0)
			for(int i=split.length-1; i>=0; i-- ) {
				res = "."+split[i] + res;
				if (res.length()>limit)
					return ".."+res;
			}
		return res;
	}

	/**
	 * Limit String
	 * @param s
	 * @param limit
	 * @return
	 */
	private String limitString (String s, int limit) {
		return (s.length()>limit) ? ".."+s.substring(s.length()-limit) : s;
	}
	
	
	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}
	/**
	 * @return the creation
	 */
	public Date getCreation() {
		return creation;
	}
	/**
	 * @return the expire
	 */
	public Date getExpire() {
		return expire;
	}
	/**
	 * @return the objClass
	 */
	public String getObjClass() {
		return objClass;
	}
	
	
}
