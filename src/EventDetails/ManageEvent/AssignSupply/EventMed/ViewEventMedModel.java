package EventDetails.ManageEvent.AssignSupply.EventMed;

import EventDetails.ManageEvent.AssignSupply.EventSupply;
import EventDetails.ManageEvent.AssignSupply.GetViewSupplyInfo;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class ViewEventMedModel {
    private String id;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    GetViewSupplyInfo getViewSupplyInfo = new GetViewSupplyInfo();

    protected ObservableList<EventSupply> getTableRecords(String id) throws SQLException {
        String sql = "select distinct h.name,h.total_qty,ev.amount from event_health ev,HEALTH_PRODUCT h where ev.health_name=h.name and ev.event_id=?";
        return getViewSupplyInfo.getTableRecords(id,sql);
    }

    protected String getRequiredAmount(){
        String sql = "select ev.amount from event_health ev where ev.health_name=? and ev.event_id=?";
        return getViewSupplyInfo.getRequiredAmount(sql);
    }

    protected void isAmountAddSuccessful(int amt, String eventID, String medName) {
        String sql = "update event_health set amount=nvl(amount,0)+? where event_id=? and health_name=?";
        getViewSupplyInfo.isAmountAddSuccessful(amt,eventID,medName,sql);
    }

}
