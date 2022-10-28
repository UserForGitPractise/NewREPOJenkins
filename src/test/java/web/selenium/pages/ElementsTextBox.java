package web.selenium.pages;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ElementsTextBox extends BasePage {

    private static final String LOGIN = "Ivan";
    private static final String EMAIL = "test@test.com";
    private static final String CURRENTADDRESS = "Los-Angeles";
    private static final String PERMANENTADDRESS = "New-York";
    private SelenideElement userNameBox = $x("//input[@id='userName']");
    private SelenideElement emailBox = $x("//input[@id='userEmail']");
    private SelenideElement currentAddressBox = $x("//textarea[@id='currentAddress']");
    private SelenideElement permanentAddressBox = $x("//textarea[@id='permanentAddress']");
    private SelenideElement submitButton = $x("//button[@id='submit']");
    private SelenideElement elementDirectoryButton = $x("//*[@id='app']/div/div/div[2]/div/div[1]/div/div[1]");
    private SelenideElement textBoxElementDirectoryButton = $x("//*[@class='text']");

    public ElementsTextBox() {
        open(BASE_URL);
        elementDirectoryButton.click();
        textBoxElementDirectoryButton.click();
    }

    public ElementsTextBox fillInDate() {
        userNameBox.sendKeys(LOGIN);
        emailBox.sendKeys(EMAIL);
        currentAddressBox.sendKeys(CURRENTADDRESS);
        permanentAddressBox.sendKeys(PERMANENTADDRESS);
        return this;
    }

    public ElementsTextBox clickSubmitButton() {
        submitButton.scrollIntoView(true).click();
        return this;
    }

    public ElementsTextBox checkRegisteredUserData() {
        waitVisibility($x("//div[@class='border col-md-12 col-sm-12']"));
        assertDoesNotThrow(
                () -> $x("//div[@class='border col-md-12 col-sm-12']").shouldBe(Condition.visible));
        List<WebElement> listOfUserData = new LinkedList<>();
        for (int i = 1; i <= 4; i++) {
            WebElement b = $x("//p[@class='mb-1'][" + i + "]");
            listOfUserData.add(b);
        }
        assertThat(listOfUserData.get(0).getText()).isEqualTo("Name:" + LOGIN);
        assertThat(listOfUserData.get(1).getText()).isEqualTo("Email:" + EMAIL);
        assertThat(listOfUserData.get(2).getText()).isEqualTo("Current Address :" + CURRENTADDRESS);
        assertThat(listOfUserData.get(3).getText()).isEqualTo("Permananet Address :" + PERMANENTADDRESS);

        return this;
    }
}