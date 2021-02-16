package EventDetails.ManageEvent;

import EventDetails.ManageEvent.AssignSupply.EventFood.ViewEventFoodController;
import EventDetails.ManageEvent.AssignSupply.EventMed.ViewEventMedController;
import EventDetails.ManageEvent.ViewAssignees.Volunteers.AddVolunteersToEventController;
import EventDetails.ManageEvent.ViewAssignees.Volunteers.ViewAssignedVolunteersController;
import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageEventEmployeesController {
    @FXML
    private Pane createEventPane;

    @FXML
    private JFXTextField eventName;


    @FXML
    private FontAwesomeIconView supplyList;

    @FXML
    private Label eventSupplyName;

    @FXML
    private FontAwesomeIconView addSupply;

    @FXML
    private JFXDatePicker eventDate;

    @FXML
    private JFXTextField eventName1;

    private void showList(FXMLLoader loader, String s) throws IOException {
        AnchorPane pane = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(s);
        stage.setScene(new Scene(pane));
        stage.show();
    }

    @FXML
    void handleAddVol() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Volunteers/addVolunteers.fxml"));
        AnchorPane pane = loader.load();
        AddVolunteersToEventController view = loader.getController();
        view.event_id=getEventID();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Assign Volunteers");
        stage.setScene(new Scene(pane));
        stage.show();
    }

    @FXML
    void handleViewAssignedVolunteers() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Volunteers/viewAssignedVolunteers.fxml"));
        AnchorPane pane = loader.load();
        ViewAssignedVolunteersController view = loader.getController();
        view.populateTableView(eventName.getText());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("View Assigned Food");
        stage.setScene(new Scene(pane));
        stage.show();

    }

    private static String eventID;


    public void handleEventID() {
        setEventID(eventName.getText().trim());
        eventDate.getEditor().setText(new ManageEventModel().getEventDate(eventName.getText()));
        eventName1.setText(new ManageEventModel().getEventName(eventName.getText()));

    }

    private void setEventID(String id) {
        eventID = id;
    }

    public static String getEventID() {
        return eventID;
    }

    @FXML
    void handleBackBtn() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../EventsEmployee.fxml"));
        createEventPane.getChildren().setAll(pane);
    }

    @FXML
    void handleConfirmBtn() throws ParseException {
        Date dateOfTheEvent = new SimpleDateFormat("yyyy-dd-MM").parse(eventDate.getEditor().getText());

        if (new ManageEventModel().saveEventInformation(eventName.getText().trim(),dateOfTheEvent))
        {
            new ShowAlertDialogue().infoBox("Update Successful!", null, "Manage Event");
            refreshFields();
        }
        else {
            new ShowAlertDialogue().infoBox("Update Unsuccessful", null, "Manage Event");
        }
    }

    private void refreshFields() {
        eventName.setText("");
        eventName1.setText("");
        eventDate.getEditor().setText("");
    }

    public void initialize() {
        eventName.requestFocus();
        TextFields.bindAutoCompletion(eventName, new ManageEventModel().getItemNameList("select id from event_details"));
    }

    @FXML
    void handleViewAssignedSupply() throws IOException {
        if(eventName.getText().contains("F")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignSupply/EventFood/ViewEventFood.fxml"));

            AnchorPane pane = loader.load();
            ViewEventFoodController view = loader.getController();
            view.populateTableView(eventName.getText());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("View Assigned Food");
            stage.setScene(new Scene(pane));
            stage.show();
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignSupply/EventMed/ViewEventMed.fxml"));
            AnchorPane pane = loader.load();
            ViewEventMedController view = loader.getController();
            view.populateTableView(eventName.getText());
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("View Assigned Medicine");
            stage.setScene(new Scene(pane));
            stage.show();

        }
    }

    @FXML
    void handleAddSupply() throws IOException {
        if(eventName.getText().contains("F")){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignSupply/EventFood/AddFoodToEvent.fxml"));
            showList(loader, "Assign Food");
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AssignSupply/EventMed/AddMedToEvent.fxml"));
            showList(loader, "Assign Med");
        }
    }



    public void setEventName(String id) {
        eventName.setText(id);
    }
}
