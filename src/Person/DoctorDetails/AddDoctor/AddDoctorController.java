package Person.DoctorDetails.AddDoctor;

import Person.DoctorDetails.DoctorDetailsModel;
import Person.PersonalInformation;
import Person.Validation;
import Utilities.ShowAlertDialogue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddDoctorController {
    @FXML
    private AnchorPane addDoctorPane;
    @FXML
    private TextField DoctorName;

    @FXML
    private TextField DoctorEmailID;

    @FXML
    private TextField DoctorPhoneNumber;

    @FXML
    private DatePicker DoctorDOB;

    @FXML
    private ComboBox DoctorSpeciality;

    @FXML
    private ComboBox DoctorGender;

    @FXML
    private TextField DoctorAddress;

    @FXML
    private TextField DoctorQualification;

    @FXML
    private TextField DoctorAvailableTime;

    private final ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Others");
    private final ObservableList<String> speciality = FXCollections.observableArrayList();

    @FXML
    private void handleConfirmButton() throws ParseException {
        Date dobDate = new SimpleDateFormat("MM/dd/yyyy").parse(DoctorDOB.getEditor().getText());
       if (new Validation().checkEmailAndPhoneValidation(DoctorEmailID.getText(),DoctorPhoneNumber.getText())){
        if (new AddDoctorModel().isAddDoctorSuccessful(DoctorName.getText(), dobDate, DoctorGender.getSelectionModel().getSelectedItem().toString(),
                DoctorAddress.getText(), DoctorPhoneNumber.getText(), DoctorSpeciality.getSelectionModel().getSelectedItem().toString(),
                DoctorEmailID.getText(), DoctorQualification.getText(), DoctorAvailableTime.getText())) {
            new ShowAlertDialogue().infoBox("Doctor Add Successful!", null, "Add Doctor" );
            refreshTextField();

        }
       }else {
           new ShowAlertDialogue().infoBox("Insert Valid Email or Phone Number", null, "Add Doctor" );
       }

    }
    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../DoctorDetails.fxml"));
        addDoctorPane.getChildren().setAll(pane);
    }
    private void refreshTextField() {
        DoctorDOB.getEditor().setText("");
        DoctorAddress.setText("");
        DoctorPhoneNumber.setText("");
        DoctorEmailID.setText("");
        DoctorQualification.setText("");
        DoctorName.setText("");
        DoctorAvailableTime.setText("");
    }

    public void initialize() {
        DoctorGender.setItems(gender);
        new DoctorDetailsModel().getSpeciality(speciality);
        DoctorSpeciality.setItems(speciality);
    }

}
