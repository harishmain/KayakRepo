package com.newt.perfecto.selenium.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.log4j.Logger;
import com.newt.perfecto.selenium.constants.ApplicationConstants;
import com.newt.perfecto.selenium.logger.Log4jWrapper;
import com.newt.perfecto.selenium.util.PropertyUtility;

public class PropertyUtility implements ApplicationConstants {
	
		private final static Logger logger = Logger.getLogger(PropertyUtility.class);
		static Log4jWrapper slog = new Log4jWrapper();
		
		/**
		 * @date 10/05/2015
		 * @author Harish Main
		 * @description Method used to read property file
		 * @return No return value
		 */
		public void getProperties(){
		
			logger.info("PropertyUtility :: getProperties, invoked...");
			
			try{
				InputStream input =  null;
				
				Properties prop = new Properties();
				input = new FileInputStream(PROPERTY_FILENAME);
				
				prop.load(input);
				// get the property value and print it out
				System.out.println("####Properties.getProperty usage####");
				System.out.println(prop.getProperty("username"));
				System.out.println();

		        System.out.println("####Properties.stringPropertyNames usage####");
		        
				for (String property : prop.stringPropertyNames()) {
				    String value = prop.getProperty(property);
				    System.out.println(property + "=" + value);
				}
				
			}catch(Exception e){
				
			}
			
		}
		
		/**
		 * @date 10/05/2015
		 * @author Harish Main
		 * @description Method used to read the environment property file and get property value for property key passed
		 * @param String as propertyKey for which needs to return property value
		 * @return propertyValues as String
		 */
		public String getEnvironmentProperty(String propertyKey){
			
			
			logger.info("PropertyUtility :: getEnvironmentProperty, invoked...");
			String propertyValue=null;
			
			try {
				
					InputStream input =  null;
					
					Properties prop = new Properties();
					input = new FileInputStream(PROPERTY_FILENAME);
					
					prop.load(input);					
					propertyValue=prop.getProperty(propertyKey);
					logger.info("PropertyUtility :: getEnvironmentProperty, propertyKey:"+propertyKey+", propertyValue:"+propertyValue);

			 }catch (IOException e) {
			    	logger.error("PropertyUtility :: getEnvironmentProperty, Exception:"+e);
			 }

			return propertyValue;
			
		}
		
		/**
		 * @date 10/05/2015
		 * @author Harish Main
		 * @description Method used to get the property value for the property key passed
		 * @param propertyFileName String as the property file name
		 * @param propertyKey String as the property key
		 * @return propertyValues as String
		 */
		public String getProperty(String propertyFileName, String propertyKey){
			
			
			logger.info("PropertyUtility :: getPropertyValue, invoked...");
			String propertyValue=null;
			
			try {				
					InputStream input =  null;
					
					Properties prop = new Properties();
					input = new FileInputStream(propertyFileName);
					
					prop.load(input);					
					propertyValue=prop.getProperty(propertyKey);
					logger.info("PropertyUtility :: getPropertyValue, propertyFileName:"+propertyFileName+", propertyKey:"+propertyKey+",  propertyValue:"+propertyValue);

			}catch (IOException e) {
			    	logger.error("PropertyUtility :: getPropertyValue, propertyFileName:"+propertyFileName+", propertyKey:"+propertyKey+", Exception:"+e);
			}

			return propertyValue;
			
		}	
		


}
