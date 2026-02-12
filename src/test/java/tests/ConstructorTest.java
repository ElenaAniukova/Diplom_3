package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest{

    @Test
    @DisplayName("Успешный переход в раздел Соусы")
    @Description("Проверяем что клик по кнопке Соусы делает раздел Соусы активным")
    public void testGoToSauces(){
        driver.get(BASE_URL);
        mainPage.clickSaucesButton();
        assertTrue("Не произошло переключения на раздел Соусы", mainPage.isTabActive("Соусы"));
    }

    @Test
    @DisplayName("Успешный переход в раздел Начинки")
    @Description("Проверяем что клик по кнопке Начинки делает раздел Начинки активным")
    public void testGoToFillings(){
        driver.get(BASE_URL);
        mainPage.clickFillingsButton();
        assertTrue("Не произошло переключения на раздел Соусы", mainPage.isTabActive("Начинки"));
        }

    @Test
    @DisplayName("Успешный переход в раздел Булки")
    @Description("Проверяем что клик по кнопке Булки делает раздел Булки активным")
    public void testGoToBuns(){
        driver.get(BASE_URL);
        mainPage.clickFillingsButton();
        assertTrue("Не произошло переключения на раздел Соусы", mainPage.isTabActive("Начинки"));

        mainPage.clickBunsButton();
        assertTrue("Не произошло переключения на раздел Булки", mainPage.isTabActive("Булки"));
    }
    }

