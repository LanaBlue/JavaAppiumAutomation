import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.CoreTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class SecondTest extends CoreTestCase {

    /**
     * Нахождение элемента в результате поиска
     */
    @Test
    public void secondTest() {

        waitForElementByXpathAndClick("//*[contains(@text,'Search Wikipedia')]", "Не найден поиск", 5);
        waitForElementByXpathAndSendKeys("//*[contains(@text,'Search…')]", "Java", "Не найдено поле для ввода", 5);
        waitForElementPresentByXpath("//*[@resource-id='org.wikipedia:id/page_list_item_description']",
                "Не найден элемент", 10);

        System.out.println("First test run");
    }

    /**
     * Отмена поиска
     */
    @Test
    public void testCancelSearch() {
        waitForElementByIdAndClick("org.wikipedia:id/search_container", "Не найден элемент", 5);
        waitForElementByIdAndClick("org.wikipedia:id/search_close_btn", "Не найден крест закрытия", 5);
        waitForElementNotPresent("org.wikipedia:id/search_close_btn", "Не найден крест закрытия", 5);
    }

    private WebElement waitForElementPresentByXpath(String xpath, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentById(String id, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String errorMsg) {
        return waitForElementPresentByXpath(xpath, errorMsg, 5);
    }

    private WebElement waitForElementByXpathAndClick(String xpath, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresentByXpath(xpath, errorMsg, timeoutInSecond);
        el.click();
        return el;
    }

    private WebElement waitForElementByIdAndClick(String id, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresentById(id, errorMsg, timeoutInSecond);
        el.click();
        return el;
    }

    private WebElement waitForElementByXpathAndSendKeys(String xpath, String text, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresentByXpath(xpath, errorMsg, timeoutInSecond);
        el.sendKeys(text);
        return el;
    }

    private boolean waitForElementNotPresent(String id, String errorMsg, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMsg + "\n");
        By by = By.id(id);
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
