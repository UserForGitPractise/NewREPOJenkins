package web;

import org.openqa.selenium.*;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class ElementCheckBox extends BasePage {
    private By baseTabButtonForms = By.xpath("//*[text()='Forms']");
    private By pageTitleName = By.cssSelector(".main-header");
    private By selectItemSuggestionText = By.xpath("//div[@class='row']/div[@class='col-12 mt-4 col-md-6']");
    private By selectedPannelItem;

    private By firstNameField = By.cssSelector("#firstName");
    private By lastNameField = By.cssSelector("#lastName");
    private By emailField = By.cssSelector("#userEmail");
    private By genderRadioButton = By.cssSelector("#gender-radio-1");
    private By mobileField = By.cssSelector("#userNumber");

    private By subjectsField = By.cssSelector("#subjectsInput");
    private By hobbiesField = By.cssSelector("#hobbies-checkbox-1");
    private By pictureField = By.cssSelector("#uploadPicture");
    private By currentAddressField = By.xpath("");
    private By stateField = By.xpath("");

    public ElementCheckBox(WebDriver driver) {

        super(driver);
        String xPathOfSelectedPannelElementOfItem = "//li/span[@class='text' and text()='Practice Form']";
        selectedPannelElementOfItem = By.xpath(xPathOfSelectedPannelElementOfItem);
        selectedPannelItem = By.xpath(xPathOfSelectedPannelElementOfItem.concat("//ancestor::div[1]"));

        driver.get(BASE_URL);
        WebElement webElement = driver.findElement(baseTabButtonForms);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        webElement.click();
    }

    public ElementCheckBox checkFormsPageTitle() {
        assertThat(driver.findElement(pageTitleName).getText().equals("Forms")).isTrue();


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
        assertThat(driver.findElement(selectItemSuggestionText).getText().equals("Please select an item from left to start practice.")).isTrue();
        return this;
    }

    public ElementCheckBox checkFormElementOfPannelToBeOpened() {
        assertThat(driver.findElement(selectedPannelElementOfItem).isDisplayed()).isTrue();
        assertThat(driver.findElement(selectedPannelItem).getAttribute("class").equals("element-list collapse show")).isTrue();
        return this;

    }

    public ElementCheckBox clickForm() {
        driver.findElement(selectedPannelElementOfItem).click();
        return this;

    }

    public ElementCheckBox fillTheFormFields() {
        driver.findElement(firstNameField).sendKeys("Nick");
        driver.findElement(lastNameField).sendKeys("Evans");
        driver.findElement(emailField).sendKeys("test@test.com");
        // driver.findElement(genderRadioButton).click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#gender-radio-1")));
        // driver.execute_script("arguments[0].click();", driver.findElement("//input[@name = 'gender"]'"))

        driver.findElement(mobileField).sendKeys("7777777777");
        driver.findElement(subjectsField).sendKeys("Math");
        driver.findElement(hobbiesField).isSelected();
        //driver.findElement(pictureField).sendKeys("Math");
        //  driver.findElement(subjectsField).sendKeys("Math");
        SelectDay selectDay = new SelectDay();
        //selectDay.selectDay(driver, "September", 7);

//        driver.findElement(pictureField).sendKeys("C:/Users/toosm/IdeaProjects/NewProjectJenkins/src/main/resources/PicturesToDownload/pic1.png");
        driver.findElement(pictureField).sendKeys(new File("src/main/resources/PicturesToDownload/pic1.png").getAbsolutePath());
        return this;

    }

    public ElementCheckBox clickSubmitButton() {
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("document.body.style.zoom = '0.6'");
//        driver.findElement(By.cssSelector("#submit")).click();
//        Actions actions = new Actions(driver);
//
//        actions.moveToElement(driver.findElement(By.cssSelector("#submit"))).click().perform();
//        executor.executeScript("document.body.style.zoom = '1.0'");
        driver.findElement(By.cssSelector("#submit")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector(".modal-content")).isDisplayed();
        driver.findElement(By.cssSelector("#closeLargeModal")).isDisplayed();
        driver.findElement(By.cssSelector("#closeLargeModal")).isEnabled();

        return this;
    }

}
