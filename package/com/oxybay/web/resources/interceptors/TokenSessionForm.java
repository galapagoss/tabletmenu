/**
 * TokenSessionForm.java
 * 10/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.interceptors;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsConstants;
import org.apache.struts2.interceptor.TokenSessionStoreInterceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import com.oxybay.plugin.multipart.MonitoredMultiPartRequest;

public class TokenSessionForm extends TokenSessionStoreInterceptor {

	private static final long serialVersionUID = -2040303238223960233L;
	/* max upload size */
	private long maxUploadSize = 0;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.interceptor.MethodFilterInterceptor#doIntercept(com.opensymphony.xwork2.ActionInvocation)
	 */
	@Override
	protected String doIntercept(ActionInvocation arg0) throws Exception {
		String res = super.doIntercept(arg0);
		if (res.equals(INVALID_TOKEN_CODE) && arg0.getAction() instanceof ActionSupport) {
			ActionSupport action = (ActionSupport)arg0.getAction();
		  // To prevent Invalid Token message for FileUpload Exception
			if (ServletActionContext.getRequest().getAttribute(MonitoredMultiPartRequest.REQ_FILEUPLOAD_EXCEPTION)==null)
				action.addFieldError("", action.getText("common.token.error"));
			else 
				action.addFieldError("", action.getText("common.upload.error.size",new String[]{(maxUploadSize/1024)+"K"}));
			return Action.INPUT;
		}
		return res;
	}
	
	@Inject(StrutsConstants.STRUTS_MULTIPART_MAXSIZE)
  public void setMaxSize(String maxSize) {
      this.maxUploadSize = Long.parseLong(maxSize);
  }
	
}
