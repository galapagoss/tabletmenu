/**
 * InputPrepareInterceptor.java
 * 06/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.interceptors;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class InputPrepareInterceptor implements Interceptor {

	private static final long serialVersionUID = -4871736193204633013L;

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
		/* Listener */
		arg0.addPreResultListener(new InputPrepareListener());
  	return arg0.invoke();
	}

}
