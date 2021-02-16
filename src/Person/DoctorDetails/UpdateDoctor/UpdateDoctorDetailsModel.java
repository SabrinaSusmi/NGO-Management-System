package Person.DoctorDetails.UpdateDoctor;

import Utilities.OracleConnection;
import Person.PersonalInformation;

import java.sql.PreparedStatement;


public class UpdateDoctorDetailsModel {
    protected boolean isUpdateDoctorSuccessful(String address, String phone, String qualification, String email, String hr, String Doctor_id) {
        OracleConnection oc = new OracleConnection();
        try {
            String sql = "update doctor set address=?,phone=?,Qualification=?,email=?,Available_hr=? where doctor_id=? ";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, address);
            ps.setString(2, phone);
            ps.setString(3, qualification);
            ps.setString(4, email);
            ps.setString(5, hr);
            ps.setString(6, Doctor_id);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("  doctorModel /// isUpdateDoctorSuccessful\n\n");
            e.printStackTrace();

            return false;
        }
        return false;
    }


    protected String[] setDoctorInformation(String[] info, String Doctor_id) {

            String sql = "select name,email,phone,qualification,address,Available_hr from Doctor where Doctor_id=?";
           return new PersonalInformation().setInformationInUpdateWindow(info,Doctor_id,sql);
    }


}
