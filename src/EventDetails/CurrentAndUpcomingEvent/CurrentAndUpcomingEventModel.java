package EventDetails.CurrentAndUpcomingEvent;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentAndUpcomingEventModel {
    protected ObservableList<CurrentAndUpcomingEvents> getTableRecords(String sql) throws SQLException {
    //    String sql = "select v.doctor_ID,v.name,v.email,v.QUALIFICATION,v.phone,v.speciality from event_doctor ev,doctor v where ev.doctor_ID=v.doctor_ID and ev.event_id=?";
        ObservableList<CurrentAndUpcomingEvents> eventlist = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String date = String.valueOf(rs.getDate(3));

                CurrentAndUpcomingEvents currentAndUpcomingEvents = new CurrentAndUpcomingEvents(id, name, date);

                eventlist.add(currentAndUpcomingEvents);
            }
            return eventlist;
        } catch (Exception e) {
            System.out.println("getTableRecords : EmployeeModel");
            e.printStackTrace();
            throw e;
        }
    }

}
