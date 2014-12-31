/**
 * ProductBsn.java
 * 22/lug/2012
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
import com.oxybay.web.beans.menu.common.AttachBean;
import com.oxybay.web.beans.menu.product.ProductBean;
import com.oxybay.web.beans.menu.product.ProductBeanFilter;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class ProductBsn extends RootBsn {

	/**
	 * @param data
	 */
	public ProductBsn(BsnDataInfo data) {
		super(data);
	}


	/**
	 * Load Products
	 * @param filter
	 */
	public void list(ProductBeanFilter filter, DomainBean domain, String noName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String filterSearch = "";
			if (filter.getLabel()!=null)
				filterSearch = filter.getLabel().generateQuery(Arrays.asList(new String[]{"c.title","c.subtitle","c.body"}));
			if (filter.getIdCategory()>0)
				filterSearch += " AND b._id IN (SELECT id_product FROM product_2_category WHERE id_category="+filter.getIdCategory()+")";
			ps = getConnection().prepareStatement("SELECT b._id,b.img,b.price,b.item_order,b.active,b.creation_date,b.update_date,c._id as c_id,c.lang as c_lang,c.title as c_title,IF(SUM(IF(l.lang=ml.lang,1,0))=a.menulangs,1,0)as all_langs,IFNULL(GROUP_CONCAT(DISTINCT l.lang),'')as lang_list " +
										 												"FROM (SELECT a1._id,a1.main_lang,COUNT(*)as menulangs FROM menu a1 LEFT OUTER JOIN menu_2_language b1 ON a1._id=b1.id_menu AND b1.deleted IS NULL WHERE a1.domain=? AND a1.active=? GROUP BY a1._id,a1.main_lang LIMIT 1) a " +
										 												"INNER JOIN product b ON a._id=b.id_menu AND b.deleted IS NULL " +
										 												"LEFT OUTER JOIN menu_2_language ml ON ml.id_menu=a._id AND ml.deleted IS NULL " +
										 												"LEFT OUTER JOIN product_2_language c ON b._id=c.id_product AND c.lang=a.main_lang AND c.deleted IS NULL " +
										 												"LEFT OUTER JOIN product_2_language l ON b._id=l.id_product AND l.deleted IS NULL " +
										 												"LEFT OUTER JOIN product_2_category d ON b._id=d.id_product " +
										 												"WHERE 1=1 " + filterSearch +" " +
										 												"GROUP BY b._id,b.img,b.price,b.item_order,b.active,b.creation_date,b.update_date,c._id,c.lang,c.title " +
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
				ProductBean item = new ProductBean(); 
				item.setId(rs.getInt("_id"));
				int img = rs.getInt("img");
				if (img>0)
					item.setImg(new AttachBean(img));
				item.setPrice(rs.getFloat("price"));
				item.setOrder(rs.getInt("item_order"));
				item.setActive(rs.getBoolean("active"));
				item.setCreationDate(rs.getTimestamp("creation_date"));
				item.setUpdateDate(rs.getTimestamp("update_date"));
				item.getMainLang().setId(rs.getInt("c_id"));
				item.getMainLang().setLabel(rs.getString("c_lang"));
				item.getMainLang().setTitle(rs.getString("c_title"));
				if (item.getMainLang().getTitle()== null || item.getMainLang().getTitle().equals(""))
					item.getMainLang().setTitle(noName);
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
	
	public ArrayList<ProductBean> listSimple(DomainBean domain, String noName) {
		ArrayList<ProductBean> res = new ArrayList<ProductBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT b._id,c.title as c_title " +
																						"FROM (SELECT a1._id,a1.main_lang,COUNT(*)as menulangs FROM menu a1 LEFT OUTER JOIN menu_2_language b1 ON a1._id=b1.id_menu AND b1.deleted IS NULL WHERE a1.domain=? AND a1.active=? GROUP BY a1._id,a1.main_lang LIMIT 1) a " +
										 												"INNER JOIN product b ON a._id=b.id_menu AND b.deleted IS NULL " +
										 												"LEFT OUTER JOIN menu_2_language ml ON ml.id_menu=a._id AND m1.deleted IS NULL " +
										 												"LEFT OUTER JOIN product_2_language c ON b._id=c.id_product AND c.lang=a.main_lang AND c.deleted IS NULL " +
										 												"LEFT OUTER JOIN product_2_language l ON b._id=l.id_product AND l.deleted IS NULL " +
										 												"GROUP BY b._id,b.img,b.price,b.item_order,b.active,b.creation_date,b.update_date,c._id,c.lang,c.title " +
										 												"ORDER BY c.title ");
			ps.setInt(1, domain.getId());
			ps.setBoolean(2,true);

			rs = ps.executeQuery();
			while (rs.next()) {
				ProductBean item = new ProductBean(); 
				item.setId(rs.getInt("_id"));
				item.getMainLang().setTitle(rs.getString("c_title"));
				if (item.getMainLang().getTitle()== null || item.getMainLang().getTitle().equals(""))
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
			ps = getConnection().prepareStatement("UPDATE product SET item_order=ELT(FIELD(_id,"+idList+"),"+orderList+"),update_date=UTC_TIMESTAMP() WHERE _id IN ("+idList+")");
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
	public boolean insert(ProductBean bean, DomainBean domain){
		boolean	res = false;
		PreparedStatement	ps 	= null;
		ResultSet rs = null;
		try{
			getConnection();
			ps = getConnection().prepareStatement("INSERT INTO product(id_menu,img,active,price,item_order,creation_date) " +
																						"SELECT a._id,?,?,?,COUNT(*)+1,UTC_TIMESTAMP() FROM (SELECT _id FROM menu WHERE domain=? AND active=? LIMIT 1) a " +
																						"INNER JOIN product ON product.id_menu=a._id " +
																						"GROUP BY a._id ", PreparedStatement.RETURN_GENERATED_KEYS);
			if (bean.getImg()!=null && bean.getImg().getId()>0)
				ps.setInt(1, bean.getImg().getId());
			else
				ps.setNull(1, Types.INTEGER);
			ps.setBoolean(2, bean.isActive());
			ps.setFloat(3, bean.getPrice());
			ps.setInt(4, domain.getId());
			ps.setBoolean(5, true);
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
	
	
	
	/**
	 * Load Categories
	 * @param bean
	 * @param domain
	 * @param noName
	 */
	public void loadCategories(ProductBean bean, DomainBean domain, String noName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT c._id,d.title " +
										 												"FROM (SELECT main_lang FROM menu WHERE domain=? AND active=? LIMIT 1) a " +
										 												"INNER JOIN product_2_category b ON b.id_product=? " +
										 												"INNER JOIN category c ON b.id_category=c._id AND c.deleted IS NULL " +
										 												"LEFT OUTER JOIN category_2_language d ON c._id=d.id_category AND d.lang=a.main_lang AND d.deleted IS NULL");
			ps.setInt(1, domain.getId());
			ps.setBoolean(2, true);
			ps.setInt(3, bean.getId());
			getData().getLog().info(ps);
			rs = ps.executeQuery();
			while (rs.next()) 
				bean.getCategories().add(rs.getInt("_id"));
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
	}
	
	
	/**
	 * Set categories
	 * @param menu
	 */
	public boolean setCategories(ProductBean product) {	
		if (!delCategories(product.getId()))
			return false;
		if (product.getCategories().isEmpty())
			return true;
		
		boolean res = false;
		PreparedStatement ps = null;
		try {
			String query = "";
			
			for(int i=0; i<product.getCategories().size(); i++)
				query += ",(?,?)";
			query = query.substring(1);
			
			ps = getConnection().prepareStatement("INSERT INTO product_2_category(id_product,id_category) VALUES "+query);
			int cont = 0;
			for(Integer item : product.getCategories()) {
				cont++;
				ps.setInt(cont, product.getId());
				cont++;
				ps.setInt(cont, item);
			}

			res = ps.executeUpdate()>0;
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	
	/**
	 * Del Categories
	 * @param idMenu
	 * @return
	 */
	public boolean delCategories(int idProduct) {	
		boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("DELETE FROM product_2_category WHERE id_product=?");
			ps.setInt(1,idProduct);
			res = ps.executeUpdate()>=0;
			ps.close(); ps = null;
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
}
