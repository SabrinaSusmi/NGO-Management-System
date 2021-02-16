package Accounts.Designation;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DesignationModel {
    protected ObservableList<Designation> getDesignationTableRecords() throws SQLException {
        String sql = "select id, designation_name, type, amount,num_of_employee(designation_name,id) from designation order by id";
        ObservableList<Designation> designations = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String type = rs.getString(3);
                int salary = rs.getInt(4);
                int employeeCount=rs.getInt(5);

                Designation designation = new Designation(salary,name,type,id,employeeCount);

                designations.add(designation);
            }
            return designations;
        } catch (Exception e) {
            System.out.println("getTableRecords : desigModel");
            e.printStackTrace();
            throw e;
        }
    }
    protected boolean isDesignationAddedSuccessful(String type, String name, int salary){
        try {
            String sql = "insert into designation (designation_name,type,amount) values(?,?,?)";

            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);

            ps.setString(1,name);
            ps.setString(2,type);
            ps.setInt(3,salary);

            int x= ps.executeUpdate();
            if(x>0){
                return true;
            }
        } catch (Exception e) {
            System.out.println("isDesignationAddedSuccessful\n\n");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    protected boolean isDesignationUpdateSuccessful(String type, String name, int salary,String id){
        try {
            String sql = "update designation set designation_name=?,type=?,amount=? where id=?";

            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);

            ps.setString(1,name);
            ps.setString(2,type);
            ps.setInt(3,salary);
            ps.setString(4,id);

            int x= ps.executeUpdate();
            if(x>0){
                return true;
            }
        } catch (Exception e) {
            System.out.println("isDesignationAddedSuccessful\n\n");
            e.printStackTrace();
            return false;
        }
        return false;
    }

    protected String[] showDesignationDetails(String[] info, String desig_id) {
        try {
            List<String> list = new ArrayList<>();
            String sql = "select * from designation where id=?";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, desig_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(String.valueOf(rs.getInt(4)));

            }
            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
