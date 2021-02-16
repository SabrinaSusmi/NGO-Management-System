package Person.DoctorDetails;


import Person.DoctorDetails.ViewDoctorInformation.ViewDoctorInformationController;
import Person.PersonalInformation;
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

public class DoctorDetailsController {
    @FXML
    public Pane DoctorDetailsPane;
    @FXML
    public AnchorPane DoctorDetailsAnchorPane;
    @FXML
    private TableView<Doctor> DoctorTable;
    @FXML
    private Button addButton;

    @FXML
    private Button updateButton;
    @FXML
    private TableColumn<Doctor, Integer> colDoctorID;

    @FXML
    private TableColumn<Doctor, String> colDoctorName;

    @FXML
    private TableColumn<Doctor, String> colDoctorPhone;

    @FXML
    private TableColumn<Doctor, String> colDoctorEmail;

    @FXML
    private TableColumn<Doctor, String> colDoctorSpeciality;

    @FXML
    private TextField searchDoctorTextField;


    public void handleAddNew() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("AddDoctor/AddDoctor.fxml"));
        DoctorDetailsPane.getChildren().setAll(pane);
    }


    public void handleUpdate() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("UpdateDoctor/UpdateDoctorDetails.fxml"));
        DoctorDetailsPane.getChildren().setAll(pane);
    }

    public void initialize() {
        populateTableView();
        viewDetails();
        if(!(new PersonalInformation().checkDesignation())){
            addButton.setVisible(false);
            updateButton.setVisible(false);
        }
        searchDoctorTextField.getStyleClass().add("search-field");
        searchFilterData(searchDoctorTextField, DoctorTable);
    }

    private void searchFilterData(TextField searchField, TableView<Doctor> table) {
        try {
            FilteredList<Doctor> filteredList = new FilteredList<>(DoctorDetailsModel.getDoctorTableRecords(), b -> true);

            searchField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredList.setPredicate(doctor -> DoctorDetailsModel.isMatchSuccessful(newValue, doctor));
                sortFilteredData(filteredList, table);
            });
        } catch (SQLException throwables) {
            System.out.println("doctorDetailsController : search box");
            throwables.printStackTrace();
        }
    }

    private void sortFilteredData(FilteredList<Doctor> filteredList, TableView<Doctor> doctorTable) {
        SortedList<Doctor> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(doctorTable.comparatorProperty());
        doctorTable.setItems(sortedList);
    }


    private void populateTableView() {
        colDoctorID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDoctorEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDoctorPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colDoctorSpeciality.setCellValueFactory(new PropertyValueFactory<>("speciality"));
        try {
            DoctorTable.setItems(DoctorDetailsModel.getDoctorTableRecords());
        } catch (SQLException throwables) {
            System.out.println("doctorDetailsController: initialize");
            throwables.printStackTrace();
        }
    }

    private void viewDetails() {
        DoctorTable.setRowFactory(tv -> {
            TableRow<Doctor> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("ViewDoctorInformation/ViewDoctorInformation.fxml"));
                        AnchorPane pane = loader.load();
                        ViewDoctorInformationController view=loader.getController();
                        view.displayInformation(getDoctorID());
                        Stage stage = new Stage(StageStyle.DECORATED);
                        stage.setTitle("Doctor Details");
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




    public void handleRemoveDoctorOption() {
        Doctor removeSelectedDoctor=DoctorTable.getSelectionModel().getSelectedItem();
        if(removeSelectedDoctor==null){
            new ShowAlertDialogue().infoBox("no Doctor is selected",null,"Remove an Doctor" );
            return;
        }
        int ans = new ShowAlertDialogue().confirmationBox("Do you want to remove this Doctor?", null, "remove Doctor");
        if (ans == 1) {
            String doctorId =(getDoctorID());

            DoctorTable.getItems().removeAll(DoctorTable.getSelectionModel().getSelectedItem());
            if (new DoctorDetailsModel().isDeleteDoctorSuccessful(doctorId)) {
                new ShowAlertDialogue().infoBox("delete Successful!", null, "delete Doctor" );
            }
            else {
                new ShowAlertDialogue().infoBox("Delete Failed!", null, "delete Doctor" );

            }
        }
        else {
            new ShowAlertDialogue().infoBox("cancel",null,"Remove a Doctor" );

        }
    }

    public String getDoctorID() {
        return DoctorTable.getSelectionModel().getSelectedItem().id;
    }
}
