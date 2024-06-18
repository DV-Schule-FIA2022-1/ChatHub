package chat.server;

import chat.message.Message;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.BindException;
import java.net.ServerSocket;
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
        this.socket = new ServerSocket(port); // Bind the socket during initialization
        this.start();
    }

    public static synchronized Server getInstance(SocketManager socketManager, ServerController serverController, int port)
    {
        if (instance == null)
        {
            try
            {
                instance = new Server(socketManager, serverController, port);
            }
            catch (BindException e)
            {
                System.out.println("Port " + port + " is already in use. Server not started.");
                instance = null;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                instance = null;
            }
        }
        else
        {
            System.out.println("Server is already running on port " + instance.port);
        }

        return instance;
    }

    @Override
    public void run()
    {
        while (!isInterrupted())
        {
            try
            {
                clientList.add(new ClientProxy(this, serverController, socket.accept()));
                System.out.println("Client joined");
            }
            catch (InterruptedIOException e)
            {
                this.interrupt();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        socketManager.closeAllSockets();
    }

    public void verteileNachricht(Message nachricht) throws IOException
    {
        for (ClientProxy c : clientList)
        {
            c.schreiben(nachricht);

        }
    }
}