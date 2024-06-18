package chat.chatBot;

public class Chat_bot implements Processor
{
    private String InputMsg;
    private String OutpubMsg;
    private Requester requester;

    public Chat_bot() {}

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

    @Override
    public String process(String inputMsg)
    {
        try
        {
            requester = new Requester();
            setOutpubMsg(requester.sendRequest(inputMsg));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return OutpubMsg;
    }
}
