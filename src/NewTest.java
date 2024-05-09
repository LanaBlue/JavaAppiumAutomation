import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.openqa.selenium.By;

public class NewTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }


    /**
     * Ex2: Создание метода
     */
    @Test
    public void testAssertFieldSearch() {
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Skip')]"), "Не найдена кнопка пропуска", 5);
        MainPageObject.waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"), "Не найдена строка поиска", 10);
//        assertElementHasText("//*[@class=android.widget.EditText]","Search Wikipedia","В поле поиска нет такого названия");
        MainPageObject.assertElementHasText("//*[@resource-id='org.wikipedia:id/search_src_text']", "Search Wikipedia", "В поле поиска нет такого названия");
    }

    /**
     * Ex3: Тест: отмена поиска
     */
    @Test
    public void testCancelSearchAfterSearch() {
        String line = "Hello";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(line);
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearchButton();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    /**
     * Ex5 - Перелистывание экранов Onboarding
     */
    @Test
    public void testSwipeOnboarding() {
    }

    /**
     * Ex6 - assert title
     */
    @Test
    public void testAssertTitle() {
        String searchLine = "Java (programming language)";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.assertThereIsNoResultOfSearch();
           }

}
