/**
 * TagBean.java
 * 11/set/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.common;

import com.oxybay.web.beans.menu.discount.DiscountContainerBean;
import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="tag",order=1)
public class TagBean {

	/* id */
	@TableType(TableType.KEY)
	private int id = 0;
	/* label */
	private String label = "";
	
	/* discounts */
	@TableType(TableType.SKIP)
	private DiscountContainerBean discounts = new DiscountContainerBean();
	
	
	/**
	 * Empty Constructor
	 */
	public TagBean() {}

	/**
	 * @param id
	 * @param label
	 */
	public TagBean(int id, String label) {
		this.id = id;
		this.label = label;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the discounts
	 */
	public DiscountContainerBean getDiscounts() {
		return discounts;
	}

	/**
	 * @param discounts the discounts to set
	 */
	public void setDiscounts(DiscountContainerBean discounts) {
		this.discounts = discounts;
	}
	
	
}
