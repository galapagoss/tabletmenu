/**
 * ManagerAction.java
 * 13/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.general;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.MenuBean;
import com.oxybay.web.beans.menu.common.CurrencyBean;
import com.oxybay.web.business.menu.AttachBsn;
import com.oxybay.web.business.menu.MenuBsn;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;
import com.oxybay.web.resources.keys.CustomKeys;

public class MenuManagerAction extends AdminAction {

	private static final long serialVersionUID = 7750380386761848115L;
	
	private static final Class<MenuBean> BEANCLASS	= MenuBean.class; 
	
	/* bean */
	private MenuBean bean = new MenuBean();


	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		
		return load();
	}
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "menu.manage";
	}




	/**
	 * Load Current Menu
	 * @return
	 * @throws Exception
	 */
	private String load() throws Exception {
		
		if (!mainTable.loadBean(bean, "menu.active=1 AND domain="+userAccount.getDomain().getId())) {
			// Create Untitled Menu
			boolean res = false;
			bean.setTitle(getText("menu.manager.new.untitled"));
			bean.setCurrency(new CurrencyBean(CustomKeys.DEFAULT_CURRENCY_ID));
			bean.setDomain(userAccount.getDomain());
			bean.setActive(true);
			bean.setDefaultLanguage(CustomKeys.DEFAULT_LANGUAGE);
			if (!res)
				addActionError(getText("menu.messages.save.error.init"));
		} else {
			MenuBsn menuBsn = new MenuBsn(bsnData);
			menuBsn.loadTextLanguages(bean);
		}
		
		return INPUT;
	}
	
	/**
	 * Save Menu data
	 * @return
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		AttachBsn attachBsn = new AttachBsn(userAccount.getDomain());
		attachBsn.prepareFile(this, bean, "logo");
		if (mainTable.updateBean(bean, "title,subtitle,currency,body,logo,defaultLanguage")>0)
			addActionMessage(getText("table.form.messages.update.ok"));
		else
			addActionError(getText("table.form.messages.update.error"));
			
		return INPUT;
	}
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#checkInputPrepare()
	 */
	@Override
	public boolean checkInputPrepare() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#inputPrepare()
	 */
	@Override
	public void inputPrepare() throws Exception {
		super.inputPrepare();
		MenuBsn menuBsn = new MenuBsn(bsnData);
		request.setAttribute("languages", menuBsn.getMenuLanguages(userAccount.getDomain()));
		TableBsn curBsn = new TableBsn(bsnData, CurrencyBean.class);
		request.setAttribute("currencies", curBsn.listSimple(1, ""));
	}

	/**
	 * @return the bean
	 */
	public MenuBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(MenuBean bean) {
		this.bean = bean;
	}

}
