package chat.server;

import chat.message.Message;
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
    private Message nachricht;
    private TextArea message;

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
            while ((nachricht = (Message) in.readObject()) != null)
            {
                System.out.println("Empfangen vom Client: " + nachricht);
                server.verteileNachricht(nachricht);
                Platform.runLater(() ->
                {
                    message = mainViewController.getChatMainController().getMessage();
                    message = new TextArea();
                    message.setText(mainViewController.getClient().getUser().getFirstName() +
                            ": " + mainViewController.getChatMainController().getMsg().toString());
                    mainViewController.getMessageContainer().getChildren().add(message);
                });
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public void write(Message nachricht)
    {
        try
        {
            out.writeObject(nachricht);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
