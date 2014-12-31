/**
 * ConnectionInfoMonitorBean.java
 * 25/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.connections;

import java.util.Date;

public class ConnectionInfoMonitorBean {
	
	private static final int LIMIT_CLASSSTRING			= 30;

	/* db */
	private String db = "";
	/* pointer */
  private String pointer = "";
	/* business class */
  private String bsnClass = "";
  /** creation date  */
  private Date created = null;
  /** milliseconds to obtain DB connection */
  private long timeObtainDB = 0;
  /** stack html */
  private String stackHtml = "";

  
  
	/**
	 * Constructor
	 * @param conInfo
	 */
	public ConnectionInfoMonitorBean(String db, ConnectionInfo conInfo) {
		this.db = db;
		this.pointer = conInfo.getConPointer();
		this.bsnClass = limitClass(conInfo.getBsnName(),LIMIT_CLASSSTRING);
		this.created = new Date(conInfo.getCreated());
		this.timeObtainDB = conInfo.getTimeObtainDB();
		String[] s = conInfo.getStack().split(",");
		if (s!=null)
			for(String item : s) {
				if (!this.stackHtml.equals(""))
					this.stackHtml += "<BR/>";
				this.stackHtml += limitClass(item, LIMIT_CLASSSTRING);
			}
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
	 * @return the db
	 */
	public String getDb() {
		return db;
	}


	/**
	 * @return the pointer
	 */
	public String getPointer() {
		return pointer;
	}


	/**
	 * @return the bsnClass
	 */
	public String getBsnClass() {
		return bsnClass;
	}


	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}


	/**
	 * @return the timeObtainDB
	 */
	public long getTimeObtainDB() {
		return timeObtainDB;
	}


	/**
	 * @return the stackHtml
	 */
	public String getStackHtml() {
		return stackHtml;
	}
	
	
}
