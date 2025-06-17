package pl.logicsynergy.tests.GM;

import org.testng.annotations.Test;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

public class GenerateAccountingNoteTest extends BaseTest {

    User user = new User();

    @Test
    public void generateNewAccountingNote() throws InterruptedException {
        LogInPage aaa = new LogInPage(driver);

                aaa.logInValidData(user.getLogin(), user.getPassword())
                        .openGMModule()
                        .openSubMenuAccountingDecree()
                        .openAccountingNote()
                        .openSubMenuEdit()
                        .generateAccountingNote();
    }
}
