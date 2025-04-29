package com.krivtsova.sprint4.page_object;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс главной страницы
public class HomePage {

    private final WebDriver driver;

    // локатор faq
    private final By faqAccordion = By.className("accordion");
    private final By faqAccordionItemHeader = By.className("accordion__button");
    private final By faqAccordionItemTxt = By.xpath(".//*[@class='accordion__panel']/p");

    // локатор кнопки "заказать" в шапке
    private final By topOrderButton = By.className("Button_Button__ra12g");

    // локатор кнопки "заказать" в пункте "как это работает"
    private final By bigOrderButton = By.xpath(".//div[starts-with(@class, 'Home_RoadMap')]//button[starts-with(@class, 'Button_Button')]");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания прогрузки страницы
    public void waitForLoadPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(faqAccordion));
        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(faqAccordion));
    }

    // метод для нажатия на кнопку в шапке
    public void clickTopOrderButton() {
        driver.findElement(topOrderButton).click();
    }
    
    // метод для нажатия на кнопку заказа в пункте "как это работает" c листанием до элемента
    public void clickBigOrderButton() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(bigOrderButton));
        new WebDriverWait(driver, Duration.ofSeconds(2))
            .until(ExpectedConditions.presenceOfElementLocated(bigOrderButton));
        driver.findElement(bigOrderButton).click();
    }

    // метод нажатия на faq
    public void clickFaq(int index) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElements(faqAccordionItemHeader).get(index));
    }

    //Ожидаем, что аккордион открылся
    public void waitForOpenAccordion(int indexWait){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(driver.findElements(faqAccordionItemTxt).get(indexWait)));
    }
    
    // метод проверки нажатия на faq
    public boolean isDisplayedFaq(int index) {
        return driver.findElements(faqAccordionItemHeader).get(index).isDisplayed();
    }

    // метод получения текста заголовка из порядкового номера faq
    public String getFaqAccordionTxtHeader(int index) {
        return driver.findElements(faqAccordionItemHeader).get(index).getText();
    }

    // метод получения текста заголовка из порядкового номера faq
    public String getFaqAccordionTxt(int index) {
        return driver.findElements(faqAccordionItemTxt).get(index).getText();
    }

    /// BEG ============== Дополнительно 

    
    private final By logoYa = By.xpath(".//a[starts-with(@class,'Header_LogoYandex')]");

    // получение ссылки из лого Яндекс 
    public String getLinkLogoYa() {
        return driver.findElement(logoYa).getAttribute("href");
    }
    
    /// END =========== Дополнительно
}
