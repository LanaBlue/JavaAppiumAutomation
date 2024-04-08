import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("AutomationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C://test/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        WebElement elementToInitSearch = driver.findElement(By.xpath("//*[contains(@text,'Search Wikipedia')]"));
        elementToInitSearch.click();

//        WebElement elementToEnterSearchLine = driver.findElement(By.xpath("//*[contains(@text,'Search…')]"));
        WebElement elementToEnterSearchLine = waitForElementPresentByXpath(
                "//*[contains(@text,'Search…')]",
                "Cannot find not input",
                5
        );

        elementToEnterSearchLine.sendKeys("Java");
        // waitForElementPresentByXpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='Object-oriented programming language']",
        waitForElementPresentByXpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']",
                "Не найден локатор", 10);

        System.out.println("First test run");
    }

    private WebElement waitForElementPresentByXpath(String xpath, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String errorMsg) {
        return waitForElementPresentByXpath(xpath, errorMsg, 5);
    }

    private WebElement waitForElementAndClick(String xpath, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresentByXpath(xpath, errorMsg, timeoutInSecond);
        el.click();
        return el;
    }

    private WebElement waitForElementAndSendKeys(String xpath, String text, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresentByXpath(xpath, errorMsg, timeoutInSecond);
        el.sendKeys(text);
        return el;
    }
}
