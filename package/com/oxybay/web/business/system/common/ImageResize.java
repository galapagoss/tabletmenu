/**
 * ImageResize.java
 * 17/jul/2012
 * @author Gaetano
 * 
 * Specific to resize an image
 */

package com.oxybay.web.business.system.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ImageResize {

	/* width */
	public abstract int w();
	/* height */
	public abstract int h();
	/* crop */
	public abstract boolean crop();
}
