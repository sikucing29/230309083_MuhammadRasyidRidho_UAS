package com.demoblaze.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    
    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Configuration file not found, using defaults");
            // Set default values
            properties.setProperty("browser", "chrome");
            properties.setProperty("timeout", "30");
            properties.setProperty("headless", "false");
            properties.setProperty("base.url", "https://www.demoblaze.com");
        }
    }
    
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public static String getBrowser() {
        return getProperty("browser");
    }
    
    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }
    
    public static String getBaseUrl() {
        return getProperty("base.url");
    }
}
