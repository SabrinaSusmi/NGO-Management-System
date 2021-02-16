package EventDetails.CurrentAndUpcomingEvent;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class CurrentAndUpcomingEventController {
    @FXML
    private TableView<CurrentAndUpcomingEvents> informationTable;

    @FXML
    private TableColumn<CurrentAndUpcomingEvents,String> colId;

    @FXML
    private TableColumn<CurrentAndUpcomingEvents,String> colName;

    @FXML
    private TableColumn<CurrentAndUpcomingEvents,String> colDate;


    public void populateTableView(String type) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            if (type.equalsIgnoreCase("future")) {
                informationTable.setItems(new CurrentAndUpcomingEventModel().getTableRecords
                        ("select id,name,event_date from event_details where (" +
                                "EXTRACT(month FROM sysdate)<EXTRACT(month FROM event_date) and " +
                                        "EXTRACT(year FROM sysdate)=EXTRACT(year FROM event_date)) or " +
                                "(EXTRACT(year FROM sysdate)<EXTRACT(year FROM event_date)) order by event_date"));
            }
            else if(type.equalsIgnoreCase("current")){
                informationTable.setItems(new CurrentAndUpcomingEventModel().getTableRecords("select id,name,event_date from event_details where " +
                        "(EXTRACT(month FROM sysdate)=EXTRACT(month FROM event_date) and EXTRACT(year FROM sysdate)=EXTRACT(year FROM event_date))" +
                        "order by event_date "));
            }
        }catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }
}
