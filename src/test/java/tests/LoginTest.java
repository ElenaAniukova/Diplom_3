package tests;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import static api.Config.BASE_URL;
import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest{


    @Before
    public void prepareUser() {

        String randomEmail = "ivan_" + System.currentTimeMillis() + "@yandex.ru";
        this.email = randomEmail;

        Response response = userClient.createUser(randomEmail, PASS_WORD, NAME);
        this.token = response.path("accessToken");
    }

    @Test
    @DisplayName("Успешный вход через кнопку «Войти в аккаунт» на главной странице сайта")
    @Description("Проверяем переход на страницу логина и успешную авторизацию")
    public void loginViaEnterAccountButton() {

        driver.get(BASE_URL);

        mainPage.clickEnterAccountButton();

        assertTrue("Заголовок 'Вход' не отображается", loginPage.isLoginHeaderDisplayed());

        loginPage.setEmail(email);
        loginPage.setPassword(PASS_WORD);
        loginPage.clickLoginButton();

        assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась на главной странице", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешный вход через кнопку «Личный кабинет» на главной странице сайта")
    @Description("Проверяем переход на страницу логина и успешную авторизацию")
    public void loginViaPersonalAccountButton() {

        driver.get(BASE_URL);

        mainPage.clickPersonalAccountButton();

        assertTrue("Заголовок 'Вход' не отображается", loginPage.isLoginHeaderDisplayed());

        loginPage.setEmail(email);
        loginPage.setPassword(PASS_WORD);
        loginPage.clickLoginButton();

        assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась на главной странице", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешный вход через кнопку «Войти» в форме регистрации")
    @Description("Проверяем переход на страницу логина и успешную авторизацию")
    public void loginViaEnterButton() {

        driver.get(BASE_URL + "register");

        registerPage.clickEnterButton();

        assertTrue("Заголовок 'Вход' не отображается", loginPage.isLoginHeaderDisplayed());

        loginPage.setEmail(email);
        loginPage.setPassword(PASS_WORD);
        loginPage.clickLoginButton();

        assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась на главной странице", mainPage.isOrderButtonDisplayed());
    }

    @Test
    @DisplayName("Успешный вход через кнопку «Войти» в форме восстановления пароля")
    @Description("Проверяем переход на страницу логина и успешную авторизацию")
    public void loginViaBackToLoginLink() {

        driver.get(BASE_URL + "forgot-password");

        forgotPasswordPage.clickBackToLoginLink();

        assertTrue("Заголовок 'Вход' не отображается", loginPage.isLoginHeaderDisplayed());

        loginPage.setEmail(email);
        loginPage.setPassword(PASS_WORD);
        loginPage.clickLoginButton();

        assertTrue("Вход не выполнен: кнопка 'Оформить заказ' не появилась на главной странице", mainPage.isOrderButtonDisplayed());
    }
}
