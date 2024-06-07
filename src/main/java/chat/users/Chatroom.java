package chat.users;

import chat.message.Message;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Chatroom
{
    private int chatroomID;
    @Getter
    private String chatroomName;
    @Getter
    private ArrayList<User> users;
    private ArrayList<Message> messages;
    @Getter
    private LocalDateTime createdAt;
    private User admin;
    @Getter
    private int maxUsers;
    @Getter
    private String description;
    @Getter
    private boolean isPublic;
    @Setter @Getter
    private String password;

    private ChatroomController chatroomController;

    public Chatroom(String chatroomName, String description, String password, LocalDateTime createdAt, int maxUsers, boolean isPublic)
    {
        this.chatroomName = chatroomName;
        this.description = description;
        this.password = password;
        this.createdAt = createdAt;
        this.maxUsers = maxUsers;
        this.isPublic = isPublic;
        users = new ArrayList<>();
        chatroomController = new ChatroomController(this);
    }

    @Override
    public String toString()
    {
        return chatroomName + " : " + description;
    }
}
