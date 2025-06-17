package pl.logicsynergy.tests.Settlements;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

public class CardPaymentTest extends BaseTest {

    User user = new User();

    @Test
    public void cardPaymentTest() throws InterruptedException {
        WebElement cell = new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .openSettlementsModule()
                .openSettlementsCash()
                .createNewCash()
                .selectRecord()
                .openDictionaryOfCashlessPaymentTypes()
                .addNewCashlessPaymentType()
                .closeWindow()
                .doubleClickOnCashReportRecord()
                .creatingNewCashRaport()
                .clickOnKPButton()
                .enteringDocumentItem()
                .payment();

        Assert.assertEquals(cell.getText(), "Nowa pozycja", "Brak pozycji raportu kasowego o treści: Nowa pozycja");
    }
}