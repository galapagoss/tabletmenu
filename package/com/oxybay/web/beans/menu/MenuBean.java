/**
 * MenuBean.java
 * 13/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.oxybay.web.beans.menu.common.AttachBean;
import com.oxybay.web.beans.menu.common.CurrencyBean;
import com.oxybay.web.beans.menu.version.MenuVersionBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.business.system.common.ImageResize;
import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableRef;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="menu",order=0)
public class MenuBean {
	
	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* domain */
	private DomainBean domain = new DomainBean();
	/* title */
	private String title = "";
	/* subtitle */
	private String subtitle = "";
	/* body */
	private String body = "";
	/* currency */
	@TableRef("")
	@TableField("id_currency")
	private CurrencyBean currency = new CurrencyBean();
	/* active */
	private boolean active = false;
	/* logo */
	@TableRef("")
	@ImageResize(w=150,h=150,crop=true)
	private AttachBean logo = null;
	/* last version */
	@TableField("last_version")
	private MenuVersionBean lastVersion = null;
	/* default language */
	@TableField("main_lang")
	private String defaultLanguage = "";
	/* available text languages */
	@TableType(TableType.SKIP)
	private Set<String> textLangs = new HashSet<String>();
	
	
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
	 * Empty Constructor
	 */
	public MenuBean() {	}

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
	 * @return the domain
	 */
	public DomainBean getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(DomainBean domain) {
		this.domain = domain;
	}

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
	 * @return the currency
	 */
	public CurrencyBean getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(CurrencyBean currency) {
		this.currency = currency;
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
	 * @return the lastVersion
	 */
	public MenuVersionBean getLastVersion() {
		return lastVersion;
	}

	/**
	 * @param lastVersion the lastVersion to set
	 */
	public void setLastVersion(MenuVersionBean lastVersion) {
		this.lastVersion = lastVersion;
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
	 * @return the logo
	 */
	public AttachBean getLogo() {
		return logo;
	}

	/**
	 * @param logo the logo to set
	 */
	public void setLogo(AttachBean logo) {
		this.logo = logo;
	}


	/**
	 * @return the defaultLanguage
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * @param defaultLanguage the defaultLanguage to set
	 */
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	/**
	 * @return the textLangs
	 */
	public Set<String> getTextLangs() {
		return textLangs;
	}

	/**
	 * @param textLangs the textLangs to set
	 */
	public void setTextLangs(Set<String> textLangs) {
		this.textLangs = textLangs;
	}
	
}
