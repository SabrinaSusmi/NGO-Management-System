package SupplyDetails.HealthProduct.Vaccine.ViewVaccineDetails;

import SupplyDetails.HealthProduct.Vaccine.Vaccine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class ViewVaccineInformationController {

    @FXML
    private AnchorPane vaccineDetailsPane;

    @FXML
    private TableView<Vaccine> informationTableVaccine;

    @FXML
    private TableColumn<Vaccine, String> colID;

    @FXML
    private TableColumn<Vaccine, String> colName;

    @FXML
    private TableColumn<Vaccine, Date> colPurchaseDate;

    @FXML
    private TableColumn<Vaccine, Date> colExpireDate;

    @FXML
    private TableColumn<Vaccine, Integer> colQuantity;

    @FXML
    private TableColumn<Vaccine, String> colSupplier;

    @FXML
    private TableColumn<Vaccine, String> colManufacturer;

    @FXML
    private TableColumn<Vaccine, Integer> colPrice;

    @FXML
    private TableColumn<Vaccine, Integer> colType;
    @FXML
    private TextField monthNumber;
    String itemName;


    ViewVaccineInformationModel view = new ViewVaccineInformationModel();

    @FXML
    void handleBackButton(ActionEvent event) throws IOException {
        FXMLLoader.load(getClass().getResource("../vaccine.fxml"));
        Stage stage = (Stage) vaccineDetailsPane.getScene().getWindow();
        stage.close();
    }

    public void displayInformation(String name) {
        itemName=name;
       setTableViewValue();

        try {
            informationTableVaccine.setItems(view.getVaccineTableRecords(name,6));
        } catch (SQLException throwables) {
            System.out.println("medController: initialize");
            throwables.printStackTrace();
        }

    }
    public void IDOnEnter() {
        setTableViewValue();
        System.out.println("ppppp");

        try {
            System.out.println("pop");
            informationTableVaccine.setItems(view.getVaccineTableRecords(itemName, Integer.parseInt(monthNumber.getText())));
        } catch (SQLException throwables) {
            System.out.println("foodController: initialize");
            throwables.printStackTrace();
        }
    }

    private void setTableViewValue() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPurchaseDate.setCellValueFactory(new PropertyValueFactory<>("pdate"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("edate"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        colManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    }

}
