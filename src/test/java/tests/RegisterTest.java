package tests;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что при вводе валидных данных пользователя перенаправляет на страницу логина")
    public void successfulRegistrationTest() {

        driver.get(BASE_URL + "register");

        registerPage.setUsername(NAME);
        registerPage.setEmail(email);
        registerPage.setPassword(PASS_WORD);
        registerPage.clickRegisterButton();
        boolean actual = loginPage.isLoginHeaderDisplayed();
        assertTrue("Заголовок 'Вход' не найден!", actual);
    }

    @Test
    @DisplayName("Ошибка для некорректного поля")
    @Description("Проверяем, что при вводе невалидных данных в поле пароль появляется сообщение об ошибке")
    public void checkRegistrationPasswordError() {

        driver.get(BASE_URL + "register");

        String passwordFalse = "wrd1";

        registerPage.setUsername(NAME);
        registerPage.setEmail(email);
        registerPage.setPassword(passwordFalse);
        registerPage.clickRegisterButton();
        String actual = registerPage.getPasswordErrorMessage();

        assertEquals("Сообщение не возникло!", "Некорректный пароль", actual);
    }
}
