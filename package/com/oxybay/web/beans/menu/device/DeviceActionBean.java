/**
 * DeviceActionBean.java
 * 28/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.menu.device;

import java.util.Date;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableType;

@TableDef(table="device_action",order=1)
public class DeviceActionBean {
	
	/* id */
	@TableType(TableType.KEY)
	private long id = 0;
	/* id device */
	@TableField("id_device")
	private int idDevice = 0;
	/* action */
	private int action = 0;
	/* action date */
	@TableType(TableType.CREATING)
	@TableField("action_date")
	@TableOrder(id=1,type=TableOrder.TYPE_DESC,exclude="")
	private Date actionDate = null;
	/* custom data */
	@TableField("custom_data")
	private String custom = "";
	/* successfully */
	private boolean success = false;
	
	
	
	/**
	 * @param id
	 * @param idDevice
	 * @param action
	 * @param custom
	 * @param success
	 */
	public DeviceActionBean(long id, int idDevice, int action, String custom,boolean success) {
		this.id = id;
		this.idDevice = idDevice;
		this.action = action;
		this.custom = custom;
		this.success = success;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the idDevice
	 */
	public int getIdDevice() {
		return idDevice;
	}
	/**
	 * @param idDevice the idDevice to set
	 */
	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}
	/**
	 * @return the action
	 */
	public int getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(int action) {
		this.action = action;
	}
	/**
	 * @return the actionDate
	 */
	public Date getActionDate() {
		return actionDate;
	}
	/**
	 * @param actionDate the actionDate to set
	 */
	public void setActionDate(Date actionDate) {
		this.actionDate = actionDate;
	}
	/**
	 * @return the custom
	 */
	public String getCustom() {
		return custom;
	}
	/**
	 * @param custom the custom to set
	 */
	public void setCustom(String custom) {
		this.custom = custom;
	}
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
		
}
