package chat.server;

import chat.message.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread
{
    private Server instance = null;
    private ArrayList<ClientProxy> clientList;
    private ServerSocket socket;
    private int port;
    private ServerController serverController;
    private Message nachricht;

    private Server(ServerController serverController, int port) throws IOException
    {
        System.out.println("Server gestartet!");
        clientList = new ArrayList<>();
        this.port = port;
        this.serverController = serverController;
        this.start();
    }

    public synchronized Server getInstance(ServerController serverController, int port) throws IOException
    {
        if(instance == null)
        {
            instance = new Server(serverController, port);
        }

        return instance;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                socket = new ServerSocket(port);
                clientList.add(new ClientProxy(this, serverController, socket.accept()));
                socket.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void verteileNachricht(Message nachricht) throws IOException
    {
        for (ClientProxy c : clientList)
        {
            c.schreiben(nachricht);
        }
    }
}
