package Login.ChangePassword;

import Utilities.ShowAlertDialogue;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangePasswordController {
    @FXML
    private AnchorPane pane;
    @FXML
    private JFXTextField email_ID;

    @FXML
    private JFXTextField prev_password;

    @FXML
    private JFXTextField new_password;

    @FXML
    private JFXTextField retype_new_password;

    public String getEmailTextfield() {
        return email_ID.getText().trim();
    }

    public String getPrevPasswordTextfield() {
        return prev_password.getText();
    }

    public String getRetypeNewPasswordTextfield() {
        return retype_new_password.getText();
    }

    public String getNewPasswordTextfield() {
        return new_password.getText();
    }


    @FXML
    void handleChangePassword() throws IOException {
        if (getNewPasswordTextfield().equals(getRetypeNewPasswordTextfield())) {
            if (new ChangePasswordModel().isPasswordChangeSuccessful(getEmailTextfield(), getPrevPasswordTextfield(), getNewPasswordTextfield())) {
                new ShowAlertDialogue().infoBox("Password is Changed!", null, "Change Password");
                handleBackButton();
            } else new ShowAlertDialogue().infoBox("Password doesn't match", null, "Change Password");

        } else
            new ShowAlertDialogue().infoBox("Password Change is Unsuccessful ", null, "Change Password");

    }
    @FXML
    void handleBackButton() throws IOException {
        FXMLLoader.load(getClass().getResource("../Login.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }
}
