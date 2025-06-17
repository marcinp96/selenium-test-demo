package pl.logicsynergy.pages.Evidence;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VATRatesDictionaryPage {

    @FindBy(id = "gtbNew")
    private WebElement addNewRecordButton;

    @FindBy(xpath = "//*[@id='txtSymbol']/input")
    private WebElement symbolInput;

    @FindBy(xpath = "//*[@id='txtName']/input")
    private WebElement nameInput;

    @FindBy(xpath = "//*[@id='txtValue']/input")
    private WebElement valueInput;

    @FindBy(id = "gtbSave")
    private WebElement saveRecordButton;

    private WebDriver driver;
    private WebDriverWait wait;

    public VATRatesDictionaryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // <- tu driver jest już nie-null
        PageFactory.initElements(driver, this);
        System.out.println(driver);
    }

    public VATRatesDictionaryPage addNewVATRate() throws InterruptedException {
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton)).click();
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(symbolInput)).sendKeys("E");
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(nameInput)).sendKeys("23%");
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(valueInput)).clear();
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(valueInput)).sendKeys("23.0");
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(saveRecordButton)).click();

        return this;
    }
}
