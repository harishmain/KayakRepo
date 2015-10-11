package com.newt.perfecto.selenium.testcases;

import org.apache.log4j.Logger;

import com.newt.perfecto.selenium.logger.Log4jWrapper;
import com.newt.perfecto.selenium.util.CommonUtility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * CODE CHANGES HISTORY
 * ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * 	DATE		AUTHOR				METHODS MODIFIED/ADDED				CODE CHANGES DESCRIPTION
 * -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 *  10/01/2015  Harish Main      										Initial Class creation. 	
 */


/**
 * @date 10/01/2015
 * @author Harish Main
 * @description FlightTestCase class created for testing Kayak's Flight Functionality
 */
public class FlightTestCase  {
	
	private final static Logger logger = Logger.getLogger(FlightTestCase.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	CommonUtility cutil = new CommonUtility();
	
	public static void main(String[] args) {
		
		FlightTestCase obj = new FlightTestCase();
		
		logger.info("FlightTestCase :: main() invoked...");
		
		try{
						
			obj.runTestCase();						
			
		}catch(Exception e){
			logger.error("FlightTestCase :: main(), Exception: "+e.getMessage());
		}

	}
	
	/**
	 * @date 10/01/2015
	 * @author Harish Main
	 * @description Method executes the test case by creating multiple threads based on given devices 
	 * @return
	 */
	public boolean runTestCase(){
		
		boolean run_status=false;
		//String[] devices={"61944835"," ED82E4F1","2FA6C2B844108613C380E39939DA0923A6C1E66A"};
		String[] devices=cutil.getDeviceIds(); //{"02157DF2A1B46C22"}; //0E6FBDEEF6C5EECEF71F66904FC4EFDA3F803682
		
		try{
			
			logger.info("");
			logger.info("FlightTestCase :: main() *** TESTING STARTED ***");
	        ExecutorService executor = Executors.newFixedThreadPool(devices.length);
	        for(String device : devices) {
	        	//FlightTestCase test = new FlightTestCase(device);
	        	logger.info("Instantiating Thread for deviceId >>>>"+device+"<<<<");
	        	RunParallelExecution test = new RunParallelExecution(device);
	            executor.execute(test);
	        }   
	        
	        executor.shutdown();
	        logger.info("");
	        
			run_status=true;
			
		}catch(Exception e){
			logger.error("FlightTestCase :: runTestCase(), Exception: "+e.getMessage());
		}finally{
			devices=null;
		}
		
		return run_status;
	}


}
