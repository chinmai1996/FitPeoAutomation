package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Utilities {
	
	public static Properties appProperties = new Properties();
    public static String propertiesFilePath = System.getProperty("user.dir") + "/src/main/resources/application.properties";

    static {
        try {
            appProperties.load(new FileInputStream(new File(propertiesFilePath)));
        } catch (Exception e) {
            System.out.println("Exception occurred while reading the properties file" + e.getMessage());
        }
    }

    public static String getProperty(String propertyName) {
        return appProperties.getProperty(propertyName);
    }

}
