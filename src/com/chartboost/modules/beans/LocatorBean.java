package com.chartboost.modules.beans;

/**
 * Created by Leo on 3/4/14.
 * 
 * LocatorBean has two member variables
 * </br>
 * 1. val which is a string
 * 2. type which is also a string  
 * </br>
 * driver will use the val to locate a WebElement
 * in a certain way, xpath, id, name or css-selector
 * 
 * 
 * @see com.chartboost.modules.constants.LocatorType#CSS_SELECTOR
 * @see com.chartboost.modules.constants.LocatorType#ID
 * @see com.chartboost.modules.constants.LocatorType#XPATH
 * 
 * @author Leo
 * @version 1.0
 */

public class LocatorBean {

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String val;
    private String type;

    public LocatorBean() {

    }

    public LocatorBean(String val, String type) {
        this.val = val;
        this.type = type;
    }


}
