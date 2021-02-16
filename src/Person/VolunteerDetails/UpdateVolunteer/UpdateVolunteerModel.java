package Person.VolunteerDetails.UpdateVolunteer;

import Utilities.OracleConnection;
import Person.PersonalInformation;

import java.sql.PreparedStatement;

public class UpdateVolunteerModel {
    protected boolean isUpdateVolunteerSuccessful(String address, String phone, String occupation, String email,  String Volunteer_id) {
        OracleConnection oc = new OracleConnection();
        try {
            String sql = "update Volunteer set address=?,phone=?,occupation=?,email=? where Volunteer_id=? ";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, address);
            ps.setString(2, phone);
            ps.setString(3, occupation);
            ps.setString(4, email);
            ps.setString(5, Volunteer_id);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("  VolunteerModel /// isUpdateVolunteerSuccessful\n\n");
            e.printStackTrace();

            return false;
        }
        return false;
    }


    protected  String[] setVolunteerInformation(String[] info, String Volunteer_id) {

        String sql = "select email,phone,occupation,address,name from Volunteer where Volunteer_id=?";
        return new PersonalInformation().setInformationInUpdateWindow(info,Volunteer_id,sql);
    }

}
