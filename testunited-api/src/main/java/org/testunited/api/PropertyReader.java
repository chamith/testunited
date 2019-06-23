package org.testunited.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertyReader {
	static Properties prop = new Properties();
	
	static {
		InputStream inputStream = null;
		
		try {
			
			String propFileName = "application.properties";
 
			inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			} 
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
 
	
	public static String getPropValue(String key) {
		return prop.getProperty(key);
	}
}
