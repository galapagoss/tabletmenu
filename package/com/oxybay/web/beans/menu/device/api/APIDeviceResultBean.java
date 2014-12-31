package com.oxybay.web.beans.menu.device.api;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.oxybay.web.resources.keys.APIKeys.APIErrors;
import com.oxybay.web.resources.keys.APIKeys.APIStatus;

@XmlRootElement
public class APIDeviceResultBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** status */
	private APIStatus status = APIStatus.ERROR;
	
	/** API error code */
	private APIErrors code = APIErrors.NOTHING;
	
	/** last action code */
	private long call = 0;
	
	/** object */
	private Object object = null;
	
	/** simple result */
	private String value = null;

	
	/**
	 * Empty Constructor
	 */
	public APIDeviceResultBean () {	}

	/**
	 * @param statusBool
	 */
	public APIDeviceResultBean(boolean statusBool) {
		setStatus(statusBool);
	}
	
	/**
	 * @param code
	 */
	public APIDeviceResultBean(APIErrors code) {
		setStatus(false);
		this.code = code;
	}
	
	/**
	 * @param code
	 * @param call
	 */
	public APIDeviceResultBean(APIErrors code, long call) {
		setStatus(false);
		this.code = code;
		this.call = call;
	}


	/**
	 * @param call
	 * @param object
	 */
	public APIDeviceResultBean(Object object) {
		setStatus(object!=null);
		this.object = object;
	}
	
	/**
	 * @param value
	 */
	public APIDeviceResultBean(String value) {
		setStatus(value!=null && value.trim().equals(""));
		this.value = value;
	}
	
	
	
	/**
	 * @param status
	 * @param code
	 * @param call
	 * @param object
	 * @param value
	 */
	public APIDeviceResultBean(boolean statusBool, APIErrors code, long call, Object object, String value) {
		setStatus(statusBool);
		this.code = code;
		this.call = call;
		this.object = object;
		this.value = value;
	}


	/**
	 * @return the status
	 */
	public APIStatus getStatus() {
		return status;
	}


	/**
	 * @param statusBoolean the status boolean to set
	 */
	public void setStatus(boolean statusBoolean) {
		this.status = statusBoolean ? APIStatus.OK : APIStatus.ERROR;
		if (!statusBoolean)
			this.code = APIErrors.NOTFOUND;
	}

	/**
	 * If status is OK
	 * @return
	 */
	public boolean isStatusOK() {
		return this.status.equals(APIStatus.OK);
	}

	/**
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}


	/**
	 * @param object the object to set
	 */
	public void setObject(Object object) {
		this.object = object;
	}

	
	/**
	 * @return the code
	 */
	public APIErrors getCode() {
		return code;
	}


	/**
	 * @param code the code to set
	 */
	public void setCode(APIErrors code) {
		this.code = code;
	}


	/**
	 * @return the call
	 */
	public long getCall() {
		return call;
	}

	/**
	 * @param call the call to set
	 */
	public void setCall(long call) {
		this.call = call;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(APIStatus status) {
		this.status = status;
	}


	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}



