package pl.logicsynergy.pages.Settlements;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DocumentsPage {

    private WebDriver driver;

    public DocumentsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void selectOverduePayments() throws InterruptedException {
        Thread.sleep(1000);

        WebElement grid = driver.findElement(By.xpath("//div[contains(concat(' ', normalize-space(@class), ' '), ' v-grid ') and not(contains(concat(' ', normalize-space(@class), ' '), ' v-disabled '))]/div[contains(@class,'v-grid-tablewrapper')]/table"));

        Set<String> processedRows = new HashSet<>();
        List<WebElement> tmpRows = new ArrayList<>();
        int lastKnownSize = 0;

        Actions actions = new Actions(driver);

        while (true) {
            List<WebElement> newRows = method(tmpRows, processedRows);

            if (newRows.size() == lastKnownSize) {
                System.out.println("Brak nowych wierszy, kończenie przewijania.");
                break;
            }

            tmpRows = newRows;
            lastKnownSize = newRows.size();
            System.out.println("Załadowano " + lastKnownSize + " wierszy.");

            try {
                WebElement lastRow = tmpRows.get(tmpRows.size() - 1);
                actions.moveToElement(lastRow).perform();
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println("Błąd przewijania za pomocą moveToElement: " + e.getMessage());
            }

            actions.sendKeys(Keys.PAGE_DOWN).perform();
            Thread.sleep(1000);
        }
    }

    List<WebElement> method(List<WebElement> tmpRows, Set<String> processedRows) throws InterruptedException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        System.out.println("Dzisiejsza data: " + today);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(concat(' ', normalize-space(@class), ' '), ' v-grid ') and not(contains(concat(' ', normalize-space(@class), ' '), ' v-disabled '))]//tr[@role='row']")));

        System.out.println("Liczba wierszy: " + rows.size());

        List<WebElement> toProcess = rows.stream()
                .filter(row -> !processedRows.contains(row.getText()))
                .collect(Collectors.toList());

        for (WebElement row : toProcess) {
            try {
                WebElement dateCell = row.findElement(By.xpath(".//td[7]"));
                String dateText = dateCell.getText().trim();
                System.out.println("Surowa data z tabeli: " + dateText);

                if (dateText.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    LocalDate paymentDate = LocalDate.parse(dateText, dateFormatter);
                    System.out.println("Przekształcona data płatności: " + paymentDate);
                    boolean isOverdue = paymentDate.isBefore(today);

                    WebElement checkboxInput = row.findElement(By.xpath(".//td[1]//input[@type='checkbox']"));
                    boolean isCheckboxSelected = checkboxInput.isSelected();

                    if (isOverdue) {
                        System.out.println("Płatność jest przeterminowana: " + paymentDate);
                        if (!isCheckboxSelected && checkboxInput.isDisplayed() && checkboxInput.isEnabled()) {
                            new WebDriverWait(driver, Duration.ofSeconds(3))
                                    .until(ExpectedConditions.elementToBeClickable(checkboxInput))
                                    .click();
                            System.out.println("Zaznaczenie checkboxa.");
                        }
                        assert checkboxInput.isSelected() : "Checkbox dla przeterminowanej płatności nie został zaznaczony!";
                    } else {
                        System.out.println("Płatność nie jest przeterminowana: " + paymentDate);
                        assert !isCheckboxSelected : "Checkbox dla aktualnej płatności został błędnie zaznaczony!";
                    }

                    processedRows.add(row.getText());
                } else {
                    System.out.println("Nieprawidłowy lub pusty format daty: " + dateText);
                }
            } catch (NoSuchElementException e) {
                System.out.println("Błąd: Brak wymaganych elementów w wierszu: " + e.getMessage());
            }
        }

        return toProcess;
    }

}






