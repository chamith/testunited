package org.testunited.api;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

public class TestBundlePropertyReader {
	Properties prop = new Properties();
	private final static String DEFAULT_PROPERTY_FILE_NAME = "test.properties";

	public TestBundlePropertyReader(Class<?> consumer, String propertyFile) {
		InputStream inputStream = null;

		try {
			URL file = new URL("jar:" + consumer.getProtectionDomain().getCodeSource().getLocation().toString() + "!/"
					+ propertyFile);

			inputStream = file.openStream();

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + file + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public TestBundlePropertyReader(Class<?> consumer) {
		this(consumer, DEFAULT_PROPERTY_FILE_NAME);
	}

	public String getPropValue(String key) {
		return prop.getProperty(key);
	}
}
