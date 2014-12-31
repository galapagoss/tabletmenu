/**
 * UserAction.java
 * 22/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.profile.user;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.profile.UserBean;
import com.oxybay.web.beans.profile.UserBeanFilter;
import com.oxybay.web.beans.profile.common.LanguageBean;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class UserAction extends AdminAction {

	private static final long 								serialVersionUID 		= 2904732957938499079L;
	private static final Class<UserBean> 			BEANCLASS						= UserBean.class;
	
	/* user filter */
	private UserBeanFilter page = new UserBeanFilter();
	/* user */
	private UserBean bean = new UserBean();
	
	

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.BaseAction#executeAction()
	 */
	@Override
	public String executeAdminAction() throws Exception {
		page = sessionFilter(page);
		page.setDomainValue(userAccount.getDomain());
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		if (dispatch.equals(AdminActionKeys.ACTION_CREATE))
			return INPUT;
		if (dispatch.equals(AdminActionKeys.ACTION_EDIT))
			return stdEdit(bean);
		if (dispatch.equals(AdminActionKeys.ACTION_DELETE)) {
			if (!tokenPassed)	return GENERAL_NOTOKEN;
			stdDelete(bean);
		}
		if (dispatch.equals(AdminActionKeys.ACTION_DELETELIST)) {
			if (!tokenPassed)	return GENERAL_NOTOKEN;
			stdDeleteList(page);
		}
		
		return stdList(page);
	}

	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "settings.users";
	}


	/**
	 * Save Bean
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		if (bean.getId()>0) {
			if (mainTable.updateBean(bean, "nickname,firstname,lastname,active,timezone,language,type")>0)
				addActionMessage(getText("table.form.messages.update.ok"));
			else
				addActionError(getText("table.form.messages.update.error"));
			return INPUT;
		} else 
			return stdInsert(bean);
	}
	

	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#inputPrepare()
	 */
	@Override
	public void inputPrepare() throws Exception{
		TableBsn tableBsn2 = new TableBsn(bsnData, LanguageBean.class);
		request.setAttribute("langs", tableBsn2.listSimple(1, "active=1 AND application=1"));
	}


	/**
	 * @return the page
	 */
	public UserBeanFilter getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(UserBeanFilter page) {
		this.page = page;
	}


	/**
	 * @return the bean
	 */
	public UserBean getBean() {
		return bean;
	}


	/**
	 * @param bean the bean to set
	 */
	public void setBean(UserBean bean) {
		this.bean = bean;
	}

	
}
