package pages;

import abstact.AbstractClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractClass {
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id='app']/div/div/div[2]/div/div[1]")
    private WebElement buttonElements;

    @FindBy(id = "aswift_0")
    private WebElement frame;

    public MainPage open(String url) {
        driver.get(url);
        return this;
    }

    public PageElements clickTheButtonElements() {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].scrollIntoView(true);", buttonElements);
        buttonElements.click();
        return new PageElements(driver);
    }
}
