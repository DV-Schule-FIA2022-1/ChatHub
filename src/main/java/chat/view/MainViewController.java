package chat.view;

import chat.users.User;
import chat.client.Client;
import chat.users.login.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    @FXML private ImageView sendIcon;
    @FXML private ImageView sendToBot;
    @FXML private TextField searchTextfield;
    @FXML private Label username;
    @Getter
    @FXML private TextField inputField;
    @Getter
    @FXML private VBox messageContainer;
    private ChatMainController chatMainController;
    @Getter
    private Client client;

    public MainViewController(User activeUser)
    {
        this.activeUser = activeUser;
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
        sendIcon.setImage(new Image("file:src/main/resources/img/sendIcon.png"));
        sendToBot.setImage(new Image("file:src/main/resources/img/sendIcon.png"));
        searchTextfield.setPromptText("Search");
        searchTextfield.setFocusTraversable(false);
        searchTextfield.setStyle("-fx-text-fill: white;");

        this.client = LoginController.getNewClient();

        username.setText(client.getUser().getFirstName());
        searchTextfield.setPromptText("Search");
        searchTextfield.setFocusTraversable(false);
        searchTextfield.setStyle("-fx-text-fill: white;");

        // Attach click handler to sendIcon
        sendIcon.setOnMouseClicked(event ->
        {
            try
            {
                chatMainController.clickSetIcon();
                TextArea message = new TextArea();
                message.setText(client.getUser().getFirstName() + ": " + chatMainController.getInputText());
                message.setEditable(false);
                message.setWrapText(true);
                message.prefHeightProperty().bind(message.heightProperty());
                messageContainer.getChildren().add(message);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        });
        sendToBot.setOnMouseClicked(event ->
        {
            chatMainController.sendToBot();
            TextArea message = new TextArea();
            message.setText("bot: " + chatMainController.getChatResponse());
            message.setEditable(false);
            message.setWrapText(true);
            message.prefHeightProperty().bind(message.heightProperty());
            messageContainer.getChildren().add(message);
        });

    }




}
