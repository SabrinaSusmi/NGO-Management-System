package EventDetails.ManageEvent.AssignSupply.EventMed;

import EventDetails.ManageEvent.AssignSupply.EventSupply;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.sql.SQLException;

public class ViewEventMedController {
    @FXML
    private AnchorPane viewDetails;

    @FXML
    private TableView<EventSupply> informationTable;

    @FXML
    private TableColumn<EventSupply, String> colName;

    @FXML
    private TableColumn<EventSupply, Integer> colAvailableQty;

    @FXML
    private TableColumn<EventSupply, String> colRequiredQty;



    @FXML
    void handleBackButton() {
        Stage stage = (Stage) viewDetails.getScene().getWindow();
        stage.close();
    }

    public void populateTableView(String id) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAvailableQty.setCellValueFactory(new PropertyValueFactory<>("availableQty"));
        {
            Callback<TableColumn<EventSupply, String>, TableCell<EventSupply, String>> cellFactory1 = (param) -> {
//tablecell button
                final TableCell<EventSupply, String> cell = new TableCell<EventSupply, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        //cell is created only on non-empty rows
                        if (empty) {
                            setGraphic(null);
                        } else {
                            final TextField details = new TextField();
                            ViewEventMedModel view=new ViewEventMedModel();
                            details.getStyleClass().clear();
                            details.getStyleClass().add("second-text-field");
                            System.out.println(new ViewEventMedModel().getRequiredAmount());
                            view.setId(id);

                            details.setText(new ViewEventMedModel().getRequiredAmount());

                            details.setOnAction(event -> {
                                EventSupply h = getTableView().getItems().get(getIndex());
                                //write code
                                details.setEditable(true);
                                details.setText(details.getText());
                                view.isAmountAddSuccessful(Integer.parseInt(details.getText()), id, h.getName());
                            });
                            setGraphic(details);

                        }
                        setText(null);
                    }
                };

                return cell;
            };
            colRequiredQty.setCellFactory(cellFactory1);

        }

        try {
            informationTable.setItems(new ViewEventMedModel().getTableRecords(id));
        } catch (SQLException throwables) {
            System.out.println("ViewEventMedController: initialize");
            throwables.printStackTrace();
        }
    }
}
