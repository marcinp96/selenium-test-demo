package pl.logicsynergy.pages.Assets;

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
    private WebElement saveRecordButton;

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
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton)).click();
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(symbolInput)).sendKeys("OT");
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys("Przyjęcie Środka Trwałego");
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(documentTypeInput)).sendKeys("OT");
        Thread.sleep(500);
        documentTypeInput.sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.visibilityOf(operationTypeInput)).sendKeys("N - Wprowadzenie nowego ST");
        Thread.sleep(500);
        operationTypeInput.sendKeys(Keys.ENTER);
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(finalStatusInput)).sendKeys("C - Czynny");
        Thread.sleep(500);
        finalStatusInput.sendKeys(Keys.ENTER);
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(saveRecordButton)).click();

        wait.until(ExpectedConditions.visibilityOf(cellToAssert)); // Oczekiwanie na widoczność
        return cellToAssert;
    }
}
