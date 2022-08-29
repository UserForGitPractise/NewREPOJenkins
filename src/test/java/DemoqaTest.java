import UI.demoqa.pages.BasePage;
import UI.demoqa.pages.ElementsTextBox;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoqaTest {

    ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    @BeforeAll
    public static void chromeDriverSetUp () {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\toosm\\Downloads\\chromedriver_win32\\chromedriver.exe");

    }

    @BeforeEach
    public void driverStart(){
        driver.set(new ChromeDriver());
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(12)); - неявное или безусловное ожидание

    }
//    @AfterEach
//    public void driverQuit(){
//        if (driver.get() != null) {
//            driver.get().quit();
//        }
//    }
    @Test
    public void login (){
        new ElementsTextBox(driver.get())
                .fillInDate()
                .clickSubmitButton().checkRegisteredUserData();
    }


}
