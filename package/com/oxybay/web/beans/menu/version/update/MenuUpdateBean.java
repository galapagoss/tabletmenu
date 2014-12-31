/**
 * MenuUpdateBean.java
 * 29/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.version.update;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class MenuUpdateBean {

	/* version */
	private int version = 0;
	/* url sql file */
	private String sqlFile = "";
	/* size sql file */
	private long sqlFileSize = 0;
	
	
	/* incremental update files */
	private List<MenuUpdateFilesBean> files = new ArrayList<MenuUpdateFilesBean>();
	
	
	
	
	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}
	/**
	 * @return the sqlFile
	 */
	public String getSqlFile() {
		return sqlFile;
	}
	/**
	 * @param sqlFile the sqlFile to set
	 */
	public void setSqlFile(String sqlFile) {
		this.sqlFile = sqlFile;
	}
	/**
	 * @return the files
	 */
	public List<MenuUpdateFilesBean> getFiles() {
		return files;
	}
	/**
	 * @param files the files to set
	 */
	public void setFiles(List<MenuUpdateFilesBean> files) {
		this.files = files;
	}
	/**
	 * @return the sqlFileSize
	 */
	public long getSqlFileSize() {
		return sqlFileSize;
	}
	/**
	 * @param sqlFileSize the sqlFileSize to set
	 */
	public void setSqlFileSize(long sqlFileSize) {
		this.sqlFileSize = sqlFileSize;
	}
	
	
}
