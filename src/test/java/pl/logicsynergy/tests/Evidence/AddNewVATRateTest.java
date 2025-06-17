package pl.logicsynergy.tests.Evidence;

import org.testng.annotations.Test;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

//EMETST-5

public class AddNewVATRateTest extends BaseTest {
    User user = new User();

    @Test
    public void addNewVATRateTest() throws InterruptedException {
        new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .openEvidenceModule()
                .openVATRatesDictionary()
                .addNewVATRate();
    }
}
