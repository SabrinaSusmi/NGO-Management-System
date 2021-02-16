package EventDetails.ManageEvent.AssignSupply;

import Utilities.OracleConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetViewSupplyInfo {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservableList<EventSupply> getTableRecords(String id, String sql) throws SQLException {
        this.id=id;
        ObservableList<EventSupply> supplylist = FXCollections.observableArrayList();
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                int availableQty = rs.getInt(2);
                int reqQty = rs.getInt(3);

                EventSupply history = new EventSupply(availableQty, reqQty, name);

                supplylist.add(history);
            }
            return supplylist;
        } catch (Exception e) {
            System.out.println("getTableRecords : ViewSupplyInfo");
            e.printStackTrace();
            throw e;
        }
    }
    public String getRequiredAmount(String sql){
        try {
            System.out.println("p "+id+" lpl  "+getName());

            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2, id);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){

                return String.valueOf(rs.getInt(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void isAmountAddSuccessful(int amt, String eventID, String medName, String sql) {
        try {
            String budget;
            String sql2;
            if(eventID.contains("F")){
                sql2= "update food set total_qty=total_qty-? where name=?";
            }
            else {
                sql2 = "update HEALTH_PRODUCT set total_qty=total_qty-? where name=?";
            }
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, amt);
            ps.setString(2, eventID);
            ps.setString(3, medName);
            int x = ps.executeUpdate();
            if (x > 0) {
                if (isAmountUpdateSuccessful(amt,medName,sql2)) {
                    updateEventActualBudget(amt,eventID,medName);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isAmountUpdateSuccessful(int amt, String medName, String sql) {
        try {
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(1, amt);
            ps.setString(2, medName);
            int x = ps.executeUpdate();
            if (x > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    private void updateEventActualBudget(int amt, String eventID, String medName){
        try {
            String sql="update event_details set actual_budget=nvl(actual_budget,0)+GET_actual_budget(?,?,?) where id=?";
            OracleConnection oc = new OracleConnection();
            PreparedStatement ps = oc.conn.prepareStatement(sql);
            ps.setInt(3, amt);
            ps.setString(2, medName);
            ps.setString(1, eventID);
            ps.setString(4, eventID);

           ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
