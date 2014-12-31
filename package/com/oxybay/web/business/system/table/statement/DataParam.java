/**
 * DataParam.java
 * 18/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.statement;

import java.io.File;
import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;

import com.oxybay.web.beans.system.table.FieldBean;

public class DataParam {

	public static BaseStatParam getInterfaceParam(FieldBean field, boolean onlyStandard) throws Exception {
		Class<?> className = field.getField().getType();
		// String
		if (className==String.class)
			return new StringParam(field);
		// Integer
		else if (className==int.class || className==Integer.class)
			return new IntParam(field);
		// Boolean 
		else if (className==boolean.class || className==Boolean.class)
			return new BooleanParam(field);
		// Date
		else if (className==Date.class)
			return new DateParam(field);
		// Long
		else if (className==long.class || className==Long.class)
			return new LongParam(field);
		// Float
		else if (className==float.class || className==Float.class)
			return new FloatParam(field);
		// Double
		else if (className==double.class || className==Double.class)
			return new DoubleParam(field);
		// Time
		else if (className==Time.class)
			return new TimeParam(field);
		// TimeZone
		else if (className==TimeZone.class)
			return new TimeZoneParam(field);
		// File
		else if (className==File.class)
			return new FileParam(field);
		
		if(onlyStandard)
			return null;
		else
			return new ObjectParam(field);
	}
}
