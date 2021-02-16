package SupplyDetails.HealthProduct.Medicine;

public class Medicine {
    int price, quantity;
    String pdate, edate;
    String name,supplier,manufacturer,id;

    public Medicine(int price, int quantity, String pdate, String edate, String name, String supplier, String manufacturer, String id) {
        this.price = price;
        this.quantity = quantity;
        this.pdate = pdate;
        this.edate = edate;
        this.name = name;
        this.supplier = supplier;
        this.manufacturer = manufacturer;
        this.id = id;
    }


    public Medicine(int price, int quantity, String name) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
    }

    public Medicine(int price, int quantity, String edate, String name, String manufacturer) {
        this.price = price;
        this.quantity = quantity;
        this.edate = edate;
        this.name = name;
        this.manufacturer = manufacturer;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pDate) {
        this.pdate = pDate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String eDate) {
        this.edate = eDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
