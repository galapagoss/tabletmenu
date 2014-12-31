/**
 * TableFilterLike.java
 * 21/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table.filters;

import java.sql.PreparedStatement;
import java.util.List;

import com.oxybay.web.business.system.table.filter.TableFilters;

public class TableFilterString implements TableFilters {
	
	public static final short FORMAT_EQUAL					= 0;
	public static final short FORMAT_LIKE_PERC 			= 1;
	public static final short FORMAT_PERC_LIKE_PERC = 2;
	
	/* like format */
	private short matchFormat = FORMAT_EQUAL;
	/* search string */
	private String search = "";
	/* setter repeat count */
	protected int setterCount = 0;
	
	/**
	 * Constructor
	 */
	public TableFilterString() {	}
	/**
	 * Constructor
	 * @param matchFormat
	 */
	public TableFilterString(short matchFormat) {
		this.matchFormat = matchFormat;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.filter.TableFilters#generateQuery(java.lang.String)
	 */
	@Override
	public String generateQuery(List<String> tableLabels) {
		String query = "";
		String match = "";
		setterCount = 0;
		if (search!=null && !search.equals("")) {
			if (matchFormat == FORMAT_EQUAL)
				match = TableFilters.LABEL_REPLACE+"=?" ;
			else 
				match = TableFilters.LABEL_REPLACE+" LIKE ?" ;
		}
		
		if (!match.equals("")) {
			for(String fieldLabel : tableLabels) {
				if (!query.equals(""))
					query += " OR ";
				query += match.replace(TableFilters.LABEL_REPLACE, fieldLabel);
				setterCount++;
			}
			query = " AND ("+query+")";
		}
		return query;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.filter.TableFilters#setPreparedStatement(java.sql.PreparedStatement, int)
	 */
	@Override
	public int setPreparedStatement(PreparedStatement ps, int counter) throws Exception {
		if (search!=null && !search.equals("")) {
			String match = "%"+search+"%";
			if (matchFormat == FORMAT_EQUAL)
				match = search;
			else if (matchFormat == FORMAT_LIKE_PERC)
				match = search+"%";
			
			for(int i=0; i<setterCount;i++) {
				counter++;
				ps.setString(counter, match);
			}
		}
			
		return counter;
	}

	/**
	 * @return the search
	 */
	public String getSearch() {
		return search;
	}

	/**
	 * @param search the search to set
	 */
	public void setSearch(String search) {
		this.search = search;
	}

	
}
