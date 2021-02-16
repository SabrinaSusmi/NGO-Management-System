package EventDetails.ManageEvent.ViewAssignees.Volunteers;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAssignedVolunteersModel {
    protected ObservableList<AssignedVolunteerOfAnEvent> getTableRecords(String eventID) throws SQLException {
        String sql = "select v.volunteer_ID,v.name,v.email,v.address,v.phone,v.occupation from event_volunteer ev,volunteer v where ev.volunteer_id=v.volunteer_id and ev.event_id=?";
        ObservableList<AssignedVolunteerOfAnEvent> emplist = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, eventID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String address = rs.getString(4);
                String phone = rs.getString(5);
                String designation = rs.getString(6);

                AssignedVolunteerOfAnEvent assignedVolunteerOfAnEvent = new AssignedVolunteerOfAnEvent(name, email, phone, designation, address, id);

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
