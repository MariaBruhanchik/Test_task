package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.PageElements;

public class SingleTest {
    private WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void correctRegistration() {
        PageElements pageElements = new MainPage(driver).open("https://demoqa.com/")
                .clickTheButtonElements()
                .chooseCheckBox()
                .chooseRadio();
        Assert.assertEquals(pageElements.getActualResult(), "Yes");
    }

    @AfterClass
    public void stopBrowser() {
        driver.close();
        driver.quit();
        driver = null;
    }
}
