package chat.message;

import chat.client.ClientController;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable
{
    private int protocoll;
    private String value;
    private Date date;
    private int inputUserID;
    private boolean status;
    private LocalDateTime sendedAt;
    private String name;
    private transient ClientController clientController;

    public Message(ClientController clientController, int protocoll, String value, Date date, int inputUserID, boolean status)
    {
        this.clientController = clientController;
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
        return value;
    }
}
