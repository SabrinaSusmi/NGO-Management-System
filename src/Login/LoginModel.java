package Login;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginModel {
    OracleConnection oracleConnection = new OracleConnection();
    private static String designation;


    protected boolean isLoginSuccessful(String username, String password) {
        try {
            String sql = "select e.name,e.password,d.designation_name from employee e,designation d where name=? and password=? and e.designation=d.id";
            PreparedStatement ps = oracleConnection.conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                designation = resultSet.getString(3);
                return true;
            }
        } catch (Exception e) {
            System.out.println(e + " isLoginSuccessful");
            e.printStackTrace();
        }
        return false;

    }

    public static String getDesignation() {
        return designation;
    }

}
