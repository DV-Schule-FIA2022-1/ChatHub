package chat.WorkThogether.Server;

import chat.WorkThogether.Nachricht.ChangeMessage;
import chat.nachricht.Nachricht;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientProxy extends Thread
{
    private Server server;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ChangeMessage nachricht;
    private Socket client;

    public ClientProxy(Server server, Socket client) throws IOException, ClassNotFoundException
    {
        this.server = server;
        this.client = client;
        in = new ObjectInputStream(client.getInputStream());
        out = new ObjectOutputStream(client.getOutputStream());
        this.start();
        System.out.println("New client connectet!");
        schreiben(new ChangeMessage(0, 0, server.getText()));
    }

    @Override
    public void run()
    {
        try
        {
            while ((nachricht = (ChangeMessage)in.readObject())!= null)
            {
                //System.out.println("Empfangen vom Client: " + nachricht);
                //server.verteileNachricht(nachricht);
                server.changedText(nachricht, this);
            }
        }
        catch (Exception e)
        {
            //System.out.println(e.getMessage());
            try
            {
                client.close();
            } catch (IOException ex)
            {
                throw new RuntimeException(ex);
            }
            System.out.println("Client disconectet");
            server.getClientList().remove(this);
        }
    }

    public void schreiben(ChangeMessage nachricht) throws IOException
    {
        out.writeObject(nachricht);
        out.flush();
    }
}
