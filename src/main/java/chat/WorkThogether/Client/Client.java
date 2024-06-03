package chat.WorkThogether.Client;

import chat.client.ClientController;
import chat.nachricht.Nachricht;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client extends Thread
{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private EditorController clientController;
    private Nachricht nachricht;

    public Client(int port, EditorController clientController) throws IOException
    {
        socket = new Socket("localhost", port);
        System.out.println("Client gestartet");
        this.clientController = clientController;

        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        this.start();
    }

    @Override
    public void run()
    {
        try
        {
            while ((nachricht = (Nachricht)in.readObject())!= null)
            {
                System.out.println("Empfangen vom Server: " + nachricht);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void schreiben(Nachricht nachricht) throws IOException
    {
        this.nachricht = nachricht;
        out.writeObject(nachricht);
        out.flush();
    }
}