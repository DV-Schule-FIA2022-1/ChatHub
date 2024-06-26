package chat.core.client;

import chat.core.message.Message;
import chat.users.User;
import chat.view.MainViewController;
import javafx.scene.control.TextArea;
import lombok.Getter;
import java.io.*;
import java.net.Socket;

public class Client extends Thread
{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    @Getter
    private MainViewController mainViewController;
    private Message message;
    @Getter
    private User user;
    private TextArea messageArea;

    public Client(User user, int port, MainViewController mainViewController)
    {
        try
        {
            this.mainViewController = mainViewController;
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
            while ((message = (Message)in.readObject())!= null)
            {
                //clientController.getEmpfangen().clear();
                System.out.println("Empfangen vom Server: " + message);
                messageArea = new TextArea();
                messageArea.setText(mainViewController.getClient().getUser().getFirstName() +
                        ": " + mainViewController.getChatMainController().getMsg().toString());
                messageArea.setEditable(false);
                messageArea.setWrapText(true);
                messageArea.prefHeightProperty().bind(messageArea.heightProperty());
                mainViewController.getMessageContainer().getChildren().add(messageArea);
                //clientController.getEmpfangen().setText(nachricht.toString());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    public void write(Message message)
    {
        this.message = message;
        try
        {
            out.writeObject(message);
            out.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}