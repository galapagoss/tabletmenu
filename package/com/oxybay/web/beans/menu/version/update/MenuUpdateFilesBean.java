/**
 * MenuUpdateFilesBean.java
 * 29/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.version.update;

public class MenuUpdateFilesBean {

	/* url zip file */
	private String zipFile = "";
	/* size zip file */
	private long zipFileSize = 0;
	/* url cmd file */
	private String cmdFile = "";
	/* size com file */
	private long cmdFileSize = 0;
	
	
	
	/**
	 * Empty Construct
	 */
	public MenuUpdateFilesBean() {	}
	
	
	/**
	 * @param zipFile
	 * @param zipFileSize
	 * @param cmdFile
	 * @param cmdFileSize
	 */
	public MenuUpdateFilesBean(String zipFile, long zipFileSize, String cmdFile, long cmdFileSize) {
		this.zipFile = zipFile;
		this.zipFileSize = zipFileSize;
		this.cmdFile = cmdFile;
		this.cmdFileSize = cmdFileSize;
	}





	/**
	 * @return the zipFile
	 */
	public String getZipFile() {
		return zipFile;
	}
	/**
	 * @param zipFile the zipFile to set
	 */
	public void setZipFile(String zipFile) {
		this.zipFile = zipFile;
	}
	/**
	 * @return the cmdFile
	 */
	public String getCmdFile() {
		return cmdFile;
	}
	/**
	 * @param cmdFile the cmdFile to set
	 */
	public void setCmdFile(String cmdFile) {
		this.cmdFile = cmdFile;
	}


	/**
	 * @return the zipFileSize
	 */
	public long getZipFileSize() {
		return zipFileSize;
	}


	/**
	 * @param zipFileSize the zipFileSize to set
	 */
	public void setZipFileSize(long zipFileSize) {
		this.zipFileSize = zipFileSize;
	}


	/**
	 * @return the cmdFileSize
	 */
	public long getCmdFileSize() {
		return cmdFileSize;
	}


	/**
	 * @param cmdFileSize the cmdFileSize to set
	 */
	public void setCmdFileSize(long cmdFileSize) {
		this.cmdFileSize = cmdFileSize;
	}
		
}
