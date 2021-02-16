package Person.DoctorDetails.AddDoctor;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AddDoctorModel {
    private String des;
    OracleConnection oc = new OracleConnection();

    protected boolean isAddDoctorSuccessful(String name, Date dobDate, String gender, String address, String phone, String speciality,
                                            String email, String qualification, String hr) {

        try {
            java.sql.Date sqlDate = new java.sql.Date(dobDate.getTime());
            setSpeciality(speciality);
            System.out.println(getSpeciality());
            String sql = "insert into doctor(name,email,qualification,gender,phone,dob,speciality,address,Available_hr) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, qualification);
            ps.setString(4, gender);
            ps.setString(5, phone);
            ps.setDate(6, sqlDate);
            ps.setString(7, getSpeciality());
            ps.setString(8, address);
            ps.setString(9, hr);
            int x = ps.executeUpdate();
            if (x > 0) {
                setPassword();
                return true;
            }
        } catch (Exception e) {
            System.out.println("isAddDoctorSuccessful\n\n");
            e.printStackTrace();
            return false;
        }


        return false;
    }

    private String getMaxID() {
        try {
            String sql1 = "select max(doctor_id) from doctor";

            PreparedStatement ps1 = oc.conn.prepareStatement(sql1);

            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                return rs1.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void setPassword() {
        try {
            String sql = "update doctor set password=? where doctor_id=?";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, getMaxID());
            ps.setString(2, getMaxID());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setSpeciality(String speciality) {
        try {
            OracleConnection oc = new OracleConnection();
            String sql = "select id from designation where designation_name=?";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, speciality);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                des = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSpeciality() {
        return des;
    }
}
