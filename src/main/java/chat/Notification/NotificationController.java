package chat.Notification;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NotificationController implements Initializable
{
    @FXML private TextField textboxMessage;
    @FXML private Button buttonSend;
    @FXML private ListView listboxReceived;

    private NotificationMain notification;
    private String message;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        notification = new NotificationMain();
        System.out.println(notification.getMessagelist().toString());
    }
    public void sendMessage()
    {
        listboxReceived.setItems(notification.getMessagelist());
        //listboxReceived.setSelectionModel(notification.getMessagelist().addAll());

       // listboxReceived.set(notification.getMessagelist());
       // listboxReceived.itemsProperty().bind(notification.getMessagelist());

    }
    public TextField getTextboxMessage()
    {
        return textboxMessage;
    }

    public ListView getListboxReceived()
    {
        return listboxReceived;
    }
}
