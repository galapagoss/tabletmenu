/**
 * CacheMonitorAction.java
 * 24/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.admin.tools;

import com.oxybay.web.actions.admin.AdminAction;
import com.oxybay.web.beans.system.caching.CacheBean;
import com.oxybay.web.beans.system.caching.monitor.CacheMonitorBean;
import com.oxybay.web.resources.keys.VarKeys;

public class CacheMonitorAction extends AdminAction {

	private static final long serialVersionUID = 4633742697867670316L;

	/* cache bean copy */ 
	private CacheMonitorBean cache = null;
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#executeAdminAction()
	 */
	@Override
	protected String executeAdminAction() throws Exception {
		
		CacheBean cacheMem = (CacheBean)resources.getServletContext().getAttribute(VarKeys.APP_CACHE);
		if (cacheMem != null) 
			cache = cacheMem.getMonitorData();
		else
			addActionError(getText("tools.cache.notActive"));
		
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "tools.cache";
	}

	/**
	 * @return the cache
	 */
	public CacheMonitorBean getCache() {
		return cache;
	}
	
}
