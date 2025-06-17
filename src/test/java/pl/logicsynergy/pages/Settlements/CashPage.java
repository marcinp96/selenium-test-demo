package pl.logicsynergy.pages.Settlements;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.logicsynergy.tests.BaseTest;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class CashPage {

    //private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @FindBy(id = "gtbNew")
    private WebElement addNewRecordButton;

    @FindBy(xpath = "//*[@id='txtSymbol']/input")
    private WebElement symbolInput;

    @FindBy(xpath = "//*[@id='txtName']/input")
    private WebElement nameInput;

    @FindBy(xpath = "//*[@id='txtAccount']/div/div/div/div/div/div[2]/div/input")
    private WebElement accountInput;

    @FindBy(xpath = "//*[@id='rViewRole']/div/div/div[1]/input")
    private WebElement permissionToViewInput;

    @FindBy(xpath = "//*[@id='rModifyRole']/div/div/div[1]/input")
    private WebElement permissionToModifyInput;

    @FindBy(id = "gtbSave")
    private WebElement saveRecordButton;

    @FindBy(xpath = "//span[text()='Opcje']")
    private WebElement optionsButton;

    @FindBy(xpath = "//span[text()='Rodzaje płatności bezgotówkowych']")
    private WebElement typesOfCashlessPaymentsItem;

    @FindBy(xpath = "//tr[@role='row' and contains(., 'TestMP')]")
    private WebElement cashReportRecord;

    @FindBy(xpath = "//*[@id='grid']/div[3]/table/tbody")
    private WebElement tableBody;  // WebElement do ciała tabeli

    // Dodatkowe definicje do wierszy i kolumn
    @FindBy(xpath = ".//tr")
    private List<WebElement> rows;  // Lista wierszy w tabeli

    @FindBy(xpath = ".//td")
    private List<WebElement> columns;  // Lista kolumn w wierszu


    private WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public CashPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CashReportItemsPage doubleClickOnCashReportRecord() throws InterruptedException {
        Thread.sleep(1000);
        Actions actions = new Actions(driver);
        actions.doubleClick(cashReportRecord)
                .perform();
        return new CashReportItemsPage(driver);
    }

    public CashPage createNewCash() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton)).click();
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(symbolInput)).sendKeys("MP");
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys("TestMP");
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(accountInput)).sendKeys("00201");
        accountInput.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(permissionToViewInput)).sendKeys("ADMIN");
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(permissionToModifyInput)).sendKeys("ADMIN");
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(saveRecordButton)).click();

        return this;
    }

    public WebElement getCellIfExists() {

        // Iteracja po wierszach tabeli
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.xpath("./td")); // Pobieramy wszystkie kolumny w danym wierszu
            String columnText = columns.get(1).getText(); // Pobieramy tekst z drugiej kolumny

            if (columnText.contains("TestMP")) {
                return columns.get(1);  // Zwracamy komórkę z drugiej kolumny
            }
        }

        // Jeśli nie znaleziono rekordu, rzucamy wyjątek
        throw new NoSuchElementException("Nie znaleziono komórki zawierającej 'TestMP' w drugiej kolumnie.");
    }

    public CashPage selectRecord() {
        Actions actions = new Actions(driver);
        actions.moveToElement(cashReportRecord)
                .click()
                .perform();
        return this;
    }

    public DictionaryOfCashlessPaymentTypesPage openDictionaryOfCashlessPaymentTypes() throws InterruptedException {
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(optionsButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(typesOfCashlessPaymentsItem)).click();
        return new DictionaryOfCashlessPaymentTypesPage(driver);
    }
}
