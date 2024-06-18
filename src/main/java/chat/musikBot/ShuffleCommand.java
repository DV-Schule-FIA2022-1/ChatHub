package chat.musikBot;

public class ShuffleCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        Player.getInstance().shuffleQueue();
        System.out.println("Die Warteschlange wurde durchgemischt.");
    }

    @Override
    public String getDescription()
    {
        return "Mischt die Warteschlange durch.";
    }
}