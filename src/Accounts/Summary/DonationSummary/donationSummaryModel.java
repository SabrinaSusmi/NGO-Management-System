package Accounts.Summary.DonationSummary;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class donationSummaryModel {
    protected ObservableList<Donation> getDonationTableRecords(int month, int year) throws SQLException {
        String sql = "select dt.id,dt.don_date,dt.amount,dt.donor_id,d.name from donation_history dt,donor d where d.donor_id=dt.donor_id and extract(month from don_date)=? and extract(year from don_date)=?";
        ObservableList<Donation> donationList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1,month);
            ps.setInt(2,year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                Date date=rs.getDate(2);
                int amount=rs.getInt(3);
                String donorId=rs.getString(4);
                String donorName=rs.getString(5);

                Donation donation = new Donation(id,date,amount,donorId,donorName);
                donationList.add(donation);
            }
            return donationList;
        } catch (Exception e) {
            System.out.println("getTableRecords : DonationHistoryModel");
            e.printStackTrace();
            throw e;
        }
    }
    protected int getTotalDonation(int month, int year){
        String sql = "select sum(amount) from donation_history where (extract(month from don_date)=?) and (extract(year from don_date)=?)";

        try {
            OracleConnection oc=new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1,month);
            ps.setInt(2,year);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                return rs.getInt(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }




}
