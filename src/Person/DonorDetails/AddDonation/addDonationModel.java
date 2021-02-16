package Person.DonorDetails.AddDonation;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class addDonationModel {
    protected List<String> getItemNameList(String sql) {
        List<String> list = new ArrayList<>();

        OracleConnection oc = new OracleConnection();
        try {
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(String.valueOf(rs.getString(1)));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("getItemNameList");
        }
        return list;
    }
    OracleConnection oc = new OracleConnection();
    private String ID;
    protected boolean isDonationAddSuccessful(int amount,Date donation_date,String donorId) {

        try {
            java.sql.Date sqlDate = new java.sql.Date(donation_date.getTime());
            setDonorId(donorId);
            String sql = "insert into donation_history(don_date,amount,donor_id) values(?,?,?)";
            PreparedStatement ps = oc.conn.prepareStatement(sql);

            ps.setDate(1, sqlDate);
            ps.setInt(2, amount);
            ps.setString(3,getDonorId());
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("isdonationSuccessful\n\n");
            e.printStackTrace();
            return false;
        }


        return false;
    }
    protected String getDonorName(String donorID){
        try {
            OracleConnection oc=new OracleConnection();
            String sql="select name from DOnor where DOnor_ID=?";
            PreparedStatement ps=oc.conn.prepareStatement(sql);
            ps.setString(1,donorID);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    private void setDonorId(String donorId) {
        try {
            OracleConnection oc = new OracleConnection();
            String sql = "select DOnor_ID from DOnor where DOnor_ID=?";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, donorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ID = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDonorId() {
        return ID;
    }
}
