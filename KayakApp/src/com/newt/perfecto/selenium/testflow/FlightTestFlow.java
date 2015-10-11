package com.newt.perfecto.selenium.testflow;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.newt.perfecto.selenium.constants.ApplicationConstants;
import com.newt.perfecto.selenium.logger.Log4jWrapper;
import com.newt.perfecto.selenium.util.CommonUtility;
import com.newt.perfecto.selenium.util.XmlParser;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileDriver;
import com.perfectomobile.selenium.api.IMobileWebDriver;
import com.perfectomobile.selenium.options.MobileDeviceProperty;

public class FlightTestFlow implements ApplicationConstants {
	
	private final static Logger logger = Logger.getLogger(FlightTestFlow.class);
	static Log4jWrapper slog = new Log4jWrapper();
	
	IMobileWebDriver drVisual = null;
	IMobileWebDriver drNativeApp =null;
	CommonUtility cutil = new CommonUtility();
	   
	/**
	 * @date 10/05/2015
	 * @author Harish Main
	 * @param imobiledriver
	 * @param imobiledevice
	 * @param nativedriver
	 * @return boolean as testing status
	 */
	public boolean testBestFlightsFlow(IMobileDriver imobiledriver, IMobileDevice imobiledevice, IMobileWebDriver nativedriver){
				
		boolean status=false;
		drVisual = imobiledevice.getVisualDriver();
		drNativeApp=imobiledevice.getNativeDriver(APPLICATION_NAME);
		String OStype=imobiledevice.getProperty(MobileDeviceProperty.OS);
		String deviceId=imobiledevice.getDeviceId();
		XmlParser xp = new XmlParser();
		int intTotalPassenger =1; 
		int stepNumber=0; // Variable used to track which step execution failed.
		
		try{
			
			logger.info("");
			logger.info("FlightTestFlow :: testBestFlightsFlow() invoked...");
									
			drNativeApp=imobiledevice.getNativeDriver("KAYAK");
			   
			/*Launch the Mobile App */
			drNativeApp.open();
			stepNumber++;	// STEP:1
			
    	   /* Validate if App launched successfully */
    	   if (drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "validateApp")).getSize().height > 0){
    		 logger.info("["+deviceId+"] XPATH for validateApp:>>>>"+xp.getXmlValue(OStype, "validateApp")+"<<<<");
			 logger.info("["+deviceId+"] KAYAK app is launched successfully");	
			 stepNumber++;	// STEP:2
			 
			 /* Validate Flight App option is displayed on screen, if 'yes' then click on it */
			 drVisual.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);			 
			 if (drVisual.findElementByLinkText(xp.getXmlValue(OStype, "AppOption")).getSize().width > 0){
				 logger.info("["+deviceId+"] Flight Option button displaye and going to Click it");		
				 logger.info("["+deviceId+"] XPATH for Flight Option:>>>>"+xp.getXmlValue(OStype, "AppOption")+"<<<<");
				 drVisual.findElementByLinkText(xp.getXmlValue(OStype, "AppOption")).click();	
				 stepNumber++;	// STEP:3
				 
				 if (drVisual.findElementByLinkText(xp.getXmlValue(OStype, "TripType")).getSize().width > 0){					 
					 logger.info("["+deviceId+"] App successfully displaying Main-Page with different TAB options");					 
					 /* Click on Round-Trip Tab option */
					 logger.info("["+deviceId+"] XPATH for TripType:>>>>"+xp.getXmlValue(OStype, "TripType")+"<<<<");
					 drVisual.findElementByLinkText(xp.getXmlValue(OStype, "TripType")).click();
					 stepNumber++;	// STEP:4
					 logger.info("["+deviceId+"] Round-Trip Tab found and clicked successfully");
					 
				 }else {
					 logger.warn("["+deviceId+"] - Invalid screen. Tab options not found on the screen");
					 stepNumber++;	// STEP:4
				}
				 
			 }
				
			/* Enter FROM City */
	 		 drVisual.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			 if (drVisual.findElementByLinkText(xp.getXmlValue(OStype, "FromText")).getSize().width > 0){	
				 logger.info("["+deviceId+"] XPATH for FromTxt:>>>>"+xp.getXmlValue(OStype, "FromText")+"<<<<");
				 logger.info("["+deviceId+"] XPATH for FromTxtXpath:>>>>"+xp.getXmlValue(OStype, "FromTextXpath")+"<<<<");
				 drVisual.findElementByLinkText(xp.getXmlValue(OStype, "FromTextXpath")).click();
				 logger.info("["+deviceId+"] XPATH for strFlightFromXpath:>>>>"+ xp.getXmlValue(OStype, "FromTextbox")+"<<<<");
				/* Enter FROM City/Airport Name in */ 
				 drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "FromTextbox")).sendKeys(xp.getXmlValue(OStype, "FromAirportName"));
				 /* Select FROM City/Airport Name that are displayed in the result */
				 logger.info("["+deviceId+"] XPATH for FromAirportName:>>>>"+xp.getXmlValue(OStype, "FromAirportName")+"<<<<");
				 drNativeApp.findElementByXPath("//text[contains(text(),'"+xp.getXmlValue(OStype, "FromAirportName")+"')]").click();
				 logger.info("["+deviceId+"] From city is selected as  :" +xp.getXmlValue(OStype, "FromAirportName"));	
				 stepNumber++;	// STEP:5
			 }
			 else {
				 logger.warn("["+deviceId+"] From City not able to select ");
				 stepNumber++;	// STEP:5
			 }
			 
			/* Enter TO City */
			 drVisual.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			 if (drVisual.findElementByLinkText(xp.getXmlValue(OStype, "ToText")).getSize().width > 0){	
				 logger.info("["+deviceId+"] XPATH for ToTxt:>>>>"+xp.getXmlValue(OStype, "ToText")+"<<<<");
				 drVisual.findElementByLinkText(xp.getXmlValue(OStype, "ToText")).click();
				 logger.info("["+deviceId+"] XPATH for ToTextboxXpath:>>>>"+xp.getXmlValue(OStype, "ToTextbox")+"<<<<");
				 drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "ToTextbox")).sendKeys(xp.getXmlValue(OStype, "ToAirportName"));
				 /* Select TO City/Airport Name that are displayed in the result */
				 logger.info("["+deviceId+"] XPATH for ToAirportName:>>>>"+xp.getXmlValue(OStype, "ToAirportName")+"<<<<");
				 drNativeApp.findElementByXPath("//text[contains(text(),'"+xp.getXmlValue(OStype, "ToAirportName")+"')]").click();
				 logger.info("["+deviceId+"] TO city is selected as  :" +xp.getXmlValue(OStype, "ToAirportName"));	
				 stepNumber++;	// STEP:6
			 }
			 else {
				 logger.warn("["+deviceId+"] TO City not able to select");
				 stepNumber++;	// STEP:6
			 }
				 
			 /* Increment number of passengers */
			if (intTotalPassenger == 1){
				 
				 logger.info("["+deviceId+"] *** Device OS Type: " +OStype+" ***");
			 
				 if (OStype.toLowerCase().indexOf("android") >=0 ){
					 logger.info("["+deviceId+"] XPATH for Traveller counter :>>>>"+xp.getXmlValue(OStype, "IncrementTravelers")+"<<<<");
					 drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "IncrementTravelers")).click();
					 intTotalPassenger++;
					 stepNumber++;	// STEP:7
					 logger.info("["+deviceId+"] Total number of passengers selected is : "+intTotalPassenger); 
				 }
				 else if (OStype.toLowerCase().indexOf("ios") >=0 ){
					 logger.info("["+deviceId+"] XPATH for strSelectPassenger_path_iOS:>>>>"+xp.getXmlValue(OStype, "IncrementTravelers")+"<<<<");
					 drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "IncrementTravelers")).click();
					 intTotalPassenger++;
					 stepNumber++;	// STEP:7
					 logger.info("["+deviceId+"] Total number of passengers selected is : "+intTotalPassenger);
				 }
			 }
					 
			logger.info("");
			logger.info("Going Select FLIGHT Travel Class(i.e. Economy/Premiun Economy/Business/First)...");
			 /* Check and Click on preselected flight class button and change the Travel Class to Economy */
			 if (drVisual.findElementByLinkText("Business").getSize().width > 0){
				 logger.info("["+deviceId+"] PreSelected Flight Travel Class Option: Business");
				 drVisual.findElementByLinkText("Business").click();					 
			 }else if (drVisual.findElementByLinkText("Premium Economy").getSize().width > 0){	
				 logger.info("["+deviceId+"] PreSelected Flight Travel Class Option: Premium Economy");
				 drVisual.findElementByLinkText("Premium Economy").click();
			 }else if (drVisual.findElementByLinkText("Economy").getSize().width > 0){	
				 logger.info("["+deviceId+"] PreSelected Flight Travel Class Option: Economy");
				 drVisual.findElementByLinkText("Economy").click();
			 }else if(drVisual.findElementByLinkText("First").getSize().width > 0){	
				 logger.info("["+deviceId+"] PreSelected Flight Travel Class Option: First");
				 drVisual.findElementByLinkText("First").click();
			 }
			 
			/* Travel Class Option selection from POPUP and Changing the Travel Class to Economy*/ 
			 drVisual.manage().timeouts().implicitlyWait(45,TimeUnit.SECONDS);			 
			 drVisual.findElementByLinkText(xp.getXmlValue(OStype, "TravelClassText")).click(); //Select Economy option from POPUP
			 logger.info("["+deviceId+"] Travel class option '"+xp.getXmlValue(OStype, "TravelClassText")+"' selected successfully");
			 stepNumber++;	// STEP:8
			 
			 /* Validate and Click on 'Find Flight' button */
			 drVisual.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
			 if (drVisual.findElementByLinkText(xp.getXmlValue(OStype, "FindFlightButtonText")).getSize().width > 0){	
				 logger.info("["+deviceId+"] Button Value >>>>"+xp.getXmlValue(OStype, "FindFlightButtonText")+"<<<<");
				 logger.info("["+deviceId+"] 'Find Flight' button found on the page");
				 drVisual.findElementByLinkText(xp.getXmlValue(OStype, "FindFlightButtonText")).click();
				 stepNumber++;	// STEP:9
			 }
				 				
			 /* Validate Flight Details page displayed correctly or not */
			 drVisual.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);	
			 if (drVisual.findElementByLinkText(xp.getXmlValue(OStype, "TravelClassXpath")).getSize().width > 0){	
				 logger.info("["+deviceId+"] XPATH for Flight Travel Class:>>>>"+xp.getXmlValue(OStype, "TravelClassXpath")+"<<<<");
				 logger.info("["+deviceId+"] Flight Details page displayed correctly");
				 stepNumber++;	// STEP:10
			 }
				 
			 /* Sort the flight results based on cost */
			 drNativeApp.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			 if (drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "SortButton")).getSize().width > 0){	
				 logger.info("["+deviceId+"] Sort button is found in the page and clicked");
				 logger.info("["+deviceId+"] XPATH for Sort Button Icon:>>>>"+xp.getXmlValue(OStype, "SortButton")+"<<<<");
				 drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "SortButton")).click();
				 drNativeApp.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
				 logger.info("["+deviceId+"] XPATH for Sort Filter:>>>>"+xp.getXmlValue(OStype, "SortTypeFilter")+"<<<<");
				 drNativeApp.findElementByXPath(xp.getXmlValue(OStype, "SortTypeFilter")).click();

				 /* Checking OS type to click on 'Done' button in case of iOS */
				 String strOS1 = imobiledevice.getProperty("OS");
				 logger.info("["+deviceId+"] ********Device is **************" +strOS1); 
				 if (OStype.toLowerCase().indexOf("ios") >=0 ){
					 logger.info("["+deviceId+"] XPATH for 'Done' button in iOS:>>>>"+xp.getXmlValue(OStype, "DoneButton")+"<<<<");
					 drVisual.findElementByLinkText(xp.getXmlValue(OStype, "DoneButton")).click();
					 logger.info("["+deviceId+"] Done button is clicked for iOS"); 
				 }
				 
				 logger.info("["+deviceId+"] The flight result sorted by 'Least Expensive' sort order ");
				 stepNumber++;	// STEP:11
			 }
			 
				
		   }
			    	   
    	   status=true;
    	   logger.info("["+deviceId+"] *** TESTING COMPLETED ***");
			
		}catch(Exception e){
			logger.error("FlightTestFlow :: testBestFlightsFlow(), STEP:"+stepNumber+",  Exception: "+e.getMessage());
		}finally{
			drNativeApp=null;
			drVisual=null;
		}
		
		return status;
		
	}
	
	
}
