package Person.DonorDetails.AddDonor;

import Person.ImportPersonnelFile;
import Person.PersonalInformation;
import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddDonorModel {

    protected boolean addDonor(){
        String sql="call add_donor(?) ";
        return new ImportPersonnelFile().addPerson(sql);
    }


    protected boolean isAddDonorSuccessful(String name, String gender, String address, String phone, String occupation, String email) {

        try {

            String sql = "insert into donor(name,email,occupation,gender,phone,address) values(?,?,?,?,?,?)";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, occupation);
            ps.setString(4, gender);
            ps.setString(5, phone);
            ps.setString(6, address);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("isAddDonorSuccessful\n\n");
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
