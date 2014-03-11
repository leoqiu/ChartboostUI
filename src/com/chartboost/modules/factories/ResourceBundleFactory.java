package com.chartboost.modules.factories;

import com.chartboost.modules.constants.ResourceType;
import com.chartboost.modules.uiobjs.ChartboostJobsUI;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Leo on 3/3/14.
 * 
 * ResourceBundleFactory generate resource bundle instance for each UI Object,
 * </br>
 * Two types of bundles
 * 1. locator bundle
 * 2. resource bundle
 * 
 * @author Leo
 * @version 1.0
 * 
 */


public class ResourceBundleFactory {

	/*
	 * It's better for every UI Object should have its own bundle instance than some
	 * UI Objects for different testng execution threads will possibly share the
	 * same bundle instance which is NOT thread safe
	 */
    //private static HashMap<String, ResourceBundle> instances = new HashMap<String, ResourceBundle>(20);
	
	//where ResourceBundle file locate
    private static final String BASE_PATH = "resources";
    private static final String FILE_NAME_LOCATOR = "locators";
    private static final String FILE_NAME_RESOURCE = "res";

    //default language for resource bundle
    private static final String LANGUAGE = "en";
    //default region for resource bundle
    private static final String REGION = "US";


    /*
     * private constructor make it not able to be instantiated
     */
    private ResourceBundleFactory () {

    }


    /**
     * When ResourceBundle resource files are not in classpath (under src folder)
     * Get the ClassLoader to load ResourceBundle resource files outside classpath
     * 
     * @return ClassLoader
     */
    private static ClassLoader getClassLoader () {
    	File file = new File(BASE_PATH);
    	ClassLoader loader = null;
    	
    	try {
    		URL[] urls={file.toURI().toURL()};
    		loader = new URLClassLoader(urls); 
    	} catch (MalformedURLException e){
    		e.printStackTrace();
		}	
    	
    	return loader;
    }
    
    /**
     * Generate ResourceBundle instance by taking 4 parameters
     * 
     * @param language
     * @param region
     * @param resourceType
     * @param objName
     * @return ResourceBundle
     */
    public synchronized static ResourceBundle getInstance (String language, String region, String resourceType, String objName) {

        String resourceBundleFileName = getResourceBundleFileName(resourceType, objName);
//        String key = getMapKey (getLocaleCode(language, region), resourceBundleFileName);
        Locale locale = new Locale(language, region);

//        if(!instances.containsKey(key))
//            instances.put(key, ResourceBundle.getBundle(resourceBundleFileName, locale));
//        return instances.get(key);
        
        return ResourceBundle.getBundle(resourceBundleFileName, locale, getClassLoader ());
    }

    /**
     * Generate default locator bundle by taking default 
     * language and region and ResourceType.LOCATORS
     * 
     * @param objName
     * @return ResourceBundle
     */
    public synchronized static ResourceBundle getDefaultLocatorInstance (String objName) {

        String resourceBundleFileName = getResourceBundleFileName(ResourceType.LOCATORS, objName);
//        String key = getMapKey (getLocaleCode(LANGUAGE, REGION), resourceBundleFileName);
        Locale locale = new Locale(LANGUAGE, REGION);

//        if(!instances.containsKey(key))
//            instances.put(key, ResourceBundle.getBundle(resourceBundleFileName, locale));
//        return instances.get(key);
        
        return ResourceBundle.getBundle(resourceBundleFileName, locale, getClassLoader ());
    }

    /**
     * Generate default locator bundle by taking default 
     * language and region and ResourceType.RESOURCE
     * 
     * @param objName
     * @return ResourceBundle
     */
    public synchronized static ResourceBundle getDefaultResInstance (String objName) {

        String resourceBundleFileName = getResourceBundleFileName(ResourceType.RESOURCE, objName);
 //       String key = getMapKey (getLocaleCode(LANGUAGE, REGION), resourceBundleFileName);
        Locale locale = new Locale(LANGUAGE, REGION);

//        if(!instances.containsKey(key))
//            instances.put(key, ResourceBundle.getBundle(resourceBundleFileName, locale));
//        return instances.get(key);
        
        return  ResourceBundle.getBundle(resourceBundleFileName, locale, getClassLoader ());
    }

//    private static String getLocaleCode (String language, String region) {
//        return language + "_" + region;
//    }

    /**
     * Generate ResourceBundle file name with absolute path
     * </br>
     * ResourceBundle data files locate under src,
     * {@link resources}}
     * 
     * @param resourceType
     * @param ObjName
     * @return String
     */
    private static String getResourceBundleFileName (String resourceType, String ObjName) {
//        if (resourceType.equals(ResourceType.LOCATORS))
//            return BASE_PATH + ObjName + "_" + FILE_NAME_LOCATOR;
//        else
//            return BASE_PATH + ObjName + "_" + FILE_NAME_RESOURCE;
        
        
        if (resourceType.equals(ResourceType.LOCATORS))
            return ObjName + "_" + FILE_NAME_LOCATOR;
        else
            return ObjName + "_" + FILE_NAME_RESOURCE;
   	
    }


//    private static String getMapKey (String localeCode, String resourceBundleFileName) {
//            return resourceBundleFileName + "_" + localeCode;
//    }


//    public static void main (String[] args) throws Exception{
//        ResourceBundle locatorBundle1 = ResourceBundleFactory.getInstance("en", "US", ResourceType.RESOURCE, "ChartboostJobsUI");
//        ResourceBundle locatorBundle2 = ResourceBundleFactory.getInstance("zh", "CN", ResourceType.RESOURCE, "ChartboostJobsUI");
//        ResourceBundle locatorBundle3 = ResourceBundleFactory.getInstance("zh", "CN", ResourceType.RESOURCE, "ChartboostJobsUI");
//        ResourceBundle locatorBundle4 = ResourceBundleFactory.getInstance("en", "US", ResourceType.RESOURCE, "ChartboostJobsUI");
//        ResourceBundle locatorBundle5 = ResourceBundleFactory.getInstance("en", "US", ResourceType.RESOURCE, "ChartboostJobsUI");
//        ResourceBundle locatorBundle6 = ResourceBundleFactory.getInstance("zh", "CN", ResourceType.RESOURCE, "ChartboostJobsUI");
//        //new String(locatorBundle.getString(code).getBytes("ISO-8859-1"), "UTF-8")
//        String usd1 = new String(locatorBundle1.getString("USD").getBytes("ISO-8859-1"), "UTF-8");
//        String usd2 = new String(locatorBundle2.getString("USD").getBytes("ISO-8859-1"), "UTF-8");
//        String usd3 = new String(locatorBundle3.getString("USD").getBytes("ISO-8859-1"), "UTF-8");
//        String usd4 = new String(locatorBundle4.getString("USD").getBytes("ISO-8859-1"), "UTF-8");
//        String usd5 = new String(locatorBundle5.getString("USD").getBytes("ISO-8859-1"), "UTF-8");
//        String usd6 = new String(locatorBundle6.getString("USD").getBytes("ISO-8859-1"), "UTF-8");
//
//        System.out.println(usd1);
//        System.out.println(usd2);
//        System.out.println(usd3);
//        System.out.println(usd4);
//        System.out.println(usd5);
//        System.out.println(usd6);
//    }


}






















