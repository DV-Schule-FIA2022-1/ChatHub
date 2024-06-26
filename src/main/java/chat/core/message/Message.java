package chat.core.message;

import chat.view.MainViewController;
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
    private transient MainViewController mainViewController;

    public Message(MainViewController mainViewController, int protocoll, String value, Date date, int inputUserID, boolean status)
    {
        this.mainViewController = mainViewController;
        this.protocoll = protocoll;
        this.value = value;
        this.date = date;
        this.inputUserID = inputUserID;
        this.status = status;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
