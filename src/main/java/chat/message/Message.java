package chat.message;

import chat.client.ClientController;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable
{
    private LocalDateTime sendedAt;
    private String message;
    private String name;
    private transient ClientController clientController;

    public Message(ClientController clientController, String message)
    {
        this.clientController = clientController;
        this.name = clientController.getName().getText().toString();
        this.message = message;
    }

    @Override
    public String toString()
    {
        return name + ": " + message;
    }

    public String getName()
    {
        return name;
    }
}
