/**
 * AttachBean.java
 * 15/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.common;

import java.io.File;
import java.util.Date;


import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableFieldSkip;
import com.oxybay.web.business.system.table.annotations.TableType;
import com.oxybay.web.resources.Resources;
import com.oxybay.web.resources.keys.CustomKeys;

@TableDef(table="attachment",order=0)
public class AttachBean {
	
	private static final int MAX_LENGTH_FILENAME			= 100;

	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* id domain */
	@TableField("id_domain")
	private int idDomain = 0;
	/* path */
	private String path = "";
	/* filename */
	private String filename = "";
	/* filetype  */
	private String filetype = "";
	/* width */
	private int width = 0;
	/* height */
	private int height = 0;
	/* size */
	private long size = 0;
	/* file data */
	@TableField("filedata")
	@TableFieldSkip(insert=true,update=true,read=true)
	private File datafile = null;
	
	/* filename for upload */
	@TableType(TableType.SKIP)
	private String datafileFileName = "";
	/* content type for upload */
	@TableType(TableType.SKIP)
	private String datafileContentType = "";
	
	/* creation */
	@TableType(TableType.CREATING)
	@TableField("creation_date")
	private Date creationDate = null;
	/* update date */
	@TableType(TableType.UPDATING)
	@TableField("update_date")
	private Date updateDate = null;
	/* deleted */
	@TableType(TableType.DELETING)
	private Date deleted = null;
	
	
	
	/**
	 * Empty Construct
	 */
	public AttachBean() {	}
	
	
	/**
	 * @param id
	 */
	public AttachBean(int id) {
		this.id = id;
	}

	
	

	/**
	 * @param id
	 * @param idDomain
	 * @param path
	 * @param filename
	 */
	public AttachBean(int id, int idDomain, String path, String filename) {
		this.id = id;
		this.idDomain = idDomain;
		this.path = path;
		this.filename = filename;
	}


	/**
	 * @param id
	 * @param idDomain
	 * @param path
	 * @param filename
	 * @param filetype
	 * @param width
	 * @param height
	 * @param size
	 * @param datafile
	 */
	public AttachBean(int id, int idDomain, String path, String filename, String filetype, int width, int height, long size, File datafile) {
		this.id = id;
		this.idDomain = idDomain;
		this.path = path;
		this.filename = filename;
		this.filetype = filetype;
		this.width = width;
		this.height = height;
		this.size = size;
		this.datafile = datafile;
	}

	/**
	 * Reset Data
	 */
	public void reset(){
		this.id =0;
		this.path = "";
		this.filename = "";
		this.filetype = "";
		this.width = 0;
		this.height = 0;
		this.size = 0;
		this.datafile = null;
	}


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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the filetype
	 */
	public String getFiletype() {
		return filetype;
	}
	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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
	
	
	/**
	 * @return the datafile
	 */
	public File getDatafile() {
		return datafile;
	}

	/**
	 * @param datafile the datafile to set
	 */
	public void setDatafile(File datafile) {
		this.datafile = datafile;
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
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the deleted
	 */
	public Date getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the datafileFileName
	 */
	public String getDatafileFileName() {
		return datafileFileName;
	}

	/**
	 * @param datafileFileName the datafileFileName to set
	 */
	public void setDatafileFileName(String datafileFileName) {
		if (datafileFileName.length()>MAX_LENGTH_FILENAME)
			this.datafileFileName = datafileFileName.substring(datafileFileName.length()-MAX_LENGTH_FILENAME);
		else
			this.datafileFileName = datafileFileName;
	}

	/**
	 * @return the datafileContentType
	 */
	public String getDatafileContentType() {
		return datafileContentType;
	}

	/**
	 * @param datafileContentType the datafileContentType to set
	 */
	public void setDatafileContentType(String datafileContentType) {
		this.datafileContentType = datafileContentType;
	}
	
	/**
	 * Get Url
	 * @return
	 */
	public String getUrl(){
		return "/"+Resources.getInstance().getProperties().getProperty(CustomKeys.CONFIG_PARAM_FILE_FOLDER)+"/"+this.idDomain+"/"+Resources.getInstance().getProperties().getProperty(CustomKeys.CONFIG_PARAM_ATTACH_FOLDER)+"/"+this.path.replace("\\", "/")+"/"+this.filename;
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
	 * Get Real Path
	 * @param resources
	 * @return
	 */
	public String getRealPath(Resources resources){
		return resources.getInfo().getBaseDir()+File.separator+resources.getProperties().get(CustomKeys.CONFIG_PARAM_FILE_FOLDER)+File.separator+this.idDomain+File.separator+resources.getProperties().get(CustomKeys.CONFIG_PARAM_ATTACH_FOLDER)+File.separator+this.path+File.separator+this.filename;
	}
	
}
