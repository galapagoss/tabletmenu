/**
 * @author Gaetano Negrone
 * UserBean
 */

package com.oxybay.web.beans.profile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.oxybay.web.beans.profile.common.LanguageBean;
import com.oxybay.web.beans.profile.common.Permission;
import com.oxybay.web.business.system.table.annotations.TableDef;
import com.oxybay.web.business.system.table.annotations.TableField;
import com.oxybay.web.business.system.table.annotations.TableOrder;
import com.oxybay.web.business.system.table.annotations.TableRef;
import com.oxybay.web.business.system.table.annotations.TableType;


@TableDef(table="user",order=1)

public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 2653371207113996188L;
	
	public static final int TYPE_USER         = 0;
	public static final int TYPE_DOMAIN_ADMIN = 10;
	
	/* id account */
	@TableType(TableType.KEY)
	@TableField("_id")
	@TableOrder(id=2,type=TableOrder.TYPE_ASC,exclude="0")
	private int id = 0;
		
	/* password */
	@TableType(TableType.MD5)
	@TableField("pwd")
	private String password = "";
	
	/* confirm password */
	@TableType(TableType.SKIP)
	private String confirmPassword = "";
	
	/* active flag */
	private boolean active = false;
	
	/* nickname */
	@TableOrder(id=3,type=TableOrder.TYPE_ASC,exclude="")
	private String nickname = "";
	/* email */
	private String email = "";
	/* first name */
	@TableOrder(id=4,type=TableOrder.TYPE_ASC,exclude="")
	private String firstname = "";
	/* lastname */
	@TableOrder(id=5,type=TableOrder.TYPE_ASC,exclude="")
	private String lastname = "";
	
	/* user type */
	private int type;
	
	/* timezone */
	private TimeZone timezone = null;
	/* timezone ID */
	@TableType(TableType.SKIP)
	public String timezoneID = "";
	
	/* language */
	@TableField("id_lang")
	@TableRef("#.active=1")
	private LanguageBean language = null;
	
	/* creation */
	@TableType(TableType.CREATING)
	@TableField("creation_date")
	@TableOrder(id=1,type=TableOrder.TYPE_DESC,exclude="")
	private Date creationDate = null;
	
	/* update date */
	@TableType(TableType.UPDATING)
	@TableField("update_date")
	private Date updateDate = null;
	
	/* deleted */
	@TableType(TableType.DELETING)
	private Date deleted = null;
	
	/* domain bean */
	@TableType(TableType.SKIP)
	private DomainBean domain = null;
	/* id domain */
	@TableField("id_domain")
	private int idDomain = 0;
	
	/* user permission */
	@TableType(TableType.SKIP)
	private List<Permission> permission;
	
	/* sysadmin */
	@TableType(TableType.SKIP)
	private boolean sysAdmin = false;
	
	/**
	 * <p>Base Constructor</p>
	 */
	public UserBean() {	}



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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}



	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}



	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}



	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}



	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}



	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}



	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	/**
	 * @return the language
	 */
	public LanguageBean getLanguage() {
		return language;
	}



	/**
	 * @param language the language to set
	 */
	public void setLanguage(LanguageBean language) {
		this.language = language;
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



	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}



	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	

	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}



	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	/**
	 * @return the timezone
	 */
	public TimeZone getTimezone() {
		return timezone;
	}



	/**
	 * @param timezone the timezone to set
	 */
	public void setTimezone(TimeZone timezone) {
		this.timezone = timezone;
	}



	/**
	 * @return the permission
	 */
	public List<Permission> getPermission() {
		return permission;
	}



	/**
	 * @param permission the permission to set
	 */
	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}



	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}



	/**
	 * @return the timezoneID
	 */
	public String getTimezoneID() {
		return (timezone!=null) ? timezone.getID() : "";
	}



	/**
	 * @param timezoneID the timezoneID to set
	 */
	public void setTimezoneID(String timezoneID) {
		this.timezoneID = timezoneID;
		this.timezone = TimeZone.getTimeZone(timezoneID);
	}



	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}



	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * @return the sysAdmin
	 */
	public boolean isSysAdmin() {
		return sysAdmin;
	}



	/**
	 * @param sysAdmin the sysAdmin to set
	 */
	public void setSysAdmin(boolean sysAdmin) {
		this.sysAdmin = sysAdmin;
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

	
}
