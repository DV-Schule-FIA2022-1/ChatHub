package chat.users;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AuthenticationController
{
    private LoginController loginController;
    private ArrayList<User> users;
    private int counter = 0;
    private UserController userController;

    public AuthenticationController(LoginController loginController, UserController userController)
    {
        this.loginController = loginController;
        this.userController = userController;
        userController.readUser();
        users = new ArrayList<>();
    }

    public boolean checkEmail(String enteredEmail)
    {
        for (User user: userController.getUserlist())
        {
            if(!Objects.equals(enteredEmail, user.getEmail()))
            {
                System.out.println("Email nicht vorhanden");
                return false;
            }
        }
        System.out.println("Email vorhanden");
        return true;
    }

    public boolean checkPassword(String enteredEmail, String enteredPassword)
    {
        for(User user: userController.getUserlist())
        {
            if(checkEmail(enteredEmail))
            {
                if(HashFunction.toHexString(HashFunction.getSHA(enteredPassword)).equals(user.getPassword()))
                {
                    return true;
                }
                else
                {
                    if(user.getAttempts() >= 2)
                    {
                        loginController.disableLoginButton();
                    }
                    else
                    {
                        user.setAttempts();
                        System.out.println("Fehler Nummer: " + user.getAttempts());
                    }
                }
            }
        }
        return false;
    }
}
