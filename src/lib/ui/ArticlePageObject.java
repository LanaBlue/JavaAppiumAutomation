package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
private  static final String
    TITLE="org.wikipedia:id/view_page_title_text",
    FOOTER_ELEMENT ="//android.view.View[@content-desc='View article in browser']",
    OPTIONS_BUTTON="//android.widget.ImageView[@content-desc='More options']",
    OPTIONS_ADD_TO_MY_LIST_BUTTON="//[@text='Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON="//*[@text='OK']",
    CLOSE_ARTICLE_BUTTON ="//android.widget.ImageButton[@content-desc='Navigate up']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.id(TITLE),"Не найден заголовок статьи");
    }

    public String getArticleTitle(){
        WebElement titleElement = waitForTitleElement();
        return titleElement.getAttribute("text");
    }

    public void swipeToFooter(){
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Не найден конец статьи",10);
    }

    public void addArticleToMyList(String nameOfFolder){
        this.waitForElementAndClick(By.xpath(OPTIONS_BUTTON), "Не найдена кнопка - More options", 5);
        this.waitForElementAndClick(By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON), "Не найден мой лист", 5);
        this.waitForElementAndClick(By.id(ADD_TO_MY_LIST_OVERLAY), "Не найден мой лист", 5);
        this.waitForElementAndClear(By.id(MY_LIST_NAME_INPUT), "Не очистилась строка ввода", 5);
        this.waitForElementAndSendKeys(By.id(MY_LIST_NAME_INPUT), nameOfFolder, "Не найдено ", 5);
        this.waitForElementAndClick(By.xpath(MY_LIST_OK_BUTTON), "Не найдена кнопка - OK ", 5);
    }

    public void closeArticle(){
        this.waitForElementAndClick(By.xpath(CLOSE_ARTICLE_BUTTON), "Не найдена кнопка закрытия ", 5);
    }
}