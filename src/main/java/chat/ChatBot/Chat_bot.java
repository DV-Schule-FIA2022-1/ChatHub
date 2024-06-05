package chat.ChatBot;

import com.google.gson.Gson;

public class Chat_bot implements Processor{
    private String InputMsg;
    private String OutpubMsg;
    Requester requester = null;

    public String getInputMsg() {
        return InputMsg;
    }

    public void setInputMsg(String inputMsg) {
        InputMsg = inputMsg;
    }

    public String getOutpubMsg() {
        return OutpubMsg;
    }

    public void setOutpubMsg(String outpubMsg) {
        OutpubMsg = outpubMsg;
    }

    public Chat_bot() {
    }

    @Override
    public String process(String inputMsg) {
        try {
            setOutpubMsg(requester.sendRequest(inputMsg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OutpubMsg;
    }
}
