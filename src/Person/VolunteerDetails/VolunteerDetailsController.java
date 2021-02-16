package Person.VolunteerDetails;

import Utilities.ShowAlertDialogue;
import Person.VolunteerDetails.ViewVolunteerInformation.ViewVolunteerInformationController;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.SQLException;

public class VolunteerDetailsController {
    @FXML
    private Pane VolunteerDetailsPane;
@FXML
private AnchorPane VolunteerAncPane;

    @FXML
    private TableView<Volunteer> VolunteerTable;

    @FXML
    private TableColumn<Volunteer, String> colVolunteerID;

    @FXML
    private TableColumn<Volunteer, String> colVolunteerName;

    @FXML
    private TableColumn<Volunteer, String> colVolunteerPhone;

    @FXML
    private TableColumn<Volunteer, String> colVolunteerEmail;

    @FXML
    private TextField searchVolunteerTextField;

    @FXML
    private void handleUpdate() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("UpdateVolunteer/updateVolunteerDetails.fxml"));
        VolunteerDetailsPane.getChildren().setAll(pane);
    }

    @FXML
    private void handleAddNew() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddVolunteer/addVolunteer.fxml"));
        VolunteerDetailsPane.getChildren().setAll(pane);
    }

    public void initialize() {
        populateTableView();
        viewDetails();
        searchVolunteerTextField.getStyleClass().add("search-field");

        searchFilterData(searchVolunteerTextField);
    }

    private void viewDetails() {
        VolunteerTable.setRowFactory(tv -> {
            TableRow<Volunteer> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewVolunteerInformation/ViewVolunteerInformation.fxml"));
                        AnchorPane pane = loader.load();
                        ViewVolunteerInformationController view = loader.getController();
                        view.displayInformation(getVolunteerID());
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Volunteer Details");
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
        colVolunteerID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colVolunteerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colVolunteerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colVolunteerPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        try {
            VolunteerTable.setItems(VolunteerDetailsModel.getVolunteerTableRecords());
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }

    private void searchFilterData(TextField searchField) {
        try {
            FilteredList<Volunteer> filteredList = new FilteredList<>(VolunteerDetailsModel.getVolunteerTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(volunteer -> VolunteerDetailsModel.isMatchSuccessful(newValue, volunteer));
                sortFilteredData(filteredList);
            });
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Volunteer> filteredList) {
        SortedList<Volunteer> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(VolunteerTable.comparatorProperty());
        VolunteerTable.setItems(sortedList);
    }


    public void handleRemoveVolunteerOption() {
        Volunteer removeSelectedVolunteer = VolunteerTable.getSelectionModel().getSelectedItem();
        if (removeSelectedVolunteer == null) {
            new ShowAlertDialogue().infoBox("no Volunteer is selected", null, "Remove an Volunteer" );
            return;
        }
        int ans = new ShowAlertDialogue().confirmationBox("Do you want to remove this Volunteer?", null, "remove Volunteer");
        if (ans == 1) {
            String VolunteerId = getVolunteerID();

            VolunteerTable.getItems().removeAll(VolunteerTable.getSelectionModel().getSelectedItem());
            if (new VolunteerDetailsModel().isDeleteVolunteerSuccessful(VolunteerId)) {
                new ShowAlertDialogue().infoBox("delete Successful!", null, "delete Volunteer" );
            } else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "delete Volunteer" );

            }
        } else {
            new ShowAlertDialogue().infoBox("cancel", null, "Remove a Volunteer" );

        }
    }

    private String getVolunteerID() {
        String VolunteerId = VolunteerTable.getSelectionModel().getSelectedItem().getId();
        return VolunteerId;
    }
}
