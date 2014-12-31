/**
 * LanguageDefaultLabel.java
 * 25/ott/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.language;

public class LanguageDefaultLabel {

	/* id */
	private int id = 0;
	/* label */
	private String label = "";
	/* default value */
	private String value = "";
	
	
	/**
	 * 
	 */
	public LanguageDefaultLabel() {	}

	/**
	 * @param id
	 * @param label
	 * @param value
	 */
	public LanguageDefaultLabel(int id, String label, String value) {
		this.id = id;
		this.label = label;
		this.value = value;
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
	
	
}
