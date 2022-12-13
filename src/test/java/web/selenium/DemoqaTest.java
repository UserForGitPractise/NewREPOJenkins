package web.selenium;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.selenium.pages.ElementCheckBox;
import web.selenium.pages.ElementsTextBox;

import static rest.SetDriver.setOCDriver;


@Tag("web-tests")
@DisplayName("Tests for UI check")
@Feature("UI Implementaion of forms")
public class DemoqaTest {
    @BeforeAll
    public static void chromeDriverSetUp() {
        setOCDriver();
    }

    @Test
    @DisplayName("Fill in Element > TextBox form")
    @Story("Text Box implementation")
    @Description("Move to Element folder, fill in TextBox form and send form")
    public void ElementTextBoxFill() {
        new ElementsTextBox()
                .fillInDate()
                .clickSubmitButton()
                .checkRegisteredUserData();
    }

    @Test
    @DisplayName("Verify elements at Forms practise form")
    @Story("Forms folder implementation")
    @Description("Verify elements at Forms practise form: page title, selected item, item's folder to be expanded")
    public void checkFormsPageElementView() {
        new ElementCheckBox()
                .checkFormsPageTitle()
                .checkSuggestionToSelectItemText()
                .checkFormElementOfPannelToBeOpened();
    }

    @Test
    @DisplayName("Fill in Forms > Practise form")
    @Story("Forms folder implementation")
    @Description("Move to Forms folder, fill in PractiseForm and send form")
    public void fillInThePracticeForm() {
        new ElementCheckBox()
                .clickForm()
                .fillTheFormFields()
                .clickSubmitButton()
        ;
    }

}
