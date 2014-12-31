/**
 * APIKeys.java
 * 28/ago/2012
 * @author Gaetano
 */

package com.oxybay.web.resources.keys;

public class APIKeys {

	/* Keys */
	public static final String 				PREFIX_KEY																= "iu38DJ&h!aZ56";
	public static final String 				BIND_STRING_SEPARATOR 										= "-";
	public static final String 				CUSTOMDATA_STRING_SEPARATOR 							= "|";
	
	/* result */
	public static final long 					MD5CHECK_PASSED 													= -1;	
	
	/* status */
	public static enum APIStatus { 		ERROR(0), OK(1);
			private int code;
			private APIStatus(int c) { code = c;}
			public int getCode() {return code; }
	}
	
	
	/* error codes */
	public static enum APIErrors { 		NOTHING(0),
																		EXCEPTION(1), 
																		DEVICE_UNKNOWN(2),
																		HASH(3),
																		PARAMS_INCORRECT(4),
																		NOTFOUND(5);
		 private int code;
		 private APIErrors(int c) { code = c;}
		 public int getCode() {return code; } 
	}
	
	/* Actions type */
	public enum Actions { UNDEFINED(0),BIND(1), CHECKUPDATE(2), UPDATECOMPLETE(3), UNBIND(4), DOWNLOAD(5);
		 private int code;
		 private Actions(int c) { code = c;}
		 public int getCode() {return code; } 
	}
	
	
	
	/* xml generation */
	public static final String 				XML_VAR_CHARSET														= "[CHARSET]";
	public static final String 				XML_HEADER																= "<?xml version=\"1.0\" encoding=\""+XML_VAR_CHARSET+"\" standalone=\"yes\"?>";
	public static final String 				XML_ITEM_RESULT_OPEN											= "<result>";
	public static final String 				XML_ITEM_RESULT_CLOSE											= "</result>";
	public static final String 				XML_ITEM_STATUS_OPEN											= "<status>";
	public static final String 				XML_ITEM_STATUS_CLOSE											= "</status>";
	public static final String 				XML_ITEM_CALL_OPEN												= "<call>";
	public static final String 				XML_ITEM_CALL_CLOSE												= "</call>";
	public static final String 				XML_ITEM_CODE_OPEN												= "<code>";
	public static final String 				XML_ITEM_CODE_CLOSE												= "</code>";
	public static final String 				XML_ITEM_OBJECT_OPEN											= "<object>";
	public static final String 				XML_ITEM_OBJECT_CLOSE											= "</object>";
	public static final String 				XML_ITEM_VALUE_OPEN												= "<value>";
	public static final String 				XML_ITEM_VALUE_CLOSE											= "</value>";
}
