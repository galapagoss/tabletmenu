/**
 * TableDef.java
 * 17/apr/2012
 * @author Gaetano
 * 
 * Define the name of table matched
 */

package com.oxybay.web.business.system.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TableDef {

	/* table name */
	public abstract String table();
	/* default order */
	public abstract int order();
}
