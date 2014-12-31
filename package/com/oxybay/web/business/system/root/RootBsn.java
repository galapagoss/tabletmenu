/**
 * RootBsn.java
 * 17/apr/2012
 * @author Gaetano
 */
package com.oxybay.web.business.system.root;

import java.sql.Connection;

import com.oxybay.web.beans.system.resources.BsnDataInfo;


public class RootBsn extends BaseBsn {
	
	
  
  
	/**
	 * Constructor
	 */
	public RootBsn(BsnDataInfo data) {
		super(data);
	}
	
	/**
	 * Constructor
	 */
	public RootBsn() {
		super(null);
	}
	
	
	
	/**
	 * Last ID
	 * @return long
	 */
	public long lastID(Connection con) {
		return this.getData().lastID(con);
	}
	
	/**
	 * Last ID
	 * @return long
	 */
	public long lastID() throws Exception {
		return this.getData().lastID(getConnection());
	}
	
	

	/**
	 * Get query affected records 
	 * @return
	 */
	public int listCount(Connection con) {
		return this.getData().listCount(con);
	}
	
	/**
	 * Get query affected
	 * @return
	 * @throws Exception
	 */
	public int listCount() throws Exception {
		return this.getData().listCount(getConnection());
	}

}
