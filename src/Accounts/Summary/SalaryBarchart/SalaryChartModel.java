package Accounts.Summary.SalaryBarchart;

import Utilities.OracleConnection;
import javafx.scene.chart.XYChart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalaryChartModel {
    protected void setTheBarChart(XYChart.Series<String, Double> set1) {
        String sql = "select id,designation_name,amount,num_of_employee(designation_name,id)*amount from designation order by id";
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                set1.getData().add(new XYChart.Data<>(rs.getString(2),rs.getDouble(4)));
            }
        } catch (Exception e) {
            System.out.println("SalaryBarchart");
            e.printStackTrace();
        }
    }

    protected int getTotalSalary(){
        String sql = "select sum(num_of_employee(designation_name,id)*amount) from designation";

        try {
            OracleConnection oc=new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
           ResultSet rs = ps.executeQuery();
            while(rs.next()){

                return rs.getInt(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;

    }

}
