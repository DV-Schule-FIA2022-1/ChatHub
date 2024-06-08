package chat.users;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable
{
    private User activeUser;

    public MainViewController(User activeUser)
    {
        this.activeUser = activeUser;
        System.out.println(activeUser.getFirstName());
    }

    public MainViewController()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }
}
