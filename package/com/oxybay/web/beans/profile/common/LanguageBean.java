/**
 * LanguageBean.java
 * 22/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.profile.common;

import java.io.Serializable;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="language",order=0)
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = -718732050723977506L;

	/* label */
	@TableType(TableType.KEY)
	private String label = "";
	/* description */
	private String description = "";
	/* active */
	private boolean active = false;
	/* application */
	private boolean application = false;
	
	/* main language */
	@TableType(TableType.SKIP)
	private boolean mainLanguage = false;
	/* menu language */
	@TableType(TableType.SKIP)
	private boolean menuLanguage = false;

	/**
	 * Constructor
	 */
	public LanguageBean() {	}
	

	/**
	 * @param label
	 */
	public LanguageBean(String label) {
		this.label = label;
	}


	/**
	 * @param label
	 * @param description
	 * @param active
	 * @param application
	 * @param mainLanguage
	 * @param menuLanguage
	 */
	public LanguageBean(String label, String description, boolean active,
			boolean application, boolean mainLanguage, boolean menuLanguage) {
		this.label = label;
		this.description = description;
		this.active = active;
		this.application = application;
		this.mainLanguage = mainLanguage;
		this.menuLanguage = menuLanguage;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}


	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}


	/**
	 * @return the application
	 */
	public boolean isApplication() {
		return application;
	}


	/**
	 * @param application the application to set
	 */
	public void setApplication(boolean application) {
		this.application = application;
	}


	/**
	 * @return the mainLanguage
	 */
	public boolean isMainLanguage() {
		return mainLanguage;
	}


	/**
	 * @param mainLanguage the mainLanguage to set
	 */
	public void setMainLanguage(boolean mainLanguage) {
		this.mainLanguage = mainLanguage;
	}


	/**
	 * @return the menuLanguage
	 */
	public boolean isMenuLanguage() {
		return menuLanguage;
	}


	/**
	 * @param menuLanguage the menuLanguage to set
	 */
	public void setMenuLanguage(boolean menuLanguage) {
		this.menuLanguage = menuLanguage;
	}

}
