package EventDetails.ManageEvent.ViewAssignees.Doctors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class ViewAssignedDoctorsController {
    @FXML
    private AnchorPane viewDetails;

    @FXML
    private TableView<AssignedDoctorOfAnEvent> informationTable;

    @FXML
    private TableColumn<AssignedDoctorOfAnEvent,String> colId;

    @FXML
    private TableColumn<AssignedDoctorOfAnEvent,String> colName;

    @FXML
    private TableColumn<AssignedDoctorOfAnEvent,String> colSpeciality;

    @FXML
    private TableColumn<AssignedDoctorOfAnEvent,String> colEmail;

    @FXML
    private TableColumn<AssignedDoctorOfAnEvent,String> colContactNo;

    @FXML
    private TableColumn<AssignedDoctorOfAnEvent,String> colQualification;

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
        colQualification.setCellValueFactory(new PropertyValueFactory<>("qualification"));
        colSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        try {
            informationTable.setItems(new ViewAssignedDoctorsModel().getTableRecords(id));
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }
}
