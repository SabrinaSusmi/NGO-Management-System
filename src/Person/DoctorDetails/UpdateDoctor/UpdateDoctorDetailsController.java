package Person.DoctorDetails.UpdateDoctor;

import Person.Validation;
import Utilities.ShowAlertDialogue;
import Person.PersonalInformation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;

public class UpdateDoctorDetailsController {
    UpdateDoctorDetailsModel updateDoctorDetailsModel = new UpdateDoctorDetailsModel();

    @FXML
    private TextField DoctorID;

    @FXML
    private TextField DoctorName;

    @FXML
    private TextField DoctorEmailID;

    @FXML
    private TextField DoctorPhoneNumber;

    @FXML
    private TextField DoctorAvailableTime;

    @FXML
    private TextField DoctorAddress;

    @FXML
    private TextField DoctorQualification;

  @FXML
  private AnchorPane updateDoctorPane;
    public void IDOnEnter() {
        String[] list = new String[6];
        String[] info = updateDoctorDetailsModel.setDoctorInformation(list, DoctorID.getText());
        DoctorName.setText(info[0]);
        DoctorEmailID.setText(info[1]);
        DoctorPhoneNumber.setText(info[2]);
        DoctorQualification.setText(info[3]);
        DoctorAddress.setText(info[4]);
        DoctorAvailableTime.setText(info[5]);
    }

    public void handleConfirmButton() {
        if (new Validation().checkEmailAndPhoneValidation(DoctorEmailID.getText(),DoctorPhoneNumber.getText())){

            if (updateDoctorDetailsModel.isUpdateDoctorSuccessful(DoctorAddress.getText(), DoctorPhoneNumber.getText(),
                DoctorQualification.getText(), DoctorEmailID.getText(), DoctorAvailableTime.getText(), DoctorID.getText())) {
            new ShowAlertDialogue().infoBox("update Successful!", null, "update Doctor" );
            refreshTextField();
        }
        }else {
            new ShowAlertDialogue().infoBox("Insert Valid Email or Phone Number", null, "Update Doctor" );
        }
    }
    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../DoctorDetails.fxml"));
        updateDoctorPane.getChildren().setAll(pane);
    }
    private void refreshTextField() {
        DoctorAddress.setText("");
        DoctorPhoneNumber.setText("");
        DoctorQualification.setText("");
        DoctorEmailID.setText("");
        DoctorAvailableTime.setText("");
        DoctorID.setText("");
        DoctorName.setText("");
    }

    public void initialize() {
        TextFields.bindAutoCompletion(DoctorID, new PersonalInformation().getIDList("select Doctor_id from Doctor"));
    }
}
