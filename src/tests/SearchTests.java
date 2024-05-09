package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class SearchTests extends CoreTestCase {


    /**
     * Отмена поиска
     */
    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    /**
     * Тест на проверку количества найденных статей
     */
    @Test
    public void testAmountOfNotEmptySearch() {
        String searchLine = "Linkin Park Discography";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        int amountOfSearchResult = SearchPageObject.getAmountOfFoundArticles();

        assertTrue("We fount too few results ", amountOfSearchResult > 0);
    }

    /**
     * Тест на отсутствие статей при поиске
     */
    @Test
    public void testAmountOfEmptySearch() {
        String searchLine = "qwerertrttety";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }
}
