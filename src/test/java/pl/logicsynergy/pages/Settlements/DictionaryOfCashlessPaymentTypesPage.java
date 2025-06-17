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

public class DictionaryOfCashlessPaymentTypesPage {

    @FindBy(xpath = "(//*[@id='gtbNew'])[2]")
    private WebElement addNewRecordButton;

    @FindBy(xpath = "//*[@id='dbfPaymentKind']/input[1]")
    private WebElement cashlessPaymentTypeInput;

    @FindBy(xpath = "(//*[@id='txtAccount']/div/div/div/div/div/div[2]/div/input)[2]")
    private WebElement accountInput;

    @FindBy(id = "gtbSave")
    private WebElement saveRecordButton;

    @FindBy(xpath = "(//div[contains(@class, 'v-window-closebox')])[last()]")
    private WebElement closeWindowButton;

    private WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public DictionaryOfCashlessPaymentTypesPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public DictionaryOfCashlessPaymentTypesPage addNewCashlessPaymentType() throws InterruptedException {
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(addNewRecordButton)).click();
        Thread.sleep(200);
        wait.until(ExpectedConditions.visibilityOf(cashlessPaymentTypeInput)).sendKeys("1");
        Thread.sleep(200);
        Actions actions = new Actions(driver);
        actions.moveToElement(accountInput)
                .click()
                .sendKeys("00200")
                .pause(Duration.ofMillis(500))
                .sendKeys(Keys.ENTER)
                .build()
                .perform();
        //wait.until(ExpectedConditions.visibilityOf(accountInput)).sendKeys("00200");
        Thread.sleep(200);
        wait.until(ExpectedConditions.elementToBeClickable(saveRecordButton)).click();
        return this;
    }

    public CashPage closeWindow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(closeWindowButton)).click();
        return new CashPage(driver);
    }


}
