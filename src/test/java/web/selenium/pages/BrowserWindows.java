package web.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import web.selenium.NewTab;
import web.selenium.pages.BasePage;

public class BrowserWindows extends BasePage {
    private By browserWindowsDirectory = By.xpath("//*[text()='Browser Windows' and @class='text']");
    private By newTabButton = By.xpath("//button[@id='tabButton']");

    public BrowserWindows(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL);
        driver.findElement(browserWindowsDirectory).click();
    }
    public NewTab createNewTab (){
        driver.findElement(newTabButton).click();
        return new NewTab(driver);
    }
}
