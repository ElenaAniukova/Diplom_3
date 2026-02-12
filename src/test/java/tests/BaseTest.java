package tests;

import api.UserClient;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;


public abstract class BaseTest {

    protected static final String NAME = "Zina";
    protected static final String PASS_WORD = "password123";

    protected String email;
    protected WebDriver driver;

    protected RegisterPage registerPage;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected ForgotPasswordPage forgotPasswordPage;
    protected UserClient userClient;
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
        userClient = new UserClient();
        email = "eaniu" + System.currentTimeMillis() + "@yandex.ru";
    }

    @After
    public void teardownDriver() {
        if (token != null) {
            userClient.deleteUser(token);
        }
        if (driver != null) {
            driver.quit();
        }
    }
}