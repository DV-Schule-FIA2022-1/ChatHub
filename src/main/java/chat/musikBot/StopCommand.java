package org.example;

public class StopCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        Player player = Player.getInstance();
        player.stop();
        player.clearQueue();
        System.out.println("Die Wiedergabe wurde gestoppt und die Warteschlange wurde geleert.");
    }

    @Override
    public String getDescription()
    {
        return "Stoppt die Wiedergabe und leert die Warteschlange.";
    }
}