package web.selenium.pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ElementCheckBox extends BasePage {
    private SelenideElement baseTabButtonForms = $x("//*[text()='Forms']");
    private SelenideElement pageTitleName = $(".main-header");
    private SelenideElement selectItemSuggestionText = $x("//div[@class='row']/div[@class='col-12 mt-4 col-md-6']");
    private SelenideElement selectedPannelItem;

    private SelenideElement firstNameField = $("#firstName");
    private SelenideElement lastNameField = $("#lastName");
    private SelenideElement emailField = $("#userEmail");
    private SelenideElement genderRadioButton = $("#gender-radio-1");
    private SelenideElement mobileField = $("#userNumber");
    private SelenideElement subjectsField = $("#subjectsInput");
    private SelenideElement hobbiesField = $("#hobbies-checkbox-1");
    private SelenideElement pictureField = $("#uploadPicture");
    private SelenideElement currentAddressField = $x("");
    private SelenideElement stateField = $x("");

    public ElementCheckBox() {
        selectedPannelElementOfItem = $x("//li/span[@class='text' and text()='Practice Form']");
        selectedPannelItem = $x("//li/span[@class='text' and text()='Practice Form']".concat("//ancestor::div[1]"));
        open(BASE_URL);
        baseTabButtonForms.scrollIntoView(true).click();
    }

    public ElementCheckBox checkFormsPageTitle() {
        assertThat(pageTitleName.getText().equals("Forms")).isTrue();


//        Date d = new Date(1);
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
//        String date = formatter.format(d);
//        String splitter[] = date.split("-");
//        String month_year = splitter[1];
//        String day = splitter[0];
//        System.out.println(month_year);
//        System.out.println(day);


        return this;
    }

    public ElementCheckBox checkSuggestionToSelectItemText() {
        assertThat(selectItemSuggestionText.getText().equals("Please select an item from left to start practice.")).isTrue();
        return this;
    }

    public ElementCheckBox checkFormElementOfPannelToBeOpened() {
        assertThat(selectedPannelElementOfItem.isDisplayed()).isTrue();
        assertThat(selectedPannelItem.getAttribute("class").equals("element-list collapse show")).isTrue();
        return this;
    }

    public ElementCheckBox clickForm() {
        selectedPannelElementOfItem.click();
        return this;
    }

    public ElementCheckBox fillTheFormFields() {
        firstNameField.sendKeys("Nick");
        lastNameField.sendKeys("Evans");
        emailField.sendKeys("test@test.com");
        ((JavascriptExecutor) WebDriverRunner.getWebDriver()).executeScript("arguments[0].click();", $("#gender-radio-1"));
        mobileField.sendKeys("7777777777");
        subjectsField.sendKeys("Math");
        hobbiesField.isSelected();
        pictureField.sendKeys(new File("src/main/resources/qweqweqwe.png").getAbsolutePath());
        return this;

    }

    public ElementCheckBox clickSubmitButton() {
        $("#submit").sendKeys(Keys.ENTER);
        $(".modal-content").isDisplayed();
        $("#closeLargeModal").isDisplayed();
        $("#closeLargeModal").isEnabled();
        return this;
    }

}
