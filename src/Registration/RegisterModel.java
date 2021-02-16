package Registration;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class RegisterModel {
    OracleConnection oc = new OracleConnection();
    private int empID;

    public boolean isRegistrationSuccessful(String name, Date dobDate, String gender, String address, String phone,
                                            String email, String password) {

        try {
            java.sql.Date sqlDate = new java.sql.Date(dobDate.getTime());

            String sql = "insert into employee(name,email,password,gender,phone,dob,designation,address) values(?,?,?,?,?,?,?,?)";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, phone);
            ps.setDate(6, sqlDate);
            if (checkEmployeeNumber() == 0) {
                ps.setString(7, getDesignation());
            } else {
                ps.setString(7, null);

            }
            ps.setString(8, address);

            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("isAddEmployeeSuccessful\n\n");
            e.printStackTrace();
            return false;
        }


        return false;
    }

    public int checkEmployeeNumber() {
        try {
            String sql = "select count(emp_id) from employee";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void setDesignation() {
        try {
            String sql = "insert into designation(designation_name,type) values (?,?)";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, "Admin");
            ps.setString(2, "Employee");
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDesignation() {
        if (checkEmployeeNumber() == 0) {
            setDesignation();
            try {
                String sql = "select id from  designation where designation_name=?";
                PreparedStatement ps = oc.conn.prepareStatement(sql);
                ps.setString(1, "Admin");
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString(1);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
