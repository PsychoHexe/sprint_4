package com.krivtsova.sprint4;

import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.krivtsova.sprint4.page_object.HomePage;
import com.krivtsova.sprint4.page_object.OrderPage;

@RunWith(Parameterized.class)
public class OrderTest extends SetupBaseForTest {

    @Rule
    public final ErrorCollector errorCollector = new ErrorCollector();

    private final String nameText, familyText, adressText, stationName, phone, date, rentalPeriod, color, comment;

    //конструктор для запонения форм заказа
    public OrderTest(String nameText, String familyText, String adressText, String stationName, String phone, String date, String rentalPeriod, String color, String comment) {
        this.nameText = nameText;
        this.familyText = familyText;
        this.adressText = adressText;
        this.stationName = stationName;
        this.phone = phone;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;

    }

    
    @Parameterized.Parameters
    public static Object[][] setTestObj() {
        return new Object[][]{
            {"Мастер", "Маргарита", "ул.Бронная, 4", "Сокол", "89998887766", "25.04.2025", "семеро суток", "чёрный жемчуг", "Оно живо"},
            {"Воланд", "Бегемот", "ул.Большая Садовая, 10", "Чистые пруды", "89996661313", "06.05.2025", "шестеро суток", "серая безысходность", "Починяю примус"},
            {"Аннушка", "Берлиоз", "ул.Трамвайного Масла, 13", "Деловой центр", "89994440100", "30.04.2025", "сутки", "серая безысходность", "Аннушка уже разлила масло"}};
    }

    private void orderTest(OrderPage orderPage) {
        orderPage.setName(nameText);
        orderPage.setFamily(familyText);
        orderPage.setAdress(adressText);

        orderPage.setSelectItemMetro_click(stationName);

        orderPage.setPhone(phone);

        orderPage.click_next_Button();
        orderPage.waitForLoadPage_part2();

        orderPage.setDateCalendar(date);
        orderPage.setColor(color);
        orderPage.setRentalPeriod(rentalPeriod);
        orderPage.setComment(comment);
    }

    @Test
    public void orederPageTest() {

        HomePage homePage = new HomePage(driver);
        OrderPage orderPage = new OrderPage(driver);

        homePage.waitForLoadPage();
        homePage.clickTopOrderButton();
        orderPage.waitForLoadPage_part1();
        orderTest(orderPage);
        orderPage.click_next2_Button();
        orderPage.waitForLoadPage_End();
        orderPage.setEndYes();
        

        assertTrue("Неудалось оформить заказ", orderPage.getOrderModal_HeaderTxt().contains("Заказ оформлен"));
    }
}
