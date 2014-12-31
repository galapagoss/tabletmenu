package com.oxybay.web.actions.admin.menu.daily;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.daily.DailyMenuBean;
import com.oxybay.web.beans.menu.daily.DailyMenuFilter;
import com.oxybay.web.business.menu.CategoryBsn;
import com.oxybay.web.business.menu.ProductBsn;
import com.oxybay.web.business.menu.TagBsn;
import com.oxybay.web.business.menu.daily.DailyMenuBsn;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class DailyMenuAction extends AdminAction {

	private static final long serialVersionUID = 8687563247495102095L;
	
	private static final Class<DailyMenuBean> BEANCLASS	= DailyMenuBean.class;
	
	/* bean filter */
	private DailyMenuFilter page = new DailyMenuFilter();
	/* bean */
	private DailyMenuBean bean = new DailyMenuBean();

	@Override
	protected String executeAdminAction() throws Exception {
		page = sessionFilter(page);
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals(AdminActionKeys.ACTION_EDIT))
			return edit();
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		
		return list();
	}
	
	private String list() throws Exception {
		DailyMenuBsn dailyBsn = new DailyMenuBsn(bsnData);
		dailyBsn.list(page, userAccount.getDomain());
		request.setAttribute("menu", getActiveMenu());
		
		return SUCCESS;
	}
	
	public void inputPrepare() throws Exception {
		super.inputPrepare();
		ProductBsn prodBsn = new ProductBsn(bsnData);
		request.setAttribute("products", prodBsn.listSimple(userAccount.getDomain(), getText("common.untitled")));
		CategoryBsn catBsn = new CategoryBsn(bsnData);
		request.setAttribute("categories", catBsn.listSimple(userAccount.getDomain(), getText("common.untitled")));
		TagBsn tagBsn = new TagBsn(bsnData);
		request.setAttribute("tags", tagBsn.getAllTags(userAccount.getDomain().getId()));
		
		DailyMenuBsn dailyBsn = new DailyMenuBsn(bsnData);
		bean.setItems(dailyBsn.getMenuItems(userAccount.getDomain(), bean.getId(), getText("common.untitled")));
		request.setAttribute("menu", getActiveMenu());
	}
	
	/**
	 * Edit
	 * @return
	 * @throws Exception
	 */
	private String edit() throws Exception {
		
		return stdEdit(bean);
	}
	
	/**
	 * Save
	 * @return
	 * @throws Exception
	 */
	private String save() throws Exception {
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		
		if (bean.getId()>0) {
			DailyMenuBsn dailyBsn = new DailyMenuBsn(bsnData);
			if (mainTable.updateBean(bean, "active,price")>0 && dailyBsn.updateMenuItems(bean.getId(), bean.getItems()))
				addActionMessage(getText("table.form.messages.update.ok"));
			else
				addActionError(getText("table.form.messages.update.error"));
		} else {
			addActionError(getText("table.form.messages.insert.error"));
		}
		
		return INPUT;
	}

	@Override
	public String getSelectedMenu() {
		return "menu.daily";
	}

	/**
	 * @return the page
	 */
	public DailyMenuFilter getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(DailyMenuFilter page) {
		this.page = page;
	}

	/**
	 * @return the bean
	 */
	public DailyMenuBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(DailyMenuBean bean) {
		this.bean = bean;
	}

}