package chat.View;
import chat.*;
import chat.message.Message;
import chat.users.MainViewController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ChatMainController {
    private MainViewController mainViewController;
    private String inputText;
    public ChatMainController(MainViewController mainViewController) {
        this.mainViewController=mainViewController;
    }
    public void clickSetIcon() {
        if (mainViewController.getInputField() != null) {
            inputText = mainViewController.getInputField().getText(); // Get text from the input field
            System.out.println(inputText); // Print the text to the console
            mainViewController.getInputField().clear(); // Optionally clear the input field after sending
        } else {
            System.err.println("inputField is not initialized.");
        }
    }
    public void sendMsgToServer()
    {
        //bitte Mach diese
        LocalDate localDate= LocalDate.now();
        Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Message msg = new Message(mainViewController.getClient().getClientController(),"1","1",1,
                inputText,today,mainViewController.getClient().getUser().getAttempts(),true);
    }
}
