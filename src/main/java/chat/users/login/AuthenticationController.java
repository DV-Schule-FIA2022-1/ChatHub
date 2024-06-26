package chat.users.login;

import chat.users.HashedText;

public class AuthenticationController
{
    private LoginController loginController;
    private CheckEmail checkEmailFunction;

    public AuthenticationController(LoginController loginController, CheckEmail checkEmailFunction)
    {
        this.loginController = loginController;
        this.checkEmailFunction = checkEmailFunction;
    }

    public boolean checkPassword(String enteredEmail, String enteredPassword)
    {
        if(checkEmailFunction.checkEmail(enteredEmail))
        {
            if(HashedText.toHexString(HashedText.getSHA(enteredPassword)).equals(checkEmailFunction.getActiveUser().getPassword()))
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
