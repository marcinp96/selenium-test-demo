package pl.logicsynergy.tests.Assets.Documents;

//EMETST-504

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

public class AddOTDocumentTest extends BaseTest {
    User user = new User();

    @Test
    public void addOTDocumentTest() throws InterruptedException {
        WebElement OTDocumentType = new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .openAssetsModule()
                .openAssetsDocumentTypes()
                .addNewDocumentType();

        Assert.assertEquals(OTDocumentType.getText(), "Przyjęcie Środka Trwałego", "Brak rekordu w gridzie o nazwie: Przyjęcie Środka Trwałego");
    }
}
