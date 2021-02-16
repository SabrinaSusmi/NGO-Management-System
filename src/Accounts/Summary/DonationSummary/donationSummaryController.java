package Accounts.Summary.DonationSummary;

import SupplyDetails.Food.Food;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class donationSummaryController {
    @FXML
    private TextField monthNumber;
    @FXML
    private TextField yearNumber;
    @FXML
    private TableView<Donation> donationSummaryTable;

    @FXML
    private TableColumn<Donation, String> colDonID;

    @FXML
    private TableColumn<Donation, String> colDonorID;

    @FXML
    private TableColumn<Donation, Date> colDonDate;

    @FXML
    private TableColumn<Donation, String> colDonorName;

    @FXML
    private TableColumn<Donation, Integer> colDonAmount;
    @FXML
    private JFXTextField totalDonationTextfield;

    donationSummaryModel model = new donationSummaryModel();

    public void displayInformation() {

        setTableViewValue();

        try {
            donationSummaryTable.setItems(model.getDonationTableRecords(2, 2021));
        } catch (SQLException throwables) {
            System.out.println("DonationSummaryController: initialize");
            throwables.printStackTrace();
        }
    }

    private void setTableViewValue() {
        colDonID.setCellValueFactory(new PropertyValueFactory<>("donationId"));
        colDonDate.setCellValueFactory(new PropertyValueFactory<>("donDate"));
        colDonAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDonorID.setCellValueFactory(new PropertyValueFactory<>("donorId"));
        colDonorName.setCellValueFactory(new PropertyValueFactory<>("donorName"));
    }

    public void IDOnEnter() {
        totalDonation();
        setTableViewValue();
        try {
            donationSummaryTable.setItems(model.getDonationTableRecords(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText())));
        } catch (SQLException throwables) {
            System.out.println("donationController: initialize");
            throwables.printStackTrace();
        }
    }

    public void totalDonation() {
        totalDonationTextfield.setText(Integer.toString(model.getTotalDonation(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
    }

    public void initialize() {
         monthNumber.getStyleClass().clear();
        yearNumber.getStyleClass().clear();
        totalDonationTextfield.getStyleClass().clear();
    }
}
