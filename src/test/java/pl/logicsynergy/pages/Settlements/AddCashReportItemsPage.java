package pl.logicsynergy.pages.Settlements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddCashReportItemsPage {

    @FindBy (xpath = "(//*[@id='gtbNew'])[2]")
    private WebElement addNewRecordButton;

    @FindBy (xpath = "//*[@id='cbPaymentKind']/div[1]")
    private WebElement paymentKindCombobox;

    @FindBy (xpath = "//div[@id='dbfCustomer' and @class='v-csslayout v-layout v-widget v-component-group v-csslayout-v-component-group ls-database-field v-csslayout-ls-database-field v-has-width v-has-height']/input[1]")
    private WebElement customerInput;

    @FindBy (xpath = "//*[@id='btnDocuments']")
    private WebElement documentsButton;

    //Okno Wprowadzenie pozycji dokumentu
    @FindBy(xpath = "(//*[@id='txtAmount']/input)[last()]")
    private WebElement amountInput;

    @FindBy(xpath = "(//*[@id='txtAccount']/div/div/div/div/div/div[2]/div/input)[last()]")
    private WebElement accountInput;

    @FindBy(xpath = "//*[@id='txtaDescription']/div/input")
    private WebElement descriptionInput;

    @FindBy(id = "btnSave")
    private WebElement saveButton;

    @FindBy(id = "btnConfirmDocument")
    private WebElement confirmDocumentButton;

    @FindBy(id = "btnPopupNo")
    private WebElement popupNoButton;

    @FindBy(xpath = "(//*[@id='grid']/div[3]/table/tbody/tr)[last()]")
    private WebElement cashReportItemRecord;

    @FindBy(xpath = "(//*[@id='grid']/div[3]/table/tbody/tr)[last()]/td[15]")
    private WebElement cellToAssert;

    private WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public AddCashReportItemsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public DocumentsPage openSettlementDocuments() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(500);
        customerInput.sendKeys("1");
        //Thread.sleep(500);
        documentsButton.click();
        return new DocumentsPage(driver);
    }

    public AddCashReportItemsPage enteringDocumentItem() throws InterruptedException {
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOf(amountInput)).sendKeys("123,00");
        Thread.sleep(500);
        wait.until(ExpectedConditions.visibilityOf(accountInput)).sendKeys("00200");
        accountInput.sendKeys(Keys.ENTER);
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(descriptionInput)).sendKeys("Nowa pozycja");
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
        return this;
    }

    public WebElement payment() throws InterruptedException {
        Thread.sleep(500);
        //wait.until(ExpectedConditions.visibilityOf(paymentKindCombobox));
        Actions actions = new Actions(driver);
        actions.moveToElement(paymentKindCombobox)
                .pause(Duration.ofMillis(300))
                .click()
                .pause(Duration.ofMillis(300))
                .sendKeys(Keys.ARROW_DOWN)
                .pause(Duration.ofMillis(300))
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(confirmDocumentButton)).click();
        Thread.sleep(300);
        wait.until(ExpectedConditions.elementToBeClickable(popupNoButton)).click();
        Thread.sleep(800);

        //wait.until(ExpectedConditions.visibilityOf(cashReportItemRecord));
        actions.moveToElement(cashReportItemRecord)
                .click()
                .pause(Duration.ofMillis(200));

        for (int i = 0; i < 10; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT);
        }

        actions.build().perform();

        /*WebElement cell = */wait.until(ExpectedConditions.visibilityOf(cellToAssert)); // Oczekiwanie na widoczność
        /*String cellText = cell.getText();
        System.out.println(cellText);*/
        return cellToAssert;
    }
}
