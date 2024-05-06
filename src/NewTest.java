import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

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
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не найден элемент", 5);
//        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
        waitForElementPresent(By.id("pcs-edit-section-title-description"), "Не найдено", 15);
        WebElement titleElement = waitForElementPresent(By.xpath("//android.view.View[@content-desc='Java (programming language)']"), "Не найден заголовок статьи", 10);
        String atrElement = titleElement.getAttribute("text");
        Assert.assertEquals("Не совпадает", "Java (programming language)", atrElement);
    }

    /**
     * Ex2: Создание метода
     */
    @Test
    public void testAssertFieldSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 10);
//        assertElementHasText("//*[@class=android.widget.EditText]","Search Wikipedia","В поле поиска нет такого названия");
        assertElementHasText("//*[@resource-id='org.wikipedia:id/search_src_text']", "Search Wikipedia", "В поле поиска нет такого названия");
    }

    /**
     * Ex3: Тест: отмена поиска
     */
    @Test
    public void testCancelSearchAfterSearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Hello", "Не найден элемент", 5);

//        WebElement titleElement = waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title']"), "Не найден заголовок статьи", 10);
//        String atrElement = titleElement.getAttribute("text");
//        Assert.assertEquals("Не совпадает", "Hello", atrElement);
        assertElementHasText("//*[@resource-id='org.wikipedia:id/page_list_item_title']", "Hello", "Не совпадает");
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Не найден крест закрытия", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "Крест закрытия найден", 5);
    }

    /**
     * Свайп приложения
     */
    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Appium", "Не найден элемент", 5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"), "Не найден элемент - appium", 10);
        waitForElementPresent(By.xpath("//android.view.View[@content-desc='Automation for Apps']"), "Не найден заголовок статьи", 10);

        swipeUpToFindElement(By.xpath("//android.view.View[@content-desc='View article in browser']"), "Error message", 5);
    }

    /**
     * Сохранение первой статьи
     */
    @Test
    public void testSaveFirstArticleToMyList() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не найден элемент", 5);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
        waitForElementPresent(By.id("pcs-edit-section-title-description"), "Не найдено", 15);
        //     WebElement titleElement = waitForElementPresent(By.xpath("//android.view.View[@content-desc='Java (programming language)']"), "Не найден заголовок статьи", 10);

        // waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"), "Error cannot find - more options", 5);
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='Saved']"), "Error cannot find - more options", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding button"), "Error cannot find - Got it", 5);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"), "Error cannot find input to set name ", 5);

        String nameOfFolder = "Learning programming";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"), nameOfFolder, "Error cannot find input to set name ", 5);
        waitForElementAndClick(By.id("//*[@text='OK']"), "Cannot find button - OK ", 5);
        waitForElementAndClick(By.id("//android.widget.ImageButton[@content-desc='Navigate up']"), "Cannot find button - OK ", 5);
        waitForElementAndClick(By.id("//android.widget.FrameLayout[@content-desc='My lists']"), "Cannot find button - My lists ", 5);
        waitForElementAndClick(By.xpath("//*[@text='" + nameOfFolder + "']"), "Cannot find created folder ", 5);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"), "Cannot find saved article");
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"), "Cannot delete saved article", 5);
    }


    /**
     * Тест на проверку количества найденных статей
     */
    @Test
    public void testAmountOfNotEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        String searchLine = "Linkin Park Discography";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), searchLine, "Не найден элемент", 5);
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(By.xpath(searchResultLocator), "Cannot find anything by the request " + searchLine, 5);
        int amountOfSearchResult = getAmountOfElements(By.xpath(searchResultLocator));
        Assert.assertTrue("We fount too few results ", amountOfSearchResult > 0);
    }


    /**
     * Тест на отсутствие статей при поиске
     */
    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        String searchLine = "qwerertrttety";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), searchLine, "Не найден элемент", 5);
        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
        waitForElementPresent(By.xpath(searchResultLocator), "Cannot find anything by the request " + searchLine, 5);
//        String emptyResultLocator = "//*[@text='No results']";
        String emptyResultLocator = "//*[contains(@text,'Search Wikipedia')]";
        waitForElementPresent(By.xpath(emptyResultLocator), "Cannot find " + searchLine, 20);
        // int amountOfSearchResult = getAmountOfElements(By.xpath(searchResultLocator));
        // Assert.assertTrue("We fount too few results ", amountOfSearchResult > 0);
        assertElementNotPresent(By.xpath(searchResultLocator), "We have found some results " + searchLine);
    }


    /**
     * Проверка ротации экрана
     */
    @Test
    public void testRotations() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        String searchLine = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), searchLine, "Не найден элемент", 5);
//        String searchResultLocator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";
//        waitForElementPresent(By.xpath(searchResultLocator), "Cannot find anything by the request " + searchLine, 5);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
//        String titleBeforeRotation = waitForElementAndGetAttribute(By.xpath("//android.view.View[@content-desc='Java (programming language)']"),"content-desc","Cannot find title",15);
        String titleBeforeRotation = waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),"content-desc","Cannot find title",2);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String titleAfterRotation = waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),"content-desc","Cannot find title",15);
        Assert.assertEquals("Title have been changed after screen rotation",titleAfterRotation,titleBeforeRotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String titleAfterSecondRotation = waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"),"content-desc","Cannot find title",15);
        Assert.assertEquals("Title have been changed after screen rotation",titleAfterRotation,titleAfterSecondRotation);
    }

    /**
     * Бэкграунд приложения
     */
    @Test
    public void testCheckSearchArticleInBackground(){
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не найден элемент", 5);
        waitForElementPresent(By.xpath("//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
      //  waitForElementPresent(By.id("pcs-edit-section-title-description"), "Не найдено", 15);
        driver.runAppInBackground(2);
        waitForElementPresent(By.xpath("//*[@text='Java (programming language)']"), "Не найден элемент после бэкграунда", 10);
    }


    /**
     * Ex6 - assert title
     */
    @Test
    public void testAssertTitle(){
        waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Java", "Не найден элемент", 5);
        String searchLine = "Java (programming language)";
        waitForElementPresent(By.xpath("//*[@text='"+searchLine+"']"), "Не найден элемент - object", 10);
        waitForElementAndClick(By.xpath("//*[@text='Java (programming language)']"), "Не найден элемент - object", 10);
        assertElementPresent(By.xpath("//android.webkit.WebView[@content-desc='Java (programming language)']"), "We have not found results " + searchLine);
    }

    private void assertElementPresent(By by, String errorMsg){
        int amountOfElement = getAmountOfElements(by);
        if (amountOfElement == 0) {
            String defaultMsg = "An element'" + by.toString() + "'supposet to be present'";
            throw new AssertionError(defaultMsg + " " + errorMsg);
        }
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String errorMsg, long timeoutInSeconds) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSeconds);
        return el.getAttribute(attribute);
    }

    private void assertElementNotPresent(By by, String errorMsg) {
        int amountOfElement = getAmountOfElements(by);
        if (amountOfElement > 0) {
            String defaultMsg = "An element'" + by.toString() + "'supposet to be not present'";
            throw new AssertionError(defaultMsg + " " + errorMsg);
        }
    }

    private int getAmountOfElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();
    }

    protected void swipeUp(int timeOfSwipe) {
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

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeElementToLeft(By by, String errorMsg) {
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

    protected void swipeUpToFindElement(By by, String errorMsg, int maxSwipes) {
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

    private WebElement waitForElementAndClear(By by, String errorMsg, long timeoutInSecond) {
        WebElement el = waitForElementPresent(by, errorMsg, timeoutInSecond);
        el.clear();
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
