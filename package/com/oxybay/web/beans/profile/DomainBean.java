/**
 * DomainBean.java
 * 12/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.profile;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableType;
import com.oxybay.web.resources.keys.APIKeys;

@TableDef(table="domain",order=1)
@XmlRootElement
public class DomainBean implements Serializable {

	private static final long serialVersionUID = 6131768696708556290L;
	
	/* id */
	@TableType(TableType.KEY)
	@TableField("_id")
	private int id = 0;
	/* label */
	@TableOrder(id=1,type=TableOrder.TYPE_ASC,exclude="")
	private String label = "";
	/* bind device key */
	@TableField("device_key")
	private String bindKey = "";
	/* unlock device key */
	@TableField("unlock_key")
	private String unlockKey = "";
	
	/* description */
	private String description = "";
	/* system */
	private boolean system = false;
	/* active */
	private boolean active = false;
	/* creation date */
	@TableType(TableType.CREATING)
	@TableField("creation_date")
	private Date creationDate = null;
	
	/**
	 * Empty Constructor
	 */
	public DomainBean() { }

	/**
	 * @param id
	 * @param label
	 * @param description
	 * @param system
	 * @param active
	 * @param creationDate
	 */
	public DomainBean(int id, String label, String description, boolean system, boolean active, Date creationDate) {
		this.id = id;
		this.label = label;
		this.description = description;
		this.system = system;
		this.active = active;
		this.creationDate = creationDate;
	}

	
	/**
	 * @param id
	 */
	public DomainBean(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the system
	 */
	public boolean isSystem() {
		return system;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param system the system to set
	 */
	public void setSystem(boolean system) {
		this.system = system;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @param creationDate the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return the bindKey
	 */
	public String getBindKey() {
		return bindKey;
	}

	/**
	 * @param bindKey the bindKey to set
	 */
	public void setBindKey(String bindKey) {
		this.bindKey = bindKey;
	}

	/**
	 * @return the unlockKey
	 */
	public String getUnlockKey() {
		return unlockKey;
	}

	/**
	 * @param unlockKey the unlockKey to set
	 */
	public void setUnlockKey(String unlockKey) {
		this.unlockKey = unlockKey;
	}
	
	/**
	 * Get Complete Bind Key
	 * @return
	 */
	public String getCompleteBindKey() {
		return label+APIKeys.BIND_STRING_SEPARATOR+bindKey;
	}
}
