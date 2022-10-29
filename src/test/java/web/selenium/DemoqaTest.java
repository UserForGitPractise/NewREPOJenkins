package web.selenium;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import web.selenium.pages.ElementCheckBox;
import web.selenium.pages.ElementsTextBox;

import java.net.MalformedURLException;


@Tag("web-tests")
@DisplayName("Tests for UI check")
@Feature("UI Implementaion of forms")
public class DemoqaTest{
    ChromeOptions options = new ChromeOptions();
    ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeAll
    public static void chromeDriverSetUp() {

        try {
            if (System.getProperty("driver").equals("linux")) {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_linux");
            } else {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
            }
        } catch (NullPointerException e){
            System.out.println("OC for chromedriver is not set up");
        }


    }

    @BeforeEach
    public void driverStart() throws MalformedURLException {
        //options.addArguments("--headless","--no-gpu");
        //ChromeOptions options1 = new ChromeOptions();
       // driver.set(new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"),options1));
        driver.set(new ChromeDriver(options));
//        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12)); - неявное или безусловное ожидание
    }


    @AfterEach
    public void driverQuit() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    @Test
    @DisplayName("Log in")
    @Story("Log in to the system")
    @Description("Log in and check registered user data")
    public void login() {
        new ElementsTextBox(driver.get())
                .fillInDate()
                .clickSubmitButton()
                .checkRegisteredUserData();
    }

    @Test
    @DisplayName("Check user forms page element to be in view")
    @Story("User form to be viewable")
    @Description("Check Page Form title, check text (suggested to select item), check form element of panel to be Opened")
    public void checkFormsPageElementView() {
        new ElementCheckBox(driver.get())
                .checkFormsPageTitle()
                .checkSuggestionToSelectItemText()
                .checkFormElementOfPannelToBeOpened();
    }

    @Test
    @DisplayName("Filling in the Practise form")
    @Story("Implementation of practise form")
    @Description("Check filling in form fields and click Submit Button")
    public void fillInThePracticeForm() {
        new ElementCheckBox(driver.get())
                .clickForm()
                .fillTheFormFields()
        .clickSubmitButton()
        ;
    }

}
