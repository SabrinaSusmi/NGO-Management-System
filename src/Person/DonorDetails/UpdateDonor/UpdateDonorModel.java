package Person.DonorDetails.UpdateDonor;

import Utilities.OracleConnection;
import Person.PersonalInformation;

import java.sql.PreparedStatement;


public class UpdateDonorModel {
    protected boolean isUpdateDonorSuccessful(String address, String phone, String occupation, String email,  String Donor_id) {
        OracleConnection oc = new OracleConnection();
        try {
            String sql = "update donor set address=?,phone=?,occupation=?,email=? where donor_id=? ";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, address);
            ps.setString(2, phone);
            ps.setString(3, occupation);
            ps.setString(4, email);
            ps.setString(5, Donor_id);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("  donorModel /// isUpdateDonorSuccessful\n\n");
            e.printStackTrace();

            return false;
        }
        return false;
    }


    protected  String[] setDonorInformation(String[] info, String Donor_id) {

        String sql = "select email,phone,occupation,address,name from Donor where Donor_id=?";
        return new PersonalInformation().setInformationInUpdateWindow(info,Donor_id,sql);
    }

}
