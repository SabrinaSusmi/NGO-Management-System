package Main;

import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class SplashController {
    @FXML
    private Circle Logo;
    public void initialize(){
        Logo.setStroke(Color.FLORALWHITE);
        Image image = new Image("images/Peace.jpg",false);
        Logo.setFill(new ImagePattern(image));
        Logo.setEffect(new DropShadow(+30d,0d,3d,Color.CRIMSON));
    }

}
