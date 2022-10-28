package web.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectDay {
    public void selectDay(WebDriver driver, String month, int day) {

        driver.findElement(By.cssSelector("#dateOfBirthInput")).click();

        driver.findElement(By.xpath(String.format("//*[contains(@aria-label,'%s %d')]", month, day))).click();


    }
}
