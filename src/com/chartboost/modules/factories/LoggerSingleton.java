package com.chartboost.modules.factories;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.chartboost.modules.constants.ConfigConstants;
import com.chartboost.modules.utils.MyAppender;

public class LoggerSingleton {
	
	
	private static final LoggerSingleton singleLogger = new LoggerSingleton();
	
	private static Logger logger = Logger.getLogger("");
	private Properties p;
	
	//static MyAppender app = new MyAppender();
	public static Logger getLogger() {
		//logger.addAppender(app);
		return logger;
	}

	private LoggerSingleton () {
		String log4JPropertyFile = ConfigConstants.LOG_CONFIG_LOCATION;
		p = new Properties();
		
		try {
		    p.load(new FileInputStream(log4JPropertyFile));
		    PropertyConfigurator.configure(p);
		    //logger.info("Wow! I'm configured!");
		} catch (IOException e) {
		    //DAMN! I'm not....

		}
	}
	
	public static LoggerSingleton getInstance () {
		return singleLogger;
	}
	
    public void info(String myclass, String msg) {
    	logger.info("[" + myclass + "] " + msg);

    }
    
    public void info(String msg) {
    	logger.info(msg);

    }

    public void error(String myclass, String msg, Exception ce) {               
    	logger.error("[" + myclass + "] " + msg, ce);      
    }

    public void warning(String myclass, String msg) {
    	logger.warn("[" + myclass + "] " + msg);
    }  

}
