package Person.VolunteerDetails.AddVolunteer;

import Person.ImportPersonnelFile;
import Person.PersonalInformation;
import Person.Validation;
import Utilities.ShowAlertDialogue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddVolunteerController {
   @FXML
   public AnchorPane addVolunteerPane;
    @FXML
    private TextField VolunteerName;

    @FXML
    private TextField VolunteerEmailID;

    @FXML
    private TextField VolunteerPhoneNumber;

    @FXML
    private ComboBox VolunteerGender;

    @FXML
    private TextField VolunteerAddress;


    @FXML
    private TextField VolunteerOccupation;
    private ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Others");
    public void initialize() {
        VolunteerGender.setItems(gender);

    }

@FXML
    public void handleConfirmVolunteerAddBtn() {
    if (new Validation().checkEmailAndPhoneValidation(VolunteerEmailID.getText(),VolunteerPhoneNumber.getText())){
        if(new AddVolunteerModel().isAddVolunteerSuccessful(VolunteerName.getText(),VolunteerGender.getSelectionModel().getSelectedItem().toString(),
                VolunteerAddress.getText(),VolunteerPhoneNumber.getText(),VolunteerOccupation.getText(),VolunteerEmailID.getText())){
            new ShowAlertDialogue().infoBox("Volunteer Add Successful!", null, "Add Volunteer");
            refreshTextField();
        }
}else {
        new ShowAlertDialogue().infoBox("Insert Valid Email or Phone Number", null, "Add Volunteer" );
    }    }

    private void refreshTextField() {
        VolunteerName.setText("");
        VolunteerEmailID.setText("");
        VolunteerAddress.setText("");
        VolunteerOccupation.setText("");
        VolunteerPhoneNumber.setText("");
    }

    public void handleImportFileButton() {
      //  String fileName = new ImportPersonnelFile().getFileDirectory();
        if(new AddVolunteerModel().addVolunteer()){
            new ShowAlertDialogue().infoBox("Volunteer Added Successfully",null,"Volunteer add");
        }
    }

    public void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../volunteerDetails.fxml"));
        addVolunteerPane.getChildren().setAll(pane);    }
}
