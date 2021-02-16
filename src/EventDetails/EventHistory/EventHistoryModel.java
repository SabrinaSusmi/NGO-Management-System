package EventDetails.EventHistory;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EventHistoryModel {
    protected ObservableList<History> getTableRecords() throws SQLException {
        String sql = "select id,name,event_date,actual_budget,est_budget from event_details ";
        ObservableList<History> histories = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            getSelectedInfo(histories, ps);
            return histories;
        } catch (Exception e) {
            System.out.println("getTableRecords : EmployeeModel");
            e.printStackTrace();
            throw e;
        }
    }

    protected ObservableList<History> getPastTableRecords( int month) throws SQLException {
        String sql = "select id,name,event_date,actual_budget,est_budget from event_details where event_date <sysdate and event_date> add_months(sysdate,-?)  order by event_date";
        ObservableList<History> histories = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1,month);
            getSelectedInfo(histories, ps);


            return histories;
        } catch (Exception e) {
            System.out.println("getTableRecords : foodModel");
            e.printStackTrace();
            throw e;
        }
    }


    private void getSelectedInfo(ObservableList<History> emplist, PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String id = rs.getString(1);
            String name = rs.getString(2);
            String event_date = String.valueOf(rs.getDate(3));
            int actual_budget = rs.getInt(4);
            int est_budget = rs.getInt(5);


            History history = new History(name,id,event_date,actual_budget,est_budget );

            emplist.add(history);
        }
    }

}
