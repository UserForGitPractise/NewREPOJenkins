package web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoqaTest {


    ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    @BeforeAll
    public static void chromeDriverSetUp() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");


    }

    @BeforeEach
    public void driverStart() {

        driver.set(new ChromeDriver());
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12)); - неявное или безусловное ожидание

    }

    @AfterEach
    public void driverQuit() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    @Test
    public void login() {

        new ElementsTextBox(driver.get())
                .fillInDate()
                .clickSubmitButton().checkRegisteredUserData();
    }

    @Test
    public void checkFormsPageElementView() {
        new ElementCheckBox(driver.get())
                .checkFormsPageTitle()
                .checkSuggestionToSelectItemText()
                .checkFormElementOfPannelToBeOpened();
    }

    @Test
    public void fillInThePracticeForm() {
        new ElementCheckBox(driver.get())
                .clickForm()
                .fillTheFormFields()
        .clickSubmitButton()
        ;
    }

}
