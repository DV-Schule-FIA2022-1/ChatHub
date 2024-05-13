import lombok.Getter;

import java.io.File;

public class Chathub
{
    @Getter
    private static Chathub instance;
    private File configFile;
    public static void main(String[] args)
    {
        instance = new Chathub();
    }

    private void createFolder()
    {

    }
}
