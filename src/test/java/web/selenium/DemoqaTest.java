package web.selenium;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import web.selenium.pages.ElementCheckBox;
import web.selenium.pages.ElementsTextBox;


@Tag("web-tests")
@DisplayName("Tests for UI check")
@Feature("UI Implementaion of forms")
public class DemoqaTest {
    @AfterEach
    public void driverQuit() {
        Selenide.closeWebDriver();
    }

    @Test
    @DisplayName("Log in")
    @Story("Log in to the system")
    @Description("Log in and check registered user data")
    public void login() {
        new ElementsTextBox()
                .fillInDate()
                .clickSubmitButton()
                .checkRegisteredUserData();
    }

    @Test
    @DisplayName("Check user forms page element to be in view")
    @Story("User form to be viewable")
    @Description("Check Page Form title, check text (suggested to select item), check form element of panel to be Opened")
    public void checkFormsPageElementView() {
        new ElementCheckBox()
                .checkFormsPageTitle()
                .checkSuggestionToSelectItemText()
                .checkFormElementOfPannelToBeOpened();
    }

    @Test
    @DisplayName("Filling in the Practise form")
    @Story("Implementation of practise form")
    @Description("Check filling in form fields and click Submit Button")
    public void fillInThePracticeForm() {
        new ElementCheckBox()
                .clickForm()
                .fillTheFormFields()
                .clickSubmitButton();
    }
}