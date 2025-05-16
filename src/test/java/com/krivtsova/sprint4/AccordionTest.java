package com.krivtsova.sprint4;

import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.krivtsova.sprint4.page_object.HomePage;

//тест для блока "Вопросы о главном"
@RunWith(Parameterized.class)
public class AccordionTest extends SetupBaseForTest{

    @Rule
    public final ErrorCollector errorCollector = new ErrorCollector();
    //порядоковый номер accordion Item
    private final int elementNumber;

    //Ожидаемый тескт вопроса
    private final String headerTxt;

    //Ожидаемый текст ответа
    private final String itemTxt;

    //Конструктор класса теста вопросов
    public AccordionTest(int elementNumber, String headerTxt, String itemTxt) {

        this.elementNumber = elementNumber;
        this.headerTxt = headerTxt;
        this.itemTxt = itemTxt;
    }

    @Parameterized.Parameters
    public static Object[][] setTestObj() {
        return new Object[][]{
            {0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
            {1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
            {2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
            {3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
            {4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
            {5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
            {6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
            {7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},};
    }


    @Test
    public void testAccordion() {

        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadPage();
        homePage.clickFaq(elementNumber);
        homePage.waitForOpenAccordion(elementNumber);
        assertEquals("Ошибка в заголовке FAQ " + elementNumber, headerTxt, homePage.getFaqAccordionTxtHeader(elementNumber));
        assertEquals("Ошибка в тексте FAQ " + elementNumber, itemTxt, homePage.getFaqAccordionTxt(elementNumber));

    }
}
