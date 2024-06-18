package chat.musikBot;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler
{

    private static CommandHandler instance;
    private Map<String, Command> commands = new HashMap<>();

    private CommandHandler() {}

    public static CommandHandler getInstance()
    {
        if (instance == null)
        {
            instance = new CommandHandler();
        }
        return instance;
    }

    // Methode zur Registrierung eines neuen Befehls
    public void register(String command, Command executor)
    {
        commands.put(command, executor);
    }

    // Methode zur Behandlung eines Befehls
    public void handle(String command)
    {
        Command executor = commands.get(command);
        if (executor != null)
        {
            executor.execute(command);
        }
        else
        {
            System.out.println("Unbekannter Befehl: " + command);
        }
    }

    public Map<String, Command> getCommands()
    {
        return commands;
    }
}