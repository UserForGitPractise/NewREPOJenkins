package web.selenium.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class BasePage {

    protected static final String BASE_URL = "https://demoqa.com/";


    SelenideElement selectedPannelElementOfItem;

    public BasePage() {
        Configuration.timeout = Duration.ofSeconds(20).getSeconds();

    }

    protected void waitVisibility(SelenideElement selenideElement) {
        selenideElement.shouldBe(Condition.visible);
    }
}
