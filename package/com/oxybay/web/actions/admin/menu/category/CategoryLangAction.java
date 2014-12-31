/**
 * CategoryLangAction.java
 * 21/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.category;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.category.CategoryLangBean;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class CategoryLangAction extends AdminAction {

	private static final long serialVersionUID = -711167021042496901L;

	private static final Class<CategoryLangBean> 			BEANCLASS						= CategoryLangBean.class;
	
	/* bean */
	private CategoryLangBean bean = new CategoryLangBean();

	
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
		String val = "'"+bean.getLabel().replace("'", "")+"'";
		mainTable.loadBean(bean, "id_category="+bean.getIdCategory()+" AND lang="+val);
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
		return "menu.categories";
	}


	/**
	 * @return the bean
	 */
	public CategoryLangBean getBean() {
		return bean;
	}


	/**
	 * @param bean the bean to set
	 */
	public void setBean(CategoryLangBean bean) {
		this.bean = bean;
	}


}
