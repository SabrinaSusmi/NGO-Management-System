package EventDetails.EventHistory;

import EventDetails.EventHistory.EventSupply.Food.ViewAssignedFoodController;
import EventDetails.EventHistory.EventSupply.Health.ViewAssignedMedController;
import EventDetails.ManageEvent.ViewAssignees.Doctors.ViewAssignedDoctorsController;
import EventDetails.ManageEvent.ViewAssignees.Employee.ViewAssignedEmployeesController;
import EventDetails.ManageEvent.ViewAssignees.Volunteers.ViewAssignedVolunteersController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.SQLException;

public class EventHistoryController {

    @FXML
    private Pane eventDetailsPane;

    @FXML
    private TableView<History> informationTable;

    @FXML
    private TableColumn<History, String> colID;

    @FXML
    private TableColumn<History, String> colName;

    @FXML
    private TableColumn<History, String> colDate;

    @FXML
    private TableColumn<History, Integer> colActualBudget;

    @FXML
    private TableColumn<History, Integer> colEstBudget;

    @FXML
    private TableColumn colEmployee;

    @FXML
    private TableColumn colDoctor;

    @FXML
    private TableColumn colvolunteer;

    @FXML
    private TableColumn colSupplies;

    @FXML
    private TextField monthNumber;
    @FXML
    private AnchorPane viewDetails;

    @FXML
    private FontAwesomeIconView searchIcon;


    public void IDOnEnter() {
        populateTableView();
        try {
            informationTable.setItems(new EventHistoryModel().getPastTableRecords(Integer.parseInt(monthNumber.getText())));
        } catch (SQLException throwables) {
            System.out.println("foodController: initialize");
        }
    }

    public void handleBackButton() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Events.fxml"));
        viewDetails.getChildren().setAll(pane);

    }

    public void initialize() {
        monthNumber.getStyleClass().clear();
        searchIcon.getStyleClass().clear();
        populateTableView();
    }

    private void populateTableView() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        colActualBudget.setCellValueFactory(new PropertyValueFactory<>("actBudget"));
        colEstBudget.setCellValueFactory(new PropertyValueFactory<>("estBudget"));
        Callback<TableColumn<History, String>, TableCell<History, String>> cellFactory1 = (param) -> {
//tablecell button
            final TableCell<History, String> cell = new TableCell<History, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {
                        final Button details = new Button("view");
                        details.getStyleClass().clear();
                        details.getStyleClass().add("second-button");
                        details.setOnAction(event -> {
                            History h = getTableView().getItems().get(getIndex());
                            //write code
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ManageEvent/ViewAssignees/Employee/viewAssignedEmployees.fxml"));
                            AnchorPane pane = null;
                            try {
                                pane = loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ViewAssignedEmployeesController view = loader.getController();
                            view.populateTableView(h.getId());
                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("Assigned Employees");
                            stage.setScene(new Scene(pane));
                            stage.show();
                        });
                        setGraphic(details);
                    }
                    setText(null);
                }


            };

            return cell;
        };
        colEmployee.setCellFactory(cellFactory1);
        Callback<TableColumn<History, String>, TableCell<History, String>> cellFactory2 = (param) -> {
//tablecell button
            final TableCell<History, String> cell = new TableCell<History, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {
                        final Button details = new Button("view");
                        details.getStyleClass().clear();
                        details.getStyleClass().add("second-button");
                        details.setOnAction(event -> {
                            History h = getTableView().getItems().get(getIndex());
                            //write code
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ManageEvent/ViewAssignees/Volunteers/viewAssignedVolunteers.fxml"));
                            AnchorPane pane = null;
                            try {
                                pane = loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ViewAssignedVolunteersController view = loader.getController();
                            view.populateTableView(h.getId());
                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("Assigned Volunteers");
                            stage.setScene(new Scene(pane));
                            stage.show();
                        });
                        setGraphic(details);
                    }
                    setText(null);
                }


            };

            return cell;
        };
        colvolunteer.setCellFactory(cellFactory2);
        Callback<TableColumn<History, String>, TableCell<History, String>> cellFactory3 = (param) -> {
//tablecell button
            final TableCell<History, String> cell = new TableCell<History, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                    } else {
                        final Button details = new Button("view");
                        details.getStyleClass().clear();
                        details.getStyleClass().add("second-button");
                        details.setOnAction(event -> {
                            History h = getTableView().getItems().get(getIndex());
                            //write code
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ManageEvent/ViewAssignees/Doctors/ViewAssignedDoctors.fxml"));
                            AnchorPane pane = null;
                            try {
                                pane = loader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            ViewAssignedDoctorsController view = loader.getController();
                            view.populateTableView(h.getId());
                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("Assigned Doctors");
                            stage.setScene(new Scene(pane));
                            stage.show();
                        });
                        setGraphic(details);
                    }
                    setText(null);
                }


            };

            return cell;
        };
        colDoctor.setCellFactory(cellFactory3);
        Callback<TableColumn<History, String>, TableCell<History, String>> cellFactory4 = (param) -> {
//tablecell button
            final TableCell<History, String> cell = new TableCell<History, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //cell is created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        final Button details = new Button("view");
                        details.getStyleClass().clear();
                        details.getStyleClass().add("second-button");
                        details.setOnAction(event -> {
                            History h = getTableView().getItems().get(getIndex());
                            //write code
                            AnchorPane pane = null;
                            if (h.getId().contains("F")) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("EventSupply/Food/viewAssignedFood.fxml"));
                                try {
                                    pane = loader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                ViewAssignedFoodController view = loader.getController();
                                view.populateTableView(h.getId());
                            } else if (h.getId().contains("H")) {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("EventSupply/Health/viewAssignedMed.fxml"));
                                try {
                                    pane = loader.load();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                ViewAssignedMedController view = loader.getController();
                                view.populateTableView(h.getId());
                            }
                            Stage stage = new Stage(StageStyle.DECORATED);
                            stage.setTitle("Upcoming Event Details");
                            stage.setScene(new Scene(pane));
                            stage.show();
                        });
                        setGraphic(details);
                        setText(null);
                    }
                }


            };

            return cell;
        };
        colSupplies.setCellFactory(cellFactory4);

        try {
            informationTable.setItems(new EventHistoryModel().getTableRecords());
        } catch (SQLException throwables) {
            System.out.println("employeeDetailsController: initialize");
            throwables.printStackTrace();
        }
    }

}
