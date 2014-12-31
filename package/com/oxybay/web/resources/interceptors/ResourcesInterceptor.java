/**
 * ResourcesInterceptor.java
 * 06/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.oxybay.web.actions.BaseAction;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;
import com.oxybay.web.resources.Resources;


public class ResourcesInterceptor implements Interceptor {

	private static final long serialVersionUID = -4874736193204633013L;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
	 */
	@Override
	public void destroy() {}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
	 */
	@Override
	public void init() {}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.Interceptor#intercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		if (arg0.getAction() instanceof BaseAction) {
			BaseAction action =(BaseAction)arg0.getAction();
			/* Resources */
			action.resources = Resources.getInstance();
			action.resLoaded = action.resources.getInfo() != null && action.resources.getInfo().isStarted();
			/* Business Data */
			action.bsnData = new BsnDataInfo(action.resources, action.log);
			/* execute action */
	  	String res = arg0.invoke();
	  	/* release connections */
	  	RootBsn.releaseConnections(action.bsnData);
	  	return res;
		}
		return arg0.invoke();
	}

}
