/**
 * TableOrder.java
 * 26/giu/2012
 * @author Gaetano
 * 
 * Define the order list in the DB table
 */

package com.oxybay.web.business.system.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableOrder {

	public static final String TYPE_ASC  = "ASC";
	public static final String TYPE_DESC = "DESC";
	
	/* order id */
	public abstract int id();
	/* order type */
	public abstract String type(); 
	/* null exclude value */
	public abstract String exclude();
}
