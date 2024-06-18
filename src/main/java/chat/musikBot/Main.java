package chat.musikBot;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {

        System.out.println("Das Projekt befindet sich in: " + System.getProperty("user.dir"));

        CommandHandler commandHandler = new CommandHandler();
        commandHandler.register("/play", new PlayCommand());

        Scanner scanner = new Scanner(System.in);
        while (true)
        {
            System.out.println("Bitte geben Sie den Befehl ein:");
            String command = scanner.nextLine();
            commandHandler.handle(command);
        }
    }
}
