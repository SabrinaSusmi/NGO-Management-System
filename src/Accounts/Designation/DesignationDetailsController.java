package Accounts.Designation;

import Person.DoctorDetails.ViewDoctorInformation.ViewDoctorInformationController;
import Person.PersonalInformation;
import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DesignationDetailsController implements Initializable {

    @FXML
    private AnchorPane designationDetailsAnchorPane;

    @FXML
    private Pane designationDetailsPane;

    @FXML
    private TableView<Designation> designationTable;

    @FXML
    private TableColumn<Designation, String> colDesigID;

    @FXML
    private TableColumn<Designation, String> colDesigType;

    @FXML
    private TableColumn<Designation, String> colDesigName;

    @FXML
    private TableColumn<Designation, Integer> colDesigSalary;
    @FXML
    private TableColumn<Designation, Integer> colEmpCount;

    @FXML
    private JFXTextField searchDesigTextField;

    @FXML
    private JFXTextField desigNameTextField;

    @FXML
    private JFXTextField desigSalaryTextField;

    @FXML
    private JFXComboBox desigTypeComboBox;

    private ObservableList<String> desigTypeList = FXCollections.observableArrayList("Doctor", "Employee");

    DesignationModel designationModel = new DesignationModel();

    @FXML
    void desigConfirmBtn() throws InvocationTargetException {
        if (setActionType()) {
            new ShowAlertDialogue().infoBox("Designation Added Successful!", null, "Add Designation");
            populateTableView();
            desigNameTextField.setText("");
            desigSalaryTextField.setText("");
        } else {
            new ShowAlertDialogue().infoBox("Designation Added Failed!", null, "Add Designation");
        }
    }

    void populateTableView() {
        colDesigID.setCellValueFactory(new PropertyValueFactory<>("desigID"));
        colDesigName.setCellValueFactory(new PropertyValueFactory<>("desigName"));
        colDesigType.setCellValueFactory(new PropertyValueFactory<>("desigType"));
        colDesigSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colEmpCount.setCellValueFactory(new PropertyValueFactory<>("numOfEmployee"));

        try {
            designationTable.setItems(designationModel.getDesignationTableRecords());
        } catch (SQLException throwables) {
            System.out.println("desigController: initialize");
            throwables.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        desigTypeComboBox.setItems(desigTypeList);
        populateTableView();
        viewDetails();
    }

    public String getDesignationID() throws InvocationTargetException {
        try {
            return designationTable.getSelectionModel().getSelectedItem().getDesigID();
        } catch (Exception e) {
        }
        return "0";
    }

    public boolean setActionType() throws InvocationTargetException {

        if (getDesignationID().equalsIgnoreCase("0")) {
            return designationModel.isDesignationAddedSuccessful(String.valueOf(desigTypeComboBox.getValue()),
                    desigNameTextField.getText(), Integer.parseInt(desigSalaryTextField.getText()));
        } else return designationModel.isDesignationUpdateSuccessful(String.valueOf(desigTypeComboBox.getValue()),
                desigNameTextField.getText(), Integer.parseInt(desigSalaryTextField.getText()), getDesignationID());
    }

    private void viewDetails() {
        designationTable.setRowFactory(tv -> {
            TableRow<Designation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    try {
                        displayInformation(getDesignationID());
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });

    }

    private void displayInformation(String id) {

        String[] list = new String[4];
        String[] info = designationModel.showDesignationDetails(list, id);
        desigTypeComboBox.setValue(info[2]);
        desigNameTextField.setText(info[1]);
        desigSalaryTextField.setText(info[3]);

    }

    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Accounts.fxml"));
        designationDetailsPane.getChildren().setAll(pane);

    }

}

