/**
 * LanguageCustomLabel.java
 * 25/ott/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.language;

import java.util.Date;

public class LanguageCustomLabel {
	
	/* id */
	private int id = 0;
	/* value */
	private String value = "";
	/* creation date */
	private Date creationeDate = null;
	/* update date */
	private Date updateDate = null;
	/* default */
	private LanguageDefaultLabel label = null;
	
	
	/**
	 * 
	 */
	public LanguageCustomLabel() {	}


	/**
	 * @param id
	 * @param value
	 * @param label
	 */
	public LanguageCustomLabel(int id, String value, LanguageDefaultLabel label) {
		this.id = id;
		this.value = value;
		this.label = label;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}


	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/**
	 * @return the creationeDate
	 */
	public Date getCreationeDate() {
		return creationeDate;
	}


	/**
	 * @param creationeDate the creationeDate to set
	 */
	public void setCreationeDate(Date creationeDate) {
		this.creationeDate = creationeDate;
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
	 * @return the label
	 */
	public LanguageDefaultLabel getLabel() {
		return label;
	}


	/**
	 * @param label the label to set
	 */
	public void setLabel(LanguageDefaultLabel label) {
		this.label = label;
	}
	
}
