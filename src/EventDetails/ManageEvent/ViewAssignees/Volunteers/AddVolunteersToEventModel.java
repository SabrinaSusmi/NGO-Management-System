package EventDetails.ManageEvent.ViewAssignees.Volunteers;

import EventDetails.ManageEvent.GetInformationForEvent;

import java.util.ArrayList;

public class AddVolunteersToEventModel {

    GetInformationForEvent ev = new GetInformationForEvent();

    protected String[] getSearchedList(String[] info,String text){
        String sql = "select volunteer_ID from volunteer where volunteer_id like ? or name like ?";
        return ev.getSearchedListForEvent(info, text, sql);


    }

    protected int getTotalID() {
        String sql = "select count(volunteer_id) from volunteer ";
        return ev.getTotalNumberOfIDs(sql);

    }

    protected String[] getVolunteerList(String[] info) {
        String sql = "select volunteer_ID from volunteer ";

        return ev.getList(info, sql);

    }

    public boolean isAssignVolunteerSuccessful(ArrayList<String> id, String eventID){
        String sql = "insert into event_volunteer values(?,?)";
        return ev.isAssignSuccessful(id, eventID, sql);

    }
}