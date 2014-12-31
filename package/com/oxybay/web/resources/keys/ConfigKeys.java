/**
 * ConfigKeys
 */

package com.oxybay.web.resources.keys;

import java.io.File;

public class ConfigKeys {
	
	public static final String 		SITE_NAME													= "TABLET MENU WEBSITE";
	public static final String 		SITE_LABEL												= "tabletmenu";
	public static final String 		SITE_VERSION											= "1.0";
	
	public static final String[] 	JDBC_DB_LIST 											= {"jdbc/tablet_menu"};
	public static final int 			JDBC_DB_MAIN 											= 0;
	
	public static final String 		TIME_FORMAT_SHORT									= "HH:mm";
	public static final String 		TIME_FORMAT_LONG									= "HH:mm:ss";
	
	public final static String 		CONFIG_PATH_FILE									= File.separator+"WEB-INF"+File.separator+"config.ini";
	public final static String 		CONFIG_PARAM_CACHE_ACTIVATE				= "CACHE_ACTIVATE";
	public final static String 		CONFIG_PARAM_CACHE_EXPIRETIME			= "CACHE_EXPIRETIME";
	
}