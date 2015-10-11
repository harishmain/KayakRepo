package com.newt.perfecto.selenium.services;

import java.io.File;

import org.apache.log4j.Logger;

import com.newt.perfecto.selenium.constants.ApplicationConstants;
import com.newt.perfecto.selenium.logger.Log4jWrapper;
import com.newt.perfecto.selenium.services.ApplicationManager;
import com.newt.perfecto.selenium.testflow.FlightTestFlow;
import com.newt.perfecto.selenium.util.CommonUtility;
import com.perfectomobile.selenium.MobileDriver;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileDriver;
import com.perfectomobile.selenium.api.IMobileWebDriver;
import com.perfectomobile.selenium.options.MobileApplicationInstallOptions;
import com.perfectomobile.selenium.options.MobileDeviceProperty;

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
 * @description MobileCloudServices Class provides cloud connectivity services for the application
 */
public class MobileCloudServices implements ApplicationConstants {
	
	private IMobileDriver imobiledriver;
	private IMobileDevice imobiledevice;
	private IMobileWebDriver nativedriver;
	
	private String mobile_device_os=null;
	private String application_name=APPLICATION_NAME;
		
	public MobileCloudServices(String device){
		this.imobiledriver = new MobileDriver(); 
		this.imobiledevice = imobiledriver.getDevice(device);
		this.mobile_device_os = imobiledevice.getProperty(MobileDeviceProperty.OS); // Gets the device OS
		this.nativedriver = imobiledevice.getNativeDriver(application_name);
	}
	
	
	private final static Logger logger = Logger.getLogger(MobileCloudServices.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	ApplicationManager appmgr = new ApplicationManager();
	CommonUtility cutil = new CommonUtility();
	
	/**
	 * @date 10/01/2015
	 * @author Harish Main
	 * @description Method to Open a Mobile Device and display the Home screen
	 */
	public boolean DeviceOpen(){
		
		boolean status=false;
		
		logger.info("MobileCloudServices :: DeviceOpen() invoked on device: "+imobiledevice.getDeviceId());
		
		try{			
			/* Opening the device */
			imobiledevice.open();
			
			logger.info("MobileCloudServices :: DeviceOpen(), Model: " + imobiledevice.getProperty("model") + ", deviceId: " + imobiledevice.getDeviceId() + " Opened");
					
			/* Opening Home screen on the opened device */
			imobiledevice.home();
			
			status=true;
			
		}catch (Exception e){
			logger.error("MobileCloudServices :: DeviceOpen(), deviceId:" + imobiledevice.getDeviceId() + ", Exception:"+e.getMessage());
		}
		
		return status;
		
	}
	

	/**
	 * @date 10/01/2015
	 * @author Harish Main
	 * @description 
	 */
	public boolean OpenApplication(){
		
		boolean status=false;
		
		logger.info("MobileCloudServices :: OpenApplication() invoked on device: "+imobiledevice.getDeviceId());
		
		try{
			//Opening the application
			nativedriver.open();
			status=true;
			logger.info("MobileCloudServices :: OpenApplication(), Application:"+application_name+" opened on device:"+imobiledevice.getDeviceId());

		}catch (Exception e){
			logger.error("MobileCloudServices :: OpenApplication(), deviceId:" + e.getMessage());
		}
		
		return status;
		
	}	
	
	/**
	 * @date 10/01/2015
	 * @author Harish Main
	 * @description 
	 */
	public boolean CloseApplication(){
		
		boolean status=false;		
		
		logger.info("MobileC"
				+ "loudServices :: CloseApplication() performing on device: "+imobiledevice.getDeviceId());		
		try{				
			//Closing the application
			nativedriver.close();
			status=true;
			logger.info("["+imobiledevice.getDeviceId()+"] MobileCloudServices :: CloseApplication() status:"+status);
			
		}catch(Exception e){
			logger.error("MobileCloudServices :: CloseApplication(), device: "+imobiledevice.getDeviceId()+", Exception :" + e.getMessage());
		}finally{
			nativedriver=null;
		}
		
		return status;
		
	}
	
	/**
	 * @date 10/01/2015
	 * @author Harish Main
	 * @description 
	 */
	public boolean DeviceClose(){
		
		boolean status=false;
		
		logger.info("["+imobiledevice.getDeviceId()+"] MobileCloudServices :: DeviceClose() invoked... ");
	
		try{	
			//Closing the device
			imobiledevice.close();
			status=true;	
			logger.info("["+imobiledevice.getDeviceId()+"] MobileCloudServices :: DeviceClose() status:"+status);
		}catch(Exception e){
			logger.error("MobileCloudServices :: DeviceClose()device: "+imobiledevice.getDeviceId()+", Exception :" + e.getMessage());
		}finally{
			imobiledevice=null;
		}
		
		return status;
		
	}
	
	/**
	 * @date 10/09/2015
	 * @author Harish Main
	 * @description Method to quit the driver
	 * @return boolean status indicating driver quit status
	 */
	public boolean DriverQuit(){
		boolean status=false;
		try{
			logger.info("");
			logger.info("*** Quit Driver ***");
			imobiledriver.quit();
			status=true;
			logger.info("["+imobiledevice.getDeviceId()+"] MobileCloudServices :: DriverQuit(), status:"+status);
			
			cutil.downloadReport(imobiledriver);
			
		}catch(Exception e){
			logger.error("MobileCloudServices :: DriverQuit()device: "+imobiledevice.getDeviceId()+", Exception :" + e.getMessage());
		}
		
		return status;
           
	}
	
	/**
	 * @date 10/09/2015
	 * @author Harish Main
	 * @description Method to quit the driver and download Perfecto Mobile Driver
	 * @return boolean status indicating driver quit status
	 */
	public boolean DriverQuitDownloadReport(){
		boolean status=false;
		try{
			logger.info("");
			logger.info("MobileCloudServices :: DriverQuitDownloadReport() invoked");
			logger.info("["+imobiledevice.getDeviceId()+"] *** Quit Driver ***");
			imobiledriver.quit();
			status=true;
			logger.info("["+imobiledevice.getDeviceId()+"] MobileCloudServices :: DriverQuitDownloadReport(), status:"+status);
			
			cutil.downloadReport(imobiledriver);
			
		}catch(Exception e){
			logger.error("MobileCloudServices :: DriverQuitDownloadReport()device: "+imobiledevice.getDeviceId()+", Exception :" + e.getMessage());
		}
		
		return status;
           
	}	
	
	/**
	 * @date 10/02/2015
	 * @author Harish Main
	 * @description Method to install the Mobile App on the opened device
	 */
	public boolean installApp(){
		
		boolean status=false;
		String app_identifier=APPLICATION_IDENTIFER;
		File file = null;
		//Upload IPA file from my folder to repository
		//String repositoryKeyiOS = "PUBLIC:PMIOSDemo.ipa";
		String repositoryKeyiOS = REPOSITORY_PUBLIC_KEY + IPA_FILENAME;
		
		//Upload APK file from my folder to repository
		//String repositoryKeyAndroid = "PRIVATE:TFmFm GPS Tracking Application_apkpure.com.apk";
		String repositoryKeyAndroid = REPOSITORY_PUBLIC_KEY + APK_FILENAME;
		
		try{	

			logger.info("");
			logger.info("MobileCloudServices :: installApp() invoked...");
			
			file = new File(APK_KIT_PATH + APK_FILENAME );
			imobiledriver.uploadMedia(repositoryKeyAndroid, file);
			
			String OS=imobiledevice.getProperty(MobileDeviceProperty.OS);
			cutil.sleep(5000);
			
			logger.info("MobileCloudServices :: installApp() OS:" +OS+ " starting installation...");
			
			if (OS.equals("iOS")){
				logger.info("MobileCloudServices :: installApp(), OS: iOS, installing "+file.getName()+" on "+imobiledevice.getProperty(MobileDeviceProperty.MODEL));
				imobiledevice.installApplication(repositoryKeyiOS);
			}else{
				logger.info("MobileCloudServices :: installApp(), OS: Android, installing "+file.getName()+" on "+imobiledevice.getProperty(MobileDeviceProperty.MODEL));
				MobileApplicationInstallOptions installOption = new MobileApplicationInstallOptions();
                installOption.setInstrument(true);
                imobiledevice.installApplication(repositoryKeyAndroid);
                
                // logic to check if installation is completed. 
                for(int i=0;i<5;i++){
                	logger.info("MobileCloudServices :: installApp(), installation in process...");
                	if(imobiledevice.isApplicationInstalled(app_identifier)){
                		logger.info("MobileCloudServices :: installApp(), installation .apk completed...");
                		logger.info("");
                		break;
                	}else{
                		cutil.sleep(30*1000); //Installation not complete so continue to sleep for 30 seconds
                	}
                }
                
			}			
			status=true;
			
		}catch(Exception e){
			logger.error("MobileCloudServices :: installApp(), device: "+imobiledevice.getDeviceId()+", Exception :" + e.getMessage());
		}
		
		return status;
		
	}
	
	public void startFlightTestFlow(){
		
		FlightTestFlow tf = new FlightTestFlow();
		
		try{
			
			logger.info("");
			logger.info("MobileCloudServices :: startFlightTestFlow() invoked...");			
			tf.testBestFlightsFlow(imobiledriver, imobiledevice, nativedriver );
			logger.info("");	
			
		}catch(Exception e){
			logger.error("MobileCloudServices :: startFlightTestFlow()device: "+imobiledevice.getDeviceId()+", Exception :" + e.getMessage());
		}
		
		

		
	}
	


}
