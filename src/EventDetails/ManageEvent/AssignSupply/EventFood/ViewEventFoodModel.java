package EventDetails.ManageEvent.AssignSupply.EventFood;

import EventDetails.ManageEvent.AssignSupply.EventSupply;
import EventDetails.ManageEvent.AssignSupply.GetViewSupplyInfo;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ViewEventFoodModel {
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    GetViewSupplyInfo getViewSupplyInfo = new GetViewSupplyInfo();

    protected ObservableList<EventSupply> getTableRecords(String id) throws SQLException {
        String sql = "select distinct f.name,f.total_qty,ev.amount from event_food ev,food f where ev.food_name=f.name and ev.event_id=?";
        return getViewSupplyInfo.getTableRecords(id,sql);
    }
 /*   protected String getRequiredAmount(){
        String sql = "select ev.amount from event_food ev where ev.food_name=? and ev.event_id=?";
        return getViewSupplyInfo.getRequiredAmount(sql);
    }*/

    protected void isAmountAddSuccessful(int amt, String eventID, String foodName) {
        String sql = "update event_food set amount=nvl(amount,0)+? where event_id=? and food_name=?";
        getViewSupplyInfo.isAmountAddSuccessful(amt,eventID,foodName,sql);
    }
}
