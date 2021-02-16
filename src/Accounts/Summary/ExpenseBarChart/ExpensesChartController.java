package Accounts.Summary.ExpenseBarChart;


import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;


public class ExpensesChartController {
    @FXML
    private BarChart<String, Double> ExpensesChart;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private TextField monthNumber;
    @FXML
    private TextField yearNumber;
    @FXML
    private JFXTextField totalExpensesTextfield;
    @FXML
    private TableView<Expense> ExpenseSummaryTable;

    @FXML
    private TableColumn<Expense, String> colID;

    @FXML
    private TableColumn<Expense, String> colType;

    @FXML
    private TableColumn<Expense, String> colTitle;

    @FXML
    private TableColumn<Expense, String> colDate;

    @FXML
    private TableColumn<Expense, Integer> colBudget;

    ExpensesChartModel model = new ExpensesChartModel();

    public void initialize() {
        XYChart.Series<String, Double> set1 = new XYChart.Series<>();
        model.setTheBarChart(set1);
        ExpensesChart.getData().add(set1);
    }

    public void monthOnEnter() {
        totalExpense();
        setTableViewValue();
        XYChart.Series<String, Double> set1 = new XYChart.Series<>();
        ExpensesChart.getData().clear();
        ExpensesChart.layout();
        int y = Integer.parseInt(yearNumber.getText());
        int m = Integer.parseInt(monthNumber.getText());
        model.setChartAccordingToInput(set1, m, y);
        ExpensesChart.getData().add(set1);
        try {
            ExpenseSummaryTable.setItems(model.getExpenseSummaryTableRecords(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText())));

        } catch (SQLException throwables) {
            System.out.println("donationController: initialize");
            throwables.printStackTrace();
        }
    }
    private void setTableViewValue() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colBudget.setCellValueFactory(new PropertyValueFactory<>("cost"));
    }

    public void totalExpense() {
        totalExpensesTextfield.setText(Integer.toString(new ExpensesChartModel().getTotalExpense(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
    }
}