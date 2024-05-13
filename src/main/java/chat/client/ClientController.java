package chat.client;

import chat.client.Client;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import chat.message.Message;
import java.io.IOException;
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

    public void createUser()
    {
        try
        {
            client = new Client(Integer.parseInt(serverport.getText()), this, name.getText());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage()
    {
        try
        {
            nachricht = new Message(this, eingabe.getText());
            client.schreiben(nachricht);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
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
