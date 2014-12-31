/**
 * PaginatorBean.java
 * 22/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.common;

import java.util.List;

import com.oxybay.web.resources.keys.CustomKeys;

public class PaginatorBean {

	private static final int DEFAULT_PAGE						= 1;
	
	/* itemsForPage */
	private int itemsForPage = CustomKeys.TABLE_ROWSTOVIEW[0];
	/* page */
	private int page = DEFAULT_PAGE;
	/* items */
	private List<Object> items = null;
	/* total items count */
	private int total = 0;
	/* submit */
	private boolean submitted = false;
	
	/* flag previous page */
	private boolean existPrevPage = false;
	/* flag next page */
	private boolean existNextPage = false;
	/* number of pages */
	private int numPages = 1;
	/* id key list */
	private String idList = "";
	/* order key list */
	private String orderList = "";
	/* order id */
	private int order = 0;

	
	
	/**
	 * Empty Constructor
	 */
	public PaginatorBean() {	}
	
	
	/**
	 * Calculate Query Range
	 * @return
	 */
	public String calculateQueryRange() {
		return  ""+((this.page-1) * this.itemsForPage)+","+(this.itemsForPage+1);
	}
	
	
	/**
	 * @return the itemsForPage
	 */
	public int getItemsForPage() {
		return itemsForPage;
	}



	/**
	 * @param itemsForPage the itemsForPage to set
	 */
	public void setItemsForPage(int itemsForPage) {
		this.itemsForPage = itemsForPage;
	}



	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}



	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}



	/**
	 * @return the items
	 */
	public List<Object> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<Object> items) {
		this.items = items;
		this.existPrevPage = (this.page>1);
		if (this.items.size() > this.itemsForPage) {
			this.existNextPage = true;
			this.items.remove(this.itemsForPage);
		}
	}


	/**
	 * @return the existPrevPage
	 */
	public boolean isExistPrevPage() {
		return existPrevPage;
	}


	/**
	 * @return the existNextPage
	 */
	public boolean isExistNextPage() {
		return existNextPage;
	}


	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}


	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
		if (total>0)
			this.numPages = ((total-1)/itemsForPage)+1;
	}


	/**
	 * @return the numPages
	 */
	public int getNumPages() {
		return numPages;
	}


	/**
	 * @return the submitted
	 */
	public boolean isSubmitted() {
		return submitted;
	}


	/**
	 * @param submitted the submitted to set
	 */
	public void setSubmitted(boolean submitted) {
		this.submitted = submitted;
	}


	/**
	 * @return the idList
	 */
	public String getIdList() {
		return idList;
	}


	/**
	 * @param idList the idList to set
	 */
	public void setIdList(String idList) {
		this.idList = idList;
	}


	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}


	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}


	/**
	 * @return the orderList
	 */
	public String getOrderList() {
		return orderList;
	}


	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(String orderList) {
		this.orderList = orderList;
	}

	
}
