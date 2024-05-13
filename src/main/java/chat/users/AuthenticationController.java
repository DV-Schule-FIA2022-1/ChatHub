package chat.users;

import java.util.ArrayList;

public class AuthenticationController
{
    private LoginController loginController;
    private ArrayList<User> users;
    private int counter = 0;

    public AuthenticationController()
    {
        users = new ArrayList<>();
    }

    public boolean checkEmail()
    {
        //Check ob die Email bereits in der Datenbank existiert
        return true;
        //Wenn nicht return false;
    }

    public boolean checkPassword()
    {
        //Wenn die Email existiert dann überprüfe das passwort


        //Wenn Passwort falsch dann erlaube nur max 3 versuche
        if(users.get(counter).getAttempts() < 3)
        {
            users.get(counter).setAttempts();
        }
        //Anmeldeversuch für 5 min blockiert -> return false
        else
        {

        }
        return true;
    }
}
