/***
 * @author Gaetano Negrone
 * 
 * Base client action
 */

package com.oxybay.web.actions;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsConstants;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import com.oxybay.web.beans.profile.UserBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.resources.Resources;
import com.oxybay.web.resources.keys.VarKeys;
import com.oxybay.web.resources.utils.Utils;

public abstract class BaseAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID   = 0L;
	// General Results
	protected static final String		GENERAL_ERROR			= "general.error";
	protected static final String		GENERAL_HOME			= "general.home";
	protected static final String		GENERAL_NOSESSION	= "general.no.session";
	protected static final String		GENERAL_NOACCESS	= "general.no.access";
	protected static final String		GENERAL_NOTOKEN		= "general.no.token";
	
	/* Request */
	protected HttpServletRequest request = null;
	/* Session */
	protected HttpSession session = null;
	/* Logger */
	public Logger log = Logger.getLogger(this.getClass());
	/* dispatch */
	protected String dispatch = "";
	/* token check passed */
	protected boolean tokenPassed = false;
	/* resources */
	public Resources resources = null;
	/* User Account */
	protected UserBean userAccount = null;
	/* Data to pass at business logic */
	public BsnDataInfo bsnData;
	/* resources loaded */
	public boolean resLoaded = false;
	/* dev mode */
	private boolean devMode = false;
	
	/**
	 * <p>Method to override</p>
	 */
	public String execute() throws Exception {
				
		// Resources Test
		if (!resLoaded) {
			log.error("Resources Not Available");
			return GENERAL_ERROR;
    }

		// Check User
		if (!checkUser()) 
			return GENERAL_NOSESSION;
		
		if (!canAccess())
			return GENERAL_NOACCESS;
		
		// Execute Action
		try {
			return executeAction();
			
		} catch (Exception e) {
			log.error(Utils.trace(e));
			if (devMode)
				throw e;
		}
		return GENERAL_ERROR;
	}
	
	
	/**
	 * Method for data submission with token
	 */
	public String postData() throws Exception {
		tokenPassed = true;
		return execute();
	}
	
	
	/**
	 * <p>Abstract Method to extend</p>
	 */
	public abstract String executeAction() throws Exception;


	/**
	 * Check User in Session.
	 * Return true if the check was successfully.
	 */
	protected boolean checkUser() {
		if (isUserRequired()) {
			if (userAccount == null || userAccount.getId() == 0) {
				return false;
			}
		}
		
		if (userAccount == null) {
			userAccount = new UserBean();
		}
		
		return true;
	}
	
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = this.request.getSession();
		userAccount = (UserBean) session.getAttribute(VarKeys.SESSION_USERACCOUNT);
	}
	
	/**
	 * <P>
	 * Get dispatch field
	 * <P>
	 * @return Returns the dispatch.
	 */
	public String getDispatch() {
		return dispatch;
	}


	/**
	 * <P>
	 * Set dispatch field
	 * <P> 
	 * @param dispatch The dispatch to set.
	 */
	public void setDispatch(String dispatch) {
		this.dispatch = dispatch;
	}
	
	@Inject(StrutsConstants.STRUTS_DEVMODE)
  public void setDevMode(String mode) {
		this.devMode = "true".equals(mode);
  }

	/**
	 * Get Action Name
	 * @return
	 */
	public String getActionname() {
		return ActionContext.getContext().getName().replaceAll("!(.*)","");
	}
	
	/**
	 * Return true if the user can access this action.
	 * @return true if the user can access this action.
	 */
	public abstract boolean canAccess();
	
	/**
	 * Return true if this action required a valid user logged in to be executed.
	 * @return true if this action required a valid user logged in to be executed.
	 */
	public abstract boolean isUserRequired();

}