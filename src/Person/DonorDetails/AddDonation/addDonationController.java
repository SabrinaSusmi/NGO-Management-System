package Person.DonorDetails.AddDonation;

import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class addDonationController {
    @FXML
    private Pane addDonationPane;
    @FXML
    private DatePicker donationDate;
    @FXML
    private JFXTextField donationAmount;
    @FXML
    private TextField donorId;
    @FXML
    private JFXTextField donorName;
    addDonationModel donationModel = new addDonationModel();
    @FXML
    private void handleBackBtn() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../donorDetails.fxml"));
        addDonationPane.getChildren().setAll(pane);
    }
    @FXML
    private void handleConfirmBtn() throws ParseException {
        Date donation_date = new SimpleDateFormat("MM/dd/yyyy").parse(donationDate.getEditor().getText());
        if(donationModel.isDonationAddSuccessful(Integer.parseInt(donationAmount.getText()),donation_date,donorId.getText())){
            new ShowAlertDialogue().infoBox("Donation Added Successfully!",null,"Add Donation");
            refreshTextField();
        }
        else {
            new ShowAlertDialogue().infoBox("Donation Adding Failed!",null,"Add Donation");
        }
    }

    private void refreshTextField() {
        donorId.setText("");
        donationDate.getEditor().setText("");
        donorName.setText("");
        donationAmount.setText("");
    }
    public void initialize() {
        TextFields.bindAutoCompletion(donorId, new addDonationModel().getItemNameList("select DOnor_ID from DOnor"));
    }
    public void handleDonorID() {
        donorName.setText(new addDonationModel().getDonorName(donorId.getText()));
    }

}
