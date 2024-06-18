package chat.musikBot;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler
{
    private final Map<String, Command> commands = new HashMap<>();

    public void register(String name, Command command)
    {
        commands.put(name, command);
    }

    public void handle(String command)
    {
        String[] parts = command.split(" ", 2);
        String name = parts[0];
        String argument = parts.length > 1 ? parts[1] : "";

        Command cmd = commands.get(name);
        if (cmd != null) {
            cmd.execute(argument);
        } else {
            System.out.println("Unbekannter Befehl: " + name);
        }
    }
}
