
package Person.EmployeeDet;


import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeDetailsModel {


    OracleConnection oc = new OracleConnection();


    public void getDesignation(ObservableList<String> list) {
        String sql = "select designation_name from designation where type='Employee'";
        OracleConnection oc = new OracleConnection();
        try {
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\n setEmployeeInformation -> updateEmployeeModel");
        }
    }

    protected boolean isDeleteEmployeeSuccessful(int emp_id) {

     try {
            String sql = "delete from Employee where emp_id=?";
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, emp_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e + "  EmployeeModel  /// isDeleteEmployeeSuccessful");
            e.printStackTrace();
        }
        return false;
    }

    protected  ObservableList<Employee> getEmployeeTableRecords() throws SQLException {
        String sql = "select emp_id,name,email,nvl(phone,'N/A') phone,get_designation(emp_id) designation from employee order by emp_id";
        ObservableList<Employee> emplist = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String designation = rs.getString("designation");

                Employee employee = new Employee(name, email, phone, designation, id);

                emplist.add(employee);
            }
            return emplist;
        } catch (Exception e) {
            System.out.println("getTableRecords : EmployeeModel");
            e.printStackTrace();
            throw e;
        }
    }
    protected static boolean isMatchSuccessful(String newValue, Employee employee) {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
        String lowerCaseResult = newValue.toLowerCase();
        if (String.valueOf(employee.getId()).contains(lowerCaseResult)) {
            return true;
        } else if ((employee.getName()).contains(lowerCaseResult)) {
            return true;
        } else return (employee.getDesignation()).contains(lowerCaseResult);
    }
}