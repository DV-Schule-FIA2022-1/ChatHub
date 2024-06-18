package chat.view;

import chat.message.Message;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ChatMainController {
    private MainViewController mainViewController;
    private String inputText;
    public ChatMainController(MainViewController mainViewController) {
        this.mainViewController=mainViewController;
    }
    public void clickSetIcon() throws IOException {
        if (mainViewController.getInputField() != null) {
            inputText = mainViewController.getInputField().getText(); // Get text from the input field
            mainViewController.getInputField().clear(); // Optionally clear the input field after sending
            sendMsgToServer();
        } else {
            System.err.println("inputField is not initialized.");
        }
    }
    public void sendMsgToServer() throws IOException {
        //bitte Mach diese Patrik Oder Nikita
        LocalDate localDate= LocalDate.now();
        Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Message msg = new Message(mainViewController.getClient().getClientController(),1,
                inputText,today,mainViewController.getClient().getUser().getAttempts(),true);
        mainViewController.getClient().schreiben(msg);
    }

}
