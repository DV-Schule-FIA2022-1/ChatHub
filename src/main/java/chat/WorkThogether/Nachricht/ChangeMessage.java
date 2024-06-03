package chat.WorkThogether.Nachricht;

import java.io.Serializable;
import java.util.Date;

public class ChangeMessage implements Serializable
{
    public ChangeMessage(int position, String text, boolean isInsertion, Date timestamp)
    {
        this.position = position;
        this.text = text;
        this.isInsertion = isInsertion;
        this.timestamp = timestamp;
    }

    private int position;
    private String text;
    private boolean isInsertion;
    private Date timestamp;

    public int getPosition()
    {
        return position;
    }

    public String getText()
    {
        return text;
    }

    public boolean getIsInsertion()
    {
        return isInsertion;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }
}
