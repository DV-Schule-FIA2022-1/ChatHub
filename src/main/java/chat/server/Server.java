package chat.server;

import chat.message.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread
{
    private static Server instance = null;
    private ArrayList<ClientProxy> clientList;
    private ServerSocket socket;
    private int port;
    private ServerController serverController;
    private Message nachricht;
    private SocketManager socketManager;

    private Server(SocketManager socketManager, ServerController serverController, int port) throws IOException
    {
        System.out.println("Server gestartet!");
        clientList = new ArrayList<>();
        this.port = port;
        this.serverController = serverController;
        this.socketManager = socketManager;
        this.start();
    }

    public static synchronized Server getInstance(SocketManager socketManager, ServerController serverController, int port)
    {
        try
        {
            if(instance == null)
            {
                instance = new Server(socketManager, serverController, port);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
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
                socketManager.addSocket(socket);
                clientList.add(new ClientProxy(this, serverController, socket.accept()));
                socketManager.closeAllSockets();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                socketManager.closeAllSockets();
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
