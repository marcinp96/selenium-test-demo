package pl.logicsynergy.pages.Settlements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CashReportItemsPage {

    @FindBy(id = "btnKP")
    private WebElement KPbutton;

    //Okno wprowadzanie nowego raportu
    @FindBy(id = "btnPopupYes")
    private WebElement popupYesButton;

    @FindBy(xpath = "//*[@id='txtaDescription']/input")
    private WebElement descriptionInput1;

    @FindBy(id = "btnConfirm")
    private WebElement popupConfirmButton;

    private WebDriver driver;
    private WebDriverWait wait;

    public CashReportItemsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        System.out.println(driver);
    }

    public CashReportItemsPage creatingNewCashRaport() throws InterruptedException {
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(popupYesButton)).click();
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(descriptionInput1)).sendKeys("Nowy raport");
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(popupConfirmButton)).click();
        return this;
    }

    public AddCashReportItemsPage clickOnKPButton() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(KPbutton)).click();
        return new AddCashReportItemsPage(driver);
    }
}
