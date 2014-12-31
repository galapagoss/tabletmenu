package com.oxybay.web.business.profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.profile.UserBean;
import com.oxybay.web.beans.profile.common.LanguageBean;
import com.oxybay.web.beans.profile.common.Permission;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

/**
 * @author OxyBay Consulting SL.
 * Copyright (C) - All rights reserved.
 */
public class UserBsn extends RootBsn {

	/**
	 * Basic Constructor
	 */
	public UserBsn(BsnDataInfo bsnData) {
		super(bsnData);
	}
	
	/**
	 * Login user
	 * 
	 * @param email     Email/username
	 * @param password  User password
	 * @return the logged in user, null if not found
	 */
	public UserBean login(String email, String password) {	
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserBean userBean = null;
		try {
			ps = getConnection().prepareStatement(
					"SELECT a._id, nickname, email, timezone, type, update_date, a.creation_date, deleted, b.label, " +
					"d._id as d_id,d.label as d_label,d.description as d_desc, d.active as d_active, d.system as d_system,d.creation_date as d_creation " +
					"FROM user a " +
					"INNER JOIN domain d ON a.id_domain=d._id AND d.active=1 " +
					"LEFT JOIN language b ON a.id_lang = b.label " +
					"WHERE email=? AND pwd=MD5(?) AND deleted IS NULL ");
			ps.setString(1,email);
			ps.setString(2,password);

			rs = ps.executeQuery();
			if (rs.next()) {
				userBean = new UserBean();
				userBean.setId(rs.getInt("_id"));
				userBean.setEmail(rs.getString("email"));
				String tz = rs.getString("timezone");
				userBean.setTimezone((tz!=null) ? TimeZone.getTimeZone(tz) : TimeZone.getDefault());
				userBean.setNickname(rs.getString("nickname"));
				userBean.setCreationDate(rs.getTimestamp("creation_date"));
				userBean.setUpdateDate(rs.getTimestamp("update_date"));
				userBean.setDeleted(rs.getTimestamp("deleted"));
				userBean.setLanguage(new LanguageBean(rs.getString("label")));
				userBean.setType(rs.getInt("type"));
				
				userBean.setSysAdmin(rs.getBoolean("d_system"));
				if (!userBean.isSysAdmin())
					userBean.setDomain(new DomainBean(rs.getInt("d_id"), rs.getString("d_label"), rs.getString("d_desc"), false, rs.getBoolean("d_active"), rs.getTimestamp("d_creation")));
				
				userBean.setPermission(getPermission(userBean.getId()));
			}
			
			rs.close(); rs = null;
			ps.close(); ps = null;
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return userBean;
	}
	
	/**
	 * Return user permission.
	 * @param userId user ID.
	 * @return user permission
	 */
	public List<Permission> getPermission(int userId) {
		List<Permission> userPermissions = new ArrayList<Permission>();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT permission_id " +
																"FROM user_permission " +
																"WHERE user_id = ?");
			ps.setInt(1,userId);

			rs = ps.executeQuery();
			while (rs.next()) {
				userPermissions.add(Permission.getById(rs.getInt("permission_id")));
			}
			
			rs.close(); rs = null;
			ps.close(); ps = null;
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return userPermissions;
	}
	
	/**
	 * Check Actual password
	 * @param userId
	 * @param pwd
	 * @return
	 */
	public boolean checkPassword(int userId, String pwd) {
		boolean res = false;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT _id FROM user WHERE _id=? AND pwd=MD5(?) ");
			ps.setInt(1,userId);
			ps.setString(2, pwd);

			rs = ps.executeQuery();
			res =rs.next();
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		return res;
	}
	
}