package pl.logicsynergy.pages.GM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DocumentsListPage {

    //Zakladka Lista dokumentow
    @FindBy(id = "gtbNew")
    private WebElement addNewDocumentButton;

    //Okno Tworz nowy dokument
    @FindBy(xpath = "//*[@id='dbfStorageTxtSymboltid']")
    private WebElement storageNameInput;

    @FindBy(xpath = "//*[@id='cbDocumentKind']/div[@class='v-filterselect v-widget v-has-width']/input")
    private WebElement documentTypeInput;

    @FindBy (id = "btnCreate")
    private WebElement createNewDocumentButton;

    //Okno Dokument
    @FindBy (xpath = "//*[@id='btnUpdateRegistryNo']/span")
    private WebElement updateRegistryNumberButton;

    @FindBy (xpath = "//*[@id='dbfCustomer']/input[1]")
    private WebElement customerIdInput;

    @FindBy (xpath = "//*[@id='txtAccount5']/div/div/div[1]/input")
    private WebElement account5Input;

    @FindBy(id = "gtbSave")
    private WebElement saveDocumentHeaderButton;

    @FindBy(xpath = "//*[@id='gtbPosition']//*[@id='gtbNew']")
    private WebElement addDocumentPositionButton;

    @FindBy(xpath = "//*[@id='dbfIndexTxtSymbol']/div/input")
    private WebElement indexInput;

    @FindBy(xpath = "//*[@id='txtDocumentPrice']/input")
    private WebElement priceInput;

    @FindBy(id = "gtbSave")
    private WebElement saveDocumentPositionButton;

    @FindBy(xpath = "//*[@id='btnConfirm']")
    private WebElement confirmDocumentButton;

    //Komunikat zatwierdzania/drukowania dokumentu
    @FindBy(xpath = "//*[@id='btnPopupYes']")
    private WebElement popupYesButton;

    @FindBy(xpath = "//*[@id='btnPopupNo']")
    private WebElement popupNoButton;

    private WebDriver driver;
    private WebDriverWait wait;

    public DocumentsListPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // <- tu driver jest już nie-null
        PageFactory.initElements(driver, this);
        System.out.println(driver);
    }

    public DocumentsListPage addNewDocument(String storageName, String documentType, String customerId, String account5, String index, String price) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Kliknięcie przycisku "Dodaj nowy dokument"
        wait.until(ExpectedConditions.elementToBeClickable(addNewDocumentButton)).click();

        // Wprowadzenie nazwy magazynu
        wait.until(ExpectedConditions.visibilityOf(storageNameInput)).sendKeys(storageName);
        Thread.sleep(500);
        storageNameInput.sendKeys(Keys.ENTER);

        // Wprowadzenie rodzaju dokumentu
        Actions actions = new Actions(driver);

        actions.moveToElement(documentTypeInput)
                .click()
                .sendKeys(documentType) // Wprowadź tekst
                .pause(Duration.ofMillis(500)) // Pauza 0,5 sekundy
                .sendKeys(Keys.ENTER) // Kliknij Enter
                .build()
                .perform();

        // Kliknięcie przycisku "Utwórz nowy dokument"
        wait.until(ExpectedConditions.elementToBeClickable(createNewDocumentButton)).click();

        // Wprowadzenie danych w formularzu
        wait.until(ExpectedConditions.visibilityOf(customerIdInput)).sendKeys(customerId);
        wait.until(ExpectedConditions.visibilityOf(account5Input)).sendKeys(account5);

        // Zapis nagłówka dokumentu
        wait.until(ExpectedConditions.elementToBeClickable(saveDocumentHeaderButton)).click();
        Thread.sleep(500);

        // Dodawanie pozycji dokumentu
        wait.until(ExpectedConditions.elementToBeClickable(addDocumentPositionButton)).click();
        wait.until(ExpectedConditions.visibilityOf(indexInput)).sendKeys(index);
        Thread.sleep(500);
        indexInput.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(priceInput)).sendKeys(price);

        // Zapis pozycji dokumentu i potwierdzenie
        wait.until(ExpectedConditions.elementToBeClickable(saveDocumentPositionButton)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(confirmDocumentButton)).click();

        return this;
    }

    public String getConfirmComunicat() {
        String xpath = "//div[@class='v-slot v-slot-ls-wrap']";
        String confirmComunicat = driver.findElement(By.xpath(xpath)).getText();
        return confirmComunicat;
    }

    public WebElement getAddedDocumentElement() {
        String xpath = "//div[@id='grid']//td[@class='v-grid-cell' and text()='" + "MPTest" + "']";
        return driver.findElement(By.xpath(xpath));
    }
}
