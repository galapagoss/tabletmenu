/**
 * Utilities
 */

package com.oxybay.web.resources.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


public class Utils {

  /**
   * <p>
   * Formats a date following a defined pattern
   * </p>
   * @param date The date to format
   * @param pattern The pattern to use to format the date
   * @return The formatted string
   */
  public static String formatDate(java.util.Date date, String pattern) {
    if (date == null)
      return "";
    else {
      SimpleDateFormat f = new SimpleDateFormat(pattern);
      return f.format(date);
    }
  }
  
  /**
   * <p>
   * Extracts a date from a string
   * </p>
   * @param date The string date to convert in a Date object
   * @param time The time string, expressed as hh:mm
   * @return The converted Date Object or null if failed
   */
  public static java.util.Date stringToDate(String date, String pattern) {
    SimpleDateFormat f = new SimpleDateFormat(pattern);
    try {
    	return f.parse(date);
    } catch(Exception e) {
    	return null;
    }
  }
  
  /**
   * 
   * @param date
   * @param pattern
   * @return
   */
  public static Date tryStrToDate(String date, String pattern) {
  	Date res = null;
  	try { res = stringToDate(date, pattern);} catch(Exception e){}
  	return res;
  }
  
  
  /**
   * <p>Read cookie param</p>
   * @param param			param
   * @param request   HttpServletRequest		
   * @return the value found otherwise ""
   */
  public static String getCookieParam(String param, HttpServletRequest request) {
  	
    String res = "";
  	Cookie[] cc = request.getCookies();
    if (cc != null) {
      for (int i =0; i< cc.length; i++) {
        Cookie c = cc[i];
        if (c.getName().equals(param)) 
          res = ""+c.getValue();
      }
    }
    return res;
  }
  
  
  /**
   * <p>Set a param value into cookie (current domain)</p>
   * @param label			label
   * @param value			value 
   * @param response  HttpServletResponse		
   */
  public static void setCookieParam(String param, String value, int maxAge, String path, HttpServletResponse response) {
    Cookie c = new Cookie(param,value);
    c.setMaxAge(maxAge);
    c.setPath(path);
    response.addCookie(c);
  }
  
  
  /**
   * <p>Remove cookie</p>
   * @param label			label param
   * @param response  HttpServletResponse		
   */
  public static void removeCookieParam(String param, HttpServletResponse response) {
    Cookie c = new Cookie(param,"");
    c.setMaxAge(0);
    c.setPath("/");
    response.addCookie(c);
  }
  
  /**
   * <p> Move a file into a directory </p>
   * @param fileUploaded
   * @param destFile 
   * @param overwrite
   */
  public static File moveUploadedFile(File fileUploaded, File destFile, boolean overwrite) throws Exception {
	  
	  File finalFile = null;
	  
  	if ((fileUploaded != null) && (fileUploaded.getName() != null)) {
  		
  		// Create path directory, if not exists
  		File workPath = null;
  	  try { 
  	  	workPath = new File(destFile.getAbsolutePath().substring(0,destFile.getAbsolutePath().lastIndexOf(File.separator)));
  	  } catch (Exception e) { throw new Exception("common.upload.error.pathBase");}
  	  
  	  if (! workPath.exists()) {
  		  try {
  			  if (! workPath.mkdirs()) 
  				  throw new Exception("common.upload.error.directoryFailed");
  		  } catch (Exception eDir) { throw new Exception("common.upload.error.directoryException"); }
  	  }
  	  
  	  // Search an unique file name into directory or delete existing file
  	  String baseFilename = destFile.getName();
  	  String extFilename  = "";
  	  int strDotPos = destFile.getName().lastIndexOf(".");
  	  if (strDotPos != -1) {
  		  extFilename  = destFile.getName().substring(strDotPos);
  		  baseFilename = destFile.getName().substring(0, strDotPos);
  	  }

  	  finalFile = new File(workPath.getAbsolutePath() + "/" + baseFilename + extFilename);
  	  if (overwrite) {
  	  	if (finalFile.exists())
  	  		if (!finalFile.delete())
  	  			throw new Exception("common.upload.error.deleteOldVersion");
  	  }
  	  else {
  	  	int cont = 0;
    	  while (finalFile.exists()) {
    	  	cont++;
    		  finalFile = new File(workPath.getAbsolutePath() + "/" + baseFilename + cont + extFilename);
    	  }
  	  }
  	  FileUtils.copyFile(fileUploaded, finalFile);
		}
  	else  
  		throw new Exception("common.upload.error.noUpload");
  	
  	return finalFile;
  }
  
  
  /**
   * Get MD5 Crypt	
   * @param src
   * @return
   */
	public static String getMD5Crypt(String src) {
		
		byte[] defaultBytes = src.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
		  algorithm.reset();
		  algorithm.update(defaultBytes);
		  byte messageDigest[] = algorithm.digest();

		  StringBuffer hexString = new StringBuffer();
		  for (int i=0;i<messageDigest.length;i++) {
		  	String hex = Integer.toHexString(0xFF & messageDigest[i]);
		  	if(hex.length()==1)
		  		hexString.append('0');
		  	hexString.append(hex);
		  }
		                                   
		  src = hexString+"";
		}
		catch(NoSuchAlgorithmException nsae) {
			Logger log = Logger.getLogger(Utils.class);
			log.error("Error To Crypt : " + nsae.toString());
			src = "";
		}
		return src;
  }
	  
  /** <p> Create a string with whole stack trace of an expection. Useful for log4j </p>
   * @param e an Exception
   * @return the full stack trace of e as String
   */
  public static String trace(Exception e) {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    PrintWriter writer = new PrintWriter(bytes,true);
    e.printStackTrace(writer);
    String string=bytes.toString();
    return string;
	}
  
  /** <p> Create a string with whole stack trace of an expection. Useful for log4j </p>
   * @param e an Exception
   * @param message
   * @return the full stack trace of e as String
   */
  public static String trace(Exception e, String message) {
  	return message+"\r\n"+trace(e);
	}
  
  /**
	 * 
	 * @param message
	 * @return
	 */
	public static String getCurrentURL(HttpServletRequest request)	{
		String url = ""+request.getRequestURL();
		if (request.getQueryString()!= null  &&  !request.getQueryString().equals(""))
			url += "?"+request.getQueryString();
	  return url;
	}
 	
	
	/**
	 * Get Base Url
	 * @param request
	 * @return
	 */
	public static String getBaseURL(HttpServletRequest request)	{
		return (""+request.getRequestURL()).replace(request.getRequestURI(), "");
	}
	
	 /**
   * Get Class Method depending
   * @param offset
   * @return
   */
  public static String getClassMethod (int offset) {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		if (stack.length>offset)
			return stack[offset].getClassName()+"."+stack[offset].getMethodName();
		return "";
  }
 
  /**
   * Get Text Without HTML Tags
   * @param src
   * @return
   */
  public static String getWithoutHTML(String src) {
  	return src.replaceAll("<(.|\n)*?>","");
  }
  
  /**
   * Get formatted file size
   * @param size
   * @return
   */
  public static String getFormattedSize(long size) {
  	if (size<1024)
  		return size+" B";
  	else if (size<(1024*1024))
  		return (size/1024)+" KB";
  	else {
  		NumberFormat nf = NumberFormat.getInstance();
  		nf.setMinimumIntegerDigits(1);
  		return nf.format(size/1024*1024)+" MB";
  	}
  }
}