package Accounts.Summary;

import Accounts.Summary.ExpenseBarChart.ExpensesChartModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SummaryController {
    @FXML
    private Pane barChartpane;

    @FXML
    private Pane SummaryPane;


    @FXML
    private TextField monthNumber;

    @FXML
    private TextField yearNumber;


    @FXML
    private JFXTextField netTotal;

    @FXML
    private JFXButton viewSummary;

    @FXML
    private JFXTextField totalDonation;

    @FXML
    private JFXTextField totalExpenses;

    @FXML
    private JFXTextField totalEventCost;

    @FXML
    private JFXTextField totalSupplyCost;

    SummaryModel sm=new SummaryModel();
    public void initialize() {
        monthNumber.requestFocus();
    }
    public void handleSummary() {
        totalDonation.setText(String.valueOf(sm.getTotalDonation(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
        totalEventCost.setText(String.valueOf(sm.getTotalEventCost(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
        totalExpenses.setText(String.valueOf(sm.getTotalExpense(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
        totalSupplyCost.setText(String.valueOf(sm.getTotalSupplyCost(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
        netTotal.setText(String.valueOf(sm.getNetTotal(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
    }

    @FXML
    void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Accounts.fxml"));
        SummaryPane.getChildren().setAll(pane);

    }

    public void handlesalaryChart() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("SalaryBarchart/salarychart.fxml"));
        barChartpane.getChildren().setAll(pane);
    }

    public void handleExpensesChart() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("ExpenseBarchart/expenseschart.fxml"));
        barChartpane.getChildren().setAll(pane);
    }

    public void handleDonationSummary() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("DonationSummary/donationSummary.fxml"));
        barChartpane.getChildren().setAll(pane);
    }


    public void handleEventSummary() throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("EventSummary/EventSummary.fxml"));
        barChartpane.getChildren().setAll(pane);
    }
}
