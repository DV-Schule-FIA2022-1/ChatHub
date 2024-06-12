package chat.users.login;

import chat.users.functions.CheckEmailFunction;
import chat.users.functions.HashFunction;

public class AuthenticationController
{
    private LoginController loginController;
    private CheckEmailFunction checkEmailFunction;

    public AuthenticationController(LoginController loginController, CheckEmailFunction checkEmailFunction)
    {
        this.loginController = loginController;
        this.checkEmailFunction = checkEmailFunction;
    }

    public boolean checkPassword(String enteredEmail, String enteredPassword)
    {
        if(checkEmailFunction.checkEmail(enteredEmail))
        {
            if(HashFunction.toHexString(HashFunction.getSHA(enteredPassword)).equals(checkEmailFunction.getActiveUser().getPassword()))
            {
                loginController.setActiveUser(checkEmailFunction.getActiveUser());
                return true;
            }
            else
            {
                if(checkEmailFunction.getActiveUser().getAttempts() >= 2)
                {
                    loginController.disableLoginButton();
                }
                else
                {
                    checkEmailFunction.getActiveUser().setAttempts();
                    System.out.println("Fehler Nummer: " + checkEmailFunction.getActiveUser().getAttempts());
                }
            }
        }
        return false;
    }
}
