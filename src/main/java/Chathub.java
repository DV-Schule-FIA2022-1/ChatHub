import chat.users.Address;
import chat.users.User;
import chat.users.UserController;
import chat.users.chatroom.Chatroom;
import chat.users.chatroom.ChatroomController;
import chat.users.enums.ChathubEnum;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Chathub
{
    @Getter
    private static Chathub instance;
    public static void main(String[] args)
    {
        instance = new Chathub();
        //LocalDate date = LocalDate.of(2024, 6, 3);
        //UserController test = new UserController();
        //User usertest = new User("Test", "Test2", "1234", "werwe@test.de", Date.valueOf(date), new Address("teststraße", "Würzburg", "97082", "Germany"));
        //test.addUser(usertest);
        //test.readUser();

        //Chatroom defaultroom = new Chatroom("Default", "Default Roomn", "1234", LocalDateTime.now(), 20, true);
        //ChatroomController chatroomController = new ChatroomController(defaultroom);
        //chatroomController.createChatroom(defaultroom);

        System.out.println(ChathubEnum.DatabasePath.getPath());
    }
}
