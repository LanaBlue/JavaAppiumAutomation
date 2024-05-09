import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstTest extends CoreTestCase {
    private MainPageObject MainPageObject;
    protected void setUp() throws Exception{
        super.setUp();

        MainPageObject = new MainPageObject(driver);
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
