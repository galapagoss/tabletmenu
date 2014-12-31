/**
 * ImageConvertBsn.java
 * 16/lug/2012
 * @author Gaetano
 */

package com.oxybay.web.business.system.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.oxybay.web.business.system.root.RootBsn;

public class ImageConvertBsn extends RootBsn {
	
	
	/* convert exe filename */
	private String convertExe = "";

	/**
	 * Constructor
	 */
	public ImageConvertBsn() {
		super();
		this.convertExe = System.getProperty("os.name").toLowerCase().contains("windows") ? "im-convert" : "convert"; 
	}

	
	/**
	 * Resize Image with specific annotation
	 * @param file
	 * @param spec
	 * @return
	 */
	public boolean imageResize(File file, ImageResize spec) {
		return imageResize(file, spec.w(), spec.h(), spec.crop());
	}
	
	/**
	 * Resize Image
	 * @param file
	 * @param w
	 * @param h
	 * @param type
	 * @return
	 */
	public boolean imageResize(File file, int w, int h, boolean crop) {
		String strW = w>0 ? ""+w : "";
		String strH = h>0 ? ""+h : "";
		String ignoreRatio = (!crop && w>0 && h>0) ? "!" : "^";
		String strCrop = (crop && w>0 && h>0)  ? " \\ -gravity NorthWest -background black -extent "+w+"x"+h : "";
		
		return execShell(convertExe+" \""+file.getAbsolutePath()+"\" -resize "+strW+"x"+strH+ignoreRatio+strCrop+" \""+file.getAbsolutePath()+"\"");
	}
	
	/**
	 * Execute Shell Command
	 * @param cmd
	 * @return
	 */
	private boolean execShell(String cmd) {
		getData().getLog().info("EXEC SHELL CMD: "+cmd);
		Runtime runtime = Runtime.getRuntime();
		Process pr = null;
		try { 
			pr = runtime.exec(cmd); 
		} catch (IOException e) {
			traceError(e); 
			return false;
		} 
		try { 
			pr.waitFor(); 
		} catch (InterruptedException e) { 
			traceError(e);
			return false;
		} 
		BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream())); 
		String line = ""; 
		try { 
			while ((line=buf.readLine())!=null) { 
				getData().getLog().info(line); 
			} 
		} catch (IOException e) { traceError(e); }
		
		return true;
	}
}
