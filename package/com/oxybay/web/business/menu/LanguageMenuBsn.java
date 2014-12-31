/**
 * LanguageMenuBsn.java
 * 25/ott/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.oxybay.web.beans.menu.common.DomainFilter;
import com.oxybay.web.beans.menu.language.LanguageCustomLabel;
import com.oxybay.web.beans.menu.language.LanguageDefaultLabel;
import com.oxybay.web.beans.menu.language.LanguageMenuBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class LanguageMenuBsn extends RootBsn {

	/**
	 * @param data
	 */
	public LanguageMenuBsn(BsnDataInfo data) {
		super(data);
	}


	/**
	 * List
	 * @param filter
	 */
	public void list(DomainFilter filter) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT _id,menu_id,label,description,(label in (SELECT lang FROM menu_2_language WHERE id_menu=menu_id AND deleted IS NULL))as active,(main_lang=label)as main,(labels>customs)as customfaults " +
										 												"FROM (SELECT (SELECT COUNT(*) FROM language_label)as labels) a1, " +
										 												" (SELECT a._id as menu_id,a.main_lang,b._id,b.label,b.description,COUNT(c._id)as customs " +
										 												"  FROM (SELECT _id,domain,main_lang FROM menu WHERE domain=? AND active=? LIMIT 1) a " +
										 												"  INNER JOIN language b ON b.active=? " +
										 												"  LEFT OUTER JOIN language_label_custom c ON c.domain=a.domain AND c.lang=b.label " +
										 												"  GROUP BY a._id,a.main_lang,b._id,b.label,b.description ) a2 " +
										 												"ORDER BY main DESC,active DESC,label " +
										 												"LIMIT "+filter.calculateQueryRange());
			ps.setString(1, filter.getIdDomain().getSearch());
			ps.setBoolean(2, true);
			ps.setBoolean(3, true);
			
			rs = ps.executeQuery();
			List<Object> res = new ArrayList<Object>();
			while (rs.next()) 
				res.add(new LanguageMenuBean(rs.getInt("_id"), rs.getInt("menu_id"), rs.getString("label"), rs.getString("description"), rs.getBoolean("active"), rs.getBoolean("main"), rs.getBoolean("customfaults")));
			
			filter.setItems(res);
			filter.setTotal(listCount());
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
	}
	
	/**
	 * Load Bean
	 * @param bean
	 * @param idDomain
	 */
	public void load(LanguageMenuBean bean, int idDomain) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT EXISTS(SELECT _id FROM menu_2_language WHERE id_menu=? AND lang=? AND deleted IS NULL)as active,EXISTS(SELECT _id FROM menu WHERE _id=? AND main_lang=?)as main, " +
																						"a._id as label_id,a.label,a.default_value,b._id,b.value " +
										 												"FROM language_label a " +
										 												"LEFT OUTER JOIN language_label_custom b ON b.id_label=a._id AND b.domain=? AND b.lang=? ");
			ps.setInt(1, bean.getIdMenu());
			ps.setString(2, bean.getLabel());
			ps.setInt(3, bean.getIdMenu());
			ps.setString(4, bean.getLabel());
			ps.setInt(5, idDomain);
			ps.setString(6, bean.getLabel());
			rs = ps.executeQuery();
			while (rs.next()) {
				bean.setActive(rs.getBoolean("active"));
				bean.setMain(rs.getBoolean("main"));
				bean.getCustomLabels().add(new LanguageCustomLabel(rs.getInt("_id"), rs.getString("value"), new LanguageDefaultLabel(rs.getInt("label_id"), rs.getString("label"), rs.getString("default_value"))));
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
	}
	
	/**
	 * Update Bean
	 * @param bean
	 * @param idDomain
	 * @return
	 */
	public boolean update(LanguageMenuBean bean, int idDomain) {
		if (bean.getId()>0) {
			if (!updateMenuLang(bean))
				return false;
		} else if (!insertMenuLang(bean))
			return false;
		return updateCustomLabels(bean, idDomain);
	}
	
	/**
	 * Reset Menu Language
	 * @param bean
	 * @return
	 */
	private boolean updateMenuLang(LanguageMenuBean bean) {
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("UPDATE menu_2_language SET update_date=UTC_TIMESTAMP(),deleted="+(!bean.isActive() && !bean.isMain() ? "UTC_TIMESTAMP()" : "NULL")+" WHERE id_menu=? AND lang=? ");
			ps.setInt(1, bean.getIdMenu());
			ps.setString(2, bean.getLabel());
			return ps.executeUpdate()>0;
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return false;
	}
	
	/**
	 * Update Menu Language
	 * @param bean
	 * @return
	 */
	private boolean insertMenuLang(LanguageMenuBean bean) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("INSERT INTO menu_2_language(id_menu,lang,creation_date,deleted) VALUES (?,?,UTC_TIMESTAMP(),"+(!bean.isActive() && !bean.isMain() ? "UTC_TIMESTAMP()" : "NULL")+")",PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, bean.getIdMenu());
			ps.setString(2, bean.getLabel());
			if(ps.executeUpdate()>0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) { 
					bean.setId(rs.getInt(1));
					return true;
				}
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
		}
		return false;
	}
	
	/**
	 * Update Custom Labels
	 * @param bean
	 * @param idDomain
	 * @return
	 */
	private boolean updateCustomLabels(LanguageMenuBean bean, int idDomain){
		if (bean.getCustomLabels().size()==0)
			return true;
		PreparedStatement	ps 	= null;
		ResultSet rs = null;
		for(LanguageCustomLabel custom : bean.getCustomLabels()) {
			try{
				getConnection();
				ps = getConnection().prepareStatement("INSERT INTO language_label_custom (_id,domain,lang,id_label,value,creation_date) " +
																							" VALUES (?,?,?,?,?,UTC_TIMESTAMP()) " +
																							" ON DUPLICATE KEY UPDATE value=?,update_date=UTC_TIMESTAMP()", PreparedStatement.RETURN_GENERATED_KEYS);
				if (custom.getId()>0)
					ps.setInt(1, custom.getId());
				else
					ps.setNull(1, Types.INTEGER);
				ps.setInt(2, idDomain);
				ps.setString(3, bean.getLabel());
				ps.setInt(4, custom.getLabel().getId());
				ps.setString(5, custom.getValue());
				ps.setString(6, custom.getValue());
				if(ps.executeUpdate()>0) {
					rs = ps.getGeneratedKeys();
					if (rs.next()) 
						custom.setId(rs.getInt(1));
				}
				
			}catch(Exception ex){
				traceQueryError(ps,ex);
				return false;
			}finally{
				try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
				try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			}
		}
		return true;
	}
}
