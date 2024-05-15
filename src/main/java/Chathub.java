import chat.users.User;
import lombok.Getter;
import java.io.File;
import java.security.NoSuchAlgorithmException;

public class Chathub
{
    @Getter
    private static Chathub instance;
    private File configFile;
    public static void main(String[] args) throws NoSuchAlgorithmException
    {
        instance = new Chathub();
    }

    private void createFolder()
    {

    }
}
