package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    /**
     * Проверка ротации экрана
     */
    @Test
    public void testRotations() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        String titleBeforeRotation = ArticlePageObject.getArticleTitle();
        //String titleBeforeRotation = MainPageObject.waitForElementAndGetAttribute(By.id("pcs-edit-section-title-description"), "content-desc", "Cannot find title", 2);
        this.rotateScreenLandscape();
        String titleAfterRotation =  ArticlePageObject.getArticleTitle();
        assertEquals("Title have been changed after screen rotation", titleAfterRotation, titleBeforeRotation);
        this.rotateScreenPortrait();
        String titleAfterSecondRotation = ArticlePageObject.getArticleTitle();
        assertEquals("Title have been changed after screen rotation", titleAfterRotation, titleAfterSecondRotation);
    }

    /**
     * Бэкграунд приложения
     */
    @Test
    public void testCheckSearchArticleInBackground() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        //  waitForElementPresent(By.id("pcs-edit-section-title-description"), "Не найдено", 15);
        this.backgroundApp(2);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
