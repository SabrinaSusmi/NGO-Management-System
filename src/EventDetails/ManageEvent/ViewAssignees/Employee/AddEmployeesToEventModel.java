package EventDetails.ManageEvent.ViewAssignees.Employee;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddEmployeesToEventModel {
    protected String[] getSearchedList(String[] info,String text){
        List<String> list = new ArrayList<>();

        try {

            String sql = "select emp_id from EMPLOYEE where emp_id like ? or name like ?";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, "%" + text + "%");
            ps.setString(2, "%" + text + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected int getTotalID() {
        try {
            String sql = "select count(emp_id) from EMPLOYEE ";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    protected String[] getEmployeeList(String[] info) {
        List<String> list = new ArrayList<>();

        try {

            String sql = "select emp_id from EMPLOYEE ";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isAssignEmployeeSuccessful(ArrayList<String> id, String eventID){
        int arraySize = id.size();
        //System.out.println(arraySize);
        try {
            String sql = "insert into event_employee values(?,?)";
            OracleConnection oc = new OracleConnection();
            for(int i=0; i<arraySize;i++){
                PreparedStatement ps = oc.conn.prepareStatement(sql);
                ps.setString(1, eventID);
                ps.setString(2,id.get(i));
                int x = ps.executeUpdate();
                if(x<1)
                    return false;
            }

        } catch (SQLException e) {
            System.out.println("isAssignEmployeeSuccessful\n\n");
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
