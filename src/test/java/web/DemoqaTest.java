package web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.util.Objects.isNull;
@Tag("web-tests")
public class DemoqaTest {

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


//        if (System.getProperty("driver").equals("windows")) {
//            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
//        }
//        else {
//            System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver_linux");
//        }


    }

    @BeforeEach
    public void driverStart() {
        options.addArguments("--headless","--no-gpu");
        driver.set(new ChromeDriver(options));
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
