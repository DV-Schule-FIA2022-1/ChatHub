package chat.musikBot;

import java.util.Queue;

public class QueueCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        // Holt die Video-Warteschlange von der Player-Instanz
        Queue<String> videoQueue = Player.getInstance().getVideoQueue();

        // Überprüft, ob die Warteschlange leer ist
        if (videoQueue.isEmpty())
        {
            System.out.println("Die Warteschlange ist leer.");
        }
        // Wenn nein, listet alle Videos in der Warteschlange auf
        else
        {
            System.out.println("Videos in der Warteschlange:");
            int position = 1;
            for (String videoLink : videoQueue)
            {
                System.out.println(position + ". " + videoLink);
                position++;
            }
        }
    }

    @Override
    public String getDescription()
    {
        return "Zeigt die Videos in der Warteschlange an.";
    }
}