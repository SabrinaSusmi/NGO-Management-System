package EventDetails.ManageEvent.ViewAssignees.Employee;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewAssignedEmployeesController  {

    @FXML
    private AnchorPane viewDetails;
    @FXML
    private TableView<AssignedEmployeeOfAnEvent> informationTable;

    @FXML
    private TableColumn<AssignedEmployeeOfAnEvent, Integer> colId;

    @FXML
    private TableColumn<AssignedEmployeeOfAnEvent, String> colName;

    @FXML
    private TableColumn<AssignedEmployeeOfAnEvent, String> colDesignation;

    @FXML
    private TableColumn<AssignedEmployeeOfAnEvent, String> colEmail;

    @FXML
    private TableColumn<AssignedEmployeeOfAnEvent, String> colContactNo;

    @FXML
    private TableColumn<AssignedEmployeeOfAnEvent, String> colAddress;

    @FXML
    void handleBackButton() throws IOException {
        FXMLLoader.load(getClass().getResource("../../ManageEvent.fxml"));
        Stage stage = (Stage) viewDetails.getScene().getWindow();
        stage.close();
    }


    public void populateTableView(String  id) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colContactNo.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        try {
            informationTable.setItems(new ViewAssignedEmployeesModel().getTableRecords(id));
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }
}
