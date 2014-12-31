/**
 * MenuVersion.java
 * 13/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.version;

import java.util.Date;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableType;
import com.oxybay.web.resources.Resources;
import com.oxybay.web.resources.keys.CustomKeys;

@TableDef(table="menu_version",order=1)
public class MenuVersionBean {
	
	public static final String BASE_FOLDER 		= "deploy_";

	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* id menu */
	@TableField("id_menu")
	private int idMenu = 0;
	/* id domain */
	@TableField("id_domain")
	private int idDomain = 0;
	/* version */
	@TableOrder(id=1,type=TableOrder.TYPE_DESC,exclude="")
	private int version = 0;
	/* sql file name */
	@TableField("file_sql")
	private String fileSql = "";
	/* sql file size */
	@TableField("file_sql_size")
	private long fileSqlSize = 0;
	/* zip attachments file name */
	@TableField("file_zip")
	private String fileZip = "";
	/* zip attachments file size */
	@TableField("file_zip_size")
	private long fileZipSize = 0;
	/* operations file name */
	@TableField("file_op")
	private String fileOp = "";
	/* operations file size */
	@TableField("file_op_size")
	private long fileOpSize = 0;
	/* date deploy end */
	@TableField("deploy_end")
	private Date deployEnd = null;
	/* creation */
	@TableType(TableType.CREATING)
	@TableField("creation_date")
	@TableOrder(id=1,type=TableOrder.TYPE_DESC,exclude="")
	private Date creationDate = null;
	
	
	/**
	 * Constructor
	 */
	public MenuVersionBean() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the idMenu
	 */
	public int getIdMenu() {
		return idMenu;
	}

	/**
	 * @param idMenu the idMenu to set
	 */
	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	/**
	 * @return the idDomain
	 */
	public int getIdDomain() {
		return idDomain;
	}

	/**
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(int idDomain) {
		this.idDomain = idDomain;
	}

	/**
	 * @return the fileSql
	 */
	public String getFileSql() {
		return fileSql;
	}

	/**
	 * @param fileSql the fileSql to set
	 */
	public void setFileSql(String fileSql) {
		this.fileSql = fileSql;
	}

	/**
	 * @return the fileZip
	 */
	public String getFileZip() {
		return fileZip;
	}

	/**
	 * @param fileZip the fileZip to set
	 */
	public void setFileZip(String fileZip) {
		this.fileZip = fileZip;
	}

	/**
	 * @return the fileOp
	 */
	public String getFileOp() {
		return fileOp;
	}

	/**
	 * @param fileOp the fileOp to set
	 */
	public void setFileOp(String fileOp) {
		this.fileOp = fileOp;
	}

	/**
	 * @return the deployEnd
	 */
	public Date getDeployEnd() {
		return deployEnd;
	}

	/**
	 * @param deployEnd the deployEnd to set
	 */
	public void setDeployEnd(Date deployEnd) {
		this.deployEnd = deployEnd;
	}

	/**
	 * Get Base Url
	 * @return
	 */
	private String getBaseUrl(){
		return "/"+Resources.getInstance().getProperties().getProperty(CustomKeys.CONFIG_PARAM_FILE_FOLDER)+"/"+this.idDomain+"/"+Resources.getInstance().getProperties().getProperty(CustomKeys.CONFIG_PARAM_VERSION_FOLDER)+"/"+BASE_FOLDER+this.version+"/";
	}
	
	/**
	 * Get Base URL
	 * @param idDomain
	 * @param idVersion
	 * @return
	 */
	public static String getBaseUrl(int idDomain, int idVersion) {
		return "/"+Resources.getInstance().getProperties().getProperty(CustomKeys.CONFIG_PARAM_FILE_FOLDER)+"/"+idDomain+"/"+Resources.getInstance().getProperties().getProperty(CustomKeys.CONFIG_PARAM_VERSION_FOLDER)+"/"+BASE_FOLDER+idVersion+"/";
	}
	
	/**
	 * Get SQL File URL
	 * @return
	 */
	public String getSQLUrl(){
		return getBaseUrl()+this.fileSql;
	}
	/**
	 * Get ZIP File URL
	 * @return
	 */
	public String getZIPUrl(){
		return getBaseUrl()+this.fileZip;
	}
	/**
	 * Get CMD File URL
	 * @return
	 */
	public String getOPUrl(){
		return getBaseUrl()+this.fileOp;
	}

	/**
	 * @return the fileSqlSize
	 */
	public long getFileSqlSize() {
		return fileSqlSize;
	}

	/**
	 * @param fileSqlSize the fileSqlSize to set
	 */
	public void setFileSqlSize(long fileSqlSize) {
		this.fileSqlSize = fileSqlSize;
	}

	/**
	 * @return the fileZipSize
	 */
	public long getFileZipSize() {
		return fileZipSize;
	}

	/**
	 * @param fileZipSize the fileZipSize to set
	 */
	public void setFileZipSize(long fileZipSize) {
		this.fileZipSize = fileZipSize;
	}

	/**
	 * @return the fileOpSize
	 */
	public long getFileOpSize() {
		return fileOpSize;
	}

	/**
	 * @param fileOpSize the fileOpSize to set
	 */
	public void setFileOpSize(long fileOpSize) {
		this.fileOpSize = fileOpSize;
	}
	
	
}
