package com.newt.perfecto.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.log4j.Logger;
import com.newt.perfecto.selenium.constants.ApplicationConstants;
import com.newt.perfecto.selenium.logger.Log4jWrapper;
import com.perfectomobile.httpclient.MediaType;
import com.perfectomobile.httpclient.utils.FileUtils;
import com.perfectomobile.selenium.api.IMobileDriver;

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
 * @description CommonUtility Class contains all common utility methods
 */
public class CommonUtility implements ApplicationConstants {
	
	private final static Logger logger = Logger.getLogger(CommonUtility.class);
	static Log4jWrapper slog = new Log4jWrapper();	
	
	/**
	 * @date 10/01/2015
	 * @author Harish Main
	 * @description Sleep method
	 * @param millis
	 */
	public void sleep(long millis) {
		
		try{
			Thread.sleep(millis);
		}catch (InterruptedException e) {
			logger.error("CommonUtility :: sleep(), Exception: "+e.getMessage());
		}
		
	}
	
/*	public static void main(String[] args){
		
		CommonUtility testObj = new CommonUtility();
		testObj.getDeviceIds();
	}*/
	
	/**
	 * @date 10/02/2015
	 * @author Harish Main
	 * @description Method used to download Report based on driver passed
	 * @param driver
	 * @return boolean true on successful download of Report
	 */
	public boolean downloadReport(IMobileDriver driver) {
		
		boolean status=false;
		String report_path=REPORTS_PATH;
		String report_name=null;
		InputStream reportStream = null;
		try{
			
			logger.info("CommonUtility :: downloadReport() invoked...");
			
	    	driver.equals("Android");
	        reportStream = driver.downloadReport(MediaType.HTML);
	        
	        if (reportStream != null) {	        	
	        	report_name = report_path+"FlightTestReport.html";
				File reportFile = new File(report_name);				
				FileUtils.write(reportStream, reportFile);				
				status=true;
			}
	        
		}catch (IOException e) {
			logger.error("Failed to write report:" + report_name + ", IOException:" + e.getMessage());
		}catch (Exception e){
			logger.error("CommonUtility :: downloadReport(), Exception:"+e.getMessage());
		}
        
        return status;
	    
	}
	
	/**
	 * @date 10/05/2015
	 * @author Harish Main
	 * @description Method used to read Excel data file and get DeviceIds
	 * @return String[] as list of devices
	 */
	public String[] getDeviceIds(){
		
		String arrDeviceId[]=null;
		String deviceDataFile=null;
		Workbook workbook;
		Sheet sheet;
		
		try{			
			logger.info("CommonUtility :: getDeviceIds() invoked...");
			deviceDataFile = DATA_PATH+"devices.xls";
			FileInputStream fs = new FileInputStream(deviceDataFile);
			workbook = Workbook.getWorkbook(fs);
			sheet = workbook.getSheet("Sheet1");
			int iRow = 1;
			arrDeviceId= sheet.getCell(0, iRow).getContents().split(";");
			for(String device: arrDeviceId){
				logger.info("CommonUtility :: getDeviceIds(), device:"+device);
			}
			
			
		}catch (Exception e){
			logger.error("CommonUtility :: getDeviceIds(), Exception:"+e.getMessage());
		}finally{
			deviceDataFile=null;
			workbook=null;
			sheet=null;
		}
        
		return arrDeviceId;
		
	}


}
