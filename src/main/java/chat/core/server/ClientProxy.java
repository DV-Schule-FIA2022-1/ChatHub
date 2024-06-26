package chat.core.server;

import chat.core.message.Message;
import chat.view.MainViewController;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientProxy extends Thread
{
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MainViewController mainViewController;
    private Message message;
    private TextArea messageArea;

    public ClientProxy(Server server, MainViewController mainViewController, Socket client)
    {
        this.server = server;
        this.mainViewController = mainViewController;

        try
        {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        this.start();
    }

    @Override
    public void run()
    {
        try
        {
            while ((message = (Message) in.readObject()) != null)
            {
                System.out.println("Empfangen vom Client: " + message);
                server.sendToAllClients(message);
                Platform.runLater(() ->
                {
                    messageArea = new TextArea();
                    messageArea.setText(mainViewController.getClient().getUser().getFirstName() +
                            ": " + mainViewController.getChatMainController().getMsg().toString());
                    messageArea.setEditable(false);
                    messageArea.setWrapText(true);
                    messageArea.prefHeightProperty().bind(messageArea.heightProperty());
                    mainViewController.getMessageContainer().getChildren().add(messageArea);
                });
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void write(Message message)
    {
        this.message = message;
        try
        {
            out.writeObject(message);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
