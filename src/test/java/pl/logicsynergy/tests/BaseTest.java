package pl.logicsynergy.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.logicsynergy.utils.DriverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Odczytujemy parametr systemowy: -Dheadless=true
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        driver = DriverFactory.getDriver(headless);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        logger.info("Załadowano ustawienia przeglądarki");

        driver.get("http://172.18.93.93:8100/eMediaZadania1/ui");
        logger.info("Załadowano stronę logowania");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Zamknięto przeglądarkę");
        }
    }
}
