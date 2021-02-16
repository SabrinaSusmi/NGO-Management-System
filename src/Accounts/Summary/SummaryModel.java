package Accounts.Summary;

import Utilities.OracleConnection;
import javafx.scene.chart.XYChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SummaryModel {

    protected int getTotalExpense(int month, int year){
        String sql = "select sum(amount) from expenses where (extract(month from expense_date)=?) and (extract(year from expense_date)=?)";

        try {
            Integer rs = getResultFromDB(month, year, sql);
            if (rs != null) return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    protected int getTotalDonation(int month, int year){
        String sql = "select sum(amount) from donation_history where (extract(month from don_date)=?) and (extract(year from don_date)=?)";

        try {
            Integer rs = getResultFromDB(month, year, sql);
            if (rs != null) return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    protected int getTotalSupplyCost(int month, int year){
       return getTotalFoodSupplyCost(month,year)+getTotalHealthSupplyCost(month, year);
    }

    protected int getTotalHealthSupplyCost(int month, int year){
        String sql1 = "select sum(qty*unit_price) from HEALTH_PRODUCT where (extract(month from purchase_date)=?) and (extract(year from purchase_date)=?)";

        try {
            Integer rs1 = getResultFromDB(month, year, sql1);
            if (rs1 != null) return rs1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    protected int getTotalFoodSupplyCost(int month, int year){
        String sql1 = "select sum(qty*unit_price) from food where (extract(month from purchase_date)=?) and (extract(year from purchase_date)=?)";

        try {
            Integer rs1 = getResultFromDB(month, year, sql1);
            if (rs1 != null) return rs1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    protected int getTotalEventCost(int month, int year){
      return getTotalFoodEventCost(month, year)+getTotalHealthEventCost(month,year);

    }
    protected int getTotalFoodEventCost(int month, int year){
        String sql ="select sum(ef.amount*f.unit_price) from event_details ed,event_food ef,food f  " +
                    "where (extract(month from ed.event_date)=?) and (extract(year from ed.event_date)=?) " +
                                "and ed.id=ef.event_id and ef.food_name=f.name";

        try {
            Integer rs = getResultFromDB(month, year, sql);
            if (rs != null) return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }
    protected int getTotalHealthEventCost(int month, int year){
        String sql ="select sum(ef.amount*f.unit_price) from event_details ed,event_health ef,health_product f  " +
                "where (extract(month from ed.event_date)=?) and (extract(year from ed.event_date)=?) " +
                "and ed.id=ef.event_id and ef.health_name=f.name";

        try {
            Integer rs = getResultFromDB(month, year, sql);
            if (rs != null) return rs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

    private Integer getResultFromDB(int month, int year, String sql) throws SQLException {
        OracleConnection oc = new OracleConnection();
        PreparedStatement ps = oc.conn.prepareStatement(sql);
        ps.setInt(1, month);
        ps.setInt(2, year);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getInt(1);
        }
        return null;
    }

    protected int getNetTotal(int month, int year){
        return getTotalDonation(month, year)-getTotalExpense(month, year)-getTotalSupplyCost(month, year);
    }
}
