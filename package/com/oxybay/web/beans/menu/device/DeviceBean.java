package com.oxybay.web.beans.menu.device;

import java.io.Serializable;
import java.util.Date;

import com.oxybay.web.beans.menu.version.MenuVersionBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableRef;
import com.oxybay.web.business.system.table.annotations.TableType;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 *
 */
@TableDef(table="device",order=1)
public class DeviceBean implements Serializable {
	
	private static final long serialVersionUID = -2041300130703889331L;
	
	/* id */
	@TableType(TableType.KEY)
	private int id = 0;
	
	/* domain bean */
	@TableType(TableType.SKIP)
	private DomainBean domain = null;
	/* id domain */
	@TableField("domain")
	private int idDomain = 0;
	/* android device id */
	@TableField("device_id")
	private String deviceId = "";
	/* current version */
	@TableRef("")
	private MenuVersionBean version = null;
	/* last update */
	@TableField("last_update")
	private Date lastUpdate = null;
	
	/* creation */
	@TableType(TableType.CREATING)
	@TableField("creation_date")
	@TableOrder(id=1,type=TableOrder.TYPE_DESC,exclude="")
	private Date creationDate = null;
	/* deleted */
	@TableType(TableType.DELETING)
	private Date deleted = null;


	/**
	 * Constructor
	 */
	public DeviceBean() {	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the domain
	 */
	public DomainBean getDomain() {
		return domain;
	}


	/**
	 * @param domain the domain to set
	 */
	public void setDomain(DomainBean domain) {
		this.domain = domain;
	}


	/**
	 * @return the idDomain
	 */
	public int getIdDomain() {
		return idDomain;
	}


	/**
	 * @param idDomain the idDomain to set
	 */
	public void setIdDomain(int idDomain) {
		this.idDomain = idDomain;
	}


	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}


	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}


	/**
	 * @return the version
	 */
	public MenuVersionBean getVersion() {
		return version;
	}


	/**
	 * @param version the version to set
	 */
	public void setVersion(MenuVersionBean version) {
		this.version = version;
	}


	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}


	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}


	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	/**
	 * @return the deleted
	 */
	public Date getDeleted() {
		return deleted;
	}


	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Date deleted) {
		this.deleted = deleted;
	}
	
	
	
}