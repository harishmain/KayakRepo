package com.newt.perfecto.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import com.newt.perfecto.selenium.constants.ApplicationConstants;
import com.newt.perfecto.selenium.logger.Log4jWrapper;

/**
 * @date 10/05/2015
 * @author Harish Main
 * @description XMLParser class
 *
 */
public class XmlParser implements ApplicationConstants {
	
	private final static Logger logger = Logger.getLogger(XmlParser.class);
	static Log4jWrapper slog = new Log4jWrapper();	

	/**
	 * @date 10/05/2015
	 * @author Harish Main
	 * @description Method used to get the xpath or xml property value
	 * @param OStype as String
	 * @param property as String
	 * @return String as Xml property value
	 */
	public String getXmlValue(String OStype, String property) {
		
		String returnval = "";
		//String OStype = null;
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();
		File xmlDocument = new File(DATA_PATH+"KayakFlightsXpath.xml");
		InputSource inputSource = null;
		
		try {
			inputSource = new InputSource(new FileInputStream(xmlDocument));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("XmlParser :: getXmlValue(), Exception: "+e.getMessage());
		}
			
		try {
			//OStype = device.getProperty(MobileDeviceProperty.OS);
			//OStype = "Android";
			// OStype=TypeOS;
			if (OStype.equalsIgnoreCase("Android")) {
				returnval = xPath.evaluate("//Object/Name[.='" + property + "']/../Android", inputSource);
			}
			if (OStype.equalsIgnoreCase("iOS")) {
				returnval = xPath.evaluate("//Object/Name[.='" + property + "']/../iOS", inputSource);
			}
			
		}catch(XPathExpressionException e1){ 
			logger.error("XmlParser :: getXmlValue(), XPathExpressionException: "+e1.getMessage());
		}catch(NullPointerException e2){ 
			logger.error("XmlParser :: getXmlValue(), NullPointerException: "+e2.getMessage());
		}catch (Exception e) {
			logger.error("XmlParser :: getXmlValue(), Exception: "+e.getMessage());
		}
		
		return returnval.replace(" &amp; ", " & ");
	}
	
}
