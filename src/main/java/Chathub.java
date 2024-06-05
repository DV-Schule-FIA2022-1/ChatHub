import chat.users.Address;
import chat.users.User;
import chat.users.UserController;
import lombok.Getter;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;

public class Chathub
{
    @Getter
    private static Chathub instance;
    public static void main(String[] args)
    {
        instance = new Chathub();
        LocalDate date = LocalDate.of(2024, 6, 3);
        UserController test = new UserController();
        User usertest = new User("Test", "Test2", "1234", "werwe@test.de", Date.valueOf(date), new Address("teststraße", "Würzburg", "97082", "Germany"));
        test.addUser(usertest);
        test.readUser();

        System.out.println(test.getUserlist());
    }
}
