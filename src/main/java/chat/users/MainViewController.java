package chat.users;

import chat.View.ChatMainController;
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
import chat.users.*;
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
    @FXML private TextField inputField;
    private ChatMainController chatMainController;
    public MainViewController(User activeUser)
    {
        this.activeUser = activeUser;
        //username.setText(activeUser.getFirstName());
        //role.setText(activeUser.getRole().getRoleName());
    }

    public MainViewController()
    {

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
        // Initialize search text field
        searchTextfield.setPromptText("Search");
        searchTextfield.setFocusTraversable(false);
        searchTextfield.setStyle("-fx-text-fill: white;");

        // Attach click handler to sendIcon
        sendIcon.setOnMouseClicked(event -> chatMainController.clickSetIcon());
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public ImageView getChatIcon() {
        return chatIcon;
    }

    public void setChatIcon(ImageView chatIcon) {
        this.chatIcon = chatIcon;
    }

    public ImageView getHomeIcon() {
        return homeIcon;
    }

    public void setHomeIcon(ImageView homeIcon) {
        this.homeIcon = homeIcon;
    }

    public ImageView getGroupIcon() {
        return groupIcon;
    }

    public void setGroupIcon(ImageView groupIcon) {
        this.groupIcon = groupIcon;
    }

    public ImageView getSettingsIcon() {
        return settingsIcon;
    }

    public void setSettingsIcon(ImageView settingsIcon) {
        this.settingsIcon = settingsIcon;
    }

    public ImageView getSearchingIcon() {
        return searchingIcon;
    }

    public void setSearchingIcon(ImageView searchingIcon) {
        this.searchingIcon = searchingIcon;
    }

    public ImageView getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(ImageView profileIcon) {
        this.profileIcon = profileIcon;
    }

    public ImageView getTelephoneIcon() {
        return telephoneIcon;
    }

    public void setTelephoneIcon(ImageView telephoneIcon) {
        this.telephoneIcon = telephoneIcon;
    }

    public ImageView getKameraIcon() {
        return kameraIcon;
    }

    public void setKameraIcon(ImageView kameraIcon) {
        this.kameraIcon = kameraIcon;
    }

    public ImageView getUserSettingsIcon() {
        return userSettingsIcon;
    }

    public void setUserSettingsIcon(ImageView userSettingsIcon) {
        this.userSettingsIcon = userSettingsIcon;
    }

    public ImageView getEmojiIcon() {
        return emojiIcon;
    }

    public void setEmojiIcon(ImageView emojiIcon) {
        this.emojiIcon = emojiIcon;
    }

    public ImageView getAttachmentIcon() {
        return attachmentIcon;
    }

    public void setAttachmentIcon(ImageView attachmentIcon) {
        this.attachmentIcon = attachmentIcon;
    }

    public ImageView getSendIcon() {
        return sendIcon;
    }

    public void setSendIcon(ImageView sendIcon) {
        this.sendIcon = sendIcon;
    }

    public AnchorPane getPane() {
        return pane;
    }

    public void setPane(AnchorPane pane) {
        this.pane = pane;
    }

    public TextField getSearchTextfield() {
        return searchTextfield;
    }

    public void setSearchTextfield(TextField searchTextfield) {
        this.searchTextfield = searchTextfield;
    }

    public Label getUsername() {
        return username;
    }

    public void setUsername(Label username) {
        this.username = username;
    }

    public Label getStatus() {
        return status;
    }

    public void setStatus(Label status) {
        this.status = status;
    }

    public Label getRole() {
        return role;
    }

    public void setRole(Label role) {
        this.role = role;
    }

    public Circle getCircleStatus() {
        return circleStatus;
    }

    public void setCircleStatus(Circle circleStatus) {
        this.circleStatus = circleStatus;
    }

    public TextField getInputField() {
        return inputField;
    }

    public void setInputField(TextField inputField) {
        this.inputField = inputField;
    }

}
