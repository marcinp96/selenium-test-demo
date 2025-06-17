package pl.logicsynergy.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.logicsynergy.tests.BaseTest;

import java.time.Duration;
import java.util.List;

public class LogInPage {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @FindBy (id = "txtLogin")
    private WebElement loginInput;

    @FindBy(id = "txtPassword")
    private WebElement passwordInput;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    @FindBy(id = "btnPopupNo")
    private WebElement popupNoButton;

    @FindBy(xpath = "//*[@id='btnPopupOk']")
    private WebElement popupOkButton;

    private WebDriver driver;

    public LogInPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public HomePage logInValidData(String login, String password) {
        logIn(login, password);
        return new HomePage(driver);
    }

    private void logIn(String username, String password) {
        loginInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();

        if (!handlePopupNoButton()) {
            handleMessageSession();
        }
    }

    private boolean handlePopupNoButton() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(popupNoButton)).click();
            logger.info("Kliknięto w przycisk 'Nie'");
            return true; // Obsłużono popup, więc nie sprawdzamy drugiego komunikatu
        } catch (NoSuchElementException e) {
            logger.warn("Przycisk 'Nie' nie jest widoczny, pop-up prawdopodobnie się nie pojawił");
        } catch (TimeoutException e) {
            logger.error("Przycisk 'Nie' istnieje, ale nie stał się klikalny na czas");
        } catch (Exception e) {
            logger.error("Błąd przy obsłudze przycisku 'Nie': {}", e.getMessage(), e);
        }
        return false; // Nie obsłużono popupu, więc sprawdzamy komunikat
    }

    private void handleMessageSession() {
        By messageLocator = By.cssSelector(".v-label.v-widget.ls-wrap.v-label-ls-wrap.v-has-width");
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            List<WebElement> messages = driver.findElements(messageLocator);

            if (!messages.isEmpty()) { // Jeśli elementy istnieją, dopiero wtedy czekamy na widoczność
                WebElement messageElement = wait.until(ExpectedConditions.visibilityOf(messages.get(0)));
                String messageText = messageElement.getText();
                logger.info("Komunikat: {}", messageText);

                if (messageText.contains("Aplikacja eMedia została zaktualizowana.")) {
                    logger.info("Komunikat potwierdzony.");
                    popupOkButton.click();
                } else {
                    logger.warn("Nieoczekiwany tekst komunikatu: {}", messageText);
                }
            } else {
                logger.warn("Brak komunikatu aktualizacji aplikacji.");
            }
        } catch (Exception e) {
            logger.error("Błąd podczas obsługi komunikatu aktualizacji aplikacji: {}", e.getMessage(), e);
        }
    }
}
