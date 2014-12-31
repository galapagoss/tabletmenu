/**
 * DiscountBsn.java
 * 12/set/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.oxybay.web.beans.menu.discount.DiscountBean;
import com.oxybay.web.beans.menu.discount.DiscountContainerBean;
import com.oxybay.web.beans.menu.discount.DiscountBean.DiscountType;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class DiscountBsn extends RootBsn {
	
	/**
	 * @param data
	 */
	public DiscountBsn(BsnDataInfo data) {
		super(data);
	}

	/**
	 * Get Discounts
	 * @param idElem
	 * @param type
	 * @param onlyActive
	 * @return
	 */
	public List<DiscountBean> getDiscounts(int idElem, DiscountType type, boolean onlyActive) {
		List<DiscountBean> res = new ArrayList<DiscountBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT b.id,b.recurring,b.fromtime,b.totime,IFNULL(b.fixed_price,-1)as fixed_price,b.perc_discount,b.active " +
																						"FROM "+type.table+" a " +
																						"INNER JOIN discount_policy b ON a.id_discount=b.id " +
																						"WHERE a."+type.field+"=? AND (? OR b.active=?) " +
																						"ORDER BY b.priority_order ");
			
			ps.setInt(1, idElem);
			ps.setBoolean(2, !onlyActive);
			ps.setBoolean(3, true);
			rs = ps.executeQuery();
			while (rs.next()) 
				res.add(new DiscountBean(rs.getLong("id"), rs.getByte("recurring"), rs.getTime("fromtime"), rs.getTime("totime"), rs.getFloat("fixed_price"), rs.getInt("perc_discount"), rs.getBoolean("active")));
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	
	
	/**
	 * Get All Inherit Discounts
	 * @param idDomain
	 * @param idProduct
	 * @param noNameCategory
	 * @param onlyActive
	 * @return
	 */
	public List<DiscountBean> getAllInheritDiscounts(int idDomain, int idProduct, String noNameCategory, boolean onlyActive) {
		List<DiscountBean> res = new ArrayList<DiscountBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT c.id,c.recurring,c.fromtime,c.totime,c.fixed_price,c.perc_discount,c.active,b.type_discount,IF(e.label IS NOT NULL AND d.title IS NOT NULL,CONCAT(d.title,' | ',e.label),IFNULL(d.title,e.label))as label " +
																						"FROM (SELECT _id,main_lang FROM menu WHERE domain=? AND active=? LIMIT 1) a " +
																						"INNER JOIN view_inherit_discounts b ON a._id=b.id_menu " +
																						"INNER JOIN discount_policy c ON b.id_discount=c.id " +
																						"LEFT OUTER JOIN tag e ON b.type_discount=? AND b.id_ref=e.id " +
																						"LEFT OUTER JOIN category_2_language d ON ((b.type_discount=? AND b.id_ref=d.id_category) OR (b.typetag=? AND b.id_ref2=d.id_category)) AND d.lang=a.main_lang " +
																						"WHERE b.id_product=? AND (? OR c.active=?) " +
																						"ORDER BY b.type_discount DESC,b.typetag DESC,c.priority_order");
			ps.setInt(1, idDomain);
			ps.setBoolean(2, true);
			ps.setString(3, DiscountBean.SQL_DISCOUNT_TAG);
			ps.setString(4, DiscountBean.SQL_DISCOUNT_CATEGORY);
			ps.setString(5, DiscountBean.SQL_DISCOUNT_TAG_CATEGORY);
			ps.setInt(6, idProduct);
			ps.setBoolean(7, !onlyActive);
			ps.setBoolean(8, true);
			rs = ps.executeQuery();
			while (rs.next()) 
				res.add(new DiscountBean(rs.getLong("id"), rs.getByte("recurring"), rs.getTime("fromtime"), rs.getTime("totime"), rs.getFloat("fixed_price"), rs.getInt("perc_discount"), rs.getBoolean("active"), DiscountBean.getTypeFromSql(rs.getString("b.type_discount")), rs.getString("label")));
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	
	
	/**
	 * Update Discount Policies
	 * @param idElem
	 * @param container
	 * @param type
	 * @return
	 */
	public boolean updateDiscounts(int idElem, DiscountContainerBean container, DiscountType type) {
		if (container.isChanged()) {
			//delete old values
			if (!deleteAllDiscounts(idElem, type))
				return false;
			if (container.getPolicies().size()==0)
				return true;
			// create new policies and obtain ids
			int[] newIds = createNewPolicies(container.getPolicies());
			if (newIds!=null)
				return assignNewPolicies(idElem, newIds, type);
			return false;
		}
		return true;
	}
	
	
	/**
	 * Delete all discounts associated
	 * @param idElem
	 * @param type
	 * @return
	 */
	public boolean deleteAllDiscounts(int idElem, DiscountType type) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("DELETE FROM discount_policy WHERE id IN (SELECT id_discount FROM "+type.table+" WHERE "+type.field+"=?) ");
			ps.setInt(1, idElem);
			return ps.executeUpdate()>=0;
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return false;
	}
	
	/**
	 * Create New Policies
	 * @param policies
	 * @return
	 */
	private int[] createNewPolicies(List<DiscountBean> policies) {
		if (policies==null || policies.size()==0)
			return null;
		int[] res = new int[policies.size()];
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String qry = "";
			for(int i=0; i<policies.size(); i++) {
				if (i>0)
					qry += ",";
				qry += "(?,?,?,?,?,?,?)";
			}
			ps = getConnection().prepareStatement("INSERT INTO discount_policy (recurring,fromTime,toTime,fixed_price,perc_discount,active,priority_order) VALUES " +qry, PreparedStatement.RETURN_GENERATED_KEYS);
			int cont=0;
			int fields = 7;
			for(DiscountBean item : policies) {
				ps.setByte(cont*fields+1, item.getRecurring());
				ps.setTime(cont*fields+2, item.getFromTime());
				ps.setTime(cont*fields+3, item.getToTime());
				ps.setFloat(cont*fields+4, item.getFixedPrice());
				ps.setInt(cont*fields+5, item.getDiscountPrice());
				ps.setBoolean(cont*fields+6, item.isActive());
				ps.setInt(cont*fields+7, cont+1);
				cont++;
			}
			if (ps.executeUpdate()>=0) {
				rs = ps.getGeneratedKeys();
				cont = 0;
				while (rs.next()) {
					res[cont] = rs.getInt(1); 
					cont++;
				} 
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
	 * Assign new Policies
	 * @param idElem
	 * @param ids
	 * @param type
	 * @return
	 */
	private boolean assignNewPolicies(int idElem, int[] ids, DiscountType type) {
		PreparedStatement ps = null;
		try {
			String qry = "";
			for(int i=0; i<ids.length; i++) {
				if (i>0)
					qry += ",";
				qry += "(?,?)";
			}
			ps = getConnection().prepareStatement("INSERT INTO "+type.table+" ("+type.field+",id_discount) VALUES " +qry);
			int cont=0;
			int fields = 2;
			for(int id : ids) {
				ps.setInt(cont*fields+1, idElem);
				ps.setInt(cont*fields+2, id);
				cont++;
			}
			return (ps.executeUpdate()>=0);
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return false;
	}
	
}
