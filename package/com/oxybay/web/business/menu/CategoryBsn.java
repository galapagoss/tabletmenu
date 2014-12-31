/**
 * CategoryBsn.java
 * 19/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opensymphony.xwork2.util.TextParseUtil;
import com.oxybay.web.beans.menu.category.CategoryBean;
import com.oxybay.web.beans.menu.category.CategoryBeanFilter;
import com.oxybay.web.beans.menu.common.AttachBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class CategoryBsn extends RootBsn {

	/**
	 * @param data
	 */
	public CategoryBsn(BsnDataInfo data) {
		super(data);
	}


	/**
	 * Load Categories
	 * @param filter
	 */
	public void list(CategoryBeanFilter filter, DomainBean domain, String noName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT b._id,b.img,b.item_order,b.active,b.creation_date,b.update_date,c._id as c_id,c.lang as c_lang,c.title as c_title,IF(SUM(IF(l.lang=ml.lang,1,0))=a.menulangs,1,0)as all_langs,IFNULL(GROUP_CONCAT(DISTINCT l.lang),'')as lang_list " +
										 												"FROM (SELECT a1._id,a1.main_lang,COUNT(*)as menulangs FROM menu a1 LEFT OUTER JOIN menu_2_language b1 ON a1._id=b1.id_menu AND b1.deleted IS NULL WHERE a1.domain=? AND a1.active=? GROUP BY a1._id,a1.main_lang LIMIT 1) a " +
										 												"INNER JOIN category b ON a._id=b.id_menu AND b.deleted IS NULL " +
										 												"LEFT OUTER JOIN menu_2_language ml ON ml.id_menu=a._id AND ml.deleted IS NULL " +
										 												"LEFT OUTER JOIN category_2_language c ON b._id=c.id_category AND c.lang=a.main_lang AND c.deleted IS NULL " +
										 												"LEFT OUTER JOIN category_2_language l ON b._id=l.id_category AND l.deleted IS NULL " +
										 												"WHERE 1=1 " + filter.getLabel().generateQuery(Arrays.asList(new String[]{"c.title","c.subtitle","c.body"}))+" " +
										 												"GROUP BY b._id,b.img,b.item_order,b.active,b.creation_date,b.update_date,c._id,c.lang,c.title " +
										 												"ORDER BY b.item_order,b.creation_date DESC " +
										 												"LIMIT "+filter.calculateQueryRange());
			
			int counter = 1;
			ps.setInt(counter, domain.getId());
			counter++;
			ps.setBoolean(counter,true);
			filter.getLabel().setPreparedStatement(ps, counter);

			rs = ps.executeQuery();
			List<Object> res = new ArrayList<Object>();
			while (rs.next()) {
				CategoryBean item = new CategoryBean();
				item.setId(rs.getInt("_id"));
				item.setOrder(rs.getInt("item_order"));
				int img = rs.getInt("img");
				if (img>0)
					item.setImg(new AttachBean(img));
				item.setActive(rs.getBoolean("active"));
				item.setCreationDate(rs.getTimestamp("creation_date"));
				item.setUpdateDate(rs.getTimestamp("update_date"));
				item.getMainLang().setId(rs.getInt("c_id"));
				item.getMainLang().setLabel(rs.getString("c_lang"));
				item.getMainLang().setTitle(rs.getString("c_title"));
				if (item.getMainLang().getTitle().equals(""))
					item.getMainLang().setTitle(noName);
				item.setLangFault(!rs.getBoolean("all_langs"));
				item.setLanguages(TextParseUtil.commaDelimitedStringToSet(rs.getString("lang_list")));
				
				res.add(item);
			}
			
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
	 * Simple List
	 * @param domain
	 * @param noName
	 * @return
	 */
	public ArrayList<CategoryBean> listSimple(DomainBean domain, String noName) {
		ArrayList<CategoryBean> res = new ArrayList<CategoryBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT b._id,c.title " +
										 												"FROM (SELECT _id,main_lang FROM menu WHERE domain=? AND active=? LIMIT 1) a " +
										 												"INNER JOIN category b ON a._id=b.id_menu AND b.deleted IS NULL " +
										 												"LEFT OUTER JOIN category_2_language c ON b._id=c.id_category AND c.lang=a.main_lang AND c.deleted IS NULL " +
										 												"ORDER BY b.item_order ");
			ps.setInt(1, domain.getId());
			ps.setBoolean(2,true);
			rs = ps.executeQuery();
			while (rs.next()) {
				CategoryBean item = new CategoryBean();
				item.setId(rs.getInt("_id"));
				item.getMainLang().setTitle(rs.getString("title"));
				if (item.getMainLang().getTitle().equals(""))
					item.getMainLang().setTitle(noName);
				res.add(item);
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	
	/**
	 * Update Order
	 * @param idList
	 * @param orderList
	 * @return
	 */
	public boolean updateOrder(String idList, String orderList){
		boolean	res = false;
		// SQL Injection Check
		idList = idList.replace("'", "");
		orderList = orderList.replace("'", "");
		if (idList.equals("") || orderList.equals(""))
			return false;
		
		PreparedStatement	ps 	= null;
		try{
			getConnection();
			ps = getConnection().prepareStatement("UPDATE category SET item_order=ELT(FIELD(_id,"+idList+"),"+orderList+"),update_date=UTC_TIMESTAMP() WHERE _id IN ("+idList+")");
			res = (ps.executeUpdate()>=0);
			ps.close();
		}catch(Exception ex){
			traceQueryError(ps,ex);
		}finally{
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	
	/**
	 * Insert Bean
	 * @param bean
	 * @param domain
	 * @return
	 */
	public boolean insert(CategoryBean bean, DomainBean domain){
		boolean	res = false;
		PreparedStatement	ps 	= null;
		ResultSet rs = null;
		try{
			getConnection();
			ps = getConnection().prepareStatement("INSERT INTO category(id_menu,img,active,item_order,creation_date) " +
																						"SELECT menu._id,?,?,COUNT(*)+1,UTC_TIMESTAMP() FROM (SELECT _id FROM menu WHERE domain=? AND active=? LIMIT 1) menu " +
																						"INNER JOIN category ON category.id_menu=menu._id " +
																						"GROUP BY menu._id ", PreparedStatement.RETURN_GENERATED_KEYS);
			if (bean.getImg()!=null && bean.getImg().getId()>0)
				ps.setInt(1, bean.getImg().getId());
			else
				ps.setNull(1, Types.INTEGER);
			ps.setBoolean(2, bean.isActive());
			ps.setInt(3, domain.getId());
			ps.setBoolean(4, true);
			if(ps.executeUpdate()>=0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					bean.setId(rs.getInt(1));
					res = true;
				}
			}
			ps.close();
		}catch(Exception ex){
			traceQueryError(ps,ex);
		}finally{
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
		}
		return res;
	}
}
