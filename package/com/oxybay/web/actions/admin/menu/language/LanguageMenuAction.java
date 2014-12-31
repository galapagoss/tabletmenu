/**
 * LanguageMenuAction.java
 * 25/ott/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.language;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.common.DomainFilter;
import com.oxybay.web.beans.menu.language.LanguageMenuBean;
import com.oxybay.web.business.menu.LanguageMenuBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class LanguageMenuAction extends AdminAction {

	private static final long serialVersionUID = 2976327074798793754L;

	/* domain filter bean paginator */
	private DomainFilter page = new DomainFilter();
	/* bean */
	private LanguageMenuBean bean = new LanguageMenuBean();
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		page.setDomainValue(userAccount.getDomain());
		
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		if (dispatch.equals(AdminActionKeys.ACTION_EDIT))
			return edit();
		
		return list();
	}

	
	/**
	 * List
	 * @return
	 */
	private String list() {
		LanguageMenuBsn langBsn = new LanguageMenuBsn(bsnData);
		langBsn.list(page);
		return SUCCESS;
	}
	
	private String edit() {
		LanguageMenuBsn langBsn = new LanguageMenuBsn(bsnData);
		langBsn.load(bean, userAccount.getDomain().getId());
		return INPUT;
	}

	
	/**
	 * Save 
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		
		LanguageMenuBsn langBsn = new LanguageMenuBsn(bsnData);
		if (langBsn.update(bean, userAccount.getDomain().getId()))
			addActionMessage(getText("table.form.messages.update.ok"));
		else
			addActionError(getText("table.form.messages.update.error"));
		
		return INPUT;
	}
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "settings.languages";
	}

	/**
	 * @return the page
	 */
	public DomainFilter getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(DomainFilter page) {
		this.page = page;
	}


	/**
	 * @return the bean
	 */
	public LanguageMenuBean getBean() {
		return bean;
	}


	/**
	 * @param bean the bean to set
	 */
	public void setBean(LanguageMenuBean bean) {
		this.bean = bean;
	}

	
}
