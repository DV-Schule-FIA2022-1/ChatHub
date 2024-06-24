package chat.view;

import chat.client.Client;
import chat.users.User;
import chat.users.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import lombok.Getter;

import java.io.IOException;
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
    @FXML private ImageView sendIcon;
    @FXML private ImageView sendToBot;
    @FXML private TextField searchTextfield;
    @FXML private Label username;
    @Getter @FXML private TextField inputField;
    @Getter @FXML private VBox messageContainer;
    @Getter
    private ChatMainController chatMainController;
    private LoginController loginController;
    @Getter
    private Client client;

    public MainViewController(User activeUser, LoginController loginController)
    {
        this.activeUser = activeUser;
        this.loginController = loginController;
    }

    public MainViewController() {}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        chatMainController = new ChatMainController(this);

        // Load images for icons
        chatIcon.setImage(new Image("file:src/main/resources/img/chatIcon.png"));
        homeIcon.setImage(new Image("file:src/main/resources/img/homeIcon.png"));
        groupIcon.setImage(new Image("file:src/main/resources/img/groupIcon.png"));
        settingsIcon.setImage(new Image("file:src/main/resources/img/settingsIcon.png"));
        searchingIcon.setImage(new Image("file:src/main/resources/img/searchingIcon.png"));
        profileIcon.setImage(new Image("file:src/main/resources/img/profileIcon.png"));
        telephoneIcon.setImage(new Image("file:src/main/resources/img/telephoneIcon.png"));
        kameraIcon.setImage(new Image("file:src/main/resources/img/kameraIcon.png"));
        userSettingsIcon.setImage(new Image("file:src/main/resources/img/settingsPointsIcon.png"));
        sendIcon.setImage(new Image("file:src/main/resources/img/sendIcon.png"));
        sendToBot.setImage(new Image("file:src/main/resources/img/sendIcon.png"));

        searchTextfield.setPromptText("Search");
        searchTextfield.setFocusTraversable(false);
        searchTextfield.setStyle("-fx-text-fill: white;");

        // Initialize client using LoginController
        this.client = LoginController.getNewClient();
        if (client != null && client.getUser() != null)
        {
            username.setText(client.getUser().getFirstName());
        }
        else
        {
            throw new RuntimeException("Client or User is null.");
        }

        // Attach click handler to sendIcon
        sendIcon.setOnMouseClicked(event ->
        {
            try
            {
                chatMainController.clickSetIcon();
            }
            catch (IOException e)
            {
                e.printStackTrace(); // Handle this error more gracefully in production
            }
        });

        // Attach click handler to sendToBot
        sendToBot.setOnMouseClicked(event -> chatMainController.sendToBot());
    }
}
