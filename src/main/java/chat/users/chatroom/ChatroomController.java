package chat.users.chatroom;

import chat.users.User;
import chat.users.functions.HashFunction;

import java.sql.*;

public class ChatroomController
{
    private Chatroom chatroom;
    private String databaseURL = "jdbc:ucanaccess://src/main/java/chat/database/TestDB.accdb";
    private int row;
    public ChatroomController(Chatroom chatroom)
    {
        this.chatroom = chatroom;
    }

    public boolean addUser(User user)
    {
        if(chatroom.getUsers().size() < chatroom.getMaxUsers())
        {
            return chatroom.getUsers().add(user);
        }
        return false;
    }

    public boolean removeUser(User user)
    {
        return chatroom.getUsers().contains(user);
    }

    public boolean hasUser(User user)
    {
        return chatroom.getUsers().contains(user);
    }

    public void setPassword(String password)
    {
        chatroom.setPassword(password);
    }

    public boolean checkPassword(String password)
    {
        return chatroom.getPassword() != null && chatroom.getPassword().equals(password);
    }

    public void createChatroom(Chatroom chatroom)
    {
        try(Connection connection = DriverManager.getConnection(databaseURL))
        {
            String sql = "INSERT INTO Chatroom (chatroomName, description, password, createAt, maxUsers, isPublic) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, chatroom.getChatroomName());
            preparedStatement.setString(2, chatroom.getDescription());
            preparedStatement.setString(3, HashFunction.toHexString(HashFunction.getSHA(chatroom.getPassword())));
            Timestamp timestamp = Timestamp.valueOf(chatroom.getCreatedAt());
            Date sqlDate = new Date(timestamp.getTime());
            preparedStatement.setDate(4, sqlDate);
            preparedStatement.setInt(5, chatroom.getMaxUsers());
            preparedStatement.setBoolean(6, chatroom.isPublic());

            row = preparedStatement.executeUpdate();

            if(row > 0)
            {
                System.out.println("Data inserted sucessfully");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}