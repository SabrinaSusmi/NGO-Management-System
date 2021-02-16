package Person;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validation {
    public boolean checkEmailAndPhoneValidation(String email, String phone) {
        String ans = null;
        try {
            String sql = "select check_validation(?,?) from dual";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, phone);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ans = rs.getString(1);
            }
            return ans.equalsIgnoreCase("TRUE");
        } catch (Exception e) {
            System.out.println("email\n");
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkPasswordValidation(String password) {
        String ans = null;
        try {
            String sql = "select check_password(?) from dual";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ans = rs.getString(1);
            }
            return ans.equalsIgnoreCase("TRUE");
        } catch (Exception e) {
            System.out.println("email\n");
            e.printStackTrace();
        }
        return false;
    }
}
