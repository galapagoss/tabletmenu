package com.oxybay.web.actions.admin.profile;

import com.oxybay.web.actions.BaseAction;
import com.oxybay.web.beans.profile.UserBean;
import com.oxybay.web.business.profile.UserBsn;
import com.oxybay.web.resources.keys.VarKeys;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 */
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 7049889545823018986L;
	
	private UserBean user = new UserBean();

	@Override
	public String executeAction() throws Exception {
		
		if (dispatch.equals("login"))
			return processExecuteLogin();
		
		return processLoginPage();
	}
	
	/**
	 * <p>Execute Login</p>
	 */
	public String processExecuteLogin(){
		log.info("TOKEN = " + tokenPassed);
		if (!tokenPassed)	return GENERAL_NOTOKEN;
		UserBean userBean = null;
		UserBsn userBsn = new UserBsn(bsnData);
		userBean = userBsn.login(user.getNickname(), user.getPassword());
		
		if (userBean == null) {
			addActionError(getText("admin.login.error.login"));
			return processLoginPage();
		} else {
			request.getSession().setAttribute(VarKeys.SESSION_USERACCOUNT, userBean);
			return "logOK";
		}
	}

	
	
	/**
	 * Process Login Page
	 */
	public String processLoginPage() {
		if (userAccount != null && userAccount.getId() > 0) {
			// user already logged in
			return GENERAL_HOME;
		}
		
		return "login";
	}
	
	/**
	 * Get user field
	 * @return Returns the user.
	 */
	public UserBean getUser() {
		return user;
	}


	/**
	 * Set user field
	 * @param user The user to set.
	 */
	public void setUser(UserBean user) {
		this.user = user;
	}

	@Override
	public boolean canAccess() {
		return true;
	}

	@Override
	public boolean isUserRequired() {
		return false;
	}
	
}