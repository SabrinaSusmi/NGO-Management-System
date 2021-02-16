package SupplyDetails.HealthProduct.Medicine.ViewMedicineDetails;

import SupplyDetails.Food.Food;
import SupplyDetails.HealthProduct.Medicine.Medicine;
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

public class ViewMedicineInformationController {

    @FXML
    private AnchorPane medicineDetailsPane;

    @FXML
    private TableView<Medicine> informationTable;

    @FXML
    private TableColumn<Medicine, String> colID;

    @FXML
    private TableColumn<Food, String> colName;

    @FXML
    private TableColumn<Medicine, Date> colPurchaseDate;

    @FXML
    private TableColumn<Medicine, Date> colExpireDate;

    @FXML
    private TableColumn<Medicine, String> colSupplier;

    @FXML
    private TableColumn<Medicine, String> colManufacturer;

    @FXML
    private TableColumn<Medicine, Integer> colQuantity;

    @FXML
    private TableColumn<Medicine, Integer> colPrice;

    @FXML
    private TableColumn<Medicine, Integer> colType;

    @FXML
    private TextField monthNumber;
    String itemName;


    ViewMedicineInformationModel view = new ViewMedicineInformationModel();

    @FXML
    void handleBackButton() throws IOException {
        FXMLLoader.load(getClass().getResource("../medicine.fxml"));
        Stage stage = (Stage) medicineDetailsPane.getScene().getWindow();
        stage.close();
    }

    public void displayInformation(String name) {
        itemName=name;
       setTableViewValue();
        try {
            informationTable.setItems(view.getMedicineTableRecords(name,6));
        } catch (SQLException throwables) {
            System.out.println("medController: initialize");
            throwables.printStackTrace();
        }

    }
    public void IDOnEnter() {
        setTableViewValue();

        try {
            informationTable.setItems(view.getMedicineTableRecords(itemName, Integer.parseInt(monthNumber.getText())));
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

