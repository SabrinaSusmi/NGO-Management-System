package Main;

import Registration.RegisterModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static boolean isSplashLoaded = false;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root;

        root = FXMLLoader.load(getClass().getResource("../Login/login.fxml"));

        primaryStage.setTitle("NGO Management System");
        //primaryStage.setMaximized(true);
        primaryStage.setScene(new Scene(root,1800,900));
        primaryStage.setFullScreen(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}