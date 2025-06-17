package pl.logicsynergy.models.GM;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GMDocumentModel {

    private String storageName = "TST";
    private String documentType = "PZ - Przyjęcie z zewnątrz";
    private String customerId = "1877";
    private String account5 = "5010140000";
    private String index = "0000000001";
    private String price = "1234";

    public String getStorageName() {
        return storageName;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccount5() {
        return account5;
    }

    public String getIndex() {
        return index;
    }

    public String getPrice() {
        return price;
    }
}
