package chat.message;

import chat.client.ClientController;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable
{
    private String chatID;
    private String messageId;
    private int protocoll;
    private String value;
    private Date date;
    private int inputUserID;
    private boolean status;
    private LocalDateTime sendedAt;

    private String name;

    private transient ClientController clientController;

    public Message(ClientController clientController, String chatID,
                   String messageId, int protocoll, String value, Date date,
                   int inputUserID, boolean status)
    {
        this.clientController = clientController;
        this.chatID = chatID;
        this.messageId = messageId;
        this.protocoll = protocoll;
        this.value = value;
        this.date = date;
        this.inputUserID = inputUserID;
        this.status = status;
    }

    public Message(ClientController clientController, String value)
    {
        this.clientController = clientController;
        this.name = clientController.getName().getText().toString();
        this.value = value;
    }

    @Override
    public String toString() {
        return "Message{" +
                "chatID='" + chatID + '\'' +
                ", messageId='" + messageId + '\'' +
                ", protocoll=" + protocoll +
                ", value='" + value + '\'' +
                ", date=" + date +
                ", inputUserID=" + inputUserID +
                ", status=" + status +
                ", name='" + name + '\'' +
                ", clientController=" + clientController +
                '}';
    }
}
