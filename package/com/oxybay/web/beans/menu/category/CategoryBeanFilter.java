/**
 * CategoryBeanFilter.java
 * 19/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.category;

import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.beans.system.table.filters.TableFilterString;

public class CategoryBeanFilter extends PaginatorBean {
	
	/* search */
	private TableFilterString label = new TableFilterString(TableFilterString.FORMAT_PERC_LIKE_PERC);

	/**
	 * Constructor
	 */
	public CategoryBeanFilter() {
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

	
	

}
