/**
 * @author ing. Alberto Plebani, Gaetano Negrone
 */
package com.oxybay.web.resources.scheduler;

import java.io.File;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.oxybay.web.resources.keys.VarKeys;
import com.oxybay.web.resources.utils.Utils;

public class SchedulerManager {

	public static final String THREAD_TYPE_INTERVAL 			= "INTERVAL";
	public static final String THREAD_TYPE_TIME						= "TIME";
	
	public static final short	 SCHEDULING_ACTIVATE				= 0;
	public static final short	 SCHEDULING_SKIP_DUPLICATE	= 1;
	public static final short	 SCHEDULING_SKIP_SLAVE			= 2;
	public static final short	 SCHEDULING_SKIP_NOTINIT		= 3;
	
	private Hashtable<String,SchedulerThread> processTable = null;
	private ServletContext ctx = null;
	
	/* master server */
	private String masterServer = "";
	/* current server */
	private String currentServer = "";
	
	public SchedulerManager(ServletContext ctx) {
		this.ctx = ctx;
		this.processTable = new Hashtable<String,SchedulerThread>();
		try { this.currentServer = java.net.InetAddress.getLocalHost().getHostName();} catch (Exception e) {}
	}
	
	
	public boolean loadProcess(Logger log) {
		File file = new File(ctx.getRealPath("")+File.separator+"WEB-INF"+File.separator+"threads.xml");
		if (!file.exists())
			return false;
		
		log.info(" .... Load Threads ..... ");
		try {
		  
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  DocumentBuilder db = dbf.newDocumentBuilder();
		  Document doc = db.parse(file);
		  doc.getDocumentElement().normalize();
		  // Primary Server
		  NodeList nodeList = doc.getElementsByTagName("master-server");
		  if (nodeList.getLength()>0)
		  	this.masterServer = nodeList.item(0).getChildNodes().item(0).getNodeValue();
		  		  
		  // Threads
		  nodeList = doc.getElementsByTagName("threads");
		  if (nodeList.getLength()>0) {
		  	nodeList = nodeList.item(0).getChildNodes();
		  	for(int i=0; i<nodeList.getLength(); i++) {
			  	Node node = nodeList.item(i);
			  	if (node.getNodeType() == Node.ELEMENT_NODE) {
			  		Element elem = (Element)node;
			  		try {
			  			// Name
			  			String name = elem.getAttribute("name");
			  			if (name==null || name.equals("")) throw new Exception("Label not found");
			  			// Class
			  			String classN = elem.getAttribute("class");
			  			if (classN==null || classN.equals("")) throw new Exception("Class not found");
			  			// Type
			  			String typeN = elem.getAttribute("type");
			  			if (typeN==null || (!typeN.equals(THREAD_TYPE_INTERVAL) && !typeN.equals(THREAD_TYPE_TIME))) throw new Exception("Type not found or incorrect (accepted values: "+THREAD_TYPE_INTERVAL+" or "+THREAD_TYPE_TIME+")");
			  			// Log
			  			boolean logDB = false;
			  			try {logDB = Boolean.parseBoolean(elem.getAttribute("log"));} catch(Exception ex2){}
			  			// Exclusive
			  			boolean exclusive = false;
			  			try {exclusive = Boolean.parseBoolean(elem.getAttribute("exclusive"));} catch(Exception ex2){}
			  			
			  			// Check Exclusive spec
			  			if (exclusive) {
			  				if (this.masterServer.equals("")) throw new Exception("Master Server not set so exclusive mode not valid by thread "+name);
			  				if (this.currentServer.equals("")) throw new Exception("Current server not obtained from environment(verify permissions) so exclusive mode not valid by thread "+name);
			  			}
			  			
			  			// Options
			  			HashMap<String, String> params = new HashMap<String, String>();
			  			for(int i2=0; i2<node.getChildNodes().getLength(); i2++) {
			  				Node optionNode = node.getChildNodes().item(i2);
			  				if (optionNode.getNodeType() == Node.ELEMENT_NODE) 
			  					params.put(optionNode.getNodeName(), optionNode.getFirstChild().getNodeValue());
			  			}
			  			
			  			/* INTERVAL*/
			  			if (typeN.equals(THREAD_TYPE_INTERVAL)) {
			  				// Interval seconds
			  				int interval = 0;
			  				try { interval = Integer.parseInt(elem.getAttribute("interval"));} catch(Exception ex2) {}
			  				if (interval<=0) throw new Exception("Interval not found or incorrect value");
			  				// Invoke Instance
			  				SchedulerThread scheduled = (SchedulerThread)Class.forName(classN).newInstance();
			  				scheduled.init(name, interval, logDB, exclusive, params);
			  				scheduleProcess(scheduled,log);
			  			}
			  			
			  			/* TIME*/
			  			if (typeN.equals(THREAD_TYPE_TIME)) {
			  				// Hour
			  				int hour = -1;
			  				try { hour = Integer.parseInt(elem.getAttribute("hour"));} catch(Exception ex2) {}
			  				if (hour<0 || hour>23) throw new Exception("Hour not found or incorrect value");
			  				// Minute
			  				int minute = -1;
			  				try { minute = Integer.parseInt(elem.getAttribute("minute"));} catch(Exception ex2) {}
			  				if (minute<0 || minute>59) throw new Exception("Minute not found or incorrect value");
			  				// Invoke Instance
			  				SchedulerThread scheduled = (SchedulerThread)Class.forName(classN).newInstance();
			  				scheduled.init(name, hour, minute, logDB, exclusive, params);
			  				scheduleProcess(scheduled,log);
			  			}
			  			
			  		} catch(Exception ex) {
			  			log.error(Utils.trace(ex, "Skip Thread Specification row "+i));
			  		}
			  	}
			  }
		  }
	  } catch (Exception e) {
	  	log.error(Utils.trace(e));
	  }
	  return true;
	}
	
	/**
	 * This method insert processes and returns true if process can be accepted or false if not because
	 * another process with the same name is already scheduled or if process is scheduled on primary server but
	 * this is not the primary server
	 * @param st a SchedulerThread
	 * @return
	 */
	public short scheduleProcess(SchedulerThread st, Logger log) {
		
		short res = SCHEDULING_SKIP_SLAVE;
		
		if (!st.isInit())				
			res = SCHEDULING_SKIP_NOTINIT;
		else if (processTable.get(st.getProcessLabel()) != null)
			res = SCHEDULING_SKIP_DUPLICATE;
		else if (!st.isScheduleOnPrimaryServer() || (st.isScheduleOnPrimaryServer() && this.masterServer.equals(this.currentServer))) {
			processTable.put(st.getProcessLabel(), st);
			log.info("Activate Thread ->" + st.getProcessLabel());
			return SCHEDULING_ACTIVATE;
		}
		
		log.info("Skip scheduling thread " + st.getProcessLabel()+ " (cause "+res+")");
		return res;
	}
	
	/**
	 * This method starts processes managed by the manager. It starts only processes which are not
	 * running, so it is possible to call it freely
	 */
	public void startProcesses() {
		this.ctx.setAttribute(VarKeys.APP_SCHEDULED_THREAD, this.processTable);
		Enumeration<String> keys = processTable.keys();
		while(keys.hasMoreElements()) {
			String key = (String)keys.nextElement();
			SchedulerThread st = (SchedulerThread)processTable.get(key);
			if (!st.isRunning())
				st.start();
		}
	}
	
	/**
	 * This method performs process killing, setting die variable to true for all active processes
	 */
	public void killProcesses() {
		this.ctx.setAttribute(VarKeys.APP_SCHEDULED_THREAD, null);
		Enumeration<String> keys = processTable.keys();
		while(keys.hasMoreElements()) {
			String key = (String)keys.nextElement();
			SchedulerThread st = (SchedulerThread)processTable.get(key);
			if (st.isRunning())
				st.setDie(true);
		}
	}
	
}
