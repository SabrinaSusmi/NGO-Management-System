package Accounts.Summary.EventSummary;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EventSummaryModel {
    protected ObservableList<Event> getEventSummaryTableRecords(int month, int year) throws SQLException {
        String sql = "select id,name,event_date,actual_budget from event_details where extract(month from event_date)=? and extract(year from event_date)=?";
        ObservableList<Event> eventList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String date = String.valueOf(rs.getDate(3));
                int amount = rs.getInt(4);

                Event event = new Event(id, name, date, amount);
                eventList.add(event);
            }
            return eventList;
        } catch (Exception e) {
            System.out.println("getTableRecords : DonationHistoryModel");
            e.printStackTrace();
            throw e;
        }
    }

    protected int getTotalEventCost(int month, int year) {
        String sql = "select sum(actual_budget) from event_details where (extract(month from event_date)=?) and (extract(year from event_date)=?)";

        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    protected void setTheBarChart(XYChart.Series<String, Double> set1) {
        String sql = "select type,sum(actual_budget) from event_details group by type";
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            getDBEventDetailsValue(set1, ps);
        } catch (Exception e) {
            System.out.println("ExpensesBarchart");
            e.printStackTrace();
        }
    }

    private void getDBEventDetailsValue(XYChart.Series<String, Double> set1, PreparedStatement ps) throws SQLException {
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            if (rs.getInt(1) == 1) {
                set1.getData().add(new XYChart.Data<>("Food Events", rs.getDouble(2)));
            } else if (rs.getInt(1) == 2) {
                set1.getData().add(new XYChart.Data<>("Health Events", rs.getDouble(2)));
            }
        }
    }

    protected void setChartAccordingToInput(XYChart.Series<String, Double> set1, int month, int year) {
        String sql = "select type,sum(actual_budget) from event_details where extract(month from event_date)=? " +
                "and extract(year from event_date)=? group by type";
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            getDBEventDetailsValue(set1, ps);
        } catch (Exception e) {
            System.out.println("ExpensesBarchart");
            e.printStackTrace();
        }
    }


}
