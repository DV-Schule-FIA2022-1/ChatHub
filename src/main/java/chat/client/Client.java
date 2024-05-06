package chat.client;

import chat.message.Message;

import java.io.*;
import java.net.Socket;

public class Client extends Thread
{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ClientController clientController;
    private String name;
    private Message nachricht;

    public Client(int port, ClientController clientController, String name) throws IOException
    {
        socket = new Socket("localhost", port);
        System.out.println("Client gestartet");
        this.name = name;
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
            while ((nachricht = (Message)in.readObject())!= null)
            {
                clientController.getEmpfangen().clear();
                System.out.println("Empfangen vom Server: " + nachricht);
                clientController.getEmpfangen().setText(nachricht.toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void schreiben(Message nachricht) throws IOException
    {
        this.nachricht = nachricht;
        out.writeObject(nachricht);
        out.flush();
    }
}