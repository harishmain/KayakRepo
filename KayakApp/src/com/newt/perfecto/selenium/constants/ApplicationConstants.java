package com.newt.perfecto.selenium.constants;

/*
 * CODE CHANGES HISTORY
 * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * 	DATE		AUTHOR				METHODS MODIFIED/ADDED				CODE CHANGES DESCRIPTION
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *  10/01/2015  Harish Main      										Initial interface creation. 	
 */


/**
 * @date 10/01/2015
 * @author Harish Main
 * @description Interface class used to store APPLICATION CONSTANTS
 */
public interface ApplicationConstants {
	
	static final String WORKSPACE_LOCATION="C:\\Selenium\\Workspace\\";

	/* Perfecto Mobile cloud credentials */
	static final String PROPERTY_FILENAME=WORKSPACE_LOCATION+"KayakApp\\data\\Environment.properties";			
	static final String DATA_PATH= WORKSPACE_LOCATION+"KayakApp\\data\\";									
	static final String REPORTS_PATH = WORKSPACE_LOCATION+"KayakApp\\reports\\";
		
	/* Application constants variables for Log4J  */
	static final String LOG_FILENAME=WORKSPACE_LOCATION+"KayakApp\\log4j.properties";							 
	
	static final String APK_KIT_PATH=WORKSPACE_LOCATION+"KayakApp\\devkits\\android\\";
	static final String IPA_KIT_PATH=WORKSPACE_LOCATION+"KayakApp\\devkits\\ios\\";
	
	static final String APPLICATION_NAME = "KAYAK";
	static final String APPLICATION_IDENTIFER ="com.kayak.android";
	static final String APK_FILENAME="KAYAK-com.kayak.android-1126-v14.1.apk";
	static final String IPA_FILENAME="";
	
	static final String REPOSITORY_PRIVATE_KEY = "PRIVATE:";
	static final String REPOSITORY_PUBLIC_KEY = "PUBLIC:";
	
	
	
	static final String[] TEST_CASE_NAMES = {"FlightTestCase"};
	
	
}
