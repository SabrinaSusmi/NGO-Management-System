package Person.EmployeeDet.UpdateEmployee;

import Person.EmployeeDet.EmployeeDetailsModel;
import Person.Validation;
import Utilities.ShowAlertDialogue;
import Person.PersonalInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class UpdateEmployeeController implements Initializable {
    @FXML
    public TextField employeeID;
    @FXML
    public TextField employeeName;
    @FXML
    public TextField employeeEmailID;

    @FXML
    public DatePicker employeeDOB;
    @FXML
    public TextField employeePhoneNumber;
    @FXML
    public ComboBox employeeGender;
    @FXML
    public ComboBox employeeDesignation;
    @FXML
    public TextField employeeAddress;
    @FXML
    private Pane updateEmployeePane;
    UpdateEmployeeModel updateEmployeeModel = new UpdateEmployeeModel();

    private final ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female", "Others");
    private final ObservableList<String> designation = FXCollections.observableArrayList();

    @FXML
    private void handleConfirmButton() throws ParseException {
        Date dobDate = new SimpleDateFormat("yyyy-dd-MM").parse(employeeDOB.getEditor().getText());
        if (new Validation().checkEmailAndPhoneValidation(employeeEmailID.getText(), employeePhoneNumber.getText())) {
            if (updateEmployeeModel.isUpdateEmployeeSuccessful(dobDate, employeeGender.getSelectionModel().getSelectedItem().toString(),
                    employeeAddress.getText(), employeePhoneNumber.getText(), employeeDesignation.getSelectionModel().getSelectedItem().toString(),
                    employeeEmailID.getText(),  Integer.parseInt(employeeID.getText()))) {
                new ShowAlertDialogue().infoBox("update Successful!", null, "update Employee");
                refreshTextField();
            }
        } else {
            new ShowAlertDialogue().infoBox("Insert Valid Email or Phone Number", null, "Update Employee");
        }
    }

    private void refreshTextField() {
        employeeDOB.getEditor().setText("");
        employeeAddress.setText("");
        employeePhoneNumber.setText("");
        employeeEmailID.setText("");
        employeeID.setText("");
        employeeName.setText("");
        employeeGender.getEditor().setText("");
        employeeDesignation.getEditor().setText("");
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TextFields.bindAutoCompletion(employeeID, new PersonalInformation().getIDList("select emp_id from employee"));

        employeeGender.setItems(gender);
        new EmployeeDetailsModel().getDesignation(designation);
        employeeDesignation.setItems(designation);


    }

    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../employeeDetails.fxml"));
        updateEmployeePane.getChildren().setAll(pane);
    }

    @FXML
    public void IDOnEnter() {
        String[] list = new String[6];
        String[] info = updateEmployeeModel.setEmployeeInformation(list, Integer.parseInt(employeeID.getText()));
        employeeName.setText(info[0]);
        employeeEmailID.setText(info[1]);
     //   employeePassword.setText(info[1]);
        employeeDOB.getEditor().setText(info[2]);
        employeeGender.getEditor().setText(info[3]);
        employeeAddress.setText(info[4]);
        employeePhoneNumber.setText(info[5]);
    }

}