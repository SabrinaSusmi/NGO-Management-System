package EventDetails.EventHistory.EventSupply.Food;

import SupplyDetails.Food.Food;
import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewAssignedFoodModel {
    protected ObservableList<Food> getTableRecords(String eventID) throws SQLException {
        String sql = "select distinct v.name,ev.amount,max(v.UNIT_PRICE)*ev.amount ,max(v.SUPPLIER)" +
                    " from event_food ev,FOOD v where ev.food_name=v.name and ev.event_id=? " +
                    "group by v.name,ev.amount order by v.name";
        ObservableList<Food> foodList = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, eventID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                int qty = rs.getInt(2);
                int price = rs.getInt(3);
                String supplier = rs.getString(4);

                Food food = new Food(price,qty,name,supplier);

                foodList.add(food);
            }
            return foodList;
        } catch (Exception e) {
            System.out.println("getTableRecords : FoodModel");
            e.printStackTrace();
            throw e;
        }
    }
}
