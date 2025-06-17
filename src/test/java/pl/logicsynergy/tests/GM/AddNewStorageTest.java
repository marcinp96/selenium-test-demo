package pl.logicsynergy.tests.GM;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.logicsynergy.models.GM.GMStorageModel;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;
import pl.logicsynergy.tests.BaseTest;

public class AddNewStorageTest extends BaseTest {

    User user = new User();
    GMStorageModel gmStorageModel = new GMStorageModel();

    @Test
    public void addNewStorageTest() throws InterruptedException {
        WebElement newStorage = new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .openGMModule()
                .openGMStorages()
                .addNewStorage(gmStorageModel.getSymbol(), gmStorageModel.getValuationMethod(), gmStorageModel.getStorageType(), gmStorageModel.getStorageName(), gmStorageModel.getAccount(), gmStorageModel.getSaleType())
                .getAddedStorageElement();

        Assert.assertEquals(newStorage.getText(),gmStorageModel.getSymbol());
    }
}
