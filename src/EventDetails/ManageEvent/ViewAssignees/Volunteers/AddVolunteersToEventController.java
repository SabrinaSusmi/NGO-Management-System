package EventDetails.ManageEvent.ViewAssignees.Volunteers;

import EventDetails.ManageEvent.ViewAssignees.Doctors.AddDoctorToEventController;
import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddVolunteersToEventController {
    @FXML
    private AnchorPane viewDetails;
    @FXML
    private AnchorPane viewIDs;
    @FXML
    private JFXTextField searchOption;
    @FXML
    private GridPane gridPane1;
    @FXML
    private GridPane gridPane2;

    @FXML
    private GridPane gridPane3;

    AddVolunteersToEventModel addVolunteersToEventModel = new AddVolunteersToEventModel();
    JFXCheckBox[] checkBox = new JFXCheckBox[addVolunteersToEventModel.getTotalID()];
    String[] list = new String[addVolunteersToEventModel.getTotalID()];
    ArrayList<String> selectedID = new ArrayList<>();

    private void setName() {
        String[] id = addVolunteersToEventModel.getVolunteerList(list);
        setCheckBoxValues(id);

    }

    private void setCheckBoxValues(String[] id) {
        AddDoctorToEventController.checkBox(id, addVolunteersToEventModel.getTotalID(), checkBox, gridPane1, gridPane2, gridPane3);
    }

    private void getSelectedVolunteers() {
        for (int i = 0; i < addVolunteersToEventModel.getTotalID(); i++) {
            if (checkBox[i].isSelected()) {
                selectedID.add(checkBox[i].getText());
            }
        }
    }

    private void refreshGridPane() {
        gridPane1.getChildren().clear();
        gridPane2.getChildren().clear();
        gridPane3.getChildren().clear();
    }

    private void setSearchedName(String text) {
        Arrays.fill(list, null);
        String[] id = addVolunteersToEventModel.getSearchedList(list, text);
        refreshGridPane();
        setCheckBoxValues(id);


    }

    public void initialize() {
        setName();
    }

    @FXML
    void handleBackButton() throws IOException {
        //FXMLLoader.load(getClass().getResource("../../ManageEvent.fxml"));
        Stage stage = (Stage) viewDetails.getScene().getWindow();
        stage.close();
    }

    public String event_id;

    @FXML
    void handleConfirmButton(ActionEvent event) {
        getSelectedVolunteers();
        //System.out.println(selectedID);
        if (addVolunteersToEventModel.isAssignVolunteerSuccessful(selectedID, event_id)) {
            new ShowAlertDialogue().infoBox("Volunteers Assigned!", null, "Assign Volunteer");
            Stage stage = (Stage) viewDetails.getScene().getWindow();
            stage.close();
        } else {
            new ShowAlertDialogue().infoBox("Volunteers Assigning Failed!", null, "Assign Volunteer");
        }
    }

    @FXML
    public void handleSearch() {
        setSearchedName(searchOption.getText());

    }
}