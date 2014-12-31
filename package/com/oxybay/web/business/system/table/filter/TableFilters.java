/**
 * TableFilters.java
 * 21/giu/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.filter;

import java.sql.PreparedStatement;
import java.util.List;

public interface TableFilters {
	
	public static final String LABEL_REPLACE = "[LABEL]";

	/* generate query filter */
	public abstract String generateQuery(List<String> tableLabels);
	
	/* set prepared statement */
	public abstract int setPreparedStatement(PreparedStatement ps, int counter) throws Exception;
}
