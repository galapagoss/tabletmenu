/**
 * ProductAction.java
 * 22/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.menu.product;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.discount.DiscountBean.DiscountType;
import com.oxybay.web.beans.menu.product.ProductBean;
import com.oxybay.web.beans.menu.product.ProductBeanFilter;
import com.oxybay.web.business.menu.AttachBsn;
import com.oxybay.web.business.menu.CategoryBsn;
import com.oxybay.web.business.menu.DiscountBsn;
import com.oxybay.web.business.menu.MenuBsn;
import com.oxybay.web.business.menu.ProductBsn;
import com.oxybay.web.business.menu.TagBsn;
import com.oxybay.web.business.menu.TagBsn.TagType;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class ProductAction extends AdminAction {

	private static final long serialVersionUID = 8854672409470441873L;

	private static final Class<ProductBean> BEANCLASS	= ProductBean.class;
	
	/* bean filter */
	private ProductBeanFilter page = new ProductBeanFilter();
	/* bean */
	private ProductBean bean = new ProductBean();
	

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		page = sessionFilter(page);
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals("discounts"))
			return allDiscounts();
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
		ProductBsn prodBsn = new ProductBsn(bsnData);
		prodBsn.list(page, userAccount.getDomain(),getText("common.untitled"));
		CategoryBsn catBsn = new CategoryBsn(bsnData);
		request.setAttribute("categories", catBsn.listSimple(userAccount.getDomain(), getText("common.untitled")));
		MenuBsn menuBsn = new MenuBsn(bsnData);
		request.setAttribute("languages", menuBsn.getMenuLanguages(userAccount.getDomain()));
		request.setAttribute("menu", getActiveMenu());
		return SUCCESS;
	}
	
	
	/**
	 * View All Discoutns
	 * @return
	 */
	private String allDiscounts() throws Exception {
		DiscountBsn discoBsn = new DiscountBsn(bsnData);
		request.setAttribute("discounts", discoBsn.getAllInheritDiscounts(userAccount.getDomain().getId(), bean.getId(), getText("common.untitled"), false));
		request.setAttribute("menu", getActiveMenu());
		return "discounts";
	}
	
	
	
	/**
	 * Load
	 * @return
	 * @throws Exception
	 */
	private String edit() throws Exception {
		if (mainTable.loadBean(bean)) {
			ProductBsn prodBsn = new ProductBsn(bsnData);
			prodBsn.loadCategories(bean, userAccount.getDomain(), getText("common.untitled"));
			TagBsn tagBsn = new TagBsn(bsnData);
			bean.setTags(tagBsn.getTags(bean.getId(), userAccount.getDomain().getId(), TagType.PRODUCT));
			DiscountBsn discountBsn = new DiscountBsn(bsnData);
			bean.getDiscounts().setPolicies(discountBsn.getDiscounts(bean.getId(), DiscountType.PRODUCT, false));
		} else
			addActionError(getText("common.messages.error.notFound"));
		return INPUT;
	}
	
	/**
	 * Save
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		ProductBsn prodBsn = new ProductBsn(bsnData);
		TagBsn tagBsn = new TagBsn(bsnData);
		DiscountBsn discountBsn = new DiscountBsn(bsnData);
		AttachBsn attachBsn = new AttachBsn(userAccount.getDomain());
		attachBsn.prepareFile(this, bean, "img");
		if (bean.getId()>0) {
			if (mainTable.updateBean(bean, "active,price,img")>0)
				if (prodBsn.setCategories(bean) && 
						tagBsn. updateTagsRef(bean.getId(), userAccount.getDomain().getId(), TagType.PRODUCT, bean.getTags(), bean.getNewTags()) &&
						discountBsn.updateDiscounts(bean.getId(), bean.getDiscounts(), DiscountType.PRODUCT))
					addActionMessage(getText("table.form.messages.update.ok"));
				else
					addActionError(getText("table.form.messages.update.ok.partial"));
			else
				addActionError(getText("table.form.messages.update.error"));
		} else {
			if (prodBsn.insert(bean, userAccount.getDomain())) {
				if (prodBsn.setCategories(bean) && 
						tagBsn.updateTagsRef(bean.getId(), userAccount.getDomain().getId(), TagType.PRODUCT, bean.getTags(), bean.getNewTags()) &&
						discountBsn.updateDiscounts(bean.getId(), bean.getDiscounts(), DiscountType.PRODUCT)) {
					addActionMessage(getText("table.form.messages.insert.ok"));
					bean.getMainLang().setLabel(getActiveMenu().getDefaultLanguage());
					return "language";
				} else
					addActionError(getText("table.form.messages.update.ok.partial"));
			} else
				addActionError(getText("table.form.messages.insert.error"));
		}
		
		return INPUT;
	}

	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#inputPrepare()
	 */
	@Override
	public void inputPrepare() throws Exception {
		super.inputPrepare();
		request.setAttribute("menu", getActiveMenu());
		CategoryBsn catBsn = new CategoryBsn(bsnData);
		request.setAttribute("categories", catBsn.listSimple(userAccount.getDomain(), getText("common.untitled")));
		TagBsn tagBsn = new TagBsn(bsnData);
		request.setAttribute("tags", tagBsn.getAllTags(userAccount.getDomain().getId()));
	}


	/**
	 * Order Save
	 * @return
	 * @throws Exception
	 */
	private String saveOrder() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		ProductBsn prodBsn = new ProductBsn(bsnData);
		if (prodBsn.updateOrder(page.getIdList(), page.getOrderList()))
			addActionMessage(getText("table.form.messages.saveorder.ok"));
		else
			addActionError(getText("table.form.messages.saveorder.error"));
		return list();
	}
	
	
	
	/**
	 * @return the page
	 */
	public ProductBeanFilter getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(ProductBeanFilter page) {
		this.page = page;
	}


	/**
	 * @return the bean
	 */
	public ProductBean getBean() {
		return bean;
	}


	/**
	 * @param bean the bean to set
	 */
	public void setBean(ProductBean bean) {
		this.bean = bean;
	}


	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "menu.products";
	}

}
