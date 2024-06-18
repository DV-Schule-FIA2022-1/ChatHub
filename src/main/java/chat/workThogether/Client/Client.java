package chat.workThogether.Client;

import chat.workThogether.Nachricht.ChangeMessage;
import javafx.application.Platform;
import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

public class Client extends Thread
{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private EditorController clientController;
    private int port;
    private ChangeMessage nachricht;
    private int fails;

    public Client(int port, EditorController clientController)
    {
        this.clientController = clientController;
        this.port = port;

        connectToServer();
    }

    public Client(int port, EditorController clientController, int fails)
    {
        this.clientController = clientController;
        this.port = port;
        this.fails = fails;

        connectToServer();
    }

    public void connectToServer()
    {
        try
        {
            socket = new Socket("localhost", port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            this.fails = 0;
            Platform.runLater(() -> {
                clientController.getStage().setTitle("Work Thogether");
            });
        }
        catch (IOException e)
        {
            //e.printStackTrace();
            System.out.println("connection error");
        }
        System.out.println("client started");

        this.start();
    }

    @Override
    public void run()
    {
        try
        {
            while ((nachricht = (ChangeMessage)in.readObject())!= null)
            {
                //System.out.println("Empfangen vom Server: " + nachricht);
                clientController.changedText(nachricht);
            }
        }
        catch (Exception e)
        {
            if(clientController.getStage().isShowing())
            {
                if(fails < 10)
                {
                    fails++;
                    try
                    {
                        Platform.runLater(() -> {
                            clientController.getStage().setTitle("reconnecting    - current try: " + fails);
                        });
                        Thread.sleep(500);
                        Platform.runLater(() -> {
                            clientController.getStage().setTitle("reconnecting.   - current try: " + fails);
                        });
                        Thread.sleep(500);
                        Platform.runLater(() -> {
                            clientController.getStage().setTitle("reconnecting..  - current try: " + fails);
                        });
                        Thread.sleep(500);
                        Platform.runLater(() -> {
                            clientController.getStage().setTitle("reconnecting... - current try: " + fails);
                        });
                        Thread.sleep(500);
                    } catch (InterruptedException ex)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("reconnecting - current try: " + fails);
                    if (socket != null && !socket.isClosed())
                    {
                        disconnectFromServer();
                    }
                    clientController.setClient(new Client(port, clientController, fails));
                }
                else
                {
                    System.out.println("No connection possible!");
                    JOptionPane.showMessageDialog(null, "connection error", "error", JOptionPane.ERROR_MESSAGE);
                    disconnectFromServer();
                    Platform.runLater(() -> {
                        clientController.getStage().close();
                    });
                }
            }
            else
            {
                System.out.println("Client disconnected");
            }
        }
    }

    public void disconnectFromServer()
    {
        try
        {
            if (socket != null)
            {
                socket.close();
                System.out.println("Connection to server lost.");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void schreiben(ChangeMessage nachricht) throws IOException
    {
        try
        {
            this.nachricht = nachricht;
            out.writeObject(nachricht);
            out.flush();
        }
        catch (Exception e)
        {
            System.out.println("Writing is not possible without a connection!");
            clientController.getTextArea().setText(clientController.getOldText());
            JOptionPane.showMessageDialog(null, "Writing is not possible without a connection!", "error", JOptionPane.ERROR_MESSAGE);
        }
    }
}