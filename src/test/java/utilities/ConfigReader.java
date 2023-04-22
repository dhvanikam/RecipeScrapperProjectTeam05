package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties properties;
	private final static String propertyFilePath = "./src/test/resources/config/config.properties";

	public static void loadConfig() throws Throwable {

		try {
			FileInputStream fis;
			fis = new FileInputStream(propertyFilePath);
			properties = new Properties();
			try {
				properties.load(fis);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public static String getBrowserType() {
		String browser = properties.getProperty("browser");
		Loggerload.info("Get property BrowserType");
		if (browser != null)
			return browser;
		else
			throw new RuntimeException("browser not specified in the Configuration.properties file.");
	}

	public static String getApplicationUrl() {
		String url = properties.getProperty("url");
		if (url != null)
			return url;
		else
			throw new RuntimeException("url not specified in the Configuration.properties file.");
	}
	public static String getRecipePath() {
		String url = properties.getProperty("recipedatapath");
		if (url != null)
			return url;
		else
			throw new RuntimeException("morbiditi not specified in the Configuration.properties file.");
	}
	public static String getAllergyPath() {
		String url = properties.getProperty("allergyrecipedatapath");
		if (url != null)
			return url;
		else
			throw new RuntimeException("morbiditi not specified in the Configuration.properties file.");
	}

}
