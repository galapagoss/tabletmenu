/**
 * TableFilterRange.java
 * 21/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table.filters;

import java.sql.PreparedStatement;

public class TableFilterRangeNum extends TableFilterRange {
	
	/**
	 * Constructor
	 */
	public TableFilterRangeNum(short operators) { 
		super(operators);
	}
	
	
	/* (non-Javadoc)
	 * @see com.oxybay.web.beans.system.table.filters.TableFilterRange#setPreparedStatement(java.sql.PreparedStatement, int)
	 */
	@Override
	public int setPreparedStatement(PreparedStatement ps, int counter) throws Exception {
		for(int i=0;i<setterCount;i++) {
			if (setters[0]) {
				counter++;
				ps.setDouble(counter, from);
			}
			if (setters[1]) {
				counter++;
				ps.setDouble(counter, to);
			}
		}
		return counter;
	}

}
