/**
 * MenuVersionDiffBean.java
 * 26/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.version;

public class MenuVersionDiffBean {

	/* attachments updated*/
	private int attachUpdates = 0;
	/* categories updated*/
	private int catUpdates = 0;
	/* categories translation updated*/
	private int catTransUpdates = 0;
	/* products updated*/
	private int prodUpdates = 0;
	/* products translation updated*/
	private int prodTransUpdates = 0;
	/* menus updates */
	private int menuUpdates = 0;
	/* lang updates */
	private int langUpdates = 0;
	
	/**
	 * Constructor 
	 */
	public MenuVersionDiffBean() {
	}

	/**
	 * @return the attachUpdates
	 */
	public int getAttachUpdates() {
		return attachUpdates;
	}

	/**
	 * @param attachUpdates the attachUpdates to set
	 */
	public void setAttachUpdates(int attachUpdates) {
		this.attachUpdates = attachUpdates;
	}

	/**
	 * @return the catUpdates
	 */
	public int getCatUpdates() {
		return catUpdates;
	}

	/**
	 * @param catUpdates the catUpdates to set
	 */
	public void setCatUpdates(int catUpdates) {
		this.catUpdates = catUpdates;
	}

	/**
	 * @return the catTransUpdates
	 */
	public int getCatTransUpdates() {
		return catTransUpdates;
	}

	/**
	 * @param catTransUpdates the catTransUpdates to set
	 */
	public void setCatTransUpdates(int catTransUpdates) {
		this.catTransUpdates = catTransUpdates;
	}

	/**
	 * @return the prodUpdates
	 */
	public int getProdUpdates() {
		return prodUpdates;
	}

	/**
	 * @param prodUpdates the prodUpdates to set
	 */
	public void setProdUpdates(int prodUpdates) {
		this.prodUpdates = prodUpdates;
	}

	/**
	 * @return the prodTransUpdates
	 */
	public int getProdTransUpdates() {
		return prodTransUpdates;
	}

	/**
	 * @param prodTransUpdates the prodTransUpdates to set
	 */
	public void setProdTransUpdates(int prodTransUpdates) {
		this.prodTransUpdates = prodTransUpdates;
	}

	/**
	 * @return the menuUpdates
	 */
	public int getMenuUpdates() {
		return menuUpdates;
	}

	/**
	 * @param menuUpdates the menuUpdates to set
	 */
	public void setMenuUpdates(int menuUpdates) {
		this.menuUpdates = menuUpdates;
	}
	
	
	
	/**
	 * @return the langUpdates
	 */
	public int getLangUpdates() {
		return langUpdates;
	}

	/**
	 * @param langUpdates the langUpdates to set
	 */
	public void setLangUpdates(int langUpdates) {
		this.langUpdates = langUpdates;
	}

	/**
	 * Check if there's updates to deploy
	 * @return
	 */
	public boolean isToDeploy() {
		return attachUpdates>0 || catUpdates>0 || catTransUpdates>0 || prodUpdates>0 || prodTransUpdates>0 || menuUpdates>0;
	}
	
}
