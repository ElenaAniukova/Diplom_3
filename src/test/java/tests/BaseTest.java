package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {

    protected static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    protected static final String NAME = "Zina";
    protected static final String PASS_WORD = "password123";

    protected String email;
    protected WebDriver driver;

    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected String token;

    @Before
    public void setup() {

        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equals(browser)) {

            System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
            ChromeOptions options = new ChromeOptions();

            driver = new ChromeDriver(options);
        } else {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }

        driver.manage().window().maximize();


        registerPage = new RegisterPage(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        email = "eaniu" + System.currentTimeMillis() + "@yandex.ru";
    }

    @After
    public void teardownDriver() {
        if (token != null) {
            deleteUser(token);
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @Step("Удаление пользователя через API")
    public void deleteUser(String tokenForDeletion) {

        given()
                .header("Authorization", tokenForDeletion) // Без приставки Bearer
                .baseUri(BASE_URL)
                .when()
                .delete("api/auth/user")
                .then()
                .statusCode(202);
}
}