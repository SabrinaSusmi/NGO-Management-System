package EventDetails;

import EventDetails.CurrentAndUpcomingEvent.CurrentAndUpcomingEventController;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class EventsController {
    @FXML
    private Pane eventDetailsPane;
    @FXML
    private JFXTextField currentEvent;

    @FXML
    private JFXTextField upcomingEvent;

    @FXML
    void handleViewHistory() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EventHistory/EventHistory.fxml"));
        eventDetailsPane.getChildren().setAll(pane);
    }


    @FXML
    private void handleCreateEvent() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("CreateEvent/CreateEvent.fxml"));
        eventDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void handleManageEvent() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ManageEvent/ManageEvent.fxml"));
        eventDetailsPane.getChildren().setAll(pane);
    }

    EventsModel eventsModel = new EventsModel();

    public void initialize() {
        currentEvent.setText(String.valueOf(eventsModel.setTotalCurrentEvent()));
        upcomingEvent.setText(String.valueOf(eventsModel.setTotalFutureEvent()));
    }



    public void handleUpcomingEvent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentAndUpcomingEvent/CurrentAndUpcomingEvent.fxml"));
        AnchorPane pane = loader.load();
        CurrentAndUpcomingEventController view = loader.getController();
        view.populateTableView("future");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Upcoming Event Details");
        stage.setScene(new Scene(pane));
        stage.show();
    }



    public void handleCurrentEvent() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CurrentAndUpcomingEvent/CurrentAndUpcomingEvent.fxml"));
        AnchorPane pane = loader.load();
        CurrentAndUpcomingEventController view = loader.getController();
        view.populateTableView("current");
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Current Event Details");
        stage.setScene(new Scene(pane));
        stage.show();
    }
}
