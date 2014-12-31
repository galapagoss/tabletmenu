/**
 * MenuTextAction.java
 * 28/ott/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.general;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.MenuTextBean;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class MenuTextAction extends AdminAction {

	private static final long serialVersionUID = -2165921625080889341L;
	
	private static final Class<MenuTextBean> 			BEANCLASS						= MenuTextBean.class;
	
	/* bean */
	private MenuTextBean bean = new MenuTextBean();

	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		
		if (dispatch.equals(AdminActionKeys.ACTION_DELETE)) {
			stdDelete(bean);
			return "close";
		}
		
		return edit();
	}
	
	/**
	 * Edit
	 * @return
	 */
	private String edit() throws Exception {
		String val = "'"+bean.getLang().replace("'", "")+"'";
		mainTable.loadBean(bean, "id_menu="+bean.getIdMenu()+" AND lang="+val);
		return INPUT;
	}
	
	/**
	 * Save
	 * @return
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		return (bean.getId()>0) ? stdUpdate(bean) : stdInsert(bean);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "menu.manage";
	}

	/**
	 * @return the bean
	 */
	public MenuTextBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(MenuTextBean bean) {
		this.bean = bean;
	}

}
