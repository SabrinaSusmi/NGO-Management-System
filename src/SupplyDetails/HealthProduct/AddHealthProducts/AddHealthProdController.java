package SupplyDetails.HealthProduct.AddHealthProducts;

import SupplyDetails.SupplyInformation;
import Utilities.ShowAlertDialogue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddHealthProdController {

    @FXML
    private AnchorPane addNewHealthProdPane;

    @FXML
    private TextField healthProdName;

    @FXML
    private TextField healthProdSupplier;

    @FXML
    private TextField healthProdQuantity;

    @FXML
    private TextField healthProdPrice;

    @FXML
    private DatePicker healthProdPurchaseDate;

    @FXML
    private DatePicker healthProdExpiryDate;

    @FXML
    private TextField healthProdManufacturer;


    private int prodType;

    @FXML
    void handleConfirmAddHealthProd() throws ParseException {
        Date purDate = new SimpleDateFormat("MM/dd/yyyy").parse(healthProdPurchaseDate.getEditor().getText());
        Date expDate = new SimpleDateFormat("MM/dd/yyyy").parse(healthProdExpiryDate.getEditor().getText());

        if (new AddHealthProdModel().isAddHealthProdSuccessful(healthProdName.getText(), purDate, expDate, Integer.parseInt(healthProdQuantity.getText()),
                Integer.parseInt(healthProdPrice.getText()), healthProdSupplier.getText(), healthProdManufacturer.getText(), getType()
        )) {
            new ShowAlertDialogue().infoBox("Health Product Added Successfully", null, "Add Health Supply");
            refreshTextField();
        }
    }

    private void refreshTextField() {
        healthProdExpiryDate.getEditor().setText("");
        healthProdPurchaseDate.getEditor().setText("");
        healthProdName.setText("");
        healthProdPrice.setText("");
        healthProdManufacturer.setText("");
        healthProdQuantity.setText("");
        healthProdSupplier.setText("");
    }

    public void initialize() {
        TextFields.bindAutoCompletion(healthProdName, new SupplyInformation().getItemNameList("select distinct name from HEALTH_PRODUCT"));
    }

    public void setType(int type) {
        prodType = type;
    }

    private int getType() {
        return prodType;
    }

    @FXML
    private void handleBackHealth() throws IOException {
        AnchorPane pane = null;
        if (getType() == 1) {
            pane = FXMLLoader.load(getClass().getResource("../../HealthProduct/Medicine/medicine.fxml"));
        } else if (getType() == 2) {
            pane = FXMLLoader.load(getClass().getResource("../../HealthProduct/Vaccine/vaccine.fxml"));
        } else if (getType() == 3) {
            pane = FXMLLoader.load(getClass().getResource("../../Emergency/EmergencySupply.fxml"));
        }

        addNewHealthProdPane.getChildren().setAll(pane);
    }
}
