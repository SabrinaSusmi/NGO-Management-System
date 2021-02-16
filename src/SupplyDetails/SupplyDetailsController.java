package SupplyDetails;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SupplyDetailsController implements Initializable {

    @FXML
    private Pane supplyDetailsPane;

    @FXML
    public void handleSupplyEmergency() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Emergency/EmergencySupply.fxml"));
        supplyDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleSupplyFood() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Food/food.fxml"));
        supplyDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    public void handleSupplyMedicine() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HealthProduct/Medicine/medicine.fxml"));
        supplyDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleSupplyVaccine() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("HealthProduct/Vaccine/vaccine.fxml"));
        supplyDetailsPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}

