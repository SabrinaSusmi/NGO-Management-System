package Person.EmployeeDet.ViewEmployeeInformation;

import Utilities.OracleConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ViewEmployeeInformationModel {
    protected String[] showEmployeeDetails(String[] info, int emp_id) {
        try {
            List<String> list = new ArrayList<>();
            String sql = "select e.emp_id,e.name,e.email,e.dob,e.gender,e.address,e.phone,d.designation_name,show_employee_Salary(?) from employee e,designation d where d.id=e.designation and emp_id=?";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, emp_id);
            ps.setInt(2, emp_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(String.valueOf(rs.getInt(1)));
                list.add(rs.getString(2));
                list.add(rs.getString(3));
                list.add(String.valueOf(rs.getDate(4)));
                list.add(rs.getString(5));
                list.add(rs.getString(6));
                list.add(rs.getString(7));
                list.add(rs.getString(8));
                list.add(String.valueOf(rs.getInt(9)));

            }
            info = list.toArray(info);
            return info;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
