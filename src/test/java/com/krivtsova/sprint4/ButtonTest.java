/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

package com.krivtsova.sprint4;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.krivtsova.sprint4.page_object.HomePage;
import com.krivtsova.sprint4.page_object.OrderPage;

public class ButtonTest extends SetupBaseForTest{

    // Тест на переход страницу формы заказа самоката через клик кнопки в шапке
    @Test
    public void openOrderPage_headerButton() {
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadPage();
        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoadPage_part1();

        assertEquals("Неудалось перейти на страницу оформления заказа", "Для кого самокат", orderPage.getOrder_HeaderTxt());
    }
    // Тест на переход страницу формы заказа самоката через клик кнопки внизу

    @Test
    public void openOrderPage_howWorkButton() {
        HomePage homePage = new HomePage(driver);

        homePage.waitForLoadPage();

        homePage.clickBigOrderButton();

        OrderPage orderPage = new OrderPage(driver);
        orderPage.waitForLoadPage_part1();

        assertEquals("Неудалось перейти на страницу оформления заказа", "Для кого самокат", orderPage.getOrder_HeaderTxt());
    }
}