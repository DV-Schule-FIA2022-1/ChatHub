package chat.WorkThogether.Server;

import chat.WorkThogether.Nachricht.ChangeMessage;
import chat.nachricht.Nachricht;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread
{
    private ArrayList<ClientProxy> clientList;
    private ServerSocket socket;
    private int port;
    private Nachricht nachricht;

    public Server(int port) throws IOException
    {
        System.out.println("Server gestartet!");
        clientList = new ArrayList<>();
        this.port = port;
        this.start();
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                socket = new ServerSocket(port);
                clientList.add(new ClientProxy(this, socket.accept()));
                socket.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void verteileNachricht(ChangeMessage nachricht) throws IOException
    {
        for (ClientProxy c : clientList)
        {
            c.schreiben(nachricht);
        }
    }

    public static void main(String[] args) throws IOException
    {
        new Server(1234);
    }
}
