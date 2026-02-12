package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegisterPage extends BasePage{

    public RegisterPage(WebDriver driver) {
        super(driver);}


    private final By fieldName = By.xpath("//label[text()='Имя']/following-sibling::input");

    private final By fieldEmail = By.xpath("//label[text()='Email']/following-sibling::input");

    private final By fieldPassword = By.xpath("//label[text()='Пароль']/following-sibling::input");

    private final By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");

    private final By passwordErrorMessage = By.xpath("//p[text()='Некорректный пароль']");

    private final By EnterButton = By.xpath("//a[text()='Войти']");


    @Step("Ввести имя пользователя: {username}")
    public void setUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldName));
        driver.findElement(fieldName).sendKeys(username);
    }

    @Step("Ввести email: {email}")
    public void setEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldEmail));
        driver.findElement(fieldEmail).sendKeys(email);
    }

    @Step("Ввести пароль: {password}")
    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(fieldPassword));
        driver.findElement(fieldPassword).sendKeys(password);
    }

    @Step("Нажать на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        driver.findElement(registrationButton).click();
    }

    @Step("Получаем сообщение об ошибке")
    public String getPasswordErrorMessage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordErrorMessage));
        return driver.findElement(passwordErrorMessage).getText();
    }

    @Step("Нажать на кнопку 'Войти'")
    public void clickEnterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(EnterButton));
        driver.findElement(EnterButton).click();
    }

    @Step("Создать пользователя")
    public void registerNewUser(String name, String email, String password) {
        setUsername(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

}



