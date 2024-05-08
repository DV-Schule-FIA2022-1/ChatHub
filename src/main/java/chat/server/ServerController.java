package chat.server;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServerController implements Initializable
{
    @FXML private TextField portnr;
    @FXML private Label info;
    @FXML private ListView nachrichten;
    private Server testserver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }

    public void startServer()
    {
        Thread t1 = new Thread(() ->
        {
            try
            {
                testserver.getInstance(this, Integer.parseInt(portnr.getText()));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        });
        t1.start();

        info.setText("Server läuft auf 0.0.0.0/" + Integer.parseInt(portnr.getText()));
    }

    public ListView getNachrichten()
    {
        return nachrichten;
    }
}
