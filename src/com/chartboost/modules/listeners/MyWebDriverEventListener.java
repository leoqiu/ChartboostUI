package com.chartboost.modules.listeners;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.chartboost.modules.factories.LoggerSingleton;

/**
 * Created by Leo on 2/28/14.
 */

public class MyWebDriverEventListener implements WebDriverEventListener{

	//private static Logger logger = Logger.getLogger("LoggingExample");
	private LoggerSingleton logger = LoggerSingleton.getInstance();
	
	
    @Override
    public void beforeNavigateTo(String s, WebDriver webDriver) {
//    	System.out.println("Before navigating to url printing the browser associated capabilities");
//    	System.out.println(s);
//		System.out.println(webDriver.toString());
    	logger.info("Navigating to page ->  " + s);
    	logger.info("WebDriver info ->  " + webDriver.toString());
    }

    @Override
    public void afterNavigateTo(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
    	//System.out.println(webElement.toString() + " before find by");
    }

    @Override
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
    	//System.out.println(by.toString() + " after find by");
    	
    	logger.info(by.toString() + " after find by");
    }

    @Override
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
    	//System.out.println(webElement.toString() + " has been clicked");
    }

    @Override
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver) {

    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {
    	//System.out.println(throwable.getMessage() + " exception has been throwed");
    }
}
