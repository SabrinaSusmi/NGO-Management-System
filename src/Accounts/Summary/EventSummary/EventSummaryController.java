package Accounts.Summary.EventSummary;

import Accounts.Summary.ExpenseBarChart.ExpensesChartModel;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class EventSummaryController {

    @FXML
    private TextField monthNumber;
    @FXML
    private TextField yearNumber;
    @FXML
    private JFXTextField totalDonationTextfield;

    EventSummaryModel model = new EventSummaryModel();
    @FXML
    private TableView<Event> EventSummaryTable;

    @FXML
    private TableColumn<Event, String> colID;

    @FXML
    private TableColumn<Event, String> colName;

    @FXML
    private TableColumn<Event, String> colDate;

    @FXML
    private TableColumn<Event, Integer> colBudget;


    @FXML
    private BarChart<String, Double> EventChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    public void initialize() {
        XYChart.Series<String, Double> set1 = new XYChart.Series<>();
       model.setTheBarChart(set1);
        EventChart.getData().add(set1);
    }

    public void handleEvent() {
        totalDonation();
        setTableViewValue();
        XYChart.Series<String, Double> set1 = new XYChart.Series<>();
        EventChart.getData().clear();
        EventChart.layout();
        int y = Integer.parseInt(yearNumber.getText());
        int m = Integer.parseInt(monthNumber.getText());
        model.setChartAccordingToInput(set1, m, y);
        EventChart.getData().add(set1);

        try {
            EventSummaryTable.setItems(model.getEventSummaryTableRecords(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText())));

        } catch (SQLException throwables) {
            System.out.println("donationController: initialize");
            throwables.printStackTrace();
        }
    }

    private void setTableViewValue() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colBudget.setCellValueFactory(new PropertyValueFactory<>("budget"));
    }

    public void totalDonation() {
        totalDonationTextfield.setText(Integer.toString(model.getTotalEventCost(Integer.parseInt(monthNumber.getText()), Integer.parseInt(yearNumber.getText()))));
    }
}
