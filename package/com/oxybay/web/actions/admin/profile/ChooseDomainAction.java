/**
 * ChooseDomain.java
 * 12/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.profile;

import java.util.List;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.business.system.table.TableBsn;

public class ChooseDomainAction extends AdminAction {

	private static final long serialVersionUID = -454252269282852750L;
	
	/* domains list */
	private List<Object> domains = null;
	/* domain */
	private DomainBean domain = null;

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAction()
	 */
	@Override
	public String executeAdminAction() throws Exception {
		
		if (tokenPassed) {
			if (domain!=null && domain.getId()>0) {
				TableBsn tableBsn = new TableBsn(bsnData, DomainBean.class);
				tableBsn.loadBean(domain);
				if (domain!=null) {
					userAccount.setDomain(domain);
					return GENERAL_HOME;
				}
			}
			addActionError(getText("domains.choose.messages.selection.error"));
		}
		
		return INPUT;
	}

	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "";
	}





	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#checkInputPrepare()
	 */
	@Override
	public boolean checkInputPrepare() {
		return true;
	}



	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#inputPrepare()
	 */
	@Override
	public void inputPrepare() throws Exception {
		TableBsn tableBsn = new TableBsn(bsnData, DomainBean.class);
		domains = tableBsn.listSimple(1, "system<>1");
		super.inputPrepare();
	}


	/**
	 * @return the domains
	 */
	public List<Object> getDomains() {
		return domains;
	}



	/**
	 * @return the domain
	 */
	public DomainBean getDomain() {
		return domain;
	}



	/**
	 * @param domain the domain to set
	 */
	public void setDomain(DomainBean domain) {
		this.domain = domain;
	}

	
}
