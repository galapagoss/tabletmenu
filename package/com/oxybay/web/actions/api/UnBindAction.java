/**
 * UnBindAction.java
 * 03/set/2012
 * @author Gaetano
 */

package com.oxybay.web.actions.api;

import com.oxybay.web.beans.menu.device.api.APIDeviceResultBean;
import com.oxybay.web.business.device.DeviceBsn;
import com.oxybay.web.resources.keys.APIKeys.Actions;

public class UnBindAction extends APIDeviceAction {

	private static final long serialVersionUID = -6973358710317915413L;

	
	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#executeAPI()
	 */
	@Override
	protected APIDeviceResultBean executeAPI() throws Exception {
		DeviceBsn deviceBsn = new DeviceBsn(bsnData);
		return new APIDeviceResultBean(deviceBsn.unbindDevice(device));
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#checkMD5()
	 */
	@Override
	protected boolean checkMD5() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#getAction()
	 */
	@Override
	protected Actions getAction() {
		// TODO Auto-generated method stub
		return Actions.UNBIND;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#getCustom()
	 */
	@Override
	protected String getCustom() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.actions.api.APIDeviceAction#checkParams()
	 */
	@Override
	protected boolean checkParams() {
		return true;
	}

}
