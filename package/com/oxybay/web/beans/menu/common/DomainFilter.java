/**
 * DomainFilter.java
 * 26/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.common;

import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.common.PaginatorBean;
import com.oxybay.web.beans.system.table.filters.TableFilterString;

public class DomainFilter extends PaginatorBean {

	/* id domain */
	private TableFilterString idDomain = new TableFilterString();
	
	/**
	 * Constructor
	 */
	public DomainFilter() {
		super();
	}

	
	/**
	 * @return the idDomain
	 */
	public TableFilterString getIdDomain() {
		return idDomain;
	}


	/**
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(TableFilterString idDomain) {
		this.idDomain = idDomain;
	}


	/**
	 * @param domain the domain to set
	 */
	public void setDomainValue(DomainBean domain) {
		this.idDomain.setSearch(""+domain.getId());
	}

	
}
