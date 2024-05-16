package chat.users;

import java.util.ArrayList;

public class Chatroom
{
    private int chatroomID;
    private int localChatID;
    private ArrayList<User> users;

    public Chatroom()
    {
        localChatID = chatroomID++;
        users = new ArrayList<>();
    }

    public void joinChatroom()
    {

    }

    public void leaveChatroom()
    {

    }

    public void createChatroom()
    {

    }
}
