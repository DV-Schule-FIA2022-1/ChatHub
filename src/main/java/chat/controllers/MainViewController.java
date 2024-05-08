package chat.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable
{
    @FXML private Label homeIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Image background = new Image("file:src/main/Icons/homeIcon.png");
        BackgroundImage bgimage = new BackgroundImage(
                background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        Background bg = new Background(bgimage);
        homeIcon.setBackground(bg);
    }
}
