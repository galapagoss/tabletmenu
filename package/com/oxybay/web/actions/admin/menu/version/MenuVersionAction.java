/**
 * MenuVersionAction.java
 * 26/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.version;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.version.MenuVersionBean;
import com.oxybay.web.beans.menu.version.MenuVersionFilterBean;
import com.oxybay.web.business.menu.MenuVersionBsn;
import com.oxybay.web.business.system.table.TableBsn;

public class MenuVersionAction extends AdminAction {

	private static final long serialVersionUID = -8141344206946069816L;
	
	private static final Class<MenuVersionBean> BEANCLASS	= MenuVersionBean.class;
	
	/* menu version filter */
	private MenuVersionFilterBean page = new MenuVersionFilterBean();

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		mainTable = new TableBsn(bsnData, BEANCLASS);
		if (dispatch.equals("check"))
			return check();
		return list();
	}


	/**
	 * List
	 * @return
	 */
	private String list() throws Exception{
		page.setDomainValue(userAccount.getDomain());
		page.setMenuValue(bsnData, userAccount.getDomain());
		page.getDeployEnd().setSkip(true);
		mainTable.list(page);
		return SUCCESS;
	}
	
	
	/**
	 * Check updates
	 * @return
	 */
	private String check() throws Exception {
		MenuVersionFilterBean filterLast = new MenuVersionFilterBean();
		filterLast.setItemsForPage(1);
		filterLast.setPage(1);
		filterLast.setDomainValue(userAccount.getDomain());
		filterLast.setMenuValue(bsnData, userAccount.getDomain());
		filterLast.getDeployEnd().setValue(false);
		mainTable.list(filterLast);
		MenuVersionBean lastVersion = new MenuVersionBean();
		if (!filterLast.getItems().isEmpty()) 
			lastVersion = (MenuVersionBean)filterLast.getItems().get(0);
		int idMenu = 0;
		try { idMenu = Integer.parseInt(filterLast.getIdMenu().getSearch());} catch(Exception e) {}
		
		MenuVersionBsn menuBsn = new MenuVersionBsn(bsnData);
		request.setAttribute("updates", menuBsn.checkUpdates(userAccount.getDomain().getId(),idMenu,lastVersion.getCreationDate()));
		return "check";
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "menu.versions";
	}


	/**
	 * @return the page
	 */
	public MenuVersionFilterBean getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(MenuVersionFilterBean page) {
		this.page = page;
	}


	

}
