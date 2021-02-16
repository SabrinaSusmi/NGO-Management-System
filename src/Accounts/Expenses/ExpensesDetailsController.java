package Accounts.Expenses;


import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

public class ExpensesDetailsController implements Initializable
{

    @FXML
    private Pane expensesDetailsPane;

    @FXML
    private TableView<Expenses> expensesTable;

    @FXML
    private TableColumn<Expenses, String> colExpID;

    @FXML
    private TableColumn<Expenses, String> colExpType;

    @FXML
    private TableColumn<Expenses, String> colExpName;
    @FXML
    private TableColumn<Expenses, String> colExpDescription;
    @FXML
    private TableColumn<Expenses, Integer> colExpAmount;
    @FXML
    private TableColumn<Expenses, Date> colExpDate;

    @FXML
    private JFXTextField searchExpTextField;

    @FXML
    private JFXTextField expNameTextField;

    @FXML
    private JFXTextField expDescriptionTextField1;
    @FXML
    private JFXTextField expAmountTextField;
    @FXML
    private DatePicker expDate;
    @FXML
    private JFXComboBox expTypeComboBox;

    private ObservableList<String> expTypeList = FXCollections.observableArrayList("Utility","Salary","Others");

    ExpensesModel expModel = new ExpensesModel();

    @FXML
    void expConfirmBtn() throws InvocationTargetException, ParseException {
        if (setActionType()) {
            new ShowAlertDialogue().infoBox("Expense Added Successfully!", null, "Add Expense Details");
            populateTableView();
            expTypeComboBox.setValue("");
            expNameTextField.setText("");
            expAmountTextField.setText("");
            expDescriptionTextField1.setText("");
            expDate.getEditor().setText("");
        } else {
            new ShowAlertDialogue().infoBox("Expense Adding Failed!", null, "Add Expense Details");
        }
    }

    private void populateTableView() {
        colExpID.setCellValueFactory(new PropertyValueFactory<>("expId"));
        colExpType.setCellValueFactory(new PropertyValueFactory<>("expType"));
        colExpName.setCellValueFactory(new PropertyValueFactory<>("expTitle"));
        colExpDescription.setCellValueFactory(new PropertyValueFactory<>("expDescription"));
        colExpAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colExpDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        try {
            expensesTable.setItems(expModel.getExpensesTableRecords());
        } catch (SQLException throwables) {
            System.out.println("expController: initialize");
            throwables.printStackTrace();
        }
    }
   @Override
    public void initialize(URL location, ResourceBundle resources) {
       expTypeComboBox.setItems(expTypeList);
        populateTableView();
        viewDetails();
    }

    public String getExpID() throws InvocationTargetException {
        try{return expensesTable.getSelectionModel().getSelectedItem().getExpId();
        } catch (Exception e) {
            System.out.println("expid");        }
        return "0";
    }

    public boolean setActionType() throws InvocationTargetException, ParseException {
        if (getExpID().equalsIgnoreCase("0")) {
            Date eDate = new SimpleDateFormat("MM/dd/yyyy").parse(expDate.getEditor().getText());

            return expModel.isExpensesAddedSuccessful(String.valueOf(expTypeComboBox.getValue()),
                    expNameTextField.getText(), expDescriptionTextField1.getText(), Integer.parseInt(expAmountTextField.getText()),eDate);
        } else{
            DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat targetFormat = new SimpleDateFormat("MM/dd/yyyy");
            Date date = originalFormat.parse(expDate.getEditor().getText());
            String formattedDate = targetFormat.format(date);

            return expModel.isExpenseUpdateSuccessful( getExpID(),String.valueOf(expTypeComboBox.getValue()),
                expNameTextField.getText(), expDescriptionTextField1.getText(), Integer.parseInt(expAmountTextField.getText()),formattedDate);
    }}

    private void viewDetails() {
        expensesTable.setRowFactory(tv -> {
            TableRow<Expenses> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {

                    try {
                        displayInformation(getExpID());
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            });
            return row;
        });

    }

    private void displayInformation(String id) {

        String[] list = new String[5];
        String[] info = expModel.showExpenseDetails(list, id);
        expTypeComboBox.setValue(info[1]);
        expNameTextField.setText(info[2]);
        expDescriptionTextField1.setText(info[3]);
        expAmountTextField.setText(info[4]);
        expDate.getEditor().setText(info[5]);
    }
    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Accounts.fxml"));
        expensesDetailsPane.getChildren().setAll(pane);

    }

    public void handleType() {
        if(expTypeComboBox.getEditor().getText().equalsIgnoreCase("Salary")){
            expAmountTextField.setText(String.valueOf(expModel.getSalary()));
            expAmountTextField.setEditable(false);
        }
    }
}
