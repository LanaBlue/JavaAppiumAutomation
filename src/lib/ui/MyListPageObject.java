package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {
    private static final String
            FOLDER_BY_NAME_TMP = "//*[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static String getFolderXPathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TMP.replace("{FOLDER_NAME}", nameOfFolder);
    }
    private static String getSavedArticleXPathByTitle(String title) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", title);
    }

    public void openFolderByName(String nameOfFolder) {
        String folderNameXPath = getFolderXPathByName(nameOfFolder);
        this.waitForElementAndClick(By.xpath(folderNameXPath), "Cannot find created folder ", 5);
    }

    public void swipeByArticleToDelete(String title) {
        this.waitForArticleToAppearByTitle(title);
        String titleOfArticle = getSavedArticleXPathByTitle(title);
        this.swipeElementToLeft(By.xpath(titleOfArticle), "Cannot find saved article");
        this.waitForArticleToDisappearByTitle(title);
    }

    public void waitForArticleToDisappearByTitle(String title){
        String titleOfArticle = getSavedArticleXPathByTitle(title);
        this.waitForElementNotPresent(By.xpath(titleOfArticle), "Cannot delete saved article", 5);
    }
    public void waitForArticleToAppearByTitle(String title){
        String titleOfArticle = getSavedArticleXPathByTitle(title);
        this.waitForElementPresent(By.xpath(titleOfArticle), "Cannot find saved article", 5);
    }

}
