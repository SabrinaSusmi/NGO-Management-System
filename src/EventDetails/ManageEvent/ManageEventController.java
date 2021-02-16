package EventDetails.ManageEvent;

import EventDetails.ManageEvent.ViewAssignees.Doctors.ViewAssignedDoctorsController;
import EventDetails.ManageEvent.ViewAssignees.Employee.ViewAssignedEmployeesController;
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

public class ManageEventController {
    @FXML
    private Pane createEventPane;

    @FXML
    private JFXTextField eventID;

    @FXML
    private JFXTextField estimatedBudget;
    @FXML
    private JFXTextField eventName;


    @FXML
    private JFXDatePicker eventDate;


    @FXML
    private FontAwesomeIconView docIcon;

    @FXML
    private FontAwesomeIconView docList;

    @FXML
    private Label eventDoctorName;

    @FXML
    private FontAwesomeIconView addDoctor;

    @FXML
    void handleAddEmp() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Employee/addEmployees.fxml"));
        showList(loader, "Assign Employees");
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
    void handleBackBtn() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Events.fxml"));
        createEventPane.getChildren().setAll(pane);

    }

    @FXML
    void handleConfirmBtn() throws ParseException {
        Date dateOfTheEvent = new SimpleDateFormat("yyyy-dd-MM").parse(eventDate.getEditor().getText());
        if (new ManageEventModel().saveEventInformationFromAdminPart(eventID.getText().trim(),dateOfTheEvent, Integer.parseInt(estimatedBudget.getText())))
            {
                new ShowAlertDialogue().infoBox("Update Successful!", null, "Manage Event");
                refresehFields();
            }
         else {
            new ShowAlertDialogue().infoBox("Update Unsuccessful", null, "Manage Event");
        }
    }

    private void refresehFields() {
        eventID.setText("");
        estimatedBudget.setText("");
        eventDate.getEditor().setText("");
        eventName.setText("");
    }

    @FXML
    void handleViewAssignedDoctor() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Doctors/ViewAssignedDoctors.fxml"));
        AnchorPane pane = loader.load();
        ViewAssignedDoctorsController view = loader.getController();
        view.populateTableView(eventID.getText());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Assigned Doctors");
        stage.setScene(new Scene(pane));
        stage.show();

    }

    @FXML
    void handleViewAssignedEmployees() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Employee/viewAssignedEmployees.fxml"));
        AnchorPane pane = loader.load();
        ViewAssignedEmployeesController view = loader.getController();
        view.populateTableView(eventID.getText());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Assigned Employees");
        stage.setScene(new Scene(pane));
        stage.show();
       /* FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Employee/viewAssignedEmployees.fxml"));
        showList(loader, "Assigned Employees");*/
    }

    @FXML
    void handleViewAssignedVolunteers() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Volunteers/viewAssignedVolunteers.fxml"));
        AnchorPane pane = loader.load();
        ViewAssignedVolunteersController view = loader.getController();
        view.populateTableView(eventID.getText());
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Assigned Volunteers");
        stage.setScene(new Scene(pane));
        stage.show();
/*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Volunteers/viewAssignedVolunteers.fxml"));
        showList(loader, "Assigned Volunteers");
*/
    }

    private void showList(FXMLLoader loader, String s) throws IOException {
        AnchorPane pane = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(s);
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public void initialize() {
        TextFields.bindAutoCompletion(eventID, new ManageEventModel().getItemNameList("select id from event_details"));
    }

    private void setVisibility() {
        eventDoctorName.setVisible(true);
        docIcon.setVisible(true);
        docList.setVisible(true);
        addDoctor.setVisible(true);

    }




    private static String event_id;


    public void handleEventID() {
        setEventID(eventID.getText().trim());
        eventDate.getEditor().setText(new ManageEventModel().getEventDate(eventID.getText()));
        eventName.setText(new ManageEventModel().getEventName(eventID.getText()));

        makeDoctorVisible();
    }

    private void setEventID(String id) {
        event_id = id;
    }

    public static String getEventID() {
        return event_id;
    }

    private void makeDoctorVisible() {
        if (event_id.contains("H")) {
            setVisibility();
        }
    }

    public void handleAddDoctor() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewAssignees/Doctors/AddDoctorToEvent.fxml"));
        showList(loader, "Assign Volunteers");
    }
}
