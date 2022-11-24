package web.selenium;

import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SelectDay {
    public void selectDay(WebDriver driver, String month, int day) {
        $("#dateOfBirthInput").click();
        $x(String.format("//*[contains(@aria-label,'%s %d')]", month, day)).click();
    }
}
