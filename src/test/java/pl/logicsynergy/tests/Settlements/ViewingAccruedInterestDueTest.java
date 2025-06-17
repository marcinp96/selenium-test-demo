package pl.logicsynergy.tests.Settlements;

import org.testng.annotations.Test;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

public class ViewingAccruedInterestDueTest extends BaseTest {

    User user = new User();

    @Test
    public void viewingAccruedInterestTest() throws InterruptedException {
        new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .openSettlementsModule()
                .openSettlementsCash()
                .doubleClickOnCashReportRecord()
                .clickOnKPButton()
                .openSettlementDocuments()
                .selectOverduePayments();
    }
}
