/**
 * MenuBsn.java
 * 17/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.oxybay.web.beans.menu.MenuBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.profile.common.LanguageBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class MenuBsn extends RootBsn {

	/**
	 * Basic Constructor
	 */
	public MenuBsn(BsnDataInfo bsnData) {
		super(bsnData);
	}
	

	
	/**
	 * Get Menu Languages
	 * @param domain
	 * @return
	 */
	public List<LanguageBean> getMenuLanguages(DomainBean domain) {
		List<LanguageBean> res = new ArrayList<LanguageBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT a.label,a.description,a.active,a.application,IF(a.label=b.main_lang,1,0)as mainlang,IF(c.lang IS NULL,0,1)as activelang " +
																						"FROM language a  " +
																						"LEFT OUTER JOIN (SELECT _id,main_lang FROM menu WHERE domain=? AND active=? LIMIT 1) b ON 1=1 " +
																						"LEFT OUTER JOIN menu_2_language c ON b._id=c.id_menu AND c.lang=a.label AND c.deleted IS NULL " +
																						"WHERE a.active=? " +
																						"ORDER BY mainlang DESC,activelang DESC,label ");
			ps.setInt(1,domain.getId());
			ps.setBoolean(2, true);
			ps.setBoolean(3, true);
			rs = ps.executeQuery();
			while (rs.next())
				res.add(new LanguageBean(rs.getString("label"), rs.getString("description"), rs.getBoolean("active"), rs.getBoolean("application"), rs.getBoolean("mainlang"), rs.getBoolean("activelang")));
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Load Text Languages
	 * @param menu
	 */
	public void loadTextLanguages(MenuBean menu) {
		menu.getTextLangs().clear();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT lang FROM menu_text_2_language WHERE id_menu=? AND deleted IS NULL");
			ps.setInt(1,menu.getId());
			rs = ps.executeQuery();
			while (rs.next())
				menu.getTextLangs().add(rs.getString("lang"));
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
	}
	
}