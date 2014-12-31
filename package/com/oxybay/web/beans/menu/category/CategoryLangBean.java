/**
 * CategoryLangbean.java
 * 18/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.category;

import java.util.Date;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="category_2_language",order=0)
public class CategoryLangBean {

	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* id category */
	@TableField("id_category")
	private int idCategory = 0;
	/* label */
	@TableField("lang")
	private String label = "";
	/* title */
	private String title = "";
	/* subtitle */
	private String subtitle = "";
	/* body */
	private String body = "";
	
	/* creation */
	@TableType(TableType.CREATING)
	@TableField("creation_date")
	@TableOrder(id=1,type=TableOrder.TYPE_DESC,exclude="")
	private Date creationDate = null;
	/* update date */
	@TableType(TableType.UPDATING)
	@TableField("update_date")
	private Date updateDate = null;
	/* deleted */
	@TableType(TableType.DELETING)
	private Date deleted = null;
	
	
	/**
	 * Constructor
	 */
	public CategoryLangBean() {	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}


	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}


	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}


	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}


	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
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
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}


	/**
	 * @return the idCategory
	 */
	public int getIdCategory() {
		return idCategory;
	}


	/**
	 * @param idCategory the idCategory to set
	 */
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
		
	
}
