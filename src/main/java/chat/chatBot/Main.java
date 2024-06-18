package chat.chatBot;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Translator translator = new Translator("arabic");
        String translatedText = translator.process("whats up i am omar how are you");
        System.out.println(translatedText);

        Improver improver = new Improver();
        String improvedText = improver.process("hallo Ich Könnte nicht mit dir Reden, verzeihe mir bitte");
        System.out.println(improvedText);

        Chat_bot chatBot = new Chat_bot();
        String chatResponse = chatBot.process("who are you?");
        System.out.println(chatResponse);
    }
}
