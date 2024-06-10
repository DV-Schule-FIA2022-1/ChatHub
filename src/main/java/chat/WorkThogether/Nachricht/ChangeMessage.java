package chat.WorkThogether.Nachricht;

import java.io.Serializable;
import java.util.Date;

public class ChangeMessage implements Serializable
{
    private int startIndex;
    private int endIndex;
    private String newText;
    private  String removedText;
    private Date timestamp;

    public ChangeMessage(int startIndex, int endIndex, String newText)
    {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.newText = newText;
    }

    public ChangeMessage(int startIndex, int endIndex, String newText, String removedText)
    {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.newText = newText;
        this.removedText = removedText;
    }

    public int getStartIndex()
    {
        return startIndex;
    }

    public int getEndIndex()
    {
        return endIndex;
    }

    public String getNewText()
    {
        return newText;
    }

    public Date getTimestamp()
    {
        return timestamp;
    }
}
