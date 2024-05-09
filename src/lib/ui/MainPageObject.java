package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPageObject {
    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }
    public void assertElementPresent(By by, String errorMsg){
        int amountOfElement = getAmountOfElements(by);
        if (amountOfElement == 0) {
            String defaultMsg = "An element'" + by.toString() + "'supposet to be present'";
            throw new AssertionError(defaultMsg + " " + errorMsg);
        }
    }

    public String waitForElementAndGetAttribute(By by, String attribute, String errorMsg, long timeoutInSeconds) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSeconds);
        return el.getAttribute(attribute);
    }

    public void assertElementNotPresent(By by, String errorMsg) {
        int amountOfElement = getAmountOfElements(by);
        if (amountOfElement > 0) {
            String defaultMsg = "An element'" + by.toString() + "'supposet to be not present'";
            throw new AssertionError(defaultMsg + " " + errorMsg);
        }
    }

    public int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    public void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick() {
        swipeUp(200);
    }

    public void swipeElementToLeft(By by, String errorMsg) {
        WebElement el = waitForElementPresent(by, errorMsg, 10);
        int left_x = el.getLocation().getX();
        int right_x = left_x + el.getSize().getWidth();
        int upper_y = el.getLocation().getY();
        int lower_y = upper_y + el.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }

    public void swipeUpToFindElement(By by, String errorMsg, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (alreadySwiped > maxSwipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. " + errorMsg, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    public void assertElementHasText(String locator, String text, String errorText) {
        WebElement titleElement = waitForElementPresent(By.xpath(locator), errorText, 10);
        String atrElement = titleElement.getAttribute("text");
        Assert.assertEquals("Не совпадает текст", text, atrElement);

    }


    public WebElement waitForElementPresent(By by, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresent(By by, String errorMsg) {
        return waitForElementPresent(by, errorMsg, 5);
    }

    public WebElement waitForElementAndClick(By by, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSecond);
        el.click();
        return el;
    }

    public WebElement waitForElementAndClear(By by, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSecond);
        el.clear();
        return el;
    }


    public WebElement waitForElementAndSendKeys(By by, String text, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSecond);
        el.sendKeys(text);
        return el;
    }

    public boolean waitForElementNotPresent(By by, String errorMsg, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
}
