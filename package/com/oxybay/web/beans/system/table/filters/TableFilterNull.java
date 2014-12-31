/**
 * TableFilterNull.java
 * 02/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table.filters;

import java.sql.PreparedStatement;
import java.util.List;

import com.oxybay.web.business.system.table.filter.TableFilters;

public class TableFilterNull implements TableFilters {
	
	/* value */
	private boolean value = false;
	/* skip flag */
	private boolean skip = false;
	
	/**
	 * Constructor
	 */
	public TableFilterNull() {	}
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.filter.TableFilters#generateQuery(java.lang.String)
	 */
	@Override
	public String generateQuery(List<String> tableLabels) {
		String query = "";
		if (!skip) {
			for(String fieldLabel : tableLabels) {
				if (!query.equals(""))
					query += " OR ";
				query += value ? fieldLabel+" IS NULL" : fieldLabel+" IS NOT NULL";
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
		return counter;
	}

	/**
	 * @return the value
	 */
	public boolean isValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(boolean value) {
		this.value = value;
	}

	/**
	 * @return the skip
	 */
	public boolean isSkip() {
		return skip;
	}

	/**
	 * @param skip the skip to set
	 */
	public void setSkip(boolean skip) {
		this.skip = skip;
	}

	
}
