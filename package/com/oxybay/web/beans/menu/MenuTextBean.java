/**
 * MenuTextBean.java
 * 28/ott/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu;

import java.util.Date;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="menu_text_2_language",order=0)
public class MenuTextBean {

	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* id menu */
	@TableField("id_menu")
	private int idMenu = 0;
	/* language */
	@TableField("lang")
	private String lang = null;
	/* presentation text */
	@TableField("presentation")
	private String textPresentation = "";
	/* footer text */
	@TableField("footer")
	private String textFooter = "";
	
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
	 * 
	 */
	public MenuTextBean() {	}
	
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
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the textPresentation
	 */
	public String getTextPresentation() {
		return textPresentation;
	}
	/**
	 * @param textPresentation the textPresentation to set
	 */
	public void setTextPresentation(String textPresentation) {
		this.textPresentation = textPresentation;
	}
	/**
	 * @return the textFooter
	 */
	public String getTextFooter() {
		return textFooter;
	}
	/**
	 * @param textFooter the textFooter to set
	 */
	public void setTextFooter(String textFooter) {
		this.textFooter = textFooter;
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
	
	
}
