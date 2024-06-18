package chat.users;

import chat.Chathub;
import chat.View.ChatMainController;
import chat.client.Client;
import chat.mains.MainLogin;
import chat.users.User;
import chat.users.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import chat.*;
import lombok.Getter;

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
    @FXML private Label username;
    @FXML private Label status;
    @FXML private Label role;
    @FXML private Circle circleStatus;
    @Getter
    @FXML private TextField inputField;
    private ChatMainController chatMainController;
    private Client client;

    public MainViewController(User activeUser)
    {
        this.activeUser = activeUser;
        username.setText(activeUser.getFirstName());
        role.setText(activeUser.getRole().getRoleName());
    }

    public MainViewController()
    {

    }

    public void setUser(Client client)
    {
        this.client = client;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        chatMainController = new ChatMainController(this);
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

        this.client = LoginController.getNewClient();

        username.setText(client.getUser().getFirstName());
        //role.setText(client.getUser().getRole().getRoleName());
        // Initialize search text field
        searchTextfield.setPromptText("Search");
        searchTextfield.setFocusTraversable(false);
        searchTextfield.setStyle("-fx-text-fill: white;");

        // Attach click handler to sendIcon
        sendIcon.setOnMouseClicked(event -> chatMainController.clickSetIcon());
    }


}
