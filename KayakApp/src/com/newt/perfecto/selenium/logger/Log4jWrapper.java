package com.newt.perfecto.selenium.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.newt.perfecto.selenium.constants.ApplicationConstants;

public class Log4jWrapper implements ApplicationConstants {
	
public static org.apache.log4j.Logger logger = Logger.getLogger(Log4jWrapper.class);
	
	public Log4jWrapper(){
		initializeLogger();
	}
	
	public static void initializeLogger(){		
		PropertyConfigurator.configure(LOG_FILENAME);
		logger.info("Log4jWrapper Logging initialized");
	}

}
