package com.newt.perfecto.selenium.testcases;

import org.apache.log4j.Logger;

import com.newt.perfecto.selenium.logger.Log4jWrapper;
import com.newt.perfecto.selenium.services.MobileCloudServices;
import com.newt.perfecto.selenium.util.CommonUtility;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileDriver;
import com.perfectomobile.selenium.api.IMobileWebDriver;

/**
 * @date 10/03/2015
 * @author Harish Main
 * @description Class 
 *
 */
public class RunParallelExecution implements Runnable {
	
	private String deviceId=null;
	private boolean device_open=false;
	
	public RunParallelExecution(String deviceid) {
		this.deviceId=deviceid;
	}

	private final static Logger logger = Logger.getLogger(RunParallelExecution.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	CommonUtility cutil = new CommonUtility();
	
	@Override
	public void run() {
		
		MobileCloudServices mcmservice = new MobileCloudServices(deviceId);
		boolean install_app_status=false;
		try{
			
			logger.info("");
			logger.info("RunParallelExecution :: run() ** STARTED *** , deviceId: "+this.deviceId);

			/* Open Device Asynchronously */
			device_open=mcmservice.DeviceOpen();
			cutil.sleep(5000);
			
			if(device_open){
				
				/* Install Application Kit on all the devices opened */
				/* install_app_status=mcmservice.installApp(); */
				install_app_status=true; // Defaulting install_app status to true
				
				if(install_app_status){
					
					/* Open Mobile App on the opened device */
					mcmservice.OpenApplication();
					cutil.sleep(5000);
					
					/* Perform Test Flow on the device opened */
					mcmservice.startFlightTestFlow();
					
					/* UnInstall Application Kit on the device opened */
					
					
					/* Close Application */
					mcmservice.CloseApplication();
					cutil.sleep(5000);
					
					/* Close Device */
					mcmservice.DeviceClose();
					
					/* Driver Quit and Download PerfectoReport*/
					//mcmservice.DriverQuit();
					mcmservice.DriverQuitDownloadReport();

				}else{
					logger.info("RunParallelExecution :: run() ** Install Kit ERROR ***, please check the apk/ipa file exists and try again, deviceId: "+this.deviceId);
				}
				
			}
			
			logger.info("RunParallelExecution :: run() ** COMPLETED *** , deviceId: "+this.deviceId);
			
		}catch(Exception e){
			logger.error("RunParallelExecution :: run(), Exception: "+e.getMessage());
		}
		
	}

	
}
