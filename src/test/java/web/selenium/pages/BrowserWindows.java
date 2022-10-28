package web.selenium.pages;

import com.codeborne.selenide.SelenideElement;
import web.selenium.NewTab;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class BrowserWindows extends BasePage {
    private SelenideElement browserWindowsDirectory = $x("//*[text()='Browser Windows' and @class='text']");
    private SelenideElement newTabButton = $x("//button[@id='tabButton']");

    public BrowserWindows() {
        open(BASE_URL);
        browserWindowsDirectory.click();
    }

    public NewTab createNewTab() {
        newTabButton.click();
        return new NewTab();
    }
}
