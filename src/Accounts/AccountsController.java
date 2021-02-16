package Accounts;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

public class AccountsController {
    @FXML
    private Pane AccountsPane;
    @FXML
    private JFXTextField totalEmpTextField;
    @FXML
    private JFXTextField totalDocTextField;
    AccountsModel model = new AccountsModel();

    public void handleAccountsDesig() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Designation/DesignationDetails.fxml"));
        AccountsPane.getChildren().setAll(pane);
    }

    public void handleAccountsExpenses() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Expenses/ExpensesDetails.fxml"));
        AccountsPane.getChildren().setAll(pane);
    }

    public void handleAccountsSummary() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Summary/summary.fxml"));
        AccountsPane.getChildren().setAll(pane);
    }

    public void initialize() {
        totalDocTextField.getStyleClass().clear();
        totalDocTextField.getStyleClass().add("second-text-field");
        totalEmpTextField.getStyleClass().clear();
        totalEmpTextField.getStyleClass().add("second-text-field");
        totalEmpTextField.setText(Integer.toString(model.getTotalEmployees()));
        totalDocTextField.setText(Integer.toString(model.getTotalDoctors()));
    }

}
