package org.example;

public class ClearCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        Player player = Player.getInstance();
        player.clearQueue();
        System.out.println("Die Warteschlange wurde geleert.");
    }

    @Override
    public String getDescription()
    {
        return "Leert die Warteschlange.";
    }
}