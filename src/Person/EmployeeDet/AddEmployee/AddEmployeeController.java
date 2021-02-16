package Person.EmployeeDet.AddEmployee;

import Person.EmployeeDet.EmployeeDetailsModel;
import Person.ImportPersonnelFile;
import Person.PersonalInformation;
import Person.Validation;
import Registration.RegisterController;
import Utilities.ShowAlertDialogue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddEmployeeController {
    @FXML
    private AnchorPane AddEmployeePane;

    @FXML
    private TextField employeeName;

    @FXML
    private TextField employeeEmailID;

    @FXML
    private TextField employeePhoneNumber;

    @FXML
    private DatePicker employeeDOB;

    @FXML
    private ComboBox employeeDesignation;

    @FXML
    private ComboBox employeeGender;

    @FXML
    private TextField employeeAddress;


    @FXML
    private PasswordField employeePassword;

    private ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Others");
    private ObservableList<String> designation = FXCollections.observableArrayList();
    RegisterController registerController = new RegisterController();
    public String getPasswordTextfield() {
        System.out.println(registerController.encryptPassword(employeePassword.getText()));
        return registerController.encryptPassword(employeePassword.getText());
    }

    @FXML
    void handleConfirmButton() throws ParseException {
        Date dobDate = new SimpleDateFormat("MM/dd/yyyy").parse(employeeDOB.getEditor().getText());
        if (new Validation().checkEmailAndPhoneValidation(employeeEmailID.getText(), employeePhoneNumber.getText())) {
            if (new AddEmployeeModel().isAddEmployeeSuccessful(employeeName.getText(), dobDate, employeeGender.getSelectionModel().getSelectedItem().toString(),
                    employeeAddress.getText(), employeePhoneNumber.getText(), employeeDesignation.getSelectionModel().getSelectedItem().toString(),
                    employeeEmailID.getText(), getPasswordTextfield())) {
                new ShowAlertDialogue().infoBox("Employee Add Successful!", null, "Add Employee");
                refreshTextField();
            }
        } else {
            new ShowAlertDialogue().infoBox("Insert Valid Email or Phone Number", null, "Add Employee");
        }
    }


    private void refreshTextField() {
        employeeDOB.getEditor().setText("");
        employeeAddress.setText("");
        employeePhoneNumber.setText("");
        employeeEmailID.setText("");
        employeePassword.setText("");
        employeeName.setText("");
    }

    public void initialize() {

        employeeGender.setItems(gender);
        new EmployeeDetailsModel().getDesignation(designation);
        employeeDesignation.setItems(designation);


    }

    @FXML
    void handleImportFileButton() throws Exception{

        if (new AddEmployeeModel().addEmployee()) {
            new ShowAlertDialogue().infoBox("Employee Added Successfully", null, "employee add");
        } else new ShowAlertDialogue().infoBox("Select Correct File", null, "employee add");

    }

    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../employeeDetails.fxml"));
        AddEmployeePane.getChildren().setAll(pane);
    }


}
