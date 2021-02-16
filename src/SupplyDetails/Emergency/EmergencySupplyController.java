package SupplyDetails.Emergency;

import SupplyDetails.HealthProduct.AddHealthProducts.AddHealthProdController;
import SupplyDetails.Emergency.ViewEmergencySupplyDetails.ViewEmergencySupplyDetailsController;
import Utilities.ShowAlertDialogue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;


public class EmergencySupplyController {
    @FXML
    private Pane EmergencySupplyDetailsPane;


    @FXML
    private TableView<Emergency> emergencySupplyTable;

    @FXML
    private TableColumn<Emergency, String> colName;

    @FXML
    private TableColumn<Emergency, Integer> colQuantity;

    @FXML
    private TableColumn<Emergency, Integer> colPrice;

    @FXML
    private TextField searchSupplyTextField;
    EmergencySupplyModel emergencySupplyModel=new EmergencySupplyModel();

    public void initialize() {
        populateTableView();
        searchSupplyTextField.getStyleClass().add("search-field");

        searchFilterData(searchSupplyTextField, emergencySupplyTable);
        viewDetails();
    }
    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../SupplyDetails.fxml"));
        EmergencySupplyDetailsPane.getChildren().setAll(pane);

    }

    private void viewDetails() {
        emergencySupplyTable.setRowFactory(tv -> {
            TableRow<Emergency> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("ViewEmergencySupplyDetails/ViewEmergencySupplyDetails.fxml"));
                        AnchorPane pane = loader.load();
                        ViewEmergencySupplyDetailsController view=loader.getController();
                        view.displayInformation(getEmergencyName());
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

    public void handleAddOption() throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("../HealthProduct/AddHealthProducts/addHealthProd.fxml"));
        AnchorPane pane = loader.load();
        AddHealthProdController prod=loader.getController();
        prod.setType(3);
        EmergencySupplyDetailsPane.getChildren().setAll(pane);
    }

    private String getEmergencyName(){
        return emergencySupplyTable.getSelectionModel().getSelectedItem().getName();
    }
    private void populateTableView(){
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            emergencySupplyTable.setItems(emergencySupplyModel.getEmergencyTableRecords());
        } catch (SQLException e) {
            System.out.println("EmergencySupplyController: initialize");
            e.printStackTrace();
        }
    }

    private void searchFilterData(TextField searchField, TableView<Emergency> table) {
        try {
            FilteredList<Emergency> filteredList = new FilteredList<>(emergencySupplyModel.getEmergencyTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(emergency -> emergencySupplyModel.isMatchSuccessful(newValue, emergency));
                sortFilteredData(filteredList, table);
            });
        } catch (SQLException throwables) {
            System.out.println("vacDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Emergency> filteredList, TableView<Emergency> emergencyTable) {
        SortedList<Emergency> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(emergencyTable.comparatorProperty());
        emergencyTable.setItems(sortedList);
    }


    public void handleDeleteOption() {
        Emergency removeSelectedMed = emergencySupplyTable.getSelectionModel().getSelectedItem();
        if(removeSelectedMed == null){
            new ShowAlertDialogue().infoBox("No Item Is Selected", null, "Remove An Item");
            return;
        }

        int ans = new ShowAlertDialogue().confirmationBox("Do You Want To Remove This Item?",null, "Remove Item");
        if(ans == 1){
            String medName = getEmergencyName();

            emergencySupplyTable.getItems().removeAll(emergencySupplyTable.getSelectionModel().getSelectedItem());
            if(emergencySupplyModel.isDeleteSelectedEmergencySuccessful(medName)){
                new ShowAlertDialogue().infoBox("delete Successful!", null, "Delete Emergency Supply item");
            } else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "Delete Emergency Suplly item");
            }
        }
    }


}
