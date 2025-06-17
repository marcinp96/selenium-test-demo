package pl.logicsynergy.tests.GM;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.logicsynergy.models.GM.GMDocumentModel;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

public class AddNewDocumentTest extends BaseTest {

    User user = new User();
    GMDocumentModel gmDocumentModel = new GMDocumentModel();

    @Test
    public void addNewDocumentTest() throws InterruptedException {
        WebElement newDocument = new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .openGMModule()
                .openGMDocuments()
                .addNewDocument(gmDocumentModel.getStorageName(), gmDocumentModel.getDocumentType(), gmDocumentModel.getCustomerId(), gmDocumentModel.getAccount5(), gmDocumentModel.getIndex(), gmDocumentModel.getPrice())
                .getAddedDocumentElement();

        //Assert.assertTrue(newDocument.contains("Dokument zatwierdzony pod numerem:"), "Nie udalo sie zatwierdzic dokumentu.");



    }
}
