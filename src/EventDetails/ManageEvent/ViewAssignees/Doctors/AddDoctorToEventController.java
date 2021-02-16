package EventDetails.ManageEvent.ViewAssignees.Doctors;


import EventDetails.ManageEvent.ManageEventController;
import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class AddDoctorToEventController {
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

    AddDoctorToEventModel addDoctorToEventModel = new AddDoctorToEventModel();
    JFXCheckBox[] checkBox = new JFXCheckBox[addDoctorToEventModel.getTotalID()];
    String[] list = new String[addDoctorToEventModel.getTotalID()];
    ArrayList<String> selectedID = new ArrayList<String>();

    private void setName() {
        String[] id = addDoctorToEventModel.getVolunteerList(list);
        setCheckBoxValues(id);

    }

    private void setCheckBoxValues(String[] id) {
        checkBox(id, addDoctorToEventModel.getTotalID(), checkBox, gridPane1, gridPane2, gridPane3);
    }

    public static void checkBox(String[] id, int totalID, JFXCheckBox[] checkBox, GridPane gridPane1, GridPane gridPane2, GridPane gridPane3) {
        int k = 0, j = 0;

        for (int i = 0; i < totalID; i++) {

            checkBox[i] = new JFXCheckBox(id[i]);
            checkBox[i].setAlignment(Pos.CENTER_LEFT);
            if (i < 9) {
                gridPane1.addRow(i, checkBox[i]);
            }
            if (i > 8 && i < 18) {
                gridPane2.addRow(k, checkBox[i]);
                k++;
            }
            if (i > 17 && i < 27) {
                gridPane3.addRow(j, checkBox[i]);
                j++;
            }
        }
    }

    private void getSelectedVolunteers() {
        for (int i = 0; i < addDoctorToEventModel.getTotalID(); i++) {
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
        String[] id = addDoctorToEventModel.getSearchedList(list, text);
        refreshGridPane();
        setCheckBoxValues(id);
    }

    public void initialize() {
        setName();
    }


    public void handleSearch() {
        setSearchedName(searchOption.getText());
    }

    public void handleConfirmButton() {
        getSelectedVolunteers();
        //System.out.println(selectedID);
        if (addDoctorToEventModel.isAssignVolunteerSuccessful(selectedID, new ManageEventController().getEventID())) {
            new ShowAlertDialogue().infoBox("Doctor Assigned!", null, "Assign Docotr");
            Stage stage = (Stage) viewIDs.getScene().getWindow();
            stage.close();
        } else {
            new ShowAlertDialogue().infoBox("Docotr Assigning Failed!", null, "Assign Docotr");
        }
    }

    @FXML
    void handleBackButton() throws IOException {
        Stage stage = (Stage) viewIDs.getScene().getWindow();
        stage.close();
    }
}