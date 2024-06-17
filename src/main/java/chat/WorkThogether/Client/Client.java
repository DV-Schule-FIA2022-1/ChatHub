package chat.WorkThogether.Client;

import chat.WorkThogether.Nachricht.ChangeMessage;
import chat.client.ClientController;
import chat.nachricht.Nachricht;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client
{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private EditorController clientController;
    private int port;
    private ChangeMessage nachricht;
    private int fails = 0;
    Thread serverReader;

    public Client(int port, EditorController clientController)
    {
        this.clientController = clientController;
        this.port = port;
        connectToServer();
    }

    public void connectToServer()
    {
        try
        {
            socket = new Socket("localhost", port);
            System.out.println("Client gestartet");

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            //this.start();

            serverReader = new Thread(() -> {
                try
                {
                    while ((nachricht = (ChangeMessage)in.readObject())!= null)
                    {
                        //System.out.println("Empfangen vom Server: " + nachricht);
                        clientController.changedText(nachricht);
                    }
                }
                catch (IOException | ClassNotFoundException e)
                {
                    //e.printStackTrace();
                    if(clientController.getStage().isShowing())
                    {
                        if(fails < 10)
                        {
                            fails++;
                            System.out.println(socket.isConnected() + "try to reconnect! At: " + fails);
                            if (socket != null && !socket.isClosed())
                            {
                                disconnectFromServer();
                            }
                            connectToServer();
                        }
                        else
                        {

                        }
                    }
                }
            });
            serverReader.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void disconnectFromServer()
    {
        try
        {
            if (socket != null)
            {
                socket.close();
                System.out.println("Verbindung zum Server getrennt.");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void schreiben(ChangeMessage nachricht) throws IOException
    {
        this.nachricht = nachricht;
        out.writeObject(nachricht);
        out.flush();
    }
}
