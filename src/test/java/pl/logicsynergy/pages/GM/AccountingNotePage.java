package pl.logicsynergy.pages.GM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.logicsynergy.tests.BaseTest;

import java.time.Duration;

public class AccountingNotePage {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @FindBy (id = "menuBar-376")
    private WebElement menuEditButton;

    @FindBy (id = "menuBar-377")
    private WebElement generateAccountingNoteButton;

    @FindBy(xpath = "//*[@id='btnPopupYes']")
    private WebElement popupYesButton;

    @FindBy(xpath = "//*[@id='btnPopupOk']")
    private WebElement popupOkButton;

    private WebDriver driver;

    public AccountingNotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public AccountingNotePage openSubMenuEdit() {
        menuEditButton.click();
        return this;
    }

    public AccountingNotePage generateAccountingNote() {
        generateAccountingNoteButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            if (popupYesButton.isDisplayed()) {
                popupYesButton.click();
                logger.info("Kliknięto w przycisk 'Tak'");
            } else {
                logger.warn("Przycisk 'Tak' nie jest widoczny");
            }
        } catch (Exception e) {
            logger.error("Błąd przy obsłudze przycisku 'Nie': {}", e.getMessage(), e);
        }

        try {
            if (popupOkButton.isDisplayed()) {
                popupOkButton.click();
                logger.info("Kliknięto w przycisk 'Ok'");
            } else {
                logger.warn("Przycisk 'Ok' nie jest widoczny");
            }
        } catch (Exception e) {
            logger.error("Błąd przy obsłudze przycisku 'Ok': {}", e.getMessage(), e);
        }

        return this;
    }
}
