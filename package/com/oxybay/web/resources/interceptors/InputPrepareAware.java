/**
 * InputPrepareAware.java
 * 06/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.interceptors;

public interface InputPrepareAware {

	/* if inputPrepare method must be invoke */
	public abstract boolean checkInputPrepare();
	/* method to invoke before INPUT result (useful after validation) */
	public abstract void inputPrepare() throws Exception;
}
