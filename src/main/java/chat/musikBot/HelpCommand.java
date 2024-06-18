package org.example;

import java.util.Map;

public class HelpCommand implements Command
{

    @Override
    public void execute(String argument)
    {
        CommandHandler commandHandler = CommandHandler.getInstance();
        Map<String, Command> commands = commandHandler.getCommands();

        for (Map.Entry<String, Command> entry : commands.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }

    @Override
    public String getDescription()
    {
        return "Gibt eine Liste aller verfügbaren Befehle und ihre Beschreibungen aus.";
    }
}