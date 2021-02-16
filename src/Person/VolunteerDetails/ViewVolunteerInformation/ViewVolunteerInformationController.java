package Person.VolunteerDetails.ViewVolunteerInformation;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ViewVolunteerInformationController {
    @FXML
    private AnchorPane viewDetails;

    @FXML
    private TextField showID;

    @FXML
    private TextField showName;

    @FXML
    private TextField showOccupation;

    @FXML
    private TextField showEmail;

    @FXML
    private TextField showContact;

    @FXML
    private TextField showGender;

    @FXML
    private TextField showAddress;
    ViewVolunteerInformationModel viewVolunteerInformationModel = new ViewVolunteerInformationModel();

    public void displayInformation(String id) {
        String[] list = new String[8];
        String[] info = viewVolunteerInformationModel.showDonorDetails(list, id);
        showID.setText(info[0]);
        showName.setText(info[1]);
        showEmail.setText(info[2]);
        showGender.setText(info[3]);
        showAddress.setText(info[4]);
        showContact.setText(info[5]);
        showOccupation.setText(info[6]);

    }

    @FXML
    void handleBackButton() throws IOException {
        FXMLLoader.load(getClass().getResource("../VolunteerDetails.fxml"));
        Stage stage = (Stage) viewDetails.getScene().getWindow();
        stage.close();
    }

}
