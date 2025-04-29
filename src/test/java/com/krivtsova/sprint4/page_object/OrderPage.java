package com.krivtsova.sprint4.page_object;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Класс страницы заказа
public class OrderPage {

    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    /// BEG====Локаторы кнопок
    
    // локатор кнопки "Далее"
    private final By next_order_Button = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button");

    //Локатор кнопки назад во второй части формы
    private final By prev_Button = By.className("Button_Inverted__3IF-i");

    // локатор кнопки "Заказать" во второй форме
    private final By next2_order_Button = By.xpath(".//div[contains(@class, 'Order_Buttons__1xGrp')]//button[text()='Заказать']");

    /// END====Локаторы кнопок
    // локатор для получения текста заголовка 1й страницы заказа
    private final By form_orderHeaderPage = By.className("Order_Header__BZXOb");

    /// BEG=====FORM_1 - Локаторы 1 части формы

    // локатор для кого самокат
    private final By form_orderHeaderPage1 = By.xpath("//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");

    // локатор поля "Имя"
    private final By input_name = By.xpath(".//input[contains(@placeholder, 'Имя')]");

    // локатор поля "Фамилия"
    private final By input_family = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");

    // локатор поля "Адрес"
    private final By input_adress = By.xpath(".//input[contains(@placeholder, 'Адрес: куда привезти заказ')]");

    // локатор поля "Метро"
    private final By input_metro = By.className("select-search__input");
    // локатор контейнера станций
    private final By selectContainer_metro = By.xpath(".//div[starts-with(@class,'Order_Form')]//div[contains(@class, 'select-search ')]");
    // Локатор кнопки в контейнере станций
    private final By selectItems_metro = By.xpath(".//ul[contains(@class,'select-search__options')]");


    // Список доступных станций
    //private final By input_metroListItems = By.xpath(".//div[@class='select-search__select']//div[starts-with(@class,'Order_Text')]");

    // Локатор поля "Телефон"
    private final By input_phone = By.xpath(".//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]");

    /// END=====FORM_1
    
    /// BEG=====FORM_2 - Локаторы 2 части формы
    
    // локатор про аренду
    private final By form_orderHeaderPage2 = By.xpath("//div[@class='Order_Header__BZXOb' and text()='Про аренду']");

    // локатор поля "Когда привезти самокат"
    private final By input_dateCalendar = By.xpath(".//input[contains(@placeholder, '* Когда привезти самокат')]");
    //private By dateSelected = By.className("react-datepicker__day--selected");

    // локатор полей "Срок"
    private final By input_rentalPeriod = By.className("Dropdown-arrow");
    private final By input_rentalPeriod_Menu = By.className("Dropdown-menu");

    // локатор поля "Цвет"
    private final By input_colorBlack = By.id("black");
    private final By input_colorGrey = By.id("grey");

    // локатор поля "Коммент"
    private final By input_comment = By.xpath(".//input[contains(@placeholder, 'Комментарий для курьера')]");

    /// END=====FORM_2
    
    /// BEG ====== Локаторы ошибок
    private final By errorLocator = By.xpath(".//div[contains(@class, 'Input_ErrorMessage__3HvIb') and contains(@class, 'Input_Visible___syz6')]");
    /// END ====== 
    
    /// BEG ====== Локаторы всплывающих окон
    // Локатор окна "Хотите оформить заказ"
    private final By form_orderEnd = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Хотите оформить заказ?')]");

    private final By yesButton = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and text()='Да' and not(contains(@class, 'Button_Inverted__3IF-i'))]");

    // Локатор окна подтверждения
    private final By form_success = By.xpath(".//div[contains(@class, 'Order_ModalHeader__3FDaJ') and contains(text(), 'Заказ оформлен')]");
    
    // Локатор заголовка подтверждения оформления заказа во всплывающем окне
    private final By form_orderModalHeader = By.className("Order_ModalHeader__3FDaJ");

    
    //Локатор 
    /// END ====== Локаторы всплывающих окон

    public boolean checkingErrorInput(){
        List<WebElement> inputErrors = driver.findElements(errorLocator);
        for (WebElement elem : inputErrors) {
            System.err.println(elem.getText() + " " + elem.getAttribute("class"));
        }
        return !inputErrors.isEmpty();
    }

    // метод ожидания прогрузки страницы
    public void waitForLoadPage_part1() {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOf(driver.findElement(form_orderHeaderPage1)));
    }

    // метод ожидания продгрузки страницы
    public void waitForLoadPage_part2() {
        // new WebDriverWait(driver, Duration.ofSeconds(3));
        checkingErrorInput();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(driver.findElement(form_orderHeaderPage2)));
    }

    // метод ожидания продгрузки страницы подтверждения
    public void waitForLoadPage_End() {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(form_orderEnd));
    }

    // метод ожидания продгрузки страницы подтверждения
    public void waitForLoadPage_success() {
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(form_success));
    }

    /// BEG ===== Нажатие кнопок
    // метод для нажатия на кнопку "Далее"
    public void click_next_Button() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(next_order_Button));
        // driver.findElement(next_order_Button).click();
    }

    public void click_next2_Button() {
        driver.findElement(next2_order_Button).click();
    }

    // метод для нажатия на кнопку "Заказать" в шапке
    public void click_order_Button() {
        driver.findElement(next_order_Button).click();
    }

    // метод для нажатия на кнопку "Назад" 
    public void click_prev_Button() {
        driver.findElement(prev_Button).click();
    }

    /// END ===== Нажатие кнопок
    
    /// BEG ===== Заполнение полей 1 части
    // Метод заполнения поля Имя
    public void setName(String nameText) {
        this.driver.findElement(input_name).sendKeys(nameText);
        }

    // Метод заполнения поля Фамилия
    public void setFamily(String familyText) {
        this.driver.findElement(input_family).sendKeys(familyText);
        }

    // Метод заполнения поля Адрес
    public void setAdress(String adressText) {
        this.driver.findElement(input_adress).sendKeys(adressText);
    }

    //Метод для листания элемента до его дочернего элемента
    public void scrollToChildElement(WebElement parentElement, WebElement childElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Скроллим внутри родительского элемента до дочернего
        js.executeScript(
            "arguments[0].scrollTop = arguments[1].offsetTop - arguments[0].offsetTop;",
            parentElement, childElement
        );
    }

    // Метод заполнения поля метро кликом по элементу списка
    public void setSelectItemMetro_click(String stationName) {
        WebElement metroSelectContainer = driver.findElement(input_metro);
        metroSelectContainer.click();
        
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.attributeContains(selectContainer_metro, "class", "has-focus"));
        driver.findElement(input_metro).sendKeys(stationName);
        WebElement metro_option = driver.findElement(selectItems_metro);
        WebElement selectButton = metro_option.findElement(By.xpath(String.format(".//button[./div[@class='Order_Text__2broi' and text()='%s']]", stationName)));
        //scrollToChildElement(driver.findElement(input_metroListItems), selectButton);
        selectButton.click();
        }

    // Метод заполнения поля метро текстом
    public void setSelectItemMetro_inputText(String stationName) {
        driver.findElement(input_metro).click();
        //driver.findElement().sendKeys(stationName);
    }

    // Метод заполнения поля Телефон
    public void setPhone(String phone) {
        driver.findElement(this.input_phone).sendKeys(phone);
    }

    /// END ===== Заполнение полей 1 части


    /// BEG ===== Заполнение полей 2 части

    //Метод заполнения поля "Дата"
    public void setDateCalendar(String date) {
        driver.findElement(input_dateCalendar).sendKeys(date);
       
    }

    //Метод заполнения поля "Срок аренды"
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(input_rentalPeriod).click();
        driver.findElement(input_rentalPeriod_Menu).isEnabled();
        driver.findElement(By.xpath(String.format("//div[text()='%s']", rentalPeriod))).click();
        //driver.findElement(By.linkText(rentalPeriod)).click();
    }

    // Метод заполнения поля "Цвет"
    public void setColor(String color) {
        switch (color) {
            case "black":
                driver.findElement(input_colorBlack).click();
                break;
            case "gray":
                driver.findElement(input_colorGrey).click();
        }
    }

    //метод заполнения поля "коммент"
    public void setComment(String comment) {
        driver.findElement(input_comment).sendKeys(comment);
    }

    /// END ===== Заполнение полей 2 части
    
    public String getOrder_HeaderTxt (){
        return driver.findElement(form_orderHeaderPage).getText();
    }

    public String getOrderModal_HeaderTxt (){
        return driver.findElement(form_orderModalHeader).getText();
    }
    
    //Метод подтверждения заказа
    public void setEndYes() {
        waitForLoadPage_End();
        driver.findElement(yesButton).isEnabled();
        driver.findElement(yesButton).click();
    }

}
