package chat.musikBot;

public class RepeatCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        Player player = Player.getInstance();
        player.setRepeat(true);
        System.out.println("Das aktuelle Video wird wiederholt.");
    }

    @Override
    public String getDescription()
    {
        return "Wiederholt das aktuelle Video.";
    }
}