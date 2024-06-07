package chat.Notification;

import javafx.beans.property.ListProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
public class NotificationController implements Initializable
{
    @FXML private TextField textboxMessage;
    @FXML private Button buttonSend;

    @FXML private ListView listboxReceived;

    private ArrayList<String> messagelist;
    private NotificationMain notification;
    private String message;

    private ListProperty<String> listProperty = new SimpleListProperty<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        notification = new NotificationMain();

        messagelist = new ArrayList<>();
        messagelist.add("hi");
        messagelist.add("hi2");
        System.out.println(messagelist.toString());

        listProperty.set(FXCollections.observableArrayList(messagelist));
        listboxReceived.itemsProperty().bind(listProperty);
    }
    public void sendMessage()
    {
        messagelist.add(textboxMessage.getText().toString());
        listProperty.set(FXCollections.observableArrayList(messagelist));


    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
        sendMessage();
    }
    public TextField getTextboxMessage()
    {
        return textboxMessage;
    }

    public ListView getListboxReceived()
    {
        return listboxReceived;
    }
    public ArrayList<String> getMessagelist()
    {
        return messagelist;
    }

}
