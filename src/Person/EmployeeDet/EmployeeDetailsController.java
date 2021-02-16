package Person.EmployeeDet;

import Person.EmployeeDet.ViewEmployeeInformation.ViewEmployeeInformationController;
import Person.PersonalInformation;
import Utilities.ShowAlertDialogue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeDetailsController implements Initializable {

    @FXML
    public Label searchEmployeeLabel;
    @FXML
    private TextField searchEmployeeTextField;
    @FXML
    private AnchorPane employeeDetailsAnchorPane;
    @FXML
    private Pane employeeDetailsPane;
    @FXML
    private TableColumn<Employee, Integer> colEmpID;
    @FXML
    private TableColumn<Employee, String> colEmpName;
    @FXML
    private TableColumn<Employee, String> colEmpEmail;
    @FXML
    private TableColumn<Employee, String> colEmpPhone;
    @FXML
    private TableColumn<Employee, String> colEmpDesignation;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;

    @FXML
    private void handleUpdate() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("UpdateEmployee/updateEmployee.fxml"));
        employeeDetailsPane.getChildren().setAll(pane);
    }


    @FXML
    private void handleAddNew() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddEmployee/AddEmployee.fxml"));
        employeeDetailsPane.getChildren().setAll(pane);


    }


    private void viewDetails() {
        employeeTable.setRowFactory(tv -> {
            TableRow<Employee> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViewEmployeeInformation/ViewEmployeeInformation.fxml"));
                        AnchorPane pane = loader.load();
                        ViewEmployeeInformationController view = loader.getController();
                        view.displayInformation(getEmployeeId());
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Employee Details");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateTableView();
        viewDetails();
        if (!(new PersonalInformation().checkDesignation())) {
            addButton.setVisible(false);
            updateButton.setVisible(false);
        }

        searchEmployeeTextField.getStyleClass().add("search-field");

        searchFilterData(searchEmployeeTextField, employeeTable);

    }

    EmployeeDetailsModel employeeDetailsModel = new EmployeeDetailsModel();

    private void searchFilterData(TextField searchField, TableView<Employee> table) {
        try {
            FilteredList<Employee> filteredList = new FilteredList<>(employeeDetailsModel.getEmployeeTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(employee -> EmployeeDetailsModel.isMatchSuccessful(newValue, employee));
                sortFilteredData(filteredList, table);
            });
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Employee> filteredList, TableView<Employee> employeeTable) {
        SortedList<Employee> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(employeeTable.comparatorProperty());
        employeeTable.setItems(sortedList);
    }


    private void populateTableView() {
        colEmpID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmpEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmpPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colEmpDesignation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        try {
            employeeTable.setItems(employeeDetailsModel.getEmployeeTableRecords());
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }

    @FXML
    private void handleEmployeeRemoveOption() {
        Employee removeSelectedEmployee = employeeTable.getSelectionModel().getSelectedItem();
        if (removeSelectedEmployee == null) {
            new ShowAlertDialogue().infoBox("no employee is selected", null, "Remove an Employee");
            return;
        }
        int ans = new ShowAlertDialogue().confirmationBox("Do you want to remove this Employee?", null, "remove employee");
        if (ans == 1) {
            int employeeId = employeeTable.getSelectionModel().getSelectedItem().getId();

            employeeTable.getItems().removeAll(employeeTable.getSelectionModel().getSelectedItem());
            if (new EmployeeDetailsModel().isDeleteEmployeeSuccessful(employeeId)) {
                new ShowAlertDialogue().infoBox("delete Successful!", null, "delete Employee");
            } else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "delete Employee");

            }
        } else {
            new ShowAlertDialogue().infoBox("cancel", null, "Remove an Employee");

        }
    }

    public int getEmployeeId() {
        return employeeTable.getSelectionModel().getSelectedItem().getId();
    }
}
