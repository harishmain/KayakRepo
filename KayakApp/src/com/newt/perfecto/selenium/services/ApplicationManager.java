package com.newt.perfecto.selenium.services;

import org.apache.log4j.Logger;
import com.perfectomobile.selenium.*;
import com.perfectomobile.selenium.api.*;
import com.perfectomobile.selenium.options.*;
import com.newt.perfecto.selenium.logger.Log4jWrapper;
import com.newt.perfecto.selenium.util.CommonUtility;

/**
 * @date 10/02/2015
 * @author Harish Main
 * @description Class to manage applications on the perfecto mobile devices 
 *
 */
public class ApplicationManager {
	
	private final static Logger logger = Logger.getLogger(ApplicationManager.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	CommonUtility cutil = new CommonUtility();
	
	
	/**
	 * @date 10/02/2015
	 * @author Harish Main
	 * @description Method used to check if the Element is present on the Mobile App screen
	 * @param drNativeApp
	 * @param drVisualApp
	 * @param strSearchText
	 * @param iWaitSeconds
	 * @return boolean true if Element is found
	 */
	public boolean isElementPresent(IMobileWebDriver drNativeApp, IMobileWebDriver drVisualApp, String strSearchText, int iWaitSeconds){
		logger.info("ApplicationManager :: isElementPresent() invoked...");
		cutil.sleep(iWaitSeconds*1000);
		boolean status=false;
		try
		{
			if (strSearchText.indexOf("//") >= 0 )
			{
				
				if (drNativeApp.findElementByXPath(strSearchText).getSize().height > 0)
				{
					//Returning the value
					return true;
				}
				
			}else{
				//condition checks elements based on visual object				
				if (drVisualApp.findElementByLinkText(strSearchText).getSize().width > 0)
				{
					//Returning the value
					return true;
				}
				
			}
		
		}catch(Exception e){
			logger.error("ApplicationManager :: isElementPresent(), strSearchText:" + strSearchText + ", Exception:"+e);
		}
		
		return status;
	}
	
	/**
	 * @date 10/02/2015
	 * @author Harish Main
	 * @description Method used to 
	 * @param driver
	 * @return
	 */
	public IMobileDevice selectDevicefree(MobileDriver driver)
	{
		IMobileDevice myDevice=null;
		//MobileDeviceFindOptions options = null;
		try{
			//first  available device without conditions
			//options=new MobileDeviceFindOptions();
			//IMobileDevice myDevice=driver.findDevice(options);
			myDevice=driver.getDevice("ZX1F22X45W");
			myDevice.open();
			logger.info("Selected device ID:"+ myDevice.getProperty(MobileDeviceProperty.DEVICE_ID)+
					            "Selected device Model:"+ myDevice.getProperty(MobileDeviceProperty.MODEL));
		}catch(Exception e){
			logger.error("ApplicationManager :: selectDevicefree(), Exception:"+e.getMessage());
		}finally{
			myDevice=null;
			//options = null;
		}

		return myDevice;
	}
	

}