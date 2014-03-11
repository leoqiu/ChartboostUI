package com.chartboost.modules.uiobjs;

import com.chartboost.modules.constants.LocatorType;

import com.chartboost.modules.beans.LocatorBean;
import com.chartboost.modules.factories.ResourceBundleFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * Created by Leo on 2/28/14.
 *
 * This is the super class of all Chartboost UI objects, like the role of
 * Object in J2SE API
 *
 *
 * @author Leo
 * @version 1.0
 *
 */
public class ChartboostSuperUI {

    //protected member variables, be inherited
    //by all the subclasses
    protected WebDriver driver;
    protected WebDriverWait waitDriver;
    protected ResourceBundle locatorBundle;
    protected ResourceBundle resBundle;
    protected Actions action;

    /**
     * Constructor of super class
     * initialize all the protected member variables
     *
     *
     * @param driver
     * @param locatorBundle
     * @param resBundle
     */
    public ChartboostSuperUI(WebDriver driver, ResourceBundle locatorBundle, ResourceBundle resBundle) {

        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.waitDriver = new WebDriverWait(driver, 10);

        this.locatorBundle = locatorBundle;
        this.resBundle = resBundle;

        action = new Actions(this.driver);

    }

    /**
     * Static method to get the name of the class itself
     *
     * @return String (name of the class)
     */
    public static String getClassName () {
        return ChartboostSuperUI.class.getName();
    }

    /**
     * Parse ResourceBundle key to identify locator type
     * <br/>
     * For example : ResourceBundle code = jobs.benefits.perks.tab.xpath
     * the locator type will xpath which is the last part of the bundle code
     * <br/>
     * private method will only be used with the class
     *
     * @param code
     * @return String (locatorType)
     */
    private String getLocatorType (String code) {
        String[] strs = code.split("\\.");
        return strs[strs.length-1];
    }

    /**
     * Take the unique code (the key of key=value) of a ResourceBundle entry to return
     * a LocatorBean
     *
     *
     * @param code
     * @return LocatorBean {@link com.chartboost.modules.beans.LocatorBean}
     */
    protected LocatorBean getLocatorBean (String code) {

        String locatorVal = null;
        String locatorType = null;

        if (locatorBundle == null)
            locatorBundle = ResourceBundleFactory.getDefaultLocatorInstance(getClassName ());

        try {
            //take care of internationalization encoding issue
            locatorVal = new String(locatorBundle.getString(code).getBytes("ISO-8859-1"), "UTF-8");
            if(getLocatorType(code).equals(LocatorType.XPATH))
                locatorType = LocatorType.XPATH;
            else if(getLocatorType(code).equals(LocatorType.ID))
                locatorType = LocatorType.ID;
            else   //css selector
                locatorType = LocatorType.CSS_SELECTOR;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return new LocatorBean(locatorVal, locatorType);
    }

    /**
     * Base locator will always be xpath, can not be css-selector ,id or name
     * </br>
     * For example
     * jobs.benefits.perks.img.base.xpath=(//div[@class='four columns tagline perks cb-tiptip']/img)
     * </br>
     * Then the sub-locators extend from the base locator could be
     * </br>
     * (//div[@class='four columns tagline perks cb-tiptip']/img)[1]
     * (//div[@class='four columns tagline perks cb-tiptip']/img)[2]
     * (//div[@class='four columns tagline perks cb-tiptip']/img)[3]
     * ...
     * (//div[@class='four columns tagline perks cb-tiptip']/img)[n]
     *
     * @param code
     * @return String (BaseLocator)
     */
    protected String getBaseLocator (String code) {
        String locator = null;

        if (locatorBundle == null)
            locatorBundle = ResourceBundleFactory.getDefaultLocatorInstance(getClassName());

        try {
            locator = new String(locatorBundle.getString(code).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return locator;
    }

    /**
     * Resource value will always be a string, so this is
     * as simple as ResourceBundle.getString(code), be aware
     * this also needs to take care of internationalization 
     * encoding issue
     * 
     * @param code
     * @return String (res)
     */
    protected String getRes (String code) {

        String res = null;

        if (resBundle == null)
            resBundle = ResourceBundleFactory.getDefaultResInstance(getClassName());

        try {
            res = new String(resBundle.getString(code).getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * Take LocatorBean as parameter,first check which way driver should
     * use to find web element (xpath, css-selector, id, name), then wait
     * till the element shows up, lastly return the web element
     * 
     * @param locatorBean
     * @return WebElement
     */
    protected WebElement getElement (LocatorBean locatorBean) {
        WebElement elem = null;

        if(locatorBean.getType().equals(LocatorType.XPATH))
            elem = driver.findElement(By.xpath(locatorBean.getVal()));
        else if(locatorBean.getType().equals(LocatorType.ID))
            elem = driver.findElement(By.id(locatorBean.getVal()));
        else
            elem = driver.findElement(By.cssSelector(locatorBean.getVal()));

        waitDriver.until(ExpectedConditions.visibilityOf(elem));
        return elem;
    }

    /**
     * Override method, driver find web element only by xpath,
     * will be used in base.xpath case, where the locator been
     * passed will be a purely xpath string
     * 
     * @see #getBaseLocator(String)
     * @param locator
     * @return WebElement
     */
    protected WebElement getElement (String locator) {
        WebElement elem = driver.findElement(By.xpath(locator));
        waitDriver.until(ExpectedConditions.visibilityOf(elem));
        return elem;
    }

    /**
     * Wait for a WebElement by LocatorBean to show up, timeout 10 seconds
     * 
     * @param locatorBean
     */
    protected void waitElement (LocatorBean locatorBean) {
        WebElement elem = null;

        if(locatorBean.getType().equals(LocatorType.XPATH))
            elem = driver.findElement(By.xpath(locatorBean.getVal()));
        else if(locatorBean.getType().equals(LocatorType.ID))
            elem = driver.findElement(By.id(locatorBean.getVal()));
        else
            elem = driver.findElement(By.cssSelector(locatorBean.getVal()));

        waitDriver.until(ExpectedConditions.visibilityOf(elem));
    }


    /**
     * Wait for a WebElement by xpath to show up, timeout 10 seconds
     * 
     * @param locatorBean
     */
    protected void waitElement (String locator) {
        WebElement elem = driver.findElement(By.xpath(locator));
        waitDriver.until(ExpectedConditions.visibilityOf(elem));
    }

    /**
     * Mouse hover on a WebElement by xpath
     * 
     * @param locator
     */
    protected void mouseOver (String locator) {
        action.pause(500).moveToElement(getElement(locator)).pause(300).build().perform();
    }

    /**
     * Mouse hover on a WebElement by LocatorBean
     * 
     * @param locatorBean
     */
    protected void mouseOver (LocatorBean locatorBean) {
        //
    }

    /**
     * open a url
     * 
     * @param url
     */
    protected void openURL (String url) {
        driver.get(url);
    }


    /**
     * Recycle all the protected member variables
     * 
     * driver.close() - It is used to close the browser or page currently which is having the focus.
	 * driver.quit() - It is used to shut down the web driver instance or destroy the web driver instance(Close all the windows).
     * 
     */
    public void recycle () {
    	this.driver.quit();
    	this.waitDriver = null;
    	this.action = null;
    	this.locatorBundle = null;
    	this.resBundle = null;
    }

}
