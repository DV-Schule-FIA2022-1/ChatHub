package chat.server;

import javafx.application.Platform;
import chat.message.Message;

import java.io.*;
import java.net.Socket;

public class ClientProxy extends Thread
{
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    ServerController serverController;
    Message nachricht;

    public ClientProxy(Server server, ServerController serverController, Socket client) throws IOException, ClassNotFoundException
    {
        this.server = server;
        this.serverController = serverController;

        in = new ObjectInputStream(client.getInputStream());
        out = new ObjectOutputStream(client.getOutputStream());
        this.start();
    }

    @Override
    public void run()
    {
        try
        {
            while ((nachricht = (Message)in.readObject())!= null)
            {

                System.out.println("Empfangen vom Client: " + nachricht);
                server.verteileNachricht(nachricht);
                Platform.runLater(() -> serverController.getNachrichten().getItems().add(nachricht));
            }
        }
        catch (Exception e)
        {

        }
    }

    public void schreiben(Message nachricht) throws IOException
    {
        out.writeObject(nachricht);
        out.flush();
    }
}
