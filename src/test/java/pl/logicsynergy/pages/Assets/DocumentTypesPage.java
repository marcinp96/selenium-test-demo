package pl.logicsynergy.pages.Assets;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.logicsynergy.pages.Evidence.VATRatesDictionaryPage;

import java.time.Duration;

public class DocumentTypesPage {

    @FindBy(id = "gtbNew")
    private WebElement addNewRecordButton;

    @FindBy(xpath = "//*[@id='txtSymbol']/input")
    private WebElement symbolInput;

    @FindBy(xpath = "//*[@id='txtName']/input")
    private WebElement nameInput;

    @FindBy(xpath = "//*[@id='cbFType']/div/input")
    private WebElement documentTypeInput;

    @FindBy(xpath = "//*[@id='cbOperationType']/div/input")
    private WebElement operationTypeInput;

    @FindBy(xpath = "//*[@id='cbFinalStatus']/div/input")
    private WebElement finalStatusInput;

    @FindBy(id = "gtbSave")
    private WebElement saveButton;

    @FindBy(xpath = "(//*[@id='grid']/div[3]/table/tbody/tr)[last()]/td[2]")
    private WebElement cellToAssert;

    private WebDriver driver;
    private WebDriverWait wait;

    public DocumentTypesPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // <- tu driver jest już nie-null
        PageFactory.initElements(driver, this);
        System.out.println(driver);
    }

    public WebElement addNewDocumentType() throws InterruptedException {
        // Pomocnicza metoda do scrollowania:
        scrollToElement(addNewRecordButton);
        wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton));
        addNewRecordButton.click();

        scrollToElement(symbolInput);
        wait.until(ExpectedConditions.visibilityOf(symbolInput));
        symbolInput.sendKeys("OT");

        scrollToElement(nameInput);
        wait.until(ExpectedConditions.visibilityOf(nameInput));
        nameInput.sendKeys("Przyjęcie Środka Trwałego");

        scrollToElement(documentTypeInput);
        wait.until(ExpectedConditions.visibilityOf(documentTypeInput));
        documentTypeInput.sendKeys("OT");
        Thread.sleep(200);
        documentTypeInput.sendKeys(Keys.ENTER);

        scrollToElement(operationTypeInput);
        wait.until(ExpectedConditions.visibilityOf(operationTypeInput));
        operationTypeInput.sendKeys("N - Wprowadzenie nowego ST");
        Thread.sleep(200);
        operationTypeInput.sendKeys(Keys.ENTER);

        scrollToElement(finalStatusInput);
        wait.until(ExpectedConditions.visibilityOf(finalStatusInput));
        finalStatusInput.sendKeys("C - Czynny");
        Thread.sleep(200);
        finalStatusInput.sendKeys(Keys.ENTER);

        scrollToElement(saveButton);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();

        wait.until(ExpectedConditions.visibilityOf(cellToAssert)); // Oczekiwanie na widoczność
        return cellToAssert;
    }

    // Metoda scrollująca:
    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
