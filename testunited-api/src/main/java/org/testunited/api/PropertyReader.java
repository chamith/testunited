package org.testunited.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	static Properties prop = new Properties();
	static final String FILE_PREFIX = "application";
	static final String FILE_SUFFIX = ".properties";
	
	static private void loadFile(String env) {
		
		System.out.println("Loading property file for environment: " + env);

		InputStream inputStream = null;
		String envQualifier = (env == "")? "": "." + env;
		
		String propFileName = FILE_PREFIX + envQualifier + FILE_SUFFIX;

		try {
 
			inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				//throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
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
		
		System.out.println("-----------PROPERTIES------------");
		for(var p:prop.keySet()) System.out.printf("\t%s:%s\n",p, prop.get(p));
		System.out.println("---------------------------------");
	}
	static {
		loadFile("");
		loadFile(System.getProperty("env"));
	}
 
	
	public static String getPropValue(String key) {
		return prop.getProperty(key);
	}
}
