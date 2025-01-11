package pl.logicsynergy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {

    @FindBy (id = "txtLogin")
    private WebElement loginInput;

    @FindBy(id = "txtPassword")
    private WebElement passwordInput;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    private WebDriver driver;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public HomePage logInValidData(String login, String password) {
        logIn(login, password);
        return new HomePage(driver);
    }

    public LogInPage logInInvalidData(String login, String password) {
        logIn(login, password);
        return this;
    }

    private void logIn(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }
}
