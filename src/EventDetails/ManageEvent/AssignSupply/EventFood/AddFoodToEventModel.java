package EventDetails.ManageEvent.AssignSupply.EventFood;

import EventDetails.ManageEvent.GetInformationForEvent;

import java.util.ArrayList;

public class AddFoodToEventModel {
    GetInformationForEvent ev = new GetInformationForEvent();

    protected String[] getSearchedList(String[] info, String text) {
        String sql = "select distinct name from FOOD where name like ? or name like ?";

        return ev.getSearchedListForEvent(info, text, sql);
    }


    protected int getTotalID() {
        String sql = "select count(distinct name) from FOOD ";
        return ev.getTotalNumberOfIDs(sql);
    }


    protected String[] getFoodList(String[] info) {
        String sql = "select distinct name from FOOD ";
        return ev.getList(info, sql);
    }


    public boolean isAssignFoodSuccessful(ArrayList<String> id, String eventID) {
        String sql = "insert into event_food(event_id,food_name) values(?,?)";
        return ev.isAssignSuccessful(id, eventID, sql);
    }
}
