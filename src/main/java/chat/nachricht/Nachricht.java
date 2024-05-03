package chat.nachricht;

import chat.client.ClientController;
import java.io.Serializable;

public class Nachricht implements Serializable
{
    private String message;
    private String name;
    private transient ClientController clientController;

    public Nachricht(ClientController clientController, String message)
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
