/**
 * DiscountContainerBean.java
 * 25/set/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.discount;

import java.util.ArrayList;
import java.util.List;

public class DiscountContainerBean {
	
	/* flag for update*/
	private boolean changed = false;
	/* policies*/
	private List<DiscountBean> policies = new ArrayList<DiscountBean>();
	
	/**
	 * @return the changed
	 */
	public boolean isChanged() {
		return changed;
	}
	/**
	 * @param changed the changed to set
	 */
	public void setChanged(boolean changed) {
		this.changed = changed;
	}
	/**
	 * @return the policies
	 */
	public List<DiscountBean> getPolicies() {
		return policies;
	}
	/**
	 * @param policies the policies to set
	 */
	public void setPolicies(List<DiscountBean> policies) {
		this.policies = policies;
	}
	

}
