/**
 * TableRef.java
 * 17/apr/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.table.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface TableRef {

	/* table name to reference */
	public abstract String value();
}
