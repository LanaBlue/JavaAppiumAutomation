package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {
    /**
     * Сохранение первой статьи
     */
    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        // SearchPageObject.waitForElementAndClick(By.xpath("//*[@text='Object-oriented programming language']"), "Error ", 10);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String articleTitle = ArticlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        ArticlePageObject.addArticleToMyList(nameOfFolder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(nameOfFolder);
        MyListPageObject.swipeByArticleToDelete(articleTitle);


    }

    /**
     * Ex5 сохранение двух статей
     */
    @Test
    public void testSaveTwoArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String articleTitle = ArticlePageObject.getArticleTitle();
        String nameOfFolder = "Learning programming";
        ArticlePageObject.addArticleToMyList(nameOfFolder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("JavaScript");
        SearchPageObject.clickByArticleWithSubstring("High-level programming language");
        ArticlePageObject.waitForTitleElement();
        String articleTitleTwo = ArticlePageObject.getArticleTitle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(nameOfFolder);
        MyListPageObject.swipeByArticleToDelete(articleTitle);

        MyListPageObject.waitForArticleToDisappearByTitle(articleTitleTwo);
    }
}