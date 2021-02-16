package SupplyDetails.Food;

import SupplyDetails.Food.ViewFoodDetails.ViewFoodInformationController;
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

public class FoodController implements Initializable {
    @FXML
    public AnchorPane foodSupplyDetailsPane;
    @FXML
    private Pane foodDetailsPane;

    @FXML
    private TableView<Food> foodTable;

    @FXML
    private TableColumn<Food, String> colFoodName;

    @FXML
    private TableColumn<Food, Integer> colFoodQuantity;

    @FXML
    private TableColumn<Food, Integer> colFoodBuyingPrice;

    @FXML
    private TextField searchFoodTextField;


    FoodModel foodModel = new FoodModel();

    @FXML
    private void handleAddFood() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddFood/addFoodSupply.fxml"));
        foodDetailsPane.getChildren().setAll(pane);
    }
    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../SupplyDetails.fxml"));
        foodDetailsPane.getChildren().setAll(pane);

    }

    @FXML
    private void handleDeleteFood() {
        Food removeSelectedFood = foodTable.getSelectionModel().getSelectedItem();
        if (removeSelectedFood == null) {
            new ShowAlertDialogue().infoBox("no Food Item is selected", null, "Remove an Food Item");
            return;
        }
        int ans = new ShowAlertDialogue().confirmationBox("Do you want to remove this item?", null, "remove item");
        if (ans == 1) {
            String foodId = (getFoodName());

            foodTable.getItems().removeAll(foodTable.getSelectionModel().getSelectedItem());
            if (foodModel.isDeleteSelectedFoodSuccessful(foodId)) {
                new ShowAlertDialogue().infoBox("delete Successful!", null, "delete food item");
            } else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "delete food item");

            }
        } else {
            new ShowAlertDialogue().infoBox("cancel", null, "Remove an Item");

        }
    }

    private String getFoodName() {
        return foodTable.getSelectionModel().getSelectedItem().getName();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTableView();
        viewDetails();
        searchFoodTextField.getStyleClass().add("search-field");
        searchFilterData(searchFoodTextField, foodTable);

    }

    private void viewDetails() {
        foodTable.setRowFactory(tv -> {
            TableRow<Food> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewFoodDetails/ViewFoodInformation.fxml"));
                        AnchorPane pane = loader.load();
                        ViewFoodInformationController view = loader.getController();
                        view.displayInformation(getFoodName());
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

    private void populateTableView() {
        colFoodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colFoodQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colFoodBuyingPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        try {
            foodTable.setItems(foodModel.getFoodTableRecords());
        } catch (SQLException throwables) {
            System.out.println("foodController: initialize");
            throwables.printStackTrace();
        }
    }

    private void searchFilterData(TextField searchField, TableView<Food> table) {
        try {
            FilteredList<Food> filteredList = new FilteredList<>(foodModel.getFoodTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(food -> foodModel.isMatchSuccessful(newValue, food));
                sortFilteredData(filteredList, table);
            });
        } catch (SQLException throwables) {
            System.out.println("FoodDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Food> filteredList, TableView<Food> foodTable) {
        SortedList<Food> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(foodTable.comparatorProperty());
        foodTable.setItems(sortedList);
    }


}
