package com.oxybay.web.actions.admin.menu.tag;

import java.util.ArrayList;
import java.util.Arrays;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.menu.common.TagBean;
import com.oxybay.web.beans.menu.discount.DiscountBean.DiscountType;
import com.oxybay.web.beans.menu.tag.TagBeanFilter;
import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.business.menu.DiscountBsn;
import com.oxybay.web.business.menu.TagBsn;
import com.oxybay.web.business.system.table.TableBsn;
import com.oxybay.web.resources.keys.AdminActionKeys;

public class TagStatAction extends AdminAction {

	private static final long serialVersionUID = -6812555018694950922L;

	private static final Class<TagBean> BEANCLASS	= TagBean.class;
	
	/* bean filter */
	private TagBeanFilter page = new TagBeanFilter();
	/* bean */
	private TagBean bean = new TagBean();

	@Override
	protected String executeAdminAction() throws Exception {
		page = sessionFilter(page);
		mainTable = new TableBsn(bsnData, BEANCLASS);
		
		if (dispatch.equals(AdminActionKeys.ACTION_EDIT))
			return edit();
		if (dispatch.equals(AdminActionKeys.ACTION_SAVE))
			return save();
		if (dispatch.equals(AdminActionKeys.ACTION_DELETE)) {
			if (!tokenPassed)	return GENERAL_NOTOKEN;
			stdDelete(bean);
		}
		if (dispatch.equals(AdminActionKeys.ACTION_DELETELIST)) {
			if (!tokenPassed)	return GENERAL_NOTOKEN;
			stdDeleteList(page);
		}
		if (dispatch.equals("merge")) {
			if (!tokenPassed)	return GENERAL_NOTOKEN;
			merge(page);
		}
		
		return list();
	}
	
	private String list() throws Exception {
		TagBsn tagBsn = new TagBsn(bsnData);
		tagBsn.list(page, userAccount.getDomain(),getText("common.untitled"));
		request.setAttribute("menu", getActiveMenu());
		
		return SUCCESS;
	}
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#inputPrepare()
	 */
	@Override
	public void inputPrepare() throws Exception {
		request.setAttribute("menu", getActiveMenu());
	}
	
	/**
	 * Edit
	 * @return
	 * @throws Exception
	 */
	private String edit() throws Exception {
		if (mainTable.loadBean(bean)) {
			DiscountBsn discountBsn = new DiscountBsn(bsnData);
			bean.getDiscounts().setPolicies(discountBsn.getDiscounts(bean.getId(), DiscountType.TAG, false));
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
		
		if (bean.getId()>0) {
			DiscountBsn discountBsn = new DiscountBsn(bsnData);
			if (mainTable.updateBean(bean, "label")>0 && discountBsn.updateDiscounts(bean.getId(), bean.getDiscounts(), DiscountType.TAG))
				addActionMessage(getText("table.form.messages.update.ok"));
			else
				addActionError(getText("table.form.messages.update.error"));
		} else {
			addActionError(getText("table.form.messages.insert.error"));
		}
		
		return INPUT;
	}
	
	private void merge(PaginatorBean page) throws Exception {
		if (!page.getIdList().equals("")) {
			boolean isMergingTag = false;
			int newTagId = 0;
			
			String[] listID = page.getIdList().split(",");
			TagBsn tagBsn = new TagBsn(bsnData);
			
			TagBean tag = tagBsn.getTag(request.getParameter("newTag"));
			ArrayList<String> tagToBeMerged = new ArrayList<String>();
			if (tag != null) {
				newTagId = tag.getId();
				for (String string : listID) {
					if (tag != null && tag.getId() == Integer.parseInt(string)) {
						// new tag name is a tag we are merging
						isMergingTag = true;
					} else {
						tagToBeMerged.add(string);
					}
				}
				
				if (!isMergingTag) {
					addActionError(getText("tag.form.messages.merge.tag.already.existing"));
					return;
				}
			} else {
				tagToBeMerged.addAll(Arrays.asList(listID));
			}
			
			if (tag == null || (tag != null && isMergingTag)) {
				String[] fixedListId = new String[tagToBeMerged.size()];
				fixedListId = tagToBeMerged.toArray(fixedListId);
				tagBsn.merge(fixedListId, request.getParameter("newTag"), newTagId, userAccount.getDomain().getId());
			}
		}
	}

	@Override
	public String getSelectedMenu() {
		return "menu.tag";
	}

	/**
	 * @return the page
	 */
	public TagBeanFilter getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(TagBeanFilter page) {
		this.page = page;
	}

	/**
	 * @return the bean
	 */
	public TagBean getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(TagBean bean) {
		this.bean = bean;
	}

}