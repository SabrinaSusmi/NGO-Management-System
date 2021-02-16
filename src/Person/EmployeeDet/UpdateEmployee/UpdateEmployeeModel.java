package Person.EmployeeDet.UpdateEmployee;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UpdateEmployeeModel {
    private String des;

    public boolean isUpdateEmployeeSuccessful(Date dob, String gender, String address, String phone, String designation, String email, int emp_id) {
        OracleConnection oc = new OracleConnection();
        try {
            java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
            setDesignation(designation);
            String sql = "update Employee set dob=?,gender=?,address=?,phone=?,designation=?,email=? where emp_id=? ";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setDate(1, sqlDate);
            ps.setString(2, gender);
            ps.setString(3, address);
            ps.setString(4, phone);
            ps.setString(5, getDesignation());
            ps.setString(6, email);
            ps.setInt(7, emp_id);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("  employeeModel /// isUpdateEmployeeSuccessful\n\n");
            e.printStackTrace();

            return false;
        }
        return false;
    }

    private void setDesignation(String designation) {
        try {
            OracleConnection oc = new OracleConnection();
            String sql = "select id from designation where designation_name=?";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, designation);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                des = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getDesignation() {
        return des;
    }

    public String[] setEmployeeInformation(String info[], int emp_id) {

        List<String> list = new ArrayList<>();
        try {
            String sql = "select * from employee where emp_id=?";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, emp_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(2));
                list.add(rs.getString(4));
                list.add(String.valueOf(rs.getDate(5)));
                list.add(rs.getString(6));
                list.add(rs.getString(7));
                list.add(rs.getString(8));
            }
            info = list.toArray(info);
            return info;
        } catch (SQLException throwables) {
            System.out.println("setEmployeeInformation-> updateEmployeeModel");
            throwables.printStackTrace();
        }
        return null;
    }


}
