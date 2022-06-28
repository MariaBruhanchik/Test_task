package abstact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractClass {
    protected WebDriver driver;
    private final WebDriverWait wait;
    protected final int WAIT_TIMEOUTS = 10;

    public AbstractClass(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUTS));
        PageFactory.initElements(driver, this);
    }

    protected final WebElement waitForElementToBeClickable(final WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
