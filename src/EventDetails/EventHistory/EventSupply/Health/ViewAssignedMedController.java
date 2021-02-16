package EventDetails.EventHistory.EventSupply.Health;

import SupplyDetails.HealthProduct.Medicine.Medicine;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewAssignedMedController {
    @FXML
    private AnchorPane viewDetails;

    @FXML
    private TableView<Medicine> informationTable;



    @FXML
    private TableColumn<Medicine, String> colName;

    @FXML
    private TableColumn<Medicine, Integer> colQuantity;

    @FXML
    private TableColumn<Medicine, Integer> colPrice;

    @FXML
    private TableColumn<Medicine, String> colExpDate;

    @FXML
    private TableColumn<Medicine, String> colManufacturer;

    ViewAssignedMedModel viewAssignedMedModel = new ViewAssignedMedModel();

    @FXML
    void handleBackButton() throws IOException {
        FXMLLoader.load(getClass().getResource("../../../ManageEvent/ManageEventEmployees.fxml"));
        Stage stage = (Stage) viewDetails.getScene().getWindow();
        stage.close();
    }

    public void populateTableView(String id){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<>("edate"));
        colManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

        try {
            informationTable.setItems(viewAssignedMedModel.getTableRecords(id));
        } catch (SQLException e) {
            System.out.println("viewMedController: initialize");
            e.printStackTrace();
        }
    }

}
