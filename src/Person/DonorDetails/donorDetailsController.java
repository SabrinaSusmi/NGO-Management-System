package Person.DonorDetails;

import Person.DonorDetails.ViewDonorInformation.ViewDonorInformationController;
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

public class donorDetailsController {
    @FXML
    private AnchorPane donorAncPane;
    @FXML
    private Pane DonorDetailsPane;

    @FXML
    private TableView<Donor> DonorTable;

    @FXML
    private TableColumn<Donor, String> colDonorID;

    @FXML
    private TableColumn<Donor, String> colDonorName;

    @FXML
    private TableColumn<Donor, String> colDonorPhone;

    @FXML
    private TableColumn<Donor, String> colDonorEmail;


    @FXML
    private TextField searchDonorTextField;


    @FXML
    private void handleUpdate() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("UpdateDonor/updateDonorDetails.fxml"));
        DonorDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleAddNew() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddDonor/addDonor.fxml"));
        DonorDetailsPane.getChildren().setAll(pane);
    }
    @FXML
    private void handleAddDonationAmount() throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddDonation/addDonation.fxml"));
        DonorDetailsPane.getChildren().setAll(pane);
    }
    public void initialize() {
                populateTableView();
        viewDetails();
        searchDonorTextField.getStyleClass().add("search-field");

        searchFilterData(searchDonorTextField, DonorTable);
    }

    private void viewDetails() {
        DonorTable.setRowFactory(tv -> {
            TableRow<Donor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewDonorInformation/ViewDonorInformation.fxml"));
                        AnchorPane pane = loader.load();
                        ViewDonorInformationController view = loader.getController();
                        view.displayInformation(getDonorID());
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Donor Details");
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
        colDonorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDonorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDonorEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDonorPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        try {
            DonorTable.setItems(DonorDetailsModel.getDonorTableRecords());
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }
    private void searchFilterData(TextField searchField, TableView<Donor> table) {
        try {
            FilteredList<Donor> filteredList = new FilteredList<>(DonorDetailsModel.getDonorTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(donor -> DonorDetailsModel.isMatchSuccessful(newValue, donor));
                sortFilteredData(filteredList, table);
            });
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Donor> filteredList, TableView<Donor> DonorTable) {
        SortedList<Donor> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(DonorTable.comparatorProperty());
        DonorTable.setItems(sortedList);
    }


    public void handleRemoveDonorOption() {
        Donor removeSelectedDonor=DonorTable.getSelectionModel().getSelectedItem();
        if(removeSelectedDonor==null){
            new ShowAlertDialogue().infoBox("no Donor is selected",null,"Remove an Donor");
            return;
        }
        int ans = new ShowAlertDialogue().confirmationBox("Do you want to remove this Donor?", null, "remove Donor");
        if (ans == 1) {
            String DonorId = getDonorID();

            DonorTable.getItems().removeAll(DonorTable.getSelectionModel().getSelectedItem());
            if (new DonorDetailsModel().isDeleteDonorSuccessful(DonorId)) {
                new ShowAlertDialogue().infoBox("delete Successful!", null, "delete Donor");
            }
            else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "delete Donor");

            }
        }
        else {
            new ShowAlertDialogue().infoBox("cancel",null,"Remove a Donor");

        }
    }

    private String getDonorID() {
        return DonorTable.getSelectionModel().getSelectedItem().getId();
    }
}
