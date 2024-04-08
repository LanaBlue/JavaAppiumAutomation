import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class NewTest {
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
      //  capabilities.setCapability("noReset", "true");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After

    public void tearDown() {
        driver.quit();
    }

    /**
     * Отмена поиска
     */
    @Test
    public void testCancelSearch() {
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Не найден элемент", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Не найден крест закрытия", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "Крест закрытия найден", 5);
    }

    /**
     * Проверка заголовка статьи
     */
    @Test
    public void testCompareArticleTitle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
      //  waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"), "Java","Не найден элемент", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java","Не найден элемент", 5);
//        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
        waitForElementPresent(By.id("pcs-edit-section-title-description"), "Не найдено", 15);
//        waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Не найдено", 15);
        //WebElement titleElement = waitForElementPresent(By.id("org.wikipedia:id/view_page_title_text"), "Error", 10);
        WebElement titleElement = waitForElementPresent(By.xpath("//android.view.View[@content-desc='Java (programming language)']"), "Не найден заголовок статьи", 10);
        String atrElement = titleElement.getAttribute("text");
        Assert.assertEquals("Не совпадает", "Java (programming language)", atrElement);
    }

    /**
     * Ex2: Создание метода
     */
    @Test
    public void testAssertFieldSearch(){
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 10);
//        assertElementHasText("//*[@class=android.widget.EditText]","Search Wikipedia","В поле поиска нет такого названия");
        assertElementHasText("//*[@resource-id='org.wikipedia:id/search_src_text']","Search Wikipedia","В поле поиска нет такого названия");
    }

    /**
     * Ex3: Тест: отмена поиска
     */
    @Test
    public void testCancelSearchAfterSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Hello","Не найден элемент", 5);

//        WebElement titleElement = waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"), "Не найден заголовок статьи", 10);
//        String atrElement = titleElement.getAttribute("text");
//        Assert.assertEquals("Не совпадает", "Hello", atrElement);
        assertElementHasText("//*[@resource-id='org.wikipedia:id/page_list_item_title']","Hello","Не совпадает");
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Не найден крест закрытия", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "Крест закрытия найден", 5);
    }

    public void assertElementHasText(String locator,String text, String errorText){
        WebElement titleElement = waitForElementPresent(By.xpath(locator), errorText, 10);
        String atrElement = titleElement.getAttribute("text");
        Assert.assertEquals("Не совпадает текст", text, atrElement);

    }


    private WebElement waitForElementPresent(By by, String errorMsg, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMsg + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String errorMsg) {
        return waitForElementPresent(by, errorMsg, 5);
    }

    private WebElement waitForElementAndClick(By by, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSecond);
        el.click();
        return el;
    }


    private WebElement waitForElementAndSendKeys(By by, String text, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSecond);
        el.sendKeys(text);
        return el;
    }

    private boolean waitForElementNotPresent(By by, String errorMsg, long timeoutInSecond) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(errorMsg + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

}
