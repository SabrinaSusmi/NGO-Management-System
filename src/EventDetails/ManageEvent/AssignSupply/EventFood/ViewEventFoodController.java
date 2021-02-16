package EventDetails.ManageEvent.AssignSupply.EventFood;

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


public class ViewEventFoodController {
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
       // colRequiredQty.setCellValueFactory(new PropertyValueFactory<>("reqQty"));


        //problem
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
                            ViewEventFoodModel view=new ViewEventFoodModel();
                            details.getStyleClass().clear();
                            details.getStyleClass().add("second-text-field");
                         //   System.out.println(new ViewEventFoodModel().getRequiredAmount());
                            view.setId(id);

//                            details.setText(new ViewEventFoodModel().getRequiredAmount());

                            details.setOnAction(event -> {
                                EventSupply h = getTableView().getItems().get(getIndex());
                                //write code
                                details.setEditable(true);
                                details.setText(details.getText());
                               view.isAmountAddSuccessful(Integer.parseInt(details.getText()), id, h.getName());
                                System.out.println("ppp");
                            });
                            setGraphic(details);

                        }
                        setText(null);
                    }


                };

                return cell;
            };
            colRequiredQty.setCellFactory(cellFactory1);

      /* colRequiredQty.setCellFactory(TextFieldTableCell.forTableColumn());
       colRequiredQty.setOnEditCommit(
               new EventHandler<TableColumn.CellEditEvent<EventFood, String>>() {
                   @Override
                   public void handle(TableColumn.CellEditEvent<EventFood, String> event) {
                       ((EventFood) event.getTableView().getItems().get(event.getTablePosition().getRow())).setReqQty(Integer.parseInt(event.getNewValue()));
                   }
    });
       informationTable.setEditable(true);*/

        }


        try {
            informationTable.setItems(new ViewEventFoodModel().getTableRecords(id));
        } catch (SQLException throwables) {
            System.out.println("ViewEventFoodController: initialize");
            throwables.printStackTrace();
        }
    }


}
