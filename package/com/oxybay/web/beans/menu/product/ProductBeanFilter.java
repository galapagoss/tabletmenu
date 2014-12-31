/**
 * ProductBeanFilter.java
 * 22/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.product;

import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.beans.system.table.filters.TableFilterString;

public class ProductBeanFilter extends PaginatorBean {
	
	/* search */
	private TableFilterString label = new TableFilterString(TableFilterString.FORMAT_PERC_LIKE_PERC);
	/* category */
	private int idCategory = 0; 

	/**
	 * Constructor
	 */
	public ProductBeanFilter() {
		super();
	}

	/**
	 * @return the label
	 */
	public TableFilterString getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(TableFilterString label) {
		this.label = label;
	}

	/**
	 * @return the idCategory
	 */
	public int getIdCategory() {
		return idCategory;
	}

	/**
	 * @param idCategory the idCategory to set
	 */
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}


}
