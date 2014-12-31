/**
 * TableFilterRange.java
 * 21/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table.filters;

import java.sql.PreparedStatement;
import java.util.List;

import com.oxybay.web.business.system.table.filter.TableFilters;

public abstract class TableFilterRange implements TableFilters {
	
	public static final short RANGEOPERATOR_MIN_MAX 			= 0;
	public static final short RANGEOPERATOR_MIN_MAXEQ 		= 1;
	public static final short RANGEOPERATOR_MINEQ_MAX 		= 2;
	public static final short RANGEOPERATOR_MINEQ_MAXEQ 	= 3;

	/* types of range operator*/
	private short operators = RANGEOPERATOR_MIN_MAX;
	
	/* operator from */
	protected String opFrom = ">";
	/* operator to */
	protected String opTo = "<";
	/* setter flags */
	protected boolean[] setters = null;
	/* setter repeat count */
	protected int setterCount = 0;
	/* function label part 1 */
	protected String funcLabel1 = "";
	/* function label part 2 */
	protected String funcLabel2 = "";
	
	/** from */
	protected double from = 0;
	/** to */
	protected double to = 0;

	/**
	 * @param operators
	 */
	public TableFilterRange(short operators) {
		this.operators = operators;
		this.opFrom = (operators==RANGEOPERATOR_MIN_MAX || operators==RANGEOPERATOR_MIN_MAXEQ) ? ">" : ">=";
		this.opTo = (operators==RANGEOPERATOR_MIN_MAX || operators==RANGEOPERATOR_MINEQ_MAX) ? "<" : "<=";
		this.setters = new boolean[] {false,false};
	}

	/**
	 * @return the operators
	 */
	public short getOperators() {
		return operators;
	}
	
	

	/**
	 * @return the from
	 */
	public double getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(double from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public double getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(double to) {
		this.to = to;
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.filter.TableFilters#generateQuery()
	 */
	@Override
	public String generateQuery(List<String> tableLabels) {
		String query = "";
		String match = "";
		if (from!=0) {
			match = funcLabel1+TableFilters.LABEL_REPLACE+funcLabel2+" "+opFrom+" "+funcLabel1+"?"+funcLabel2;
			setters[0] = true;
		}
		if (to!=0) {
			if (!match.equals(""))
				match += " AND ";
			match += funcLabel1+TableFilters.LABEL_REPLACE+funcLabel2+" "+opTo+" "+funcLabel1+"?"+funcLabel2;
			setters[1] = true;
		}
		
		if (!match.equals("")) {
			for(String fieldLabel : tableLabels) {
				if (!query.equals(""))
					query += " OR ";
				query += "("+match.replace(TableFilters.LABEL_REPLACE, fieldLabel)+")";
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
	public abstract int setPreparedStatement(PreparedStatement ps, int counter) throws Exception;

	
}
