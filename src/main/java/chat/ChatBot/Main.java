package chat.ChatBot;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Translator test = new Translator("arabic");
        test.process("whats up i am omar how are you");
        Improver test0 = new Improver();
        test0.process("hallo Ich Könnte nicht mit dir Reden, verzeihe mir bitte");

    }
}
