package chat.view;

import chat.chatBot.Chat_bot;
import chat.core.message.Message;
import javafx.scene.control.TextArea;
import lombok.Getter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ChatMainController
{
    private MainViewController mainViewController;
    @Getter
    private String inputText;
    @Getter
    private String chatResponse;
    @Getter
    private Message msg;
    @Getter
    private TextArea message;
    public ChatMainController(MainViewController mainViewController)
    {
        this.mainViewController = mainViewController;
    }
    public void clickSetIcon()
    {
        if (!mainViewController.getInputField().getText().isEmpty())
        {
            inputText = mainViewController.getInputField().getText(); // Get text from the input field
            mainViewController.getInputField().clear(); // Optionally clear the input field after sending

            sendMsgToServer();
        }
        else
        {
            System.err.println("inputField is not initialized.");
        }
    }
    public void sendMsgToServer()
    {
        LocalDate localDate = LocalDate.now();
        Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        msg = new Message(mainViewController,1,
                inputText,today,mainViewController.getClient().getUser().getAttempts(),true);
        mainViewController.getClient().write(msg);
        message = new TextArea();
        message.setText(mainViewController.getClient().getUser().getFirstName() +
                ": " + msg.toString());
        message.setEditable(false);
        message.setWrapText(true);
        message.prefHeightProperty().bind(message.heightProperty());
        mainViewController.getMessageContainer().getChildren().add(message);

    }
    public void sendToBot()
    {
        Chat_bot chatBot = new Chat_bot();
        chatResponse = chatBot.process(mainViewController.getInputField().getText());
        mainViewController.getInputField().clear(); // Optionally clear the input field after sending
        message = new TextArea();
        message.setText("bot: " + chatResponse);
        message.setEditable(false);
        message.setWrapText(true);
        message.prefHeightProperty().bind(message.heightProperty().add(20));
        mainViewController.getMessageContainer().getChildren().add(message);
    }

}
