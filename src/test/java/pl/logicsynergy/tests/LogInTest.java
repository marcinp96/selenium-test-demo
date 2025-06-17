package pl.logicsynergy.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.logicsynergy.models.User;
import pl.logicsynergy.pages.LogInPage;

public class LogInTest extends BaseTest {

    @Test
    public void logInTest() {

        User user = new User();

        WebElement usernameLabel = new LogInPage(driver)
                .logInValidData(user.getLogin(), user.getPassword())
                .getUsernameLabel();

        Assert.assertEquals(usernameLabel.getText(), user.getLogin());
    }
}
