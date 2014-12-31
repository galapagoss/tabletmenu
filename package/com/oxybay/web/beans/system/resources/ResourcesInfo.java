/**
 * ResourcesInfo.java
 * 14/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.resources;

import java.util.Date;


public class ResourcesInfo {

	/* flag indicating that the Resources class has been initialized */
  private boolean started = false;
  /* resources creation datetime */
  private Date creationeTime = new Date();
  /** server name */
  private String serverName = "";
  /* base directory for the application */
  private String baseDir = "";
  
  
	/**
	 * @param serverName
	 * @param baseDir
	 */
	public ResourcesInfo(String serverName, String baseDir) {
		this.serverName = serverName;
		this.baseDir = baseDir;
	}


	/**
	 * @return the started
	 */
	public boolean isStarted() {
		return started;
	}


	/**
	 * @return the creationeTime
	 */
	public Date getCreationeTime() {
		return creationeTime;
	}


	/**
	 * @return the serverName
	 */
	public String getServerName() {
		return serverName;
	}


	/**
	 * @return the baseDir
	 */
	public String getBaseDir() {
		return baseDir;
	}


	/**
	 * Set Init completed
	 */
	public void startingCompleted() {
		this.started = true;
	}

	
}
