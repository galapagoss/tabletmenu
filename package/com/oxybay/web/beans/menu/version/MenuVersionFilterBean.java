/**
 * MenuVersionFilterBean.java
 * 26/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.version;

import com.oxybay.web.beans.menu.MenuBean;
import com.oxybay.web.beans.menu.common.DomainFilter;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.beans.system.table.filters.TableFilterNull;
import com.oxybay.web.beans.system.table.filters.TableFilterString;
import com.oxybay.web.business.system.table.TableBsn;

public class MenuVersionFilterBean extends DomainFilter {

	
	/* id menu */
	private TableFilterString idMenu = new TableFilterString();
	/* deploy status */
	private TableFilterNull deployEnd = new TableFilterNull();
	
	/**
	 * Constructor
	 */
	public MenuVersionFilterBean() {	}


	/**
	 * @return the idMenu
	 */
	public TableFilterString getIdMenu() {
		return idMenu;
	}


	/**
	 * @param menu the menu to set
	 */
	public void setMenuValue(BsnDataInfo bsnData, DomainBean domain) {
		MenuBean menuBean = new MenuBean();
		try {
			TableBsn tableBsn = new TableBsn(bsnData, MenuBean.class);
			tableBsn.loadBean(menuBean, "active=1 AND domain="+domain.getId());
		} catch (Exception e) {}
		this.idMenu.setSearch(""+menuBean.getId());
	}


	/**
	 * @param idMenu the idMenu to set
	 */
	public void setIdMenu(TableFilterString idMenu) {
		this.idMenu = idMenu;
	}


	/**
	 * @return the deployEnd
	 */
	public TableFilterNull getDeployEnd() {
		return deployEnd;
	}


	/**
	 * @param deployEnd the deployEnd to set
	 */
	public void setDeployEnd(TableFilterNull deployEnd) {
		this.deployEnd = deployEnd;
	}

	
	
}
