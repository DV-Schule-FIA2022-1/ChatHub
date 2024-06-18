package chat.users.login;

import chat.users.User;
import chat.users.UserController;
import lombok.Getter;
import java.util.Objects;

public class CheckEmail
{
    private UserController userController;
    @Getter
    private User activeUser;
    private LoginController loginController;

    public CheckEmail(LoginController loginController, UserController userController)
    {
        this.loginController = loginController;
        this.userController = userController;
        userController.readUser();
    }

    public boolean checkEmail(String enteredEmail)
    {
        for (User user: userController.getUserlist())
        {
            if(Objects.equals(enteredEmail, user.getEmail()))
            {
                activeUser = user;
                System.out.println("Email vorhanden");
                return true;
            }
        }
        System.out.println("Email nicht vorhanden");
        return false;
    }
}
