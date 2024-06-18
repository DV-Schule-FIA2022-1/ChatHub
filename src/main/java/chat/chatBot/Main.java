package chat.chatBot;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Requester requester = new Requester();

        Translator translator = new Translator("arabic", requester);
        String translatedText = translator.process("whats up i am omar how are you");
        System.out.println(translatedText);

        Improver improver = new Improver(requester);
        String improvedText = improver.process("hallo Ich Könnte nicht mit dir Reden, verzeihe mir bitte");
        System.out.println(improvedText);

        Chat_bot chatBot = new Chat_bot(requester);
        String chatResponse = chatBot.process("who are you?");
        System.out.println(chatResponse);
    }
}
