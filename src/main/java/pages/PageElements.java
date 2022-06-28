package pages;

import abstact.AbstractClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageElements extends AbstractClass {
    public PageElements(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Check Box')]")
    private WebElement checkBox;

    @FindBy(xpath = "//span[@class='rct-checkbox']")
    private WebElement clickCheckBoxHome;

    @FindBy(xpath = "//span[contains(text(),'Radio Button')]")
    private WebElement buttonRadio;

    @FindBy(xpath = "//label[@for='impressiveRadio']")
    private WebElement chooseImpressiveRadio;

    @FindBy(xpath = "//span[@class='text-success']")
    private WebElement actualResult;

    @FindBy(xpath = "//label[@for='yesRadio']")
    private WebElement chooseYes;

    public PageElements chooseCheckBox() {
        checkBox.click();
        waitForElementToBeClickable(clickCheckBoxHome).click();
        return this;
    }

    public PageElements chooseRadio() {
        buttonRadio.click();
        waitForElementToBeClickable(chooseImpressiveRadio).click();
        chooseYes.click();
        return this;
    }

    public String getActualResult() {
        return actualResult.getText();
    }
}
