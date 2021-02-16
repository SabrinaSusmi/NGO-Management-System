package SupplyDetails.HealthProduct.Vaccine;

import java.util.Date;

public class Vaccine {
    int price, quantity;
    Date pdate, edate;
    String name,supplier,manufacturer,id;

    public Vaccine(int price, int quantity, Date pdate, Date edate, String name, String supplier, String manufacturer, String id) {
        this.price = price;
        this.quantity = quantity;
        this.pdate = pdate;
        this.edate = edate;
        this.name = name;
        this.supplier = supplier;
        this.manufacturer = manufacturer;
        this.id = id;
    }

    public Vaccine(int price, int quantity, String name) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
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


    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
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
