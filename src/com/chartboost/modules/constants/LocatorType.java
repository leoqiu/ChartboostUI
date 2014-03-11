package com.chartboost.modules.constants;

/**
 * Created by Leo on 3/4/14.
 * 
 * Locator type
 * </br>
 * 1. id
 * 2. xpath
 * 3. selector
 * 
 * jobs.join.us.tab.<b>xpath</b>=(//a[contains(text(), 'Join Us')])[1]
 * jobs.benefits.perks.img.base.xpath=(//div[@class='four columns tagline perks cb-tiptip']/img)
 * jobs.hover.tips.<b>id</b>=tiptip_content
 * 
 * @author Leo
 * @version 1.0
 */
public class LocatorType {

    public static final String ID = "id";
    public static final String XPATH = "xpath";
    public static final String CSS_SELECTOR = "selector";

}
