package pl.logicsynergy.tests;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.logicsynergy.utils.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        driver = DriverFactory.createDriver(headless);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Wymuszona rozdzielczość niezależnie od trybu
        Dimension windowSize = new Dimension(1920, 1080);
        driver.manage().window().setSize(windowSize);
        logger.info("Ustawiono ręcznie rozmiar okna na: {}x{}", windowSize.getWidth(), windowSize.getHeight());

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

    public void takeScreenshot(String fileName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            File destination = new File("screenshots/" + fileName + "_" + timestamp + ".png");
            destination.getParentFile().mkdirs();  // Tworzy folder, jeśli nie istnieje
            FileHandler.copy(source, destination);
            logger.info("Screenshot zapisany: " + destination.getAbsolutePath());
        } catch (IOException e) {
            logger.error("Błąd przy zapisywaniu screenshotu", e);
        }
    }
}
