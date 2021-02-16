package SupplyDetails.HealthProduct.Vaccine;

import SupplyDetails.HealthProduct.AddHealthProducts.AddHealthProdController;
import SupplyDetails.HealthProduct.Vaccine.ViewVaccineDetails.ViewVaccineInformationController;
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


public class VaccineController implements Initializable {

    @FXML
    private Pane vaccineDetailsPane;

    @FXML
    private TableView<Vaccine> vaccineTable;

     @FXML
    private TableColumn<Vaccine, String> colVaccineName;

    @FXML
    private TableColumn<Vaccine, Integer> colVaccineQuantity;

    @FXML
    private TableColumn<Vaccine, Integer> colVaccineBuyingPrice;

    @FXML
    private TextField searchVaccineTextField;


    VaccineModel vaccineModel = new VaccineModel();

    @FXML
    void handleAddVaccine() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../AddHealthProducts/addHealthProd.fxml"));
        AnchorPane pane = loader.load();
        AddHealthProdController prod=loader.getController();
        prod.setType(2);
        vaccineDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    void handleDeleteVaccine() {
        Vaccine removeSelectedMed = vaccineTable.getSelectionModel().getSelectedItem();
        if(removeSelectedMed == null){
            new ShowAlertDialogue().infoBox("No Item Is Selected", null, "Remove An Item");
            return;
        }

        int ans = new ShowAlertDialogue().confirmationBox("Do You Want To Remove This Item?",null, "Remove Item");
        if(ans == 1){
            String medName = getVaccineName();

            vaccineTable.getItems().removeAll(vaccineTable.getSelectionModel().getSelectedItem());
            if(vaccineModel.isDeleteSelectedVaccineSuccessful(medName)){
                new ShowAlertDialogue().infoBox("delete Successful!", null, "Delete Vaccine item");
            } else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "Delete Vaccine item");
            }
        }
    }

    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../../SupplyDetails.fxml"));
        vaccineDetailsPane.getChildren().setAll(pane);

    }

    private String getVaccineName(){
        return vaccineTable.getSelectionModel().getSelectedItem().getName();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTableView();
        searchVaccineTextField.getStyleClass().add("search-field");
        searchFilterData(searchVaccineTextField, vaccineTable);
        viewDetails();
    }

    private void viewDetails() {
        vaccineTable.setRowFactory(tv -> {
            TableRow<Vaccine> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("ViewVaccineDetails/ViewVaccineInformation.fxml"));
                        AnchorPane pane = loader.load();
                        ViewVaccineInformationController view=loader.getController();
                        view.displayInformation(getVaccineName());
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
        colVaccineName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVaccineQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colVaccineBuyingPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            vaccineTable.setItems(vaccineModel.getVaccineTableRecords());
        } catch (SQLException e) {
            System.out.println("vaccineController: initialize");
            e.printStackTrace();
        }
    }

    private void searchFilterData(TextField searchField, TableView<Vaccine> table) {
        try {
            FilteredList<Vaccine> filteredList = new FilteredList<>(vaccineModel.getVaccineTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(vaccine -> vaccineModel.isMatchSuccessful(newValue, vaccine));
                sortFilteredData(filteredList, table);
            });
        } catch (SQLException throwables) {
            System.out.println("vacDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Vaccine> filteredList, TableView<Vaccine> vaccineTable) {
        SortedList<Vaccine> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(vaccineTable.comparatorProperty());
        vaccineTable.setItems(sortedList);
    }

}

