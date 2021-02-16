package SupplyDetails.HealthProduct.Medicine;

import SupplyDetails.HealthProduct.AddHealthProducts.AddHealthProdController;
import SupplyDetails.HealthProduct.Medicine.ViewMedicineDetails.ViewMedicineInformationController;
import Utilities.ShowAlertDialogue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MedicineController implements Initializable {

    @FXML
    private Pane medicineDetailsPane;

    @FXML
    private TableView<Medicine> medicineTable;


    @FXML
    private TableColumn<Medicine, String> colMedicineName;

    @FXML
    private TableColumn<Medicine, Integer> colMedicineQuantity;

    @FXML
    private TableColumn<Medicine, Integer> colMedicineBuyingPrice;

    @FXML
    private TextField searchMedicineTextField;


    MedicineModel medicineModel = new MedicineModel();

    @FXML
    private void handleAddMedicine() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../AddHealthProducts/addHealthProd.fxml"));
        AnchorPane pane = loader.load();
        AddHealthProdController prod=loader.getController();
        prod.setType(1);
        medicineDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void handleDeleteMedicine() {
        Medicine removeSelectedMed = medicineTable.getSelectionModel().getSelectedItem();
        if(removeSelectedMed == null){
            new ShowAlertDialogue().infoBox("No Medicine Item Is Selected", null, "Remove An Item");
            return;
        }

        int ans = new ShowAlertDialogue().confirmationBox("Do You Want To Remove This Item?",null, "Remove Item");
        if(ans == 1){
            String medName = getMedName();

            medicineTable.getItems().removeAll(medicineTable.getSelectionModel().getSelectedItem());
            if(medicineModel.isDeleteSelectedMedicineSuccessful(medName)){
                new ShowAlertDialogue().infoBox("delete Successful!", null, "Delete Medicine item");
            } else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "Delete Medicine item");
            }
        }
    }

    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../SupplyDetails.fxml"));
        medicineDetailsPane.getChildren().setAll(pane);

    }

    private String getMedName(){
        return medicineTable.getSelectionModel().getSelectedItem().getName();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewDetails();
        populateTableView();
        searchMedicineTextField.getStyleClass().add("search-field");
        searchFilterData(searchMedicineTextField, medicineTable);
    }

    private void viewDetails() {
        medicineTable.setRowFactory(tv -> {
            TableRow<Medicine> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("ViewMedicineDetails/ViewMedicineInformation.fxml"));
                        AnchorPane pane = loader.load();
                        ViewMedicineInformationController view=loader.getController();
                        view.displayInformation(getMedName());
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Item Details");
                        stage.setScene(new Scene(pane));
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    private void populateTableView(){
        colMedicineName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMedicineQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colMedicineBuyingPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            medicineTable.setItems(medicineModel.getMedicineTableRecords());
        } catch (SQLException e) {
            System.out.println("medicineController: initialize");
            e.printStackTrace();
        }
    }


    private void searchFilterData(TextField searchField, TableView<Medicine> table) {
        try {
            FilteredList<Medicine> filteredList = new FilteredList<>(medicineModel.getMedicineTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(medicine -> medicineModel.isMatchSuccessful(newValue, medicine));
                sortFilteredData(filteredList, table);
            });
        } catch (SQLException throwables) {
            System.out.println("medDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Medicine> filteredList, TableView<Medicine> medicineTable) {
        SortedList<Medicine> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(medicineTable.comparatorProperty());
        medicineTable.setItems(sortedList);
    }
}

