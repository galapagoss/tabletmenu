/**
 * CategoryAction.java
 * 19/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.category;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.category.CategoryBean;
import com.oxybay.web.beans.menu.category.CategoryBeanFilter;
import com.oxybay.web.beans.menu.discount.DiscountBean.DiscountType;
import com.oxybay.web.business.menu.AttachBsn;
import com.oxybay.web.business.menu.CategoryBsn;
import com.oxybay.web.business.menu.DiscountBsn;
import com.oxybay.web.business.menu.MenuBsn;
import com.oxybay.web.business.menu.TagBsn;
import com.oxybay.web.business.menu.TagBsn.TagType;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class CategoryAction extends AdminAction {

	private static final long serialVersionUID = 4691465382436354764L;
	
	private static final Class<CategoryBean> 			BEANCLASS						= CategoryBean.class;
	
	/* bean filter */
	private CategoryBeanFilter page = new CategoryBeanFilter();
	/* bean */
	private CategoryBean bean = new CategoryBean();
	

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		page = sessionFilter(page);
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals(AdminActionKeys.ACTION_SAVEORDER))
			return saveOrder();
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		if (dispatch.equals(AdminActionKeys.ACTION_CREATE))
			return INPUT;
		if (dispatch.equals(AdminActionKeys.ACTION_EDIT))
			return edit();
		if (dispatch.equals(AdminActionKeys.ACTION_DELETE)) {
			if (!tokenPassed)	return GENERAL_NOTOKEN;
			stdDelete(bean);
		}
		if (dispatch.equals(AdminActionKeys.ACTION_DELETELIST)) {
			if (!tokenPassed)	return GENERAL_NOTOKEN;
			stdDeleteList(page);
		}
		
		return list();
	}
	
	
	/**
	 * List
	 * @return
	 */
	private String list() throws Exception{
		CategoryBsn catBsn = new CategoryBsn(bsnData);
		catBsn.list(page, userAccount.getDomain(),getText("common.untitled"));
		MenuBsn menuBsn = new MenuBsn(bsnData);
		request.setAttribute("languages", menuBsn.getMenuLanguages(userAccount.getDomain()));
		return SUCCESS;
	}
	
	
	/**
	 * Edit
	 * @return
	 * @throws Exception
	 */
	private String edit() throws Exception {
		TagBsn tagBsn = new TagBsn(bsnData);
		bean.setTags(tagBsn.getTags(bean.getId(), userAccount.getDomain().getId(), TagType.CATEGORY));
		DiscountBsn discountBsn = new DiscountBsn(bsnData);
		bean.getDiscounts().setPolicies(discountBsn.getDiscounts(bean.getId(), DiscountType.CATEGORY, false));
		return stdEdit(bean);
	}
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#inputPrepare()
	 */
	@Override
	public void inputPrepare() throws Exception {
		TagBsn tagBsn = new TagBsn(bsnData);
		request.setAttribute("tags", tagBsn.getAllTags(userAccount.getDomain().getId()));
		request.setAttribute("menu", getActiveMenu());
	}


	/**
	 * Save
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		
		TagBsn tagBsn = new TagBsn(bsnData);
		DiscountBsn discountBsn = new DiscountBsn(bsnData);
		AttachBsn attachBsn = new AttachBsn(userAccount.getDomain());
		attachBsn.prepareFile(this, bean, "img");
		if (bean.getId()>0) {
			if (mainTable.updateBean(bean, "active,img")>0 && 
					tagBsn. updateTagsRef(bean.getId(), userAccount.getDomain().getId(), TagType.CATEGORY, bean.getTags(), bean.getNewTags()) && 
					discountBsn.updateDiscounts(bean.getId(), bean.getDiscounts(), DiscountType.CATEGORY))
				addActionMessage(getText("table.form.messages.update.ok"));
			else
				addActionError(getText("table.form.messages.update.error"));
		} else {
			CategoryBsn catBsn = new CategoryBsn(bsnData);
			if (catBsn.insert(bean, userAccount.getDomain()) && 
					tagBsn. updateTagsRef(bean.getId(), userAccount.getDomain().getId(), TagType.CATEGORY, bean.getTags(), bean.getNewTags()) && 
					discountBsn.updateDiscounts(bean.getId(), bean.getDiscounts(), DiscountType.CATEGORY)) {
				addActionMessage(getText("table.form.messages.insert.ok"));
				bean.getMainLang().setLabel(getActiveMenu().getDefaultLanguage());
				return "language";
			} else
				addActionError(getText("table.form.messages.insert.error"));
		}
		
		return INPUT;
	}

	/**
	 * Order Save
	 * @return
	 * @throws Exception
	 */
	private String saveOrder() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		
		CategoryBsn catBsn = new CategoryBsn(bsnData);
		if (catBsn.updateOrder(page.getIdList(), page.getOrderList()))
			addActionMessage(getText("table.form.messages.saveorder.ok"));
		else
			addActionError(getText("table.form.messages.saveorder.error"));
		return list();
	}
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		// TODO Auto-generated method stub
		return "menu.categories";
	}


	/**
	 * @return the page
	 */
	public CategoryBeanFilter getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(CategoryBeanFilter page) {
		this.page = page;
	}


	/**
	 * @return the bean
	 */
	public CategoryBean getBean() {
		return bean;
	}


	/**
	 * @param bean the bean to set
	 */
	public void setBean(CategoryBean bean) {
		this.bean = bean;
	}

	
}
