package com.chartboost.modules.uiobjs;

import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Leo on 2/28/14.
 *
 * ChartboostJobsUI includes all the operations on the page - "https://www.chartboost.com/jobs"
 * extends from {@link com.chartboost.modules.uiobjs.ChartboostSuperUI}
 *
 *
 * @author Leo
 * @version 1.0
 *
 */

public class ChartboostJobsUI extends ChartboostSuperUI{

    //One of the four tabs on the top - benefits & perks
    private static final String jobs_benefits_perks_tab_xpath = "jobs.benefits.perks.tab.xpath";
    //base xpath for the six divs under "Our Values" section
    private static final String jobs_benefits_perks_img_base_xpath = "jobs.benefits.perks.img.base.xpath";
    //mouse hover on "Our Values" section
    //<div id="jobs.hover.tips.id"> show mouse hover tips
    private static final String jobs_hover_tips_id = "jobs.hover.tips.id";


    /**
     *
     * Constructor
     *
     * @see com.chartboost.modules.uiobjs.ChartboostSuperUI#ChartboostSuperUI(org.openqa.selenium.WebDriver, java.util.ResourceBundle, java.util.ResourceBundle)
     * @param driver
     * @param locatorBundle
     * @param resBundle
     */
    public ChartboostJobsUI(WebDriver driver, ResourceBundle locatorBundle, ResourceBundle resBundle) {
        super(driver, locatorBundle, resBundle);
    }


    /**
     *
     * Static method to return the name of the class
     *
     * @return String
     */
    public static String getClassName () {
        return ChartboostJobsUI.class.getSimpleName();
    }


    /**
     *
     * Mouse hover each of the six parts under "Our Values" section
     * Get the tip content for each mouse hover and save it into
     * a ArrayList<String>
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getTipsWhenMouseOverBenefitsPerks() {

        ArrayList<String> tips = new ArrayList<String>();

        openURL("https://www.chartboost.com/jobs");
        getElement(getLocatorBean(jobs_benefits_perks_tab_xpath)).click();

        for (int i = 1; i <= 6; i++) {
            String curLocator = getBaseLocator(jobs_benefits_perks_img_base_xpath) + "[" + i + "]";
            mouseOver(curLocator);
            tips.add(getElement(getLocatorBean(jobs_hover_tips_id)).getText());
        }

        return tips;
    }


    @Override
    public void recycle() {
        super.recycle();
    }
}
