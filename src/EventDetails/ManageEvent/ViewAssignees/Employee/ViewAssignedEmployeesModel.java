package EventDetails.ManageEvent.ViewAssignees.Employee;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAssignedEmployeesModel {
    protected ObservableList<AssignedEmployeeOfAnEvent> getTableRecords(String eventID) throws SQLException {
        String sql = "select v.emp_ID,v.name,v.email,v.address,v.phone,v.designation from event_employee ev,employee v where ev.employee_id=v.emp_id and ev.event_id=?";
        ObservableList<AssignedEmployeeOfAnEvent> emplist = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, eventID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String phone = rs.getString(5);
                String designation = rs.getString(6);

                AssignedEmployeeOfAnEvent assignedVolunteerOfAnEvent = new AssignedEmployeeOfAnEvent(name, email, phone, designation, address, id);

                emplist.add(assignedVolunteerOfAnEvent);
            }
            return emplist;
        } catch (Exception e) {
            System.out.println("getTableRecords : EmployeeModel");
            e.printStackTrace();
            throw e;
        }
    }


}
