package chat.View;
import chat.*;
import chat.ChatBot.Main;
import chat.users.MainViewController;

import java.nio.file.LinkPermission;

public class ChatMainController {
    Chathub chathub = new Chathub();
    private MainViewController mainViewController;
    public ChatMainController(MainViewController mainViewController) {
        this.mainViewController=mainViewController;
    }
    //just a comment
    public void clickSetIcon() {
        if (mainViewController.getInputField() != null) {
            String inputText = mainViewController.getInputField().getText(); // Get text from the input field
            System.out.println(inputText); // Print the text to the console
            mainViewController.getInputField().clear(); // Optionally clear the input field after sending
        } else {
            System.err.println("inputField is not initialized.");
        }
    }
}
