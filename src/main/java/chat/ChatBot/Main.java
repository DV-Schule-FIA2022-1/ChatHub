package chat.ChatBot;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Translator test = new Translator("whats up i am omar how are u","arabic");
        test.process("whats up i am omar how are you");
    }
}
