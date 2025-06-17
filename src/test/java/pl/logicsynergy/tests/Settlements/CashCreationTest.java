package pl.logicsynergy.tests.Settlements;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

public class CashCreationTest extends BaseTest {

    User user = new User();

    @Test
    public void cashCreationTest() throws InterruptedException {
        WebElement cellToAssert = new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .openSettlementsModule()
                .openSettlementsCash()
                .createNewCash()
                .getCellIfExists();

        Assert.assertEquals(cellToAssert.getText(), "TestMP", "Brak rekordu kasy o nazwie: TestMP");
    }
}
