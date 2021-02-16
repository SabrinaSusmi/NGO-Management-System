package SupplyDetails.Food;

import java.util.Date;

public class Food {
    int price, quantity;
    Date pdate,edate;
    String name,supplier,id;

    public Food(int price, int quantity, String id, Date pdate, Date edate, String name, String supplier) {
        this.price = price;
        this.quantity = quantity;
        this.id = id;
        this.pdate = pdate;
        this.edate = edate;
        this.name = name;
        this.supplier = supplier;
    }



    public Food(String name, int quantity, int price) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
    }

    public Food(int price, int quantity,  String name, String supplier) {
        this.price = price;
        this.quantity = quantity;
        this.name = name;
        this.supplier = supplier;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
