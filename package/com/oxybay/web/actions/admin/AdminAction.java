package com.oxybay.web.actions.admin;


import com.oxybay.web.actions.BaseAction;
import com.oxybay.web.actions.admin.profile.ChooseDomainAction;
import com.oxybay.web.beans.menu.MenuBean;
import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.interceptors.InputPrepareAware;
import com.oxybay.web.resources.keys.AdminActionKeys;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 */
public abstract class AdminAction extends BaseAction implements InputPrepareAware {

	private static final long serialVersionUID = 4395668879399977101L;
	// General Results
	protected static final String		GENERAL_CHOOSEDOMAIN			= "general.chooseDomain";
	
	/* main business table */
	protected TableBsn mainTable = null;
	/* selected menu */
	protected String selectedMenu = "home";
	
	/**
	 * Check Filter in session
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected <T extends PaginatorBean> T sessionFilter(T filter) {
		String sessionVar = this.getClass().getCanonicalName()+"@"+filter.getClass().getSimpleName();
		if (!filter.isSubmitted() && session.getAttribute(sessionVar)!=null)
			return (T)session.getAttribute(sessionVar);
		
		session.setAttribute(sessionVar, filter);
		return filter;
	}
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.BaseAction#executeAction()
	 */
	@Override
	public String executeAction() throws Exception {
		
		if (userAccount.getDomain()==null && userAccount.isSysAdmin() && !this.getClass().equals(ChooseDomainAction.class))
			return GENERAL_CHOOSEDOMAIN;
		return executeAdminAction();
	}

	/**
	 * Load Active Menu
	 * @return
	 * @throws Exception
	 */
	protected MenuBean getActiveMenu() throws Exception {
		TableBsn menu2Bsn = new TableBsn(bsnData, MenuBean.class);
		MenuBean menu = new MenuBean(); 
		menu2Bsn.loadBean(menu, "active=1");
		return menu;
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract String executeAdminAction() throws Exception; 

	
	
	/**
	 * @return the selectedMenu
	 */
	public abstract String getSelectedMenu();


	/* (non-Javadoc)
	 * @see com.oxybay.web.resources.interceptors.inputprepare.InputPrepareAware#checkInputPrepare()
	 */
	@Override
	public boolean checkInputPrepare() {
		return (dispatch.equals(AdminActionKeys.ACTION_CREATE)||dispatch.equals(AdminActionKeys.ACTION_EDIT)||dispatch.equals(AdminActionKeys.ACTION_SAVE));
	}


	/* (non-Javadoc)
	 * @see com.oxybay.web.resources.interceptors.inputprepare.InputPrepareAware#inputPrepare()
	 */
	@Override
	public void inputPrepare() throws Exception{}


	@Override
	public boolean isUserRequired() {
		return true;
	}
	
	@Override
	public boolean canAccess() {
		return userAccount != null && userAccount.getId() > 0;
	}

	/**
	 * Standard Methods
	 */
	
	
	/**
	 * Item list
	 * @return
	 * @throws Exception
	 */
	protected String stdList(PaginatorBean page) throws Exception {
		mainTable.list(page);
		return SUCCESS;
	}


	/**
	 * Save Bean
	 * @return
	 * @throws Exception
	 */
	protected String stdInsert(Object bean) throws Exception {
		if (mainTable.insertBean(bean)>0)
			addActionMessage(getText("table.form.messages.insert.ok"));
		else
			addActionError(getText("table.form.messages.insert.error"));
		return INPUT;
	}
	
	
	/**
	 * Save Bean
	 * @return
	 * @throws Exception
	 */
	protected String stdUpdate(Object bean) throws Exception {
		if (mainTable.updateBean(bean)>0)
			addActionMessage(getText("table.form.messages.update.ok"));
		else
			addActionError(getText("table.form.messages.update.error"));
		
		return INPUT;
	}
	
	/**
	 * Load
	 * @return
	 * @throws Exception
	 */
	protected String stdEdit(Object bean) throws Exception {
		if (!mainTable.loadBean(bean))
			addActionError(getText("common.messages.error.notFound"));
		return INPUT;
	}
	
	
	/**
	 * Multiple deleting
	 * @return
	 * @throws Exception
	 */
	protected void stdDeleteList(PaginatorBean page) throws Exception {
		if (!page.getIdList().equals("")) {
			String[] listID = page.getIdList().split(",");
			int res = mainTable.deleteBean(mainTable.getBeanList(listID));
			if (res >= listID.length)
				addActionMessage(getText("table.form.messages.deletelist.ok"));
			else if (res>0)
				addActionError(getText("table.form.messages.deletelist.warning"));
			else
				addActionError(getText("table.form.messages.deletelist.error"));
		} else
			addActionError(getText("table.form.messages.deletelist.error.noselection"));
	}

	
	/**
	 * Delete single item
	 * @return
	 * @throws Exception
	 */
	protected void stdDelete(Object bean) throws Exception {
		if (mainTable.deleteBean(bean)>0)
			addActionMessage(getText("table.form.messages.delete.ok"));
		else
			addActionError(getText("table.form.messages.delete.error"));
	}
	
	
	
}