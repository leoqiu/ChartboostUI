package com.chartboost.tests.smoke;

import com.chartboost.modules.constants.ResourceType;
import com.chartboost.modules.factories.CBDriverFactory;
import com.chartboost.modules.factories.LoggerSingleton;
import com.chartboost.modules.factories.ResourceBundleFactory;
import com.chartboost.modules.uiobjs.ChartboostJobsUI;
import com.chartboost.tests.ChartboostTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Leo on 3/4/14.
 */

public class ChartboostJobsUISmokeTest extends ChartboostTest {

    ChartboostJobsUI chartboostJobsUI;
    
    
    @Parameters(value = {"browser-type", "language", "region"})
    @BeforeClass
    public void beforeClass(String browserType, String language, String region) {
    	super.beforeClass(browserType, language, region);
        chartboostJobsUI = new ChartboostJobsUI(CBDriverFactory.getDriver(browserType)
        ,ResourceBundleFactory.getInstance(language, region, ResourceType.LOCATORS, ChartboostJobsUI.getClassName())
        , ResourceBundleFactory.getInstance(language, region, ResourceType.RESOURCE, ChartboostJobsUI.getClassName()));
    }

    @AfterClass
    public void afterClass () {
    	super.afterClass();
    	chartboostJobsUI.recycle();    	
    }

    @Test
    public void testTipsShowsWhenMouseOverBenefitsPerksSection () {
        chartboostJobsUI.getTipsWhenMouseOverBenefitsPerks();
    }














}
