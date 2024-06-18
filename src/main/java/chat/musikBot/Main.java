package org.example;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        CommandHandler commandHandler = CommandHandler.getInstance();
        commandHandler.register("/play", new PlayCommand());
        commandHandler.register("/queue", new QueueCommand());
        commandHandler.register("/stop", new StopCommand());
        commandHandler.register("/skip", new SkipCommand());
        commandHandler.register("/clear", new ClearCommand());
        commandHandler.register("/shuffle", new ShuffleCommand());
        commandHandler.register("/repeat", new RepeatCommand());
        commandHandler.register("/help", new HelpCommand());

        // Liest Befehle vom Benutzer ein und führt sie aus
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.println("Bitte geben Sie den Befehl ein:");
            String command = scanner.nextLine();
            commandHandler.handle(command);
        }
    }
}