package com.oxybay.web.beans.menu.tag;

import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.beans.system.table.filters.TableFilterString;

public class TagBeanFilter extends PaginatorBean {
	
	/* search */
	private TableFilterString label = new TableFilterString(TableFilterString.FORMAT_PERC_LIKE_PERC);

	/**
	 * Constructor
	 */
	public TagBeanFilter() {
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
