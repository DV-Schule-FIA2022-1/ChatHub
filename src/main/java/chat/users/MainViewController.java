package chat.users;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable
{
    private User activeUser;
    @FXML private ImageView chatIcon;
    @FXML private ImageView homeIcon;
    @FXML private ImageView groupIcon;
    @FXML private ImageView settingsIcon;
    @FXML private ImageView searchingIcon;
    @FXML private ImageView profileIcon;
    @FXML private ImageView telephoneIcon;
    @FXML private ImageView kameraIcon;
    @FXML private ImageView userSettingsIcon;
    @FXML private ImageView emojiIcon;
    @FXML private ImageView attachmentIcon;
    @FXML private ImageView sendIcon;
    @FXML private AnchorPane pane;
    @FXML private TextField searchTextfield;

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
        chatIcon.setImage(new Image("file:src/main/resources/img/chatIcon.png"));
        homeIcon.setImage(new Image("file:src/main/resources/img/homeIcon.png"));
        groupIcon.setImage(new Image("file:src/main/resources/img/groupIcon.png"));
        settingsIcon.setImage(new Image("file:src/main/resources/img/settingsIcon.png"));
        searchingIcon.setImage(new Image("file:src/main/resources/img/searchingIcon.png"));
        profileIcon.setImage(new Image("file:src/main/resources/img/profileIcon.png"));
        telephoneIcon.setImage(new Image("file:src/main/resources/img/telephoneIcon.png"));
        kameraIcon.setImage(new Image("file:src/main/resources/img/kameraIcon.png"));
        userSettingsIcon.setImage(new Image("file:src/main/resources/img/settingsPointsIcon.png"));
        emojiIcon.setImage(new Image("file:src/main/resources/img/emojiIcon.png"));
        attachmentIcon.setImage(new Image("file:src/main/resources/img/attachmentIcon.png"));
        sendIcon.setImage(new Image("file:src/main/resources/img/sendIcon.png"));
        searchTextfield.setPromptText("Search");
        searchTextfield.setFocusTraversable(false);
        searchTextfield.setStyle("-fx-text-fill: white;");
    }
}
