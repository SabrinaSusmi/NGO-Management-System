package SupplyDetails.Emergency.ViewEmergencySupplyDetails;

import SupplyDetails.Emergency.Emergency;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;


public class ViewEmergencySupplyDetailsController {
    @FXML
    private AnchorPane viewDetailspane;

    @FXML
    private TableView<Emergency> informationTable;

    @FXML
    private TableColumn<Emergency, String> colID;

    @FXML
    private TableColumn<Emergency, String> colName;

    @FXML
    private TableColumn<Emergency, Date> colPurchaseDate;

    @FXML
    private TableColumn<Emergency,Date> colExpireDate;

    @FXML
    private TableColumn<Emergency,Integer> colQuantity;

    @FXML
    private TableColumn<Emergency, String> colSupplier;

    @FXML
    private TableColumn<Emergency, String> colManufacturer;

    @FXML
    private TableColumn<Emergency, Integer> colPrice;
    @FXML
    private TextField monthNumber;
    String itemName;


    ViewEmergencySupplyDetailsModel view=new ViewEmergencySupplyDetailsModel();


    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader.load(getClass().getResource("../vaccine.fxml"));
        Stage stage = (Stage) viewDetailspane.getScene().getWindow();
        stage.close();
    }
    public void displayInformation(String name) {
        itemName=name;

        setTableViewValue();

        try {
            informationTable.setItems(view.getEmergencyTableRecords(name,6));
        } catch (SQLException throwables) {
            System.out.println("medController: initialize");
            throwables.printStackTrace();
        }

    }
    public void IDOnEnter() {
        setTableViewValue();

        try {
            informationTable.setItems(view.getEmergencyTableRecords(itemName, Integer.parseInt(monthNumber.getText())));
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
