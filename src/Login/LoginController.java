package Login;

import Main.Main;
import Registration.RegisterController;
import Utilities.ShowAlertDialogue;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;


public class LoginController {
    @FXML
    private AnchorPane loginPane;
    @FXML
    private Circle Logo;
    @FXML
    private TextField usernameTextfield, passwordTextfield;

    RegisterController registerController = new RegisterController();

    public String getUsernameTextfield() {
        return usernameTextfield.getText().trim();
    }

    public String getPasswordTextfield() {
        System.out.println(registerController.encryptPassword(passwordTextfield.getText()));
        return registerController.encryptPassword(passwordTextfield.getText());
    }

    LoginModel loginModel = new LoginModel();


    ShowAlertDialogue alert = new ShowAlertDialogue();

    public void initialize() {
        if (!Main.isSplashLoaded) {
            try {
                loadSplash();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Logo.setStroke(Color.FLORALWHITE);
        Image image = new Image("images/Peace.jpg", false);
        Logo.setFill(new ImagePattern(image));
        Logo.setEffect(new DropShadow(+20d, 0d, 2d, Color.CRIMSON));
    }

    void loadSplash() throws IOException {
        try {
            Main.isSplashLoaded = true;
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../Main/Splash.fxml"));
            loginPane.getChildren().setAll(pane);

            FadeTransition fade = new FadeTransition(Duration.seconds(3), pane);
            fade.setFromValue(0.3);
            fade.setToValue(1);
            fade.setCycleCount(1);

            fade.play();
            fade.setOnFinished(event -> {
                try {
                    AnchorPane lPane = FXMLLoader.load(getClass().getResource("../Login/login.fxml"));
                    loginPane.getChildren().setAll(lPane);
                } catch (IOException e) {
                    System.out.println("Splash Screen to login");
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            System.out.println("Splash Screen");
            e.printStackTrace();
        }
    }


    @FXML
    public void handleLogin() throws IOException {


        if (usernameTextfield.getText().isEmpty() || passwordTextfield.getText().isEmpty()) {

            alert.infoBox("Please enter your username or password", null, "Form Error!");

            return;
        }

        if (loginModel.isLoginSuccessful(getUsernameTextfield(), getPasswordTextfield())) {
            AnchorPane EmployeeDetailsPane = FXMLLoader.load(getClass().getResource("../Main/Dashboard.fxml"));
            loginPane.getChildren().setAll(EmployeeDetailsPane);
        } else {
            alert.infoBox("Please enter correct username and Password", null, "Login");
            usernameTextfield.setText("");
            passwordTextfield.setText("");
            usernameTextfield.requestFocus();
        }
    }


    public void handleChangePassword() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("ChangePassword/ChangePassword.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("ChangePassword");
        stage.setScene(new Scene(pane));
        stage.show();
    }

    public void handleSignUp() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../Registration/register.fxml"));
        loginPane.getChildren().setAll(pane);

    }
}
