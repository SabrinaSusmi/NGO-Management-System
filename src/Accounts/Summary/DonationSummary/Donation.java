package Accounts.Summary.DonationSummary;

import java.util.Date;

public class Donation {
    int amount;
    String donorId,donorName,donationId;
    Date donDate;



    public Donation(String donationId,Date donDate,int amount, String donorId, String donorName) {
        this.donationId = donationId;
        this.donDate = donDate;
        this.amount = amount;
        this.donorId = donorId;
        this.donorName = donorName;
    }


    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }



    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDonDate() {
        return donDate;
    }

    public void setDonDate(Date donDate) {
        this.donDate = donDate;
    }
}
