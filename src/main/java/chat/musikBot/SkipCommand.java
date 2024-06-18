package chat.musikBot;

public class SkipCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        Player.getInstance().skip();
    }

    @Override
    public String getDescription()
    {
        return "Überspringt das aktuelle Video.";
    }
}