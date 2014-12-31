/**
 * TableFilterRangeDate.java
 * 21/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.beans.system.table.filters;

import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class TableFilterRangeDate extends TableFilterRange {

	public static final short FORMAT_DATE			= 0;
	public static final short FORMAT_TIME			= 1;
	public static final short FORMAT_DATETIME	= 2;
	
	/* format type */
	private short format = FORMAT_DATE;
	
	/**
	 * @param operators
	 */
	public TableFilterRangeDate(short operators, short format) {
		super(operators);
		this.format = format;
		if (format == FORMAT_DATE) {
			funcLabel1 = "DATE(";
			funcLabel2 = ")";
		} else if (format == FORMAT_TIME) {
			funcLabel1 = "TIME(";
			funcLabel2 = ")";
		}
	}
	

	/* (non-Javadoc)
	 * @see com.oxybay.web.beans.system.table.filters.TableFilterRange#setPreparedStatement(java.sql.PreparedStatement, int)
	 */
	@Override
	public int setPreparedStatement(PreparedStatement ps, int counter) throws Exception {
		
		for(int i=0;i<setterCount;i++) {
			if (setters[0]) {
				counter++;
				ps.setTimestamp(counter, new Timestamp((long)from));
			}
			if (setters[1]) {
				counter++;
				ps.setTimestamp(counter, new Timestamp((long)to));
			}
		}
		return counter;
	}



	/**
	 * @return the format
	 */
	public short getFormat() {
		return format;
	}

	
}
