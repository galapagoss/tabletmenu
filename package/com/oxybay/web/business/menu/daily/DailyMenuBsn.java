package com.oxybay.web.business.menu.daily;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.oxybay.web.beans.menu.daily.DailyMenuBean;
import com.oxybay.web.beans.menu.daily.DailyMenuFilter;
import com.oxybay.web.beans.menu.daily.DailyMenuItem;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class DailyMenuBsn extends RootBsn {

	/**
	 * @param data
	 */
	public DailyMenuBsn(BsnDataInfo data) {
		super(data);
	}


	/**
	 * Load DailyMenu
	 * @param filter
	 */
	public void list(DailyMenuFilter filter, DomainBean domain) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT a._id,a.active,a.price,a.week_day,a.day_type,a.creation_date,a.update_date " +
										 												"FROM daily_menu a " +
										 												"WHERE a.domain=? " +
										 												"ORDER BY a.day_type,a.week_day ASC ");
			int counter = 1;
			ps.setInt(counter, domain.getId());
			filter.getLabel().setPreparedStatement(ps, counter);

			rs = ps.executeQuery();
			List<Object> res = new ArrayList<Object>();
			int count = 0;
			while (rs.next()) {
				count++;
				DailyMenuBean item = new DailyMenuBean();
				item.setId(rs.getInt("_id"));
				item.setWeekDay(rs.getInt("week_day"));
				item.setDayType(rs.getInt("day_type"));
				item.setCreationDate(rs.getTimestamp("creation_date"));
				item.setUpdateDate(rs.getTimestamp("update_date"));
				item.setActive(rs.getBoolean("active"));
				item.setPrice(rs.getFloat("price"));
				res.add(item);
			}
			
			filter.setItemsForPage(count);
			filter.setItems(res);
			filter.setTotal(count);
			
			if (filter.getItems().size() == 0) {
				init(domain);
				
				list(filter, domain);
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
	}
	
	/**
	 * 
	 * @param domain
	 * @param dailyMenuId
	 * @param noName
	 * @return
	 */
	public ArrayList<DailyMenuItem> getMenuItems(DomainBean domain, int dailyMenuId, String noName) {
		getData().getLog().info("getMenuItems."+dailyMenuId);
		ArrayList<DailyMenuItem> res = new ArrayList<DailyMenuItem>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// products
			ps = getConnection().prepareStatement("SELECT b._id, c.title, d.item_order, b.active " +
																						"FROM (SELECT a1._id,a1.main_lang,COUNT(*)as menulangs FROM menu a1 LEFT OUTER JOIN menu_2_language b1 ON a1._id=b1.id_menu AND b1.deleted IS NULL WHERE a1.domain=? AND a1.active=? GROUP BY a1._id,a1.main_lang LIMIT 1) a " +
																						"INNER JOIN product b ON a._id=b.id_menu AND b.deleted IS NULL " +
										 												"INNER JOIN product_2_daily_menu d ON b._id=d.product_id " +
										 												"LEFT OUTER JOIN menu_2_language ml ON ml.id_menu=a._id AND m1.deleted IS NULL " +
										 												"LEFT OUTER JOIN product_2_language c ON b._id=c.id_product AND c.lang=a.main_lang AND c.deleted IS NULL " +
										 												"WHERE d.daily_menu=? " +
										 												"GROUP BY b._id, c.title, d.item_order " +
										 												"ORDER BY d.item_order");
			ps.setInt(1, domain.getId());
			ps.setBoolean(2,true);
			ps.setInt(3, dailyMenuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				if (title.equals(""))
					title = noName;
				
				DailyMenuItem item = new DailyMenuItem(DailyMenuItem.ItemType.PRODUCT, rs.getInt("_id"), rs.getBoolean("active"), title, rs.getInt("item_order"));
				res.add(item);
			}
			ps.close(); ps = null;
			rs.close(); rs = null;
			
			// categories
			ps = getConnection().prepareStatement("SELECT b._id,c.title, d.item_order, b.active " +
																						"FROM (SELECT _id,main_lang FROM menu WHERE domain=? AND active=? LIMIT 1) a " +
																						"INNER JOIN category b ON a._id=b.id_menu AND b.deleted IS NULL " +
																						"INNER JOIN category_2_daily_menu d ON b._id=d.category_id " +
																						"LEFT OUTER JOIN category_2_language c ON b._id=c.id_category AND c.lang=a.main_lang AND c.deleted IS NULL " +
																						"WHERE d.daily_menu=? " +
																						"ORDER BY d.item_order ");
			ps.setInt(1, domain.getId());
			ps.setBoolean(2,true);
			ps.setInt(3, dailyMenuId);
			getData().getLog().info(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				if (title.equals(""))
					title = noName;
				
				DailyMenuItem item = new DailyMenuItem(DailyMenuItem.ItemType.CATEGORY, rs.getInt("_id"), rs.getBoolean("active"), title, rs.getInt("item_order"));
				res.add(item);
			}
			
			ps.close(); ps = null;
			rs.close(); rs = null;
					
			// tags
			ps = getConnection().prepareStatement("SELECT b.id, b.label, a.item_order " +
																						"FROM  tag_2_daily_menu a " +
																						"INNER JOIN tag b ON a.tag_id=b.id " +
																						"WHERE a.daily_menu=? " +
																						"ORDER BY a.item_order");
			ps.setInt(1, dailyMenuId);
			rs = ps.executeQuery();
			while (rs.next()) {
				DailyMenuItem item = new DailyMenuItem(DailyMenuItem.ItemType.TAG, rs.getInt("id"), true, rs.getString("label"), rs.getInt("item_order"));
				res.add(item);
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		Collections.sort(res, new Comparator<DailyMenuItem>() {
			@Override
			public int compare(DailyMenuItem o1, DailyMenuItem o2) {
				return o1.getOrder() - o2.getOrder();
			}
		});
		return res;
	}
	
	/**
	 * Init menu
	 * @param domain
	 */
	private void init(DomainBean domain) {
		PreparedStatement ps = null;
		try {
			String qry = "";
			for(int day=0; day<7; day++)
				for(int t=0; t<2; t++) {
					if (!qry.equals(""))
						qry += ",";
					qry += "("+domain.getId()+",UTC_TIMESTAMP(),"+day+","+t+") ";
				}
			ps = getConnection().prepareStatement("INSERT INTO daily_menu (domain,creation_date,week_day,day_type) VALUES " + qry);
			getData().getLog().info(ps);
			if(ps.executeUpdate()!=14) {
				getData().getLog().error("Unexpected daily_menu init. Record inserted <> 14.");
			}
			ps.close();
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
	}
	
	public boolean updateMenuItems(int dailyMenuId, List<DailyMenuItem> items) {
		PreparedStatement ps = null;
		try {
			// delete old records. category + products
			ps = getConnection().prepareStatement("DELETE FROM category_2_daily_menu WHERE daily_menu = ?");
			ps.setInt(1, dailyMenuId);
			ps.executeUpdate();
			ps.close(); ps = null;
			
			ps = getConnection().prepareStatement("DELETE FROM product_2_daily_menu WHERE daily_menu = ?");
			ps.setInt(1, dailyMenuId);
			ps.executeUpdate();
			ps.close(); ps = null;
					
			ps = getConnection().prepareStatement("DELETE FROM tag_2_daily_menu WHERE daily_menu = ?");
			ps.setInt(1, dailyMenuId);
			ps.executeUpdate();
			
			// separate items. prodcuts, category and tags
			ArrayList<DailyMenuItem> products = new ArrayList<DailyMenuItem>();
			ArrayList<DailyMenuItem> category = new ArrayList<DailyMenuItem>();
			ArrayList<DailyMenuItem> tags     = new ArrayList<DailyMenuItem>();
			int order = 0;
			for (DailyMenuItem dailyMenuItem : items) {
				order++;
				dailyMenuItem.setOrder(order);
				if (dailyMenuItem.getType() == DailyMenuItem.ItemType.PRODUCT) {
					products.add(dailyMenuItem);
				} else if (dailyMenuItem.getType() == DailyMenuItem.ItemType.CATEGORY) {
					category.add(dailyMenuItem);
				} else if (dailyMenuItem.getType() == DailyMenuItem.ItemType.TAG) {
					tags.add(dailyMenuItem);
				}
			}
			
			// insert new records
			if (products.size() > 0) {
				String query = "INSERT INTO product_2_daily_menu " +
											 "(daily_menu,product_id,item_order) VALUES ";
				int i = 0;
				for (DailyMenuItem product : products) {
					if (i > 0) {
						query += ",";
					}
					i++;
					query += "("+dailyMenuId+","+product.getId()+","+product.getOrder()+")";
				}
				ps = getConnection().prepareStatement(query);
				ps.executeUpdate();
			}
			
			if (category.size() > 0) {
				String query = "INSERT INTO category_2_daily_menu " +
						 					 "(daily_menu,category_id,item_order) VALUES ";
				int i = 0;
				for (DailyMenuItem cat : category) {
					if (i > 0) {
						query += ",";
					}
					i++;
					query += "("+dailyMenuId+","+cat.getId()+","+cat.getOrder()+")";
				}
				ps = getConnection().prepareStatement(query);
				ps.executeUpdate();
			}
			
			if (tags.size() > 0) {
				String query = "INSERT INTO tag_2_daily_menu " +
						 					 "(daily_menu,tag_id,item_order) VALUES ";
				int i = 0;
				for (DailyMenuItem tag : tags) {
					if (i > 0) {
						query += ",";
					}
					i++;
					query += "("+dailyMenuId+","+tag.getId()+","+tag.getOrder()+")";
				}
				ps = getConnection().prepareStatement(query);
				ps.executeUpdate();
			}
			
			ps.close();
		} catch(Exception ex) {
			traceQueryError(ps,ex);
			return false;
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return true;
	}
	
}