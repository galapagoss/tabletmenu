/**
 * APIAction.java
 * 27/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.api;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.oxybay.web.actions.BaseAction;
import com.oxybay.web.beans.menu.device.api.APIDeviceResultBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.resources.keys.APIKeys;
import com.oxybay.web.resources.keys.APIKeys.APIErrors;
import com.oxybay.web.resources.keys.APIKeys.Actions;
import com.oxybay.web.resources.utils.Utils;

public abstract class APIDeviceAction extends BaseAction implements ServletRequestAware {

	private static final long serialVersionUID = 9109376033685989202L;
	
	/* id device */
	protected String device = "";
	/* menu */
	protected int menu = 0;
	
	/* hash key */
	private String hash = "";
	/* call */
	private long call = 0;

	/* API result */
	private APIDeviceResultBean result = null;
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.BaseAction#executeAction()
	 */
	@Override
	public String executeAction() throws Exception {

		// Check if params are corrects
		if (!checkParams() || device.equals("")){
			result = new APIDeviceResultBean(APIErrors.PARAMS_INCORRECT);
			return SUCCESS;
		}
		
		DeviceBsn deviceBsn = new DeviceBsn(bsnData);
		// Check MD5
		if (checkMD5()) {
			final long lastAction = deviceBsn.checkAPImd5(device, call, hash,0);
			if (lastAction!=APIKeys.MD5CHECK_PASSED) {
				result = new APIDeviceResultBean(APIErrors.HASH,lastAction);
				return SUCCESS;
			}
		}
		
		// API execution
		try {
			result = executeAPI();
		} catch(Exception e) {
			log.error(Utils.trace(e));
			result = new APIDeviceResultBean(APIErrors.EXCEPTION);
		}
		
		// Trace Action
		final long lastAction = deviceBsn.logAction(device, getAction(), getCustom(), result!=null && result.isStatusOK());
		if (lastAction>=0) 
			result.setCall(lastAction);
		 else 
			result = new APIDeviceResultBean(APIErrors.DEVICE_UNKNOWN);
    
		
		return SUCCESS;
	}

	
	/**
	 * Execute API
	 * @return
	 * @throws Exception
	 */
	protected abstract APIDeviceResultBean executeAPI() throws Exception;
	
	/**
	 * Check if should perform MD5 control
	 * @return
	 */
	protected abstract boolean checkMD5();
	
	/**
	 * Get Action to tract
	 * @return
	 */
	protected abstract Actions getAction();
	
	/**
	 * Get Custom data to trace
	 * @return
	 */
	protected abstract String getCustom();


	/**
	 * Verify if params are passed correctly
	 * @return
	 */
	protected abstract boolean checkParams();
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.BaseAction#canAccess()
	 */
	@Override
	public boolean canAccess() {
		return true;
	}


	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.BaseAction#isUserRequired()
	 */
	@Override
	public boolean isUserRequired() {
		return false;
	}


	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
	}


	/**
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}


	/**
	 * @param call the call to set
	 */
	public void setCall(long call) {
		this.call = call;
	}


	/**
	 * @param menu the menu to set
	 */
	public void setMenu(int menu) {
		this.menu = menu;
	}


	/**
	 * @return the result
	 */
	public APIDeviceResultBean getResult() {
		return result;
	}

	
}
