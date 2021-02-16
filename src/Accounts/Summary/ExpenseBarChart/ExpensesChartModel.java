package Accounts.Summary.ExpenseBarChart;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpensesChartModel {
    public void setTheBarChart(XYChart.Series<String, Double> set1) {
        String sql = "select type,sum(amount) from expenses group by type";
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set1.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
        } catch (Exception e) {
            System.out.println("ExpensesBarchart");
            e.printStackTrace();
        }
    }

    public void setChartAccordingToInput(XYChart.Series<String, Double> set1, int month, int year) {
        String sql = "select type,sum(amount) from expenses where extract(month from expense_date)=? and extract(year from expense_date)=? group by type";
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                set1.getData().add(new XYChart.Data<>(rs.getString(1), rs.getDouble(2)));
            }
        } catch (Exception e) {
            System.out.println("ExpensesBarchart");
            e.printStackTrace();
        }
    }

    protected int getTotalExpense(int month, int year) {
        String sql = "select sum(amount) from expenses where (extract(month from expense_date)=?) and (extract(year from expense_date)=?)";

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

    protected ObservableList<Expense> getExpenseSummaryTableRecords(int month, int year) throws SQLException {
        String sql = "select expenses_id,type,title,amount,EXPENSE_DATE from expenses where extract(month from EXPENSE_DATE)=? and extract(year from EXPENSE_DATE)=?";
        ObservableList<Expense> expenseList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String type = rs.getString(2);
                String title = rs.getString(3);
                int amount = rs.getInt(4);
                String date = String.valueOf(rs.getDate("EXPENSE_DATE"));
                Expense expense = new Expense(id, type, title, date, amount);
                expenseList.add(expense);
            }
            return expenseList;
        } catch (Exception e) {
            System.out.println("getTableRecords : DonationHistoryModel");
            e.printStackTrace();
            throw e;
        }
    }

}
