package com.chartboost.modules.factories;

import com.chartboost.modules.constants.BrowserType;
import com.chartboost.modules.constants.ConfigConstants;
import com.chartboost.modules.listeners.MyWebDriverEventListener;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Leo on 2/28/14
 * 
 * CBDriverFactory generates driver instance to drive
 * corresponding browsers
 * 
 * @see come.chartboost.listeners.MyWebDriverEventListener
 * @author Leo
 * @version 1.0
 * 
 */

public class CBDriverFactory {

	private CBDriverFactory () {
		
	}

	/**
	 * static synchronized method generate driver
	 * 
	 * @param browserType
	 * @return
	 */
    public synchronized  static WebDriver getDriver (String browserType) {

        WebDriver driver = null;

        /*
         * generate driver to drive firefox
         */
        if(browserType.equals(BrowserType.FF)) {
            File profileDir = new File(ConfigConstants.FF_PROFILE);
            FirefoxProfile profile = new FirefoxProfile(profileDir);
            /*
             * decorate pattern, make driver able to register with WebDriverEventListener
             */
            driver = new EventFiringWebDriver(new FirefoxDriver(profile)).register(new MyWebDriverEventListener());
        } else if (browserType.equals(BrowserType.CHROME)) {
        	try {
				driver = new EventFiringWebDriver(new RemoteWebDriver (new URL("http://localhost:9515"), DesiredCapabilities.chrome()))
							.register(new MyWebDriverEventListener()) ;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
        }


        /*
         * maximize browser
         * 
         * http://stackoverflow.com/questions/12211781/how-to-maximize-window-in-chrome-using-webdriver-python
         */
        if(driver != null) {
        	//driver.manage().window().setSize(targetSize);
        	driver.manage().window().maximize();
        }
            


        return driver;

    }



}
