package com.chartboost.modules.uiobjs;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ResourceBundle;

/**
 * Created by Leo on 2/28/14.
 */
public class ChartboostBlogsUI extends ChartboostSuperUI {

    protected WebDriver driver;
    protected WebDriverWait waitDriver;


//    public ChartboostBlogsUI(WebDriver driver, WebDriver driver1, WebDriverWait waitDriver) {
//        super(driver);
//        driver = driver1;
//        this.waitDriver = waitDriver;
//    }

//    public ChartboostBlogsUI(WebDriver driver, WebDriverWait waitDriver) {
//        this.driver = driver;
//        this.waitDriver = waitDriver;
//    }

//    public ChartboostBlogsUI(WebDriver driver, WebDriver driver1) {
//        super(driver);
//        driver = driver1;
//    }

    public ChartboostBlogsUI(WebDriver driver, ResourceBundle locatorBundle, ResourceBundle resBundle) {
        super(driver, locatorBundle, resBundle);
    }

    @Override
    public void recycle() {
        super.recycle();
    }


}




















































