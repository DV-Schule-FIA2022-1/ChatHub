package chat.ChatBot;

public class Translator implements Processor {

    private String InputMsg;
    private String OutpubMsg;

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
    public Translator(String inputMsg, String outpubMsg) {
        InputMsg = inputMsg;
    }


    @Override
    public String process(String inputMsg) {
        return OutpubMsg;
    }
}
