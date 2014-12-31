/**
 * TableField.java
 * 17/apr/2012
 * @author Gaetano
 * 
 * Define the field name in the DB table, if omitted use the same name of parameter
 */

package com.oxybay.web.business.system.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableUpdate {

	public static final short NONE 						= 0;
	public static final short EMPTYZERO_NULL 	= 1;
	
	/* field value */
	public abstract short value();
}
