package chat.users;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable
{
    @FXML private Button homeIcon;
    @FXML private Button chatIcon;
    @FXML private Button groupIcon;
    @FXML private Button settingsIcon;
    private BackgroundImage bgImage;
    private Background bg;
    private User activeUser;

    public MainViewController(User activeUser)
    {
        this.activeUser = activeUser;
        System.out.println(activeUser.getFirstName());
    }

    public MainViewController()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Image homeImage = new Image("file:src/main/Icons/homeIcon.png");
        bgImage = new BackgroundImage(
                homeImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        bg = new Background(bgImage);
        homeIcon.setBackground(bg);

        Image chatImage = new Image("file:src/main/Icons/chatIcon.png");
        bgImage = new BackgroundImage(
                chatImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        bg = new Background(bgImage);
        chatIcon.setBackground(bg);

        Image groupImage = new Image("file:src/main/Icons/groupIcon.png");
        bgImage = new BackgroundImage(
                groupImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        bg = new Background(bgImage);
        groupIcon.setBackground(bg);

        Image settingsImage = new Image("file:src/main/Icons/settingsIcon.png");
        bgImage = new BackgroundImage(
                settingsImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, true, true, true)
        );
        bg = new Background(bgImage);
        settingsIcon.setBackground(bg);
    }
}
