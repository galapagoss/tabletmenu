package com.oxybay.web.actions.admin;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 */
public class HomeAction extends AdminAction {

	private static final long serialVersionUID = -1185674355128061050L;
	

	@Override
	public String executeAdminAction() throws Exception {
		return SUCCESS;
	}


	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.admin.AdminAction#getSelectedMenu()
	 */
	@Override
	public String getSelectedMenu() {
		// TODO Auto-generated method stub
		return "home";
	}
	
}