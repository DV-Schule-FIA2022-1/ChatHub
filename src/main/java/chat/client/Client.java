package chat.client;

import chat.message.Message;
import chat.users.User;
import lombok.Getter;
import java.io.*;
import java.net.Socket;

public class Client extends Thread
{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    @Getter
    private ClientController clientController;
    private String name;
    private Message nachricht;
    @Getter
    private User user;

    public Client(User user, int port, ClientController clientController)
    {
        try
        {
            this.clientController = clientController;
            this.user = user;
            socket = new Socket("127.0.0.1", port);

            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Client gestartet");
            this.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        try
        {
            while ((nachricht = (Message)in.readObject())!= null)
            {
                //clientController.getEmpfangen().clear();
                System.out.println("Empfangen vom Server: " + nachricht);
                //clientController.getEmpfangen().setText(nachricht.toString());
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