package SupplyDetails.Food.ViewFoodDetails;

import SupplyDetails.Food.Food;
import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViewFoodInformationModel {

    protected ObservableList<Food> getFoodTableRecords(String item,int month) throws SQLException {
        String sql = "select * from food where name=? and purchase_date > add_months(sysdate,-?) order by purchase_date";
        ObservableList<Food> foodList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, item);
            ps.setInt(2,month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name=rs.getString(2);
                Date pdate=rs.getDate(3);
                Date edate=rs.getDate(4);
                int qty=rs.getInt(5);
                String sup=rs.getString(6);
                int price=rs.getInt(7);

                Food food = new Food(price,qty,id,pdate,edate,name,sup);

                foodList.add(food);
            }
            return foodList;
        } catch (Exception e) {
            System.out.println("getTableRecords : foodModel");
            e.printStackTrace();
            throw e;
        }
    }


}
