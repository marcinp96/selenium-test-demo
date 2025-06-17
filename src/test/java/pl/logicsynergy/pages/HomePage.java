package pl.logicsynergy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.logicsynergy.pages.Assets.DocumentTypesPage;
import pl.logicsynergy.pages.Evidence.VATRatesDictionaryPage;
import pl.logicsynergy.pages.GM.AccountingNotePage;
import pl.logicsynergy.pages.GM.DocumentsListPage;
import pl.logicsynergy.pages.GM.StoragesPage;
import pl.logicsynergy.pages.Settlements.CashPage;

import java.time.Duration;

public class HomePage {

    @FindBy(id = "lblUserName")
    private WebElement usernameLabel;

    //Assets
    @FindBy (id = "ASSET_SYSTEM")
    private WebElement assetsButton;

    @FindBy (id = "ASSET_DOCUMENT_TYPE")
    private WebElement assetsDocumentTypesButton;

    //Evidence
    @FindBy (id = "EVIDENCE")
    private WebElement evidenceButton;

    @FindBy (id = "EVIDENCE_IMS_DICTIONARIES")
    private WebElement evidenceImsDictionariesButton;

    @FindBy (id = "EVIDENCE_IMS_DICTIONARIES-3")
    private WebElement evidenceVATRatesDictionary;

    //FK
    @FindBy (id = "FK_SYSTEM")
    private WebElement fkButton;

    @FindBy (id = "BILLING_CASH")
    private WebElement fkDocumentsList;

    //Rozrachunki
    @FindBy (id = "BILLING_SETTLEMENTS")
    private WebElement settlementsButton;

    @FindBy (id = "BILLING_CASH")
    private WebElement settlementsCash;

    //GM
    @FindBy (id = "GM_SYSTEM")
    private WebElement gmButton;

    @FindBy (id = "GM_STORAGES")
    private WebElement gmStorages;

    @FindBy (id = "GM_DOCUMENTS_LIST")
    private WebElement gmDocumentsList;

    @FindBy (xpath = "//*[@id='grRibbon']/div/div/div/div/div[2]/div/div[9]/div/span")
    private WebElement gmAccountingDecree;

    @FindBy (xpath = "//*[@class='v-app eMediaTheme emedia v-overlay-container']/div[2]/div/div/span[1]/span")
    private WebElement gmAccountingNote;

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        System.out.println(driver);
    }

    public WebElement getUsernameLabel() {
        return usernameLabel;
    }

    //Środki trwałe Menu
    public HomePage openAssetsModule() {
        wait.until(ExpectedConditions.elementToBeClickable(assetsButton)).click();
        return this;
    }

    public DocumentTypesPage openAssetsDocumentTypes() throws InterruptedException {
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(assetsDocumentTypesButton)).click();
        return new DocumentTypesPage(driver);
    }

    //Ewidencja Menu
    public HomePage openEvidenceModule() {
        wait.until(ExpectedConditions.elementToBeClickable(evidenceButton)).click();
        return this;
    }

    public VATRatesDictionaryPage openVATRatesDictionary() throws InterruptedException {
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(evidenceImsDictionariesButton)).click();
        Thread.sleep(500);
        wait.until(ExpectedConditions.elementToBeClickable(evidenceVATRatesDictionary)).click();
        return new VATRatesDictionaryPage(driver);
    }

    //Rozrachunki Menu
    public HomePage openSettlementsModule() {
        wait.until(ExpectedConditions.elementToBeClickable(settlementsButton)).click();
        return this;
    }

    public CashPage openSettlementsCash() {
        wait.until(ExpectedConditions.elementToBeClickable(settlementsCash)).click();
        return new CashPage(driver);
    }

    //GM Menu
    public HomePage openGMModule() {
        gmButton.click();
        return this;
    }

    public StoragesPage openGMStorages() {
        gmStorages.click();
        return new StoragesPage(driver);
    }

    public DocumentsListPage openGMDocuments() {
        gmDocumentsList.click();
        return new DocumentsListPage(driver);
    }

    public HomePage openSubMenuAccountingDecree() {
        gmAccountingDecree.click();
        return this;
    }

    public AccountingNotePage openAccountingNote() {
        gmAccountingNote.click();
        return new AccountingNotePage(driver);
    }
}
