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
    public ArrayList<ClientProxy> getClientList()
    {
        return clientList;
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
            //Beide seiten sind gleich
            if(text.substring(0, changeMessage.getStartIndex()).equals(changeMessage.getNewText().substring(0, changeMessage.getStartIndex())) &&
                    text.substring(text.length() - changeMessage.getEndIndex(), text.length()).equals(changeMessage.getNewText().substring(changeMessage.getNewText().length() - changeMessage.getEndIndex(), changeMessage.getNewText().length())))
            {
                System.out.println("Text ohne wiederSpruch erkannt");
                text = changeMessage.getNewText();
            }
            //Linke seite ist gleich
            else if (text.substring(0, changeMessage.getStartIndex()).equals(changeMessage.getNewText().substring(0, changeMessage.getStartIndex())))
            {
                System.out.println("Linker Text ist gleich geblieben");
                if(changeMessage.getRemovedText().length() != 0)
                {
                    int skipIndex = changeMessage.getRemovedText().length();
                    int minLength = Math.min(text.length(), changeMessage.getRemovedText().length());
                    //Text wird so geupdatet, dass es so viel wie möglich vom Text Löschen soll, der gelöscht werden soll
                    for (int i = 0; i < minLength; i++)
                    {
                        if (text.charAt(changeMessage.getStartIndex() + i) != changeMessage.getRemovedText().charAt(i))
                        {
                            skipIndex = i;
                            System.out.println("skipIndex gefunden!");
                            break;
                        }
                    }
                    String deletedText = text.substring(changeMessage.getStartIndex(), changeMessage.getStartIndex() + skipIndex);
                    text = text.substring(0,changeMessage.getStartIndex()) + changeMessage.getNewText().substring(changeMessage.getStartIndex(), changeMessage.getNewText().length() - changeMessage.getEndIndex()) + text.substring(changeMessage.getStartIndex() + skipIndex, text.length());
                    changeMessage = new ChangeMessage(changeMessage.getStartIndex(), text.length() - changeMessage.getStartIndex() - (changeMessage.getNewText().length() - changeMessage.getStartIndex() - changeMessage.getEndIndex()), text, deletedText);
                    clientProxy.schreiben(changeMessage);
                }
                else
                {
                    //Text wird auf der linken seite eingefügt, da wo der vorhandene Teil endet
                    text = text.substring(0,changeMessage.getStartIndex()) + changeMessage.getNewText().substring(changeMessage.getStartIndex(), changeMessage.getNewText().length() - changeMessage.getEndIndex()) + text.substring(changeMessage.getStartIndex(), text.length());
                    changeMessage = new ChangeMessage(changeMessage.getStartIndex(), text.length() - changeMessage.getStartIndex() - (changeMessage.getNewText().length() - changeMessage.getStartIndex() - changeMessage.getEndIndex()), text);
                }
            }
            //Rechte Seite ist gleich
            else if (text.substring(text.length() - changeMessage.getEndIndex(), text.length()).equals(changeMessage.getNewText().substring(changeMessage.getNewText().length() - changeMessage.getEndIndex(), changeMessage.getNewText().length())))
            {
                System.out.println("Rechter Text ist gleich geblieben");
                if(changeMessage.getRemovedText().length() != 0)
                {
                    int skipIndex = changeMessage.getRemovedText().length();
                    int minLength = Math.min(text.length(), changeMessage.getRemovedText().length());

                    //Text wird so geupdatet, dass es so viel wie möglich vom Text Löschen soll, der gelöscht werden soll
                    for (int i = 1; i <= minLength; i++)
                    {
                        if (text.charAt(text.length() - changeMessage.getEndIndex() - i) != changeMessage.getRemovedText().charAt(changeMessage.getRemovedText().length() - i))
                        {
                            skipIndex = i - 1;
                            System.out.println("skipIndex gefunden!");
                            break;
                        }
                    }
                    text = text.substring(0, text.length() - changeMessage.getEndIndex() - skipIndex) + changeMessage.getNewText().substring(changeMessage.getStartIndex(), changeMessage.getNewText().length() - changeMessage.getEndIndex()) + text.substring(text.length() - changeMessage.getEndIndex(), text.length());
                }
                else
                {
                    //Der Text Konnte nicht eingefügt werden, also muss ein Update für den User her, das er auf dem selben stand ist
                    //text = text.substring(0, text.length() - changeMessage.getEndIndex()) + changeMessage.getNewText().substring(changeMessage.getStartIndex(), changeMessage.getNewText().length() - changeMessage.getEndIndex()) + text.substring(text.length() - changeMessage.getEndIndex(), text.length());
                    clientProxy.schreiben(new ChangeMessage(0,0,text));
                }
                changeMessage = new ChangeMessage(text.length() - changeMessage.getEndIndex() - (changeMessage.getNewText().length() - changeMessage.getStartIndex() - changeMessage.getEndIndex()), changeMessage.getEndIndex(), text);
            }
            //Kein Orientierungspunkt da
            else
            {
                System.out.println("Text kann nicht eingefügt werden!");
                text = changeMessage.getNewText();
                //update beim eigenen client befehlen !!!!Wichtig!!!!
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
