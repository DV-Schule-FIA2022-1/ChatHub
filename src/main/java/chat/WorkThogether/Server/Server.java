package chat.WorkThogether.Server;

import chat.WorkThogether.Nachricht.ChangeMessage;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Thread
{
    private ArrayList<ClientProxy> clientList;
    private ServerSocket socket;
    private int port;
    private String text = "";

    public String getText()
    {
        return text;
    }

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

    public void changedText(ChangeMessage changeMessage, ClientProxy clientProxy)
    {
        try
        {
            if(text.substring(0, changeMessage.getStartIndex()).equals(changeMessage.getNewText().substring(0, changeMessage.getStartIndex())) &&
                    text.substring(text.length() - changeMessage.getEndIndex(), text.length()).equals(changeMessage.getNewText().substring(changeMessage.getNewText().length() - changeMessage.getEndIndex(), changeMessage.getNewText().length())))
            {
                System.out.println("Text ohne wiederSpruch erkannt");
                text = changeMessage.getNewText();
            }
            else if (text.substring(0, changeMessage.getStartIndex()).equals(changeMessage.getNewText().substring(0, changeMessage.getStartIndex())))
            {
                System.out.println("Linker Text ist gleich geblieben");
            }
            else if (text.substring(text.length() - changeMessage.getEndIndex(), text.length()).equals(changeMessage.getNewText().substring(changeMessage.getNewText().length() - changeMessage.getEndIndex(), changeMessage.getNewText().length())))
            {
                System.out.println("Rechter Text ist gleich geblieben");
            }
            //System.out.println("Difference found between index " + changeMessage.getStartIndex() + " and index " + changeMessage.getEndIndex());
            verteileNachricht(changeMessage, clientProxy);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void verteileNachricht(ChangeMessage nachricht, ClientProxy clientProxy) throws IOException
    {
        for (ClientProxy c : clientList)
        {
            if(c != clientProxy)
            {
                c.schreiben(nachricht);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        new Server(1234);
    }
}
