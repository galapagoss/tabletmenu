/**
 * LanguageMenuBean.java
 * 25/ott/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.language;

import java.util.ArrayList;
import java.util.List;

public class LanguageMenuBean {

	/* id */
	private int id = 0;
	/* id menu */
	private int idMenu = 0;
	/* label */
	private String label = "";
	/* description */
	private String description = "";
	/* active */
	private boolean active = false;
	/* main */
	private boolean main = false;
	/* fault labels */
	private boolean faultLabels = false;
	/* custom labels */
	private List<LanguageCustomLabel> customLabels = new ArrayList<LanguageCustomLabel>();
	/* warning */
	private enum Warning {FAULTLABELS}
	

	/**
	 * 
	 */
	public LanguageMenuBean() {	}
	
	
	
	/**
	 * @param id
	 * @param idMenu
	 * @param label
	 * @param description
	 * @param active
	 * @param main
	 * @param faultLabels
	 */
	public LanguageMenuBean(int id, int idMenu, String label, String description, boolean active, boolean main, boolean faultLabels) {
		this.id = id;
		this.idMenu = idMenu;
		this.label = label;
		this.description = description;
		this.active = active;
		this.main = main;
		this.faultLabels = faultLabels;
	}




	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}




	/**
	 * @param main the main to set
	 */
	public void setMain(boolean main) {
		this.main = main;
	}



	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @return the main
	 */
	public boolean isMain() {
		return main;
	}
	/**
	 * @return the faultLabels
	 */
	public boolean isFaultLabels() {
		return faultLabels;
	};

	
	/**
	 * @return the customLabels
	 */
	public List<LanguageCustomLabel> getCustomLabels() {
		return customLabels;
	}



	/**
	 * @param customLabels the customLabels to set
	 */
	public void setCustomLabels(List<LanguageCustomLabel> customLabels) {
		this.customLabels = customLabels;
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
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}



	/**
	 * Get Warning
	 * @return
	 */
	public Warning getWarning() {
		if (this.active && this.faultLabels) 
			return Warning.FAULTLABELS;
		return null;
	}
	
}
