package SupplyDetails.Food;


import SupplyDetails.SupplyInformation;
import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FoodModel {

    protected  ObservableList<Food> getFoodTableRecords() throws SQLException {
        String sql = "select distinct name,sum(qty) qty,sum(unit_price*qty) price from food  group by name order by name";
        ObservableList<Food> foodList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
              int qty=rs.getInt(2);
              int price=rs.getInt(3);

                Food food = new Food(name, qty,price);

                foodList.add(food);
            }
            return foodList;
        } catch (Exception e) {
            System.out.println("getTableRecords : foodModel");
            e.printStackTrace();
            throw e;
        }
    }


    protected boolean isDeleteSelectedFoodSuccessful(String name) {
        String sql = "delete from food where name=?";
        return new SupplyInformation().isDeleteSelectedItemSuccessful(name,sql);

    }
    protected  boolean isMatchSuccessful(String newValue, Food food) {
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }
     //   String result = newValue.;
        return String.valueOf(food.getName()).contains(newValue);
    }
}
