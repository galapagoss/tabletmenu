/**
 * TagBsn.java
 * 11/set/2012
 * @author Gaetano
 */

package com.oxybay.web.business.menu;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.oxybay.web.beans.menu.common.TagBean;
import com.oxybay.web.beans.menu.tag.TagBeanFilter;
import com.oxybay.web.beans.menu.tag.TagStatBean;
import com.oxybay.web.beans.profile.DomainBean;
import com.oxybay.web.beans.system.resources.BsnDataInfo;
import com.oxybay.web.business.system.root.RootBsn;

public class TagBsn extends RootBsn {
	
	public enum TagType { PRODUCT("product_2_tag","id_product"),CATEGORY("category_2_tag","id_category");
	 private String table;
	 private String field;
	 private TagType(String t, String f) { table=t; field=f;}
	}

	/**
	 * @param data
	 */
	public TagBsn(BsnDataInfo data) {
		super(data);
	}

	/**
	 * Get All Tags
	 * @param idDomain
	 * @return
	 */
	public List<TagBean> getAllTags(int idDomain) {
		List<TagBean> res = new ArrayList<TagBean>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT id,label FROM tag WHERE id_domain=? ORDER BY label");
			ps.setInt(1, idDomain);
			rs = ps.executeQuery();
			while (rs.next()) 
				res.add(new TagBean(rs.getInt("id"), rs.getString("label")));
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Get Tags
	 * @param idElem
	 * @param idDomain
	 * @param type
	 * @return
	 */
	public Set<Integer> getTags(int idElem, int idDomain, TagType type) {
		Set<Integer> res = new HashSet<Integer>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = getConnection().prepareStatement("SELECT b.id " +
																						"FROM "+type.table+" a " +
																						"INNER JOIN tag b ON a.id_tag=b.id  " +
																						"WHERE a."+type.field+"=? AND b.id_domain=? ");
			ps.setInt(1, idElem);
			ps.setInt(2, idDomain);
			rs = ps.executeQuery();
			while (rs.next()) 
				res.add(rs.getInt("id"));
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Create New Tags and return ids
	 * @param tags
	 * @param idDomain
	 * @return
	 */
	private Set<Integer> createNewTags(String tags, int idDomain) {
		Set<Integer> res = new HashSet<Integer>();
		if (tags==null || tags.trim().equals(""))
			return res;
		String[] tagList = tags.split(",");
		if (tagList.length==0)
			return res;
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String qry = "";
			for(int i=0; i<tagList.length; i++) {
				if (i>0)
					qry += ",";
				qry += "(?,?)";
			}
			ps = getConnection().prepareStatement("INSERT INTO tag (label,id_domain) VALUES " +qry, PreparedStatement.RETURN_GENERATED_KEYS);
			int cont=0;
			for(String val : tagList) {
				ps.setString(cont*2+1, val);
				ps.setInt(cont*2+2, idDomain);
				cont++;
			}
			if (ps.executeUpdate()>=0) {
				rs = ps.getGeneratedKeys();
				while (rs.next()) 
					res.add(rs.getInt(1));
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
	 * Delete tags reference
	 * @param idElem
	 * @param type
	 * @return
	 */
	private boolean delTags(int idElem, TagType type) {
		boolean res = false;
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement("DELETE FROM "+type.table+" WHERE "+type.field+"=?");
			ps.setInt(1, idElem);
			res = ps.executeUpdate()>0;
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	/**
	 * Update tag reference of an object
	 * @param idElem
	 * @param idDomain
	 * @param type
	 * @param tagSet
	 * @param newTags
	 * @return
	 */
	public boolean updateTagsRef(int idElem, int idDomain, TagType type, Set<Integer> tagSet, String newTags) {
		// Create new tags and merge them
		tagSet.addAll(createNewTags(newTags, idDomain));
		// Delete previous tags
		delTags(idElem, type);
		
		if (tagSet.size()==0)
			return true;
		
		boolean res = false;
		PreparedStatement ps = null;
		try {
			String qry = "";
			for(int i=0; i<tagSet.size(); i++) {
				if (i>0)
					qry += ",";
				qry += "(?,?)";
			}
			ps = getConnection().prepareStatement("INSERT INTO "+type.table+" (id_tag,"+type.field+") VALUES " +qry);
			int cont=0;
			for(Integer i: tagSet) {
				ps.setInt(cont*2+1, i.intValue());
				ps.setInt(cont*2+2, idElem);
				cont++;
			}
			res = ps.executeUpdate()>0;
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return res;
	}
	
	public void list(TagBeanFilter filter, DomainBean domain, String noName) {
		List<Object> res = new ArrayList<Object>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String filterSearch = "";
			if (filter.getLabel()!=null)
				filterSearch = filter.getLabel().generateQuery(Arrays.asList(new String[]{"a1.label"}));
			
			String query = "SELECT a1.id, a1.label, a1.cate, a2.prod, a3.daily, (cate+prod+daily) AS tot " +
										  "FROM " +
										  	"(SELECT a.id, a.label, COUNT( b.id_category ) AS cate " +
										  	"FROM tag a " +
										  	"LEFT JOIN category_2_tag b ON a.id = b.id_tag " + 
										  	"WHERE a.id_domain=? " +
										  	"GROUP BY a.id " +
										  	") a1 " +
										  "INNER JOIN " +
										  	"(SELECT a.id, a.label,COUNT( c.id_product ) AS prod " +
										  	"FROM tag a " + 
										  	"LEFT JOIN product_2_tag c ON a.id = c.id_tag " +
										  	"WHERE a.id_domain=? " +
										  	"GROUP BY a.id " +
										  	") a2 ON a1.id = a2.id " +
										  "INNER JOIN " +
										  	"(SELECT a.id, a.label,COUNT( d.daily_menu ) AS daily " +
										  	"FROM tag a " + 
										  	"LEFT JOIN tag_2_daily_menu d ON a.id = d.tag_id " +
										  	"WHERE a.id_domain=? " +
										  	"GROUP BY a.id " +
										  	") a3 ON a1.id = a3.id " +
										  "WHERE 1=1 " + filterSearch +" " +
										  "ORDER BY tot DESC, label ASC " +
										  "LIMIT "+filter.calculateQueryRange();
			ps = getConnection().prepareStatement(query);
			ps.setInt(1, domain.getId());
			ps.setInt(2, domain.getId());
			ps.setInt(3, domain.getId());
			filter.getLabel().setPreparedStatement(ps, 3);
			
			rs = ps.executeQuery();
			while (rs.next()) 
				res.add(new TagStatBean(rs.getInt("id"), rs.getString("label"), rs.getInt("cate"), rs.getInt("prod"), rs.getInt("daily"), rs.getInt("tot")));
			
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
	 * Merge some 2 or more tags into 1.
	 * @param tagID tag ids to be merged
	 * @param newTagName Label of the new tag result of the merge
	 * @param newTagId pass id of an existing tag to be used as new tag. Pass 0 if a new tag should be created.
	 * @param idDomain
	 */
	public void merge(String[] tagID, String newTagName, int newTagId, int idDomain) {
		PreparedStatement ps = null;
		
		try {
			String inIds = "";
			int i = 0;
			for (String string : tagID) {
				i++;
				if (i > 1) {
					inIds += ",";
				}
				
				inIds += string;
			}
			
			if (newTagId <= 0) {
				// create new tag
				Set<Integer> newIDs = createNewTags(newTagName, idDomain);
				if (newIDs.size() == 1) {
					for (Integer integer : newIDs) {
						newTagId = integer;
					}
				}
			}

			if (newTagId > 0) {
				// update tag ids to be merged, to new tag id
				String query = "INSERT INTO category_2_tag (id_category,id_tag) " +
											 "SELECT DISTINCT id_category,? FROM category_2_tag WHERE id_tag IN ("+inIds+") " +
											 "ON DUPLICATE KEY UPDATE id_tag=?";
				ps = getConnection().prepareStatement(query);
				ps.setInt(1, newTagId);
				ps.setInt(2, newTagId);
				ps.executeUpdate();
				ps.close(); ps = null;
				
				query = "DELETE FROM category_2_tag WHERE id_tag IN ("+inIds+")";
				ps = getConnection().prepareStatement(query);
				ps.executeUpdate();
				ps.close(); ps = null;
				
				query = "INSERT INTO product_2_tag (id_product,id_tag) " +
								"SELECT DISTINCT id_product,? FROM product_2_tag WHERE id_tag IN ("+inIds+") " +
								"ON DUPLICATE KEY UPDATE id_tag=?";
				ps = getConnection().prepareStatement(query);
				ps.setInt(1, newTagId);
				ps.setInt(2, newTagId);
				ps.executeUpdate();
				ps.close(); ps = null;
				
				query = "DELETE FROM product_2_tag WHERE id_tag IN ("+inIds+")";
				ps = getConnection().prepareStatement(query);
				ps.executeUpdate();
				ps.close(); ps = null;
				
				query = "INSERT INTO tag_2_daily_menu (daily_menu,tag_id,item_order) " +
								"SELECT DISTINCT daily_menu,?,item_order FROM tag_2_daily_menu WHERE tag_id IN ("+inIds+")";
				ps = getConnection().prepareStatement(query);
				ps.setInt(1, newTagId);
				ps.executeUpdate();
				ps.close(); ps = null;
				
				query = "DELETE FROM tag_2_daily_menu WHERE tag_id IN ("+inIds+")";
				ps = getConnection().prepareStatement(query);
				ps.executeUpdate();
				
				// delete old tags
				query = "DELETE FROM tag WHERE id IN ("+inIds+")";
				ps = getConnection().prepareStatement(query);
				ps.executeUpdate();
				
			}
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		
		
	}
	
	public TagBean getTag(String label) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		TagBean tag = null;
		try {
			ps = getConnection().prepareStatement("SELECT id,id_domain " +
																						"FROM tag " +
																						"WHERE label=? ");
			ps.setString(1, label);
			rs = ps.executeQuery();
			while (rs.next()) {
				tag = new TagBean(rs.getInt("id"), label);
			}
			
		} catch(Exception ex) {
			traceQueryError(ps,ex);
		} finally {
			try { if (rs != null) { rs.close(); rs = null; } } catch(Exception e) {}
			try { if (ps != null) { ps.close(); ps = null; } } catch(Exception e) {}
		}
		return tag;
	}
	
}
