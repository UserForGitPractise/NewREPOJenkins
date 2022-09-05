package web;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

public class ElementsTextBox extends BasePage {

    private static final String LOGIN = "Ivan";
    private static final String EMAIL = "test@test.com";
    private static final String CURRENTADDRESS = "Los-Angeles";
    private static final String PERMANENTADDRESS = "New-York";
    private By userNameBox = By.xpath("//input[@id='userName']");
    private By emailBox = By.xpath("//input[@id='userEmail']");
    private By currentAddressBox = By.xpath("//textarea[@id='currentAddress']");
    private By permanentAddressBox = By.xpath("//textarea[@id='permanentAddress']");
    private By submitButton = By.xpath("//button[@id='submit']");
    private By elementDirectoryButton = By.xpath("//*[@id='app']/div/div/div[2]/div/div[1]/div/div[1]");
    private By textBoxElementDirectoryButton = By.xpath("//*[@class='text']");

    public ElementsTextBox(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL);

        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementDirectoryButton);
        //waitVisibility(elementDirectoryButton);
        driver.findElement(elementDirectoryButton).click();
        driver.findElement(textBoxElementDirectoryButton).click();
    }
    public ElementsTextBox fillInDate(){
        driver.findElement(userNameBox).sendKeys(LOGIN);
        driver.findElement(emailBox).sendKeys(EMAIL);
        driver.findElement(currentAddressBox).sendKeys(CURRENTADDRESS);
        driver.findElement(permanentAddressBox).sendKeys(PERMANENTADDRESS);
        return this;
    }
    public ElementsTextBox clickSubmitButton(){
        WebElement button = driver.findElement(submitButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
        return this;
    }
    public void checkRegisteredUserData(){
        waitVisibility(By.xpath("//div[@class='border col-md-12 col-sm-12']"));
        Assertions.assertDoesNotThrow(
                ()-> wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='border col-md-12 col-sm-12']")))
        );
        List<WebElement> listOfUserData = new LinkedList<>();
        for (int i = 1; i <= 4; i++) {
            WebElement b = driver.findElement(By.xpath("//p[@class='mb-1'][" + i + "]"));
            listOfUserData.add(b);
        }
        assertThat(listOfUserData.get(0).getText()).isEqualTo("Name:" + LOGIN);
        assertThat(listOfUserData.get(1).getText()).isEqualTo("Email:" + EMAIL);
        assertThat(listOfUserData.get(2).getText()).isEqualTo("Current Address :" + CURRENTADDRESS);
        assertThat(listOfUserData.get(3).getText()).isEqualTo("Permananet Address :" + PERMANENTADDRESS);

        driver.quit();
    }
}