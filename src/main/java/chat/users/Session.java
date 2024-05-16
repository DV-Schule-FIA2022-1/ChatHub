package chat.users;

import chat.message.Message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Session
{
    private int userID;
    private int chatRoomID;
    private int sessionID;
    private long startTime;
    private long endTime;
    private List<Message> messages;
    private String status;
    private List<User> participants;

    public Session(int userID, int chatRoomID, int sessionID)
    {
        this.userID = userID;
        this.chatRoomID = chatRoomID;
        this.sessionID = sessionID;
        this.startTime = System.currentTimeMillis();
        this.messages = new ArrayList<>();
        this.participants = new ArrayList<>();
        this.status = "active0";
    }

    public void endSession()
    {
        this.endTime = System.currentTimeMillis();
        this.status = "inactive";
    }
}
