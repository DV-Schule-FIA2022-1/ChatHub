package org.example;

public class PlayCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        String videoLink = argument;
        String videoId = Player.extractVideoId(videoLink);

        if (videoId == null)
        {
            System.out.println("Ungültiger Video Link.");
            return;
        }

        Player player = Player.getInstance();
        if (player.isPlaying())
        {
            player.addToQueue(videoId);
            System.out.println("Das Video mit der ID " + videoId + " wurde zur Warteschlange hinzugefügt.");
        }
        else
        {
            //player.printVideoDetails(videoId);
            player.play(videoId);
            System.out.println("Das Video mit der ID " + videoId + " wird abgespielt.");
        }
    }

    @Override
    public String getDescription()
    {
        return "Spielt das angegebene Video ab oder fügt es zur Warteschlange hinzu, wenn bereits ein Video abgespielt wird.";
    }
}