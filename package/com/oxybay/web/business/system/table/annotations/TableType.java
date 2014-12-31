/**
 * TableType.java
 * 17/apr/2012
 * @author Gaetano
 * 
 * Define the table field type:
 * 
 *  SKIP : Don't process it
 *  KEY  : Key Field, support only one key
 *  MD5  : Use MD5 encription for the value
 *  CREATING,UPDATING,DELETING : automatic datetime for operations
 *  
 */

package com.oxybay.web.business.system.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableType {
	
	public static final short SKIP 				= 0;
	public static final short KEY 				= 1;
	public static final short MD5 				= 2;
	public static final short CREATING 		= 3;
	public static final short UPDATING 		= 4;
	public static final short DELETING 		= 5;
	
	/* field type */
	public abstract short value();
}
