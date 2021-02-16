package Login.ChangePassword;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChangePasswordModel {
    protected boolean isPasswordChangeSuccessful(String email, String prevPassword, String newPassword) {
        try {
            String sql = "update employee set password=? where email=? and password=?";
            OracleConnection oracleConnection = new OracleConnection();
            PreparedStatement ps = oracleConnection.conn.prepareStatement(sql);
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.setString(3, prevPassword);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {

                return true;
            }
        } catch (Exception e) {
            System.out.println(e + " isLoginSuccessful");
        }
        return false;
    }

}
