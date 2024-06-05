package chat.Notification;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientControllerNoti implements Initializable
{
    @FXML private TextField textboxMessage;
    @FXML private Button buttonSend;
    @FXML private ListView listboxReceived;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
    public void sendMessage()
    {

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
