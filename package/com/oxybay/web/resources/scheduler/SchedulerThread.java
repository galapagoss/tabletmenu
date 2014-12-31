/**
 * @author ing. Alberto Plebani, Gaetano Negrone
 *
 */
package com.oxybay.web.resources.scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;


import org.apache.log4j.Logger;

import com.oxybay.web.beans.system.SchedulerBean;
import com.oxybay.web.business.system.SchedulerBsn;
import com.oxybay.web.resources.utils.Utils;

public abstract class SchedulerThread extends Thread{
	
	/**
	 * Constants used to identify a process scheduled at fixed time intervals
	 * or a process scheduled at a fixed time
	 */
	public static final int FIXED_INTERVAL = 1;
	public static final int FIXED_TIME		 = 2;
	
	public static final long DAY_IN_MILLIS = 1000*60*60*24;
	
	protected static Logger log = Logger.getLogger(SchedulerThread.class);
	
	/* flag check init data */
	private boolean init = false;
	
	/* flag DB logger */
	private boolean logOnDB = false;
	
	/**
	 * Stores process type (fixed_interval vs fixed_time execution)
	 */
	private int processType = -1;
	
	/**
	 * processLabel
	 */
	private String processLabel = "";
	
	/**
	 * time between two execution in milliseconds
	 */
	private long millisInterval = -1;
	
	/**
	 * offset in millisecond since the begininng of the day which represents the time at which
	 * a fixed time interval will be processed
	 */
	private long scheduledTime = -1;
	
	/**
	 * This variable is used only to check if a process is in execution
	 */
	private boolean running = false;
	
	/**
	 * This variable can be used freely to set a process description, which will be saved on
	 * db on every execution of setExecution
	 */
	private String description = "";
	
	/** Last Execution Time in memory (for no DB logging) */
	private Date memLastExecution = null;
	
	/**
	 * Execution time process variables
	 */
	private long startTime		 = -1;
	private long endTime			 = -1;
	private long executionTime = -1;
	
	/**
	 * Schedules only on primary server (get from application_properties) if set to true
	 */
	private boolean scheduleOnPrimaryServer = false;
	
	/**
	 * This variable is used to perform thread stop operation. Due to stop method deprecation, we use die as
	 * a semaphore for process execution
	 */
	private boolean die = false;
	
	/**
	 * This variable is used to keep track of process with an uniqueID
	 */
	private String processUID = "";
	
  /* custom params */
	protected HashMap<String, String> params = null;
	
	/**
	 * Initialize SchedulerThread with fixed interval execution
	 * @param processLabel label identifying process
	 * @param interval interval between two process sleep/awake (in seconds)
	 * @param logOnDB flag for tracking execution on DB
	 * @param scheduleOnPrimaryServer schedule process only on primary server
	 * @param params custom params map
	 */
	public void init(String processLabel, int interval, boolean logOnDB, boolean scheduleOnPrimaryServer, HashMap<String, String> params) {
		this.processLabel = processLabel;
		this.millisInterval = interval *1000;
		this.processType = FIXED_INTERVAL;
		this.logOnDB = logOnDB;
		this.scheduleOnPrimaryServer = scheduleOnPrimaryServer;
		this.params = params;
		this.generateUID();
		this.init = initialize();
	}
	
	/**
	 * Initialize SchedulerThread with fixed time in day
	 * @param processType process type
	 * @param processLabel label identifying process
	 * @param startHour scheduled execution time hour
	 * @param startMinute scheduled execution time minute
	 * @param logOnDB flag for tracking execution on DB
	 * @param scheduleOnPrimaryServer schedule process only on primary server
	 * @param params custom params map
	 */
	public void init(String processLabel, int startHour, int startMinute, boolean logOnDB, boolean scheduleOnPrimaryServer, HashMap<String, String> params) {
		this.processLabel = processLabel;
		this.logOnDB = logOnDB;
		this.scheduleOnPrimaryServer = scheduleOnPrimaryServer;
		this.processType = FIXED_TIME;
		this.scheduledTime = (startHour*60 + startMinute)*60*1000;
		this.params = params;
		this.generateUID();
		this.init = initialize();
	}
	
	/**
	 * This methods log the process on db.
	 */
	private void setExecution(boolean result) {
		SchedulerBsn schedulerBsn = new SchedulerBsn();
		try {
			SchedulerBean bean = new SchedulerBean();
			bean.loadFromThread(this,result);
			schedulerBsn.insertExecution(bean);
		} catch(Exception ex) {
			log.error("Scheduler process " + this.getProcessLabel() + " (" + this.getDescription() + ") problemi in set execution: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			schedulerBsn.destroy();
		}
	}
	
	/**
	 * This method returns the date of the last process execution
	 * @return a date containing the day of the last day of execution or null if never logged
	 */
	public Date getLastExecution() {
		
		if (!this.logOnDB)
			return null;
		
		Date ritorna = null;
		SchedulerBsn schedulerBsn = new SchedulerBsn();
		try {
			SchedulerBean bean = schedulerBsn.getLastExecution(this.processLabel);
			if (bean!=null) 
				ritorna = bean.getStartTime();
		} catch(Exception ex) {
			log.info("Scheduler process " + this.getProcessLabel() + " (" + this.getDescription() + ") problemi in getLastExecution: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			schedulerBsn.destroy();
		}
		return ritorna;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 * this method implements the scheduler logic and must not be called (user start() on
	 * process instance)
	 */
	public void run() {
		if (isInit()) {
			running = true;
			if (processType == FIXED_INTERVAL)		
				loopFixedInterval();
			else			
				loopFixedTime();
			running = false;
		}
	}

	/**
	 * Fixed time execution loop logic
	 */
	private void loopFixedTime() {
		
		//FIXED TIME PROCESS
		
		//Execution variables
		boolean processResult 			= false;
		long sleepTime 							= -1;
		long nextScheduledExecution = -1;
		Date lastLoggedExecution		= null;
				
		GregorianCalendar today = new GregorianCalendar();
		today=setToDayStart(today);
		
		//nextScheduledExecution is next scheduled execution's absolute time in milliseconds 
		nextScheduledExecution = today.getTimeInMillis() + scheduledTime;
		
		//Check if the process has already been executed today
		GregorianCalendar lastExecutionDay = new GregorianCalendar();
		lastLoggedExecution = getLastExecution();
		if (lastLoggedExecution!=null) {			
			lastExecutionDay.setTime(lastLoggedExecution);			
			lastExecutionDay = setToDayStart(lastExecutionDay);
		} else {
			lastExecutionDay.setTimeInMillis(0);
		}
		
		if (today.getTimeInMillis() == lastExecutionDay.getTimeInMillis()) {
			//24h execution offset
			nextScheduledExecution += DAY_IN_MILLIS;
		}
					
		while(!die) {
			
			startTime = System.currentTimeMillis();
			
			if (startTime >= nextScheduledExecution) {
				
				processResult = doLogic();
				endTime = System.currentTimeMillis();
				executionTime = endTime - startTime;
				if ((!processResult) || (this.logOnDB)) {
					if (!processResult)
						log.error("ERROR IN PROCESS " + processLabel + " started at " + Utils.formatDate(new Date(startTime), "dd/MM/yyyy HH:mm:ss") + " executed in " + executionTime + " milliseconds");
					setExecution(processResult);
				}
				this.memLastExecution = new Date();
				//24h execution offset
				nextScheduledExecution += DAY_IN_MILLIS;
				
			}
			
			sleepTime = nextScheduledExecution - System.currentTimeMillis();
			
			if (sleepTime < 0) {
				/*It may happen that start time is just a millisecond before execution time, then execution is skipped 
				 * ('if (startTime >= nextScheduledExecution) {...}' code block is skipped) but if block skipping takes at least 2
				 * milliseconds, this code line 'sleepTime = nextScheduledExecution - System.currentTimeMillis();' produces a 
				 * negative sleep time so next error is logged, but process is exceuted immediatley after so everithing works well
				 */
				
				log.error("Maybe this is only a WARNING: Process " + processLabel + " lasted too much (" + executionTime + " milliseconds). MORE THAN A DAY?! Probably it was quite near to execution time but due to scheduler stuff, execution was skipped. Will retry immediately");
				sleepTime=0;
			}
			
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				running = false;
				log.error("Process " + processLabel + " interrupted exception: " + e.getMessage());
			}
			
		}
	}

	/**
	 * Fixed interval execution loop logic
	 */
	private void loopFixedInterval() {
		
		//FIXED INTERVAL PROCESS
		
		//Execution variables
		boolean processResult 			= false;
		long sleepTime 							= -1;
		long nextIntervalExecution 	= -1;
		
		while(!die) {
			
			startTime = System.currentTimeMillis();
			
			processResult = doLogic();
			
			endTime 							= System.currentTimeMillis();
			executionTime 				= endTime - startTime;
			nextIntervalExecution = startTime + millisInterval;
			sleepTime 						= nextIntervalExecution - System.currentTimeMillis();
			
			if ((!processResult) || (this.logOnDB)) {
				if (!processResult)
					log.error("ERROR IN PROCESS " + processLabel + " started at " + Utils.formatDate(new Date(startTime), "dd/MM/yyyy HH:mm:ss") + " executed in " + executionTime + " milliseconds");
				setExecution(processResult);
			}
			this.memLastExecution = new Date();
			
			if (sleepTime < 0) {
				log.error(Utils.formatDate(new Date(System.currentTimeMillis()), "dd/MM/yyyy HH:mm:ss") + ": Process " + processLabel + " lasted too much: " + executionTime);
				sleepTime=0;
			}
			
			try {
				sleep(sleepTime);
			} catch (InterruptedException e) {
				running = false;
				log.error(Utils.formatDate(new Date(System.currentTimeMillis()), "dd/MM/yyyy HH:mm:ss") + ": Process " + processLabel + " interrupted exception: " + e.getMessage());
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * This method get a day (in form of a GregorianCalendar) and set the day to its beginning,
	 * setting its hour to 0, minute to 0, second to 0 and millisecond to 0.
	 * @param cal the GregorianCalendar
	 * @return the GregorianCalendar representing the beginning of the day
	 */
	private GregorianCalendar setToDayStart(GregorianCalendar cal) {
		
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal;
		
	}
	
	/**
	 * This method is demanded to perform process' logic (process logging is performed only if this
	 * method returns a true value)
	 * @return true if the loop should trace execution in log
	 */
	protected abstract boolean doLogic();
	
	/**
	 * Abstract Method to implement into extended class a initialization routine. 
	 * It Must Return true if thread is ready to activate.
	 * @return
	 */
	protected abstract boolean initialize();

	public int getProcessType() {
		return processType;
	}

	public void setProcessType(int processType) {
		this.processType = processType;
	}

	public String getProcessLabel() {
		return processLabel;
	}

	public void setProcessLabel(String processLabel) {
		this.processLabel = processLabel;
	}

	public long getMillisInterval() {
		return millisInterval;
	}

	public void setMillisInterval(long millisInterval) {
		this.millisInterval = millisInterval;
	}

	public long getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(long scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}

	public boolean isScheduleOnPrimaryServer() {
		return scheduleOnPrimaryServer;
	}

	public void setScheduleOnPrimaryServer(boolean scheduleOnPrimaryServer) {
		this.scheduleOnPrimaryServer = scheduleOnPrimaryServer;
	}

	public boolean isDie() {
		return die;
	}

	public void setDie(boolean die) {
		this.die = die;
	}

	public String getProcessUID() {
		return processUID;
	}

	public void setProcessUID(String processUID) {
		this.processUID = processUID;
	}
	
	private void generateUID() {
		this.processUID = "" + System.currentTimeMillis() + "-" + (int)(Math.random() * 10000);		
	}

	/**
	 * <P>
	 * Get logOnDB field
	 * <P>
	 * @return Returns the logOnDB.
	 */
	public boolean isLogOnDB() {
		return logOnDB;
	}

	/**
	 * <P>
	 * Get memLastExecution field
	 * <P>
	 * @return Returns the memLastExecution.
	 */
	public Date getMemLastExecution() {
		return memLastExecution;
	}

	/**
	 * @return the init
	 */
	public boolean isInit() {
		return init;
	}

}

