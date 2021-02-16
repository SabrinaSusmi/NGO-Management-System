package Accounts;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountsModel {
    protected int getTotalEmployees(){
        String sql = "select count(emp_id) from Employee";

        try {
            OracleConnection oc=new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    protected int getTotalDoctors(){
        String sql = "select count(doctor_id) from doctor";

        try {
            OracleConnection oc=new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
