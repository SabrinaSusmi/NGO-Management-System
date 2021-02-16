package Person.DonorDetails.AddDonor;

import Person.ImportPersonnelFile;
import Person.PersonalInformation;
import Person.Validation;
import Utilities.ShowAlertDialogue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AddDonorController {
    @FXML
    private AnchorPane addDonorPane;

    @FXML
    private TextField DonorName;

    @FXML
    private TextField DonorEmailID;

    @FXML
    private TextField DonorPhoneNumber;

    @FXML
    private ComboBox DonorGender;

    @FXML
    private TextField DonorAddress;

    @FXML
    private TextField DonorOccupation;

    private final ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Others");

    public void initialize() {
        DonorGender.setItems(gender);

    }


    public void handleDonorAddConfirmBtn() {
        if (new Validation().checkEmailAndPhoneValidation(DonorEmailID.getText(), DonorPhoneNumber.getText())) {
            if (new AddDonorModel().isAddDonorSuccessful(DonorName.getText(), DonorGender.getSelectionModel().getSelectedItem().toString(),
                    DonorAddress.getText(), DonorPhoneNumber.getText(), DonorOccupation.getText(), DonorEmailID.getText())) {
                new ShowAlertDialogue().infoBox("Donor Add Successful!", null, "Add Donor");
                refreshTextField();

            }
        } else {
            new ShowAlertDialogue().infoBox("Insert Valid Email or Phone Number", null, "Add Donor");
        }
    }

    private void refreshTextField() {
        DonorName.setText("");
        DonorEmailID.setText("");
        DonorAddress.setText("");
        DonorOccupation.setText("");
        DonorPhoneNumber.setText("");
    }

    @FXML
    public void handleImportFileButton() {
    //    String fileName = new ImportPersonnelFile().getFileDirectory();
        if(new AddDonorModel().addDonor()){
            new ShowAlertDialogue().infoBox("Donor Added Successfully",null,"Donor add");
        }
    }

    @FXML
    public void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../donorDetails.fxml"));
        addDonorPane.getChildren().setAll(pane);
    }
}
