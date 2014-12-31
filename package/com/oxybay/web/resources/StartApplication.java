package com.oxybay.web.resources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.oxybay.web.resources.scheduler.SchedulerManager;

public class StartApplication implements ServletContextListener {

	/* resources */
	private Resources resources = null;
	
	/* scheduler manager */
	private SchedulerManager scheduler = null;
	
	 
	public void contextInitialized(ServletContextEvent event) {

		resources = Resources.getInstance();
		resources.getLog().info("*** APPLICATION START ***");
    if (resources.getInfo()==null || !resources.getInfo().isStarted()) {
      resources.setup(event.getServletContext());
    }
    
    
    // Scheduler Manager
    resources.getLog().info("Initializing scheduler manager ....");
    scheduler = new SchedulerManager(event.getServletContext());
    scheduler.loadProcess(resources.getLog());    
    scheduler.startProcesses();
    resources.getLog().info("- Start Processes ...");
    
    resources.getLog().info("Application started");
	}

	public void contextDestroyed(ServletContextEvent event) {
		resources.getLog().info("*** APPLICATION DESTROYED ***");
		scheduler.killProcesses();
	}

}
