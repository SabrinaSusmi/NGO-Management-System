package EventDetails;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EventsModel {
    protected int setTotalCurrentEvent(){
        try {
            OracleConnection oc=new OracleConnection();
            String  sql="select count(id) from event_details where EXTRACT(month FROM sysdate)=EXTRACT(month FROM event_date) and EXTRACT(year FROM sysdate)=EXTRACT(year FROM event_date)";
            PreparedStatement ps=oc.conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    protected int setTotalFutureEvent(){
        try {
            OracleConnection oc=new OracleConnection();
            String  sql="select count(id) from event_details where " +
                            "(EXTRACT(month FROM sysdate)<EXTRACT(month FROM event_date) and " +
                                        "EXTRACT(year FROM sysdate)=EXTRACT(year FROM event_date)) " +
                            "or (EXTRACT(year FROM sysdate)<EXTRACT(year FROM event_date))";
            PreparedStatement ps=oc.conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ObservableList<Events> getEventsTableRecords() throws SQLException {
        String sql = "select id,name,event_date from event_details where (EXTRACT(month FROM sysdate)<=EXTRACT(month FROM event_date) and EXTRACT(year FROM sysdate)=EXTRACT(year FROM event_date)) or (EXTRACT(year FROM sysdate)<EXTRACT(year FROM event_date))";
        ObservableList<Events> eventsList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                java.util.Date date = rs.getDate(3);
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String eventDt = df.format(date);
                Events events = new Events(id,name,eventDt);
                eventsList.add(events);
            }

            return eventsList;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("getTableRecords : EventsModel");
            throw e;
        }
    }

}
