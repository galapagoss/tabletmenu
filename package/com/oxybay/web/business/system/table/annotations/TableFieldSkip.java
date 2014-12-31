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
public @interface TableFieldSkip {

	/* skip read */
	public abstract boolean read();
	/* skip insert */
	public abstract boolean insert();
	/* skip update */
	public abstract boolean update();
}
