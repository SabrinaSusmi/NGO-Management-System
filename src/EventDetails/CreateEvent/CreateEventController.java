package EventDetails.CreateEvent;

import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEventController  {
    @FXML
    private Pane createEventPane;


    @FXML
    private JFXTextField eventName;

    @FXML
    private JFXTextField eventVolunteerName;

    @FXML
    private JFXTextField eventEmployeeName;

    @FXML
    private JFXTextField estimatedBudget;

    @FXML
    private JFXDatePicker eventDate;

    @FXML
    private FontAwesomeIconView docList;

    @FXML
    private JFXComboBox eventType;

    @FXML
    private JFXTextField eventDoctorName;
    @FXML
    private FontAwesomeIconView docIcon;

    CreateEventModel createEventModel = new CreateEventModel();

    private final ObservableList<String> eventTypeValue = FXCollections.observableArrayList("Food", "Health");

    @FXML
    void handleConfirmBtn(ActionEvent event) throws ParseException {
        Date event_date = new SimpleDateFormat("MM/dd/yyyy").parse(eventDate.getEditor().getText());
        if(createEventModel.isScheduleEventSuccessful(eventName.getText(),event_date,eventType.getEditor().getText(),Integer.parseInt(estimatedBudget.getText()))){
            new ShowAlertDialogue().infoBox("Event Successfully Scheduled!",null,"Schedule Event");
            refreshTextField();
        }
        else {
            new ShowAlertDialogue().infoBox("Event Scheduling Failed!",null,"Schedule Event");
        }
    }

    private void refreshTextField() {
        eventType.getEditor().setText("");
        eventDate.getEditor().setText("");
        eventName.setText("");
        estimatedBudget.setText("");
    }

    @FXML
    private void handleBackBtn() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Events.fxml"));
        createEventPane.getChildren().setAll(pane);
    }

    public void initialize(){
        eventType.setItems(eventTypeValue);
//        setVisibility();
    }

    private void setVisibility() {

         eventDoctorName.setVisible(true);
         docIcon.setVisible(true);
         docList.setVisible(true);

    }

    private void showList(FXMLLoader loader, String s) throws IOException {
        AnchorPane pane = loader.load();
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle(s);
        stage.setScene(new Scene(pane));
        stage.show();
    }


    public void handleEventType() {
        if(eventType.getSelectionModel().getSelectedItem().equals("Health")){
           setVisibility();
        }
    }
}
