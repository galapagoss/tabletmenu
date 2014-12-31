package com.oxybay.web.beans.menu.tag;

import com.oxybay.web.beans.menu.common.TagBean;

public class TagStatBean extends TagBean {
	
	private int categoryCount;
	
	private int productCount;
	
	private int dailyCount;
	
	private int totalCount;

	public TagStatBean() {}

	public TagStatBean(int id, String label, int categoryCount, int productCount, int dailyCount, int totalCount) {
		super(id, label);
		this.categoryCount = categoryCount;
		this.productCount  = productCount;
		this.dailyCount    = dailyCount;
		this.totalCount    = totalCount;
	}

	/**
	 * @return the categoryCount
	 */
	public int getCategoryCount() {
		return categoryCount;
	}

	/**
	 * @param categoryCount the categoryCount to set
	 */
	public void setCategoryCount(int categoryCount) {
		this.categoryCount = categoryCount;
	}

	/**
	 * @return the productCount
	 */
	public int getProductCount() {
		return productCount;
	}

	/**
	 * @param productCount the productCount to set
	 */
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}

	/**
	 * @return the dailyCount
	 */
	public int getDailyCount() {
		return dailyCount;
	}

	/**
	 * @param dailyCount the dailyCount to set
	 */
	public void setDailyCount(int dailyCount) {
		this.dailyCount = dailyCount;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
