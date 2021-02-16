package EventDetails.ManageEvent.ViewAssignees.Volunteers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewAssignedVolunteersController {
    @FXML
    private AnchorPane viewDetails;
    @FXML
    private TableView<AssignedVolunteerOfAnEvent> informationTable;

    @FXML
    private TableColumn<AssignedVolunteerOfAnEvent, String> colId;

    @FXML
    private TableColumn<AssignedVolunteerOfAnEvent, String> colName;

    @FXML
    private TableColumn<AssignedVolunteerOfAnEvent, String> colQualification;

    @FXML
    private TableColumn<AssignedVolunteerOfAnEvent, String> colEmail;

    @FXML
    private TableColumn<AssignedVolunteerOfAnEvent, String> colContactNo;

    @FXML
    private TableColumn<AssignedVolunteerOfAnEvent, String> colAddress;

    @FXML
    void handleBackButton() throws IOException {
        FXMLLoader.load(getClass().getResource("../../ManageEvent.fxml"));
        Stage stage = (Stage) viewDetails.getScene().getWindow();
        stage.close();
    }


    public void populateTableView(String id) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colQualification.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));



        try {
            informationTable.setItems(new ViewAssignedVolunteersModel().getTableRecords(id));
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }
}
