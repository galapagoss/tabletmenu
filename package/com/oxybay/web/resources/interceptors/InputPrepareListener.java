/**
 * InputPrepareListener.java
 * 15/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.interceptors;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.PreResultListener;
import com.oxybay.web.actions.BaseAction;
import com.oxybay.web.resources.utils.Utils;

public class InputPrepareListener implements PreResultListener {

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.PreResultListener#beforeResult(com.opensymphony.xwork2.ActionInvocation, java.lang.String)
	 */
	@Override
	public void beforeResult(ActionInvocation arg0, String arg1) {
		
		if (arg0.getAction() instanceof InputPrepareAware && arg1.equals(Action.INPUT)) {
			InputPrepareAware actionPrepare = (InputPrepareAware)arg0.getAction(); 
			if (actionPrepare.checkInputPrepare())
				try {
					actionPrepare.inputPrepare();
				} catch(Exception e) {
					if (arg0.getAction() instanceof BaseAction)
						((BaseAction)arg0.getAction()).log.error(Utils.trace(e));
				}
		}
	}

}
