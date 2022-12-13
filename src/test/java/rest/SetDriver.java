package rest;

import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.ObjectUtils;

public class SetDriver {
    public  static void setOCDriver(){
        try {
            if (System.getProperty("driver").equals("linux") && System.getProperty("headless").equals("true")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_linux");
                Configuration.headless = true;
            } else if (System.getProperty("driver").equals("linux")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_linux");
            }
        } catch (NullPointerException e){
            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_108.exe");
        }
    }
}
