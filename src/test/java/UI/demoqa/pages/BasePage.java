package UI.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected static  final  String BASE_URL = "https://demoqa.com/";
    private static final Duration DEFAULT_TIME_TO_WAIT_DURATION = Duration.ofSeconds(3);

    protected WebDriver  driver;
    protected WebDriverWait wait;
    public BasePage(WebDriver driver){
        this.driver= driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIME_TO_WAIT_DURATION);
    }
    protected void waitVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
