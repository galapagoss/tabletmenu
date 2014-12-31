/**
 * DeviceBindBean.java
 * 28/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.device.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeviceBindBean {

	/* md5 key */
	private String key = "";
	/* id domain */
	private int domain = 0;
	/* menu */
	private int menu = 0;
	
	
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the domain
	 */
	public int getDomain() {
		return domain;
	}
	/**
	 * @param domain the domain to set
	 */
	public void setDomain(int domain) {
		this.domain = domain;
	}
	/**
	 * @return the menu
	 */
	public int getMenu() {
		return menu;
	}
	/**
	 * @param menu the menu to set
	 */
	public void setMenu(int menu) {
		this.menu = menu;
	}
	
	/**
	 * Key is invalid
	 */
	public boolean keyInvalid() {
		return key==null || key.trim().equals("");
	}
}
