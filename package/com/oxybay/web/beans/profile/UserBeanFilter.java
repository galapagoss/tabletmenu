/**
 * UserBeanFilter.java
 * 21/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.profile;

import com.oxybay.web.beans.menu.common.DomainFilter;
import com.oxybay.web.beans.system.table.filters.TableFilterRange;
import com.oxybay.web.beans.system.table.filters.TableFilterRangeDate;
import com.oxybay.web.beans.system.table.filters.TableFilterString;
import com.oxybay.web.business.system.table.annotations.filters.TableFilterMoreFields;
import com.oxybay.web.business.system.table.annotations.filters.TableFilterReference;

public class UserBeanFilter extends DomainFilter {

	/* search */
	@TableFilterMoreFields({"email","firstname","lastname"})
	private TableFilterString nickname = new TableFilterString(TableFilterString.FORMAT_PERC_LIKE_PERC);
	
	/* creation */
	private TableFilterRangeDate creationDate = new TableFilterRangeDate(TableFilterRange.RANGEOPERATOR_MINEQ_MAXEQ, TableFilterRangeDate.FORMAT_DATE);
	
	/* language */
	@TableFilterReference("language")
	private TableFilterString description = new TableFilterString();
	
	/**
	 * Constructor
	 */
	public UserBeanFilter() {	
		super();
	}

	

	/**
	 * @return the creationDate
	 */
	public TableFilterRangeDate getCreationDate() {
		return creationDate;
	}


	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(TableFilterRangeDate creationDate) {
		this.creationDate = creationDate;
	}



	/**
	 * @return the description
	 */
	public TableFilterString getDescription() {
		return description;
	}



	/**
	 * @param description the description to set
	 */
	public void setDescription(TableFilterString description) {
		this.description = description;
	}



	/**
	 * @return the nickname
	 */
	public TableFilterString getNickname() {
		return nickname;
	}



	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(TableFilterString nickname) {
		this.nickname = nickname;
	}

	
}
