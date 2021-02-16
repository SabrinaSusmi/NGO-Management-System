package SupplyDetails.Food.AddFood;

import SupplyDetails.SupplyInformation;
import Utilities.ShowAlertDialogue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddFoodSupplyController {

    @FXML
    private AnchorPane addNewSupplyPane;

    @FXML
    private TextField foodName;

    @FXML
    private TextField foodSupplier;

    @FXML
    private TextField foodQuantity;

    @FXML
    private TextField foodPrice;

    @FXML
    private DatePicker foodPurchaseDate;

    @FXML
    private DatePicker foodExpiryDate;



    @FXML
    void handleConfirmAddFood() throws ParseException {
        Date pDate = new SimpleDateFormat("MM/dd/yyyy").parse(foodPurchaseDate.getEditor().getText());
        Date eDate = new SimpleDateFormat("MM/dd/yyyy").parse(foodExpiryDate.getEditor().getText());
        if (new AddFoodSupplyModel().isAddFoodSuccessful(foodName.getText(),pDate,eDate,
                Integer.parseInt(foodQuantity.getText()),Integer.parseInt(foodPrice.getText()),foodSupplier.getText())){
            new ShowAlertDialogue().infoBox("Food Item Add Successful!", null, "Add Food Supply");
            refreshTextField();

        }

    }
    private void refreshTextField() {
        foodExpiryDate.getEditor().setText("");
        foodPurchaseDate.getEditor().setText("");
        foodName.setText("");
        foodPrice.setText("");
        foodQuantity.setText("");
        foodSupplier.setText("");

    }
    public void initialize() {
        TextFields.bindAutoCompletion(foodName, new SupplyInformation().getItemNameList("select distinct name from Food"));
        System.out.println("ll");
    }

    public void handleBackFood() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../food.fxml"));
        addNewSupplyPane.getChildren().setAll(pane);
    }
}
