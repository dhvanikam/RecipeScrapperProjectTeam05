package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Loggerload {
	private static Logger logger = LogManager.getLogger();
	
	public static void info(Object message) {
		logger.info(message);
	}
	public static void info(int num) {
		logger.info(num);
	}
	public static void warn(Object message) {
		logger.warn(message);
	}
	
	public static void debug(String message) {
		logger.debug(message);
	}
	public static void error(Object printStackTrace) {
		logger.error(printStackTrace);
		
	}
	
	

}
