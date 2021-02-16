package Utilities;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

public class ShowAlertDialogue {

    public void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public int confirmationBox(String confirmMsg, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(confirmMsg);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        Optional<ButtonType> ans = alert.showAndWait();
        if (ans.get() == ButtonType.OK) {
            return 1;
        } else return 0;
    }
}
