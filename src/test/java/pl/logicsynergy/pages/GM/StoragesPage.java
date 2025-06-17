package pl.logicsynergy.pages.GM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StoragesPage {

    //Zakladka Magazyny
    @FindBy (id = "gtbNew")
    private WebElement addNewStorageButton;

    @FindBy (id = "gtbSave")
    private WebElement saveButton;

    //Zakladka Dane magazynu
    @FindBy (xpath = "//*[@id='txtSymbol']/input")
    private WebElement symbolInput;

    @FindBy (xpath = "//*[@id='cbValuationMethod']/div/input")
    private WebElement valuationMethodSelect;

    @FindBy (xpath = "//*[@id='cbType']/div/input")
    private WebElement storageTypeInput;

    @FindBy (xpath = "//*[@id='txtName']/input")
    private WebElement storageNameInput;

    @FindBy (xpath = "//*[@id='txtAccount']/div/div/div[1]/input")
    private WebElement accountInput;

    @FindBy (xpath = "//*[@id='cbSaleType']/div/input")
    private WebElement saleTypeInput;

    private WebDriver driver;

    public StoragesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebElement getAddedStorageElement() {
        String xpath = "//div[@id='grid']//td[@class='v-grid-cell' and text()='" + "MPTest" + "']";
        return driver.findElement(By.xpath(xpath));
    }

    public StoragesPage addNewStorage(String symbol, String valuationMethod, String storageType, String storageName, String account, String saleType) throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        addNewStorageButton.click();
        Thread.sleep(1000);
        symbolInput.sendKeys(symbol);
        Thread.sleep(1000);
        valuationMethodSelect.sendKeys(valuationMethod);
        Thread.sleep(1000);
        valuationMethodSelect.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        storageTypeInput.sendKeys(storageType);
        Thread.sleep(1000);
        storageTypeInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        storageNameInput.sendKeys(storageName);
        Thread.sleep(1000);
        accountInput.sendKeys(account);
        Thread.sleep(1000);
        saleTypeInput.sendKeys(saleType);
        Thread.sleep(1000);
        saleTypeInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        saveButton.click();
        Thread.sleep(1000);

        return this;
    }
}
