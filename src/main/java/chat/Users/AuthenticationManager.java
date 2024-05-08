package chat.Users;

public class AuthenticationManager
{
    private LoginController loginController;
    private User user;

    public AuthenticationManager()
    {

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
        if(user.getAttempts() < 3)
        {
            user.setAttempts();
        }
        //Anmeldeversuch für 5 min blockiert -> return false
        else
        {

        }
        return true;
    }
}
