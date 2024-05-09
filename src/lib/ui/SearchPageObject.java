package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
    // SEARCH_INPUT="//*[contains(@text,'Search Wikipedia')]",
    SKIP_BUTTON = "//*[contains(@text,'Skip')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description']//*[@text='{SUBSTRING}']",

    // SEARCH_RESULT = "//*[@resource-id='org.wikipedia:id/page_list_item_description']";

    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_ELEMENT ="//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    public void initSearchInput() {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON), "Не найдена кнопка пропуска", 5);
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Не найдена строка поиска", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Не найдена строка поиска");
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INIT_ELEMENT), searchLine, "Не найден элемент поиска", 5);
    }

    public void waitForSearchResult(String substring) {
        String searchResultXPath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(searchResultXPath), "Не найден искомый результат поиска " + searchResultXPath);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Кнопка отмены все еще на экране", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Не найдена кнопка отмены", 5);
    }

    public void clickCancelSearchButton() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Не получилось нажать на кнопку отмены", 5);
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXPath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(searchResultXPath), "Не найден искомый результат  " + searchResultXPath, 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request ", 5);
        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel(){
        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT), "Cannot find anything by the request ", 15);
    }

    public void assertThereIsNoResultOfSearch(){
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We have found some results ");
    }
}