package pl.logicsynergy.models.GM;

public class GMStorageModel {

    private String symbol = "MPTest";
    private String valuationMethod = "F - FIFO";
    private String storageType = "M - Magazyn";
    private String storageName = "MPTest";
    private String account = "311";
    private String saleType = "W - Wprost ważona";

    public String getSymbol() {
        return symbol;
    }

    public String getValuationMethod() {
        return valuationMethod;
    }

    public String getStorageType() {
        return storageType;
    }

    public String getStorageName() {
        return storageName;
    }

    public String getAccount() {
        return account;
    }

    public String getSaleType() {
        return saleType;
    }
}
