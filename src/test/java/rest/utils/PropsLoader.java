package rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsLoader {
    public static String getProperty(String property) {
        String filePath = "./src/test/resources/secrets.properties";
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties.getProperty(property);
    }
}
