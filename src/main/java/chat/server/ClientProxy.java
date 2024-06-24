package chat.server;

import chat.message.Message;
import chat.view.MainViewController;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientProxy extends Thread {
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private MainViewController mainViewController;
    private Message nachricht;

    public ClientProxy(Server server, MainViewController mainViewController, Socket client) throws IOException
    {
        this.server = server;
        this.mainViewController = mainViewController;

        out = new ObjectOutputStream(client.getOutputStream());
        in = new ObjectInputStream(client.getInputStream());
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
                    TextArea message = new TextArea();
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

    public void schreiben(Message nachricht) throws IOException {
        out.writeObject(nachricht);
        out.flush();
    }
}
