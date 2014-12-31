/**
 * Statistics about database connections pool
 */

package com.oxybay.web.beans.system.connections;

public class ConnectionInfo {

	/** connection pointer in string (primary key) */
  private String conPointer = "";
	
	/** business invoker */
  private String bsnName = "";

  /** creation date in milliseconds */
  private long created = 0L;
  
  /** milliseconds to obtain DB connection */
  private long timeObtainDB = 0;
  
  /** milliseconds to release DB connection */
  private long timeReleaseDB = 0;
  
  /** part of stack */
  private String stack = "";


  /**
   * <p>
   * Empty Constructor
   * </p>
   */
  public ConnectionInfo() {	
  }

  
  /**
   * <p>
   * Standard Constructor
   * </p>
   */
  public ConnectionInfo(String conPointer, String bsnName, long obtainConn, String stack) {
  	this.conPointer = conPointer;
  	this.bsnName    = bsnName;
  	this.created    = System.currentTimeMillis();
  	this.timeObtainDB = obtainConn;
  	this.stack = stack;
  }


	/**
	 * <P>
	 * Get bsnName field
	 * <P>
	 * @return Returns the bsnName.
	 */
	public String getBsnName() {
		return bsnName;
	}


	/**
	 * <P>
	 * Get conPointer field
	 * <P>
	 * @return Returns the conPointer.
	 */
	public String getConPointer() {
		return conPointer;
	}


	/**
	 * <P>
	 * Get created field
	 * <P>
	 * @return Returns the created.
	 */
	public long getCreated() {
		return created;
	}



	/**
	 * <P>
	 * Get timeObtainDB field
	 * <P>
	 * @return Returns the timeObtainDB.
	 */
	public long getTimeObtainDB() {
		return timeObtainDB;
	}


	/**
	 * <P>
	 * Get timeReleaseDB field
	 * <P>
	 * @return Returns the timeReleaseDB.
	 */
	public long getTimeReleaseDB() {
		return timeReleaseDB;
	}

	/**
	 * @return the stack
	 */
	public String getStack() {
		return stack;
	}


	/**
	 * @param timeReleaseDB the timeReleaseDB to set
	 */
	public void setTimeReleaseDB(long timeReleaseDB) {
		this.timeReleaseDB = timeReleaseDB;
	}

	

}