/**
 * ProductLangAction.java
 * 22/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.product;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.product.ProductLangBean;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class ProductLangAction extends AdminAction {

	private static final long serialVersionUID = 462278592593024071L;

	private static final Class<ProductLangBean> BEANCLASS	= ProductLangBean.class;
	
	/* bean */
	private ProductLangBean bean = new ProductLangBean();

	
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
		mainTable.loadBean(bean, "id_product="+bean.getIdProduct()+" AND lang="+val);
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
		return "menu.products";
	}


	/**
	 * @return the bean
	 */
	public ProductLangBean getBean() {
		return bean;
	}


	/**
	 * @param bean the bean to set
	 */
	public void setBean(ProductLangBean bean) {
		this.bean = bean;
	}

	
}
