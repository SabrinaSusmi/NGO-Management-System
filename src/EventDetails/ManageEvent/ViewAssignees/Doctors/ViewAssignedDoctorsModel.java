package EventDetails.ManageEvent.ViewAssignees.Doctors;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAssignedDoctorsModel {
    protected ObservableList<AssignedDoctorOfAnEvent> getTableRecords(String eventID) throws SQLException {
        String sql = "select v.doctor_ID,v.name,v.email,v.QUALIFICATION,v.phone,v.speciality from event_doctor ev,doctor v where ev.doctor_ID=v.doctor_ID and ev.event_id=?";
        ObservableList<AssignedDoctorOfAnEvent> emplist = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, eventID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                String qualification = rs.getString(4);
                String phone = rs.getString(5);
                String speciality = rs.getString(6);

                AssignedDoctorOfAnEvent assignedDoctorOfAnEvent = new AssignedDoctorOfAnEvent(name, email, phone, speciality, qualification, id);

                emplist.add(assignedDoctorOfAnEvent);
            }
            return emplist;
        } catch (Exception e) {
            System.out.println("getTableRecords : EmployeeModel");
            e.printStackTrace();
            throw e;
        }
    }

}
