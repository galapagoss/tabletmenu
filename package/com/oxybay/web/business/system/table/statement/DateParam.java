/**
 * DateParam.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import com.oxybay.web.beans.system.table.FieldBean;

public class DateParam extends BaseStatParam {
	
	

	/**
	 * @param field
	 */
	public DateParam(FieldBean field) {
		super(field);
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#psSet(java.sql.PreparedStatement, int, java.lang.Object)
	 */
	@Override
	public void psSetValue(PreparedStatement ps, int count, Object value) throws Exception {
		if (value == null)
			ps.setNull(count, Types.DATE);
		else
			ps.setTimestamp(count, new Timestamp(((Date)value).getTime()));
	}

	/* (non-Javadoc)
	 * @see com.oxybay.web.business.system.table.statement.InterfaceStatParam#rsGet(java.sql.ResultSet, int)
	 */
	@Override
	protected Object rsGetValue(ResultSet rs, int count) throws Exception {
		return rs.getTimestamp(count);
	}

}
