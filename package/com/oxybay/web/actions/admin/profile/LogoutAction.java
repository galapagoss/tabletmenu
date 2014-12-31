package com.oxybay.web.actions.admin.profile;

import com.oxybay.web.actions.admin.AdminAction;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 */
public class LogoutAction extends AdminAction {

	private static final long serialVersionUID = 5607012306288720915L;

	@Override
	public String executeAdminAction() throws Exception {
		
		request.getSession().removeAttribute("userAccount");
		return SUCCESS;
	}

	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#isUserRequired()
	 */
	@Override
	public boolean isUserRequired() {
		return false;
	}


	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		return "";
	}
	
	
	
}