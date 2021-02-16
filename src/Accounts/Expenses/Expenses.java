package Accounts.Expenses;

import java.util.Date;

public class Expenses {
    int amount;
    String expId,expType,expTitle,expDescription;
    Date expDate;



    public Expenses(String expId, String expType, String expTitle,String expDescription,int amount,Date expDate) {
        this.expId = expId;
        this.expType = expType;
        this.expTitle = expTitle;
        this.expDescription = expDescription;
        this.amount = amount;
        this.expDate = expDate;
    }


    public String getExpId() {
        return expId;
    }

    public void setExpId(String expId) {
        this.expId = expId;
    }

    public String getExpType() {
        return expType;
    }

    public void setExpType(String expType) {
        this.expType = expType;
    }

    public String getExpTitle() {
        return expTitle;
    }

    public void setExpTitle(String expTitle) {
        this.expTitle = expTitle;
    }

    public String getExpDescription() {
        return expDescription;
    }

    public void setExpDescription(String expDescription) {
        this.expDescription = expDescription;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }
}
