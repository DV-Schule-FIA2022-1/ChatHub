package chat.core.client;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import chat.core.message.Message;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientController implements Initializable
{
    @FXML private TextField serverport;
    @FXML private TextField name;
    @FXML private TextField eingabe;
    @FXML private TextField empfangen;
    private Client client;
    private Message nachricht;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void sendMessage()
    {
        //nachricht = new Message(this, eingabe.getText());
        client.write(nachricht);
    }

    public TextField getEmpfangen()
    {
        return empfangen;
    }

    public TextField getName()
    {
        return name;
    }
}
