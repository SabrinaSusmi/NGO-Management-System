package Person;

import Login.LoginModel;
import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonalInformation {

    public List<String> getIDList(String sql) {
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
            System.out.println("getIDList");
        }

        return list;
    }

    public boolean checkDesignation() {
        return new LoginModel().getDesignation().contains("Admin");
    }

    public String[] setInformationInUpdateWindow(String[] info, String Doctor_id, String sql) {
        List<String> list = new ArrayList<>();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, Doctor_id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    list.add(rs.getString(i));
                }
            }
            info = list.toArray(info);
            return info;
        } catch (SQLException throwables) {
            System.out.println("setInformation-> personalInformation");
            throwables.printStackTrace();
        }
        return null;
    }




    public boolean isDeleteSuccessful(String id, String sql) {
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e + " isDeleteSuccessful");
            e.printStackTrace();
            return false;
        }
        return false;
    }








}
