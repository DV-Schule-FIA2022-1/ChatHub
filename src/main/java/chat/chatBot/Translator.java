package chat.chatBot;
import chat.*;

import java.io.IOException;

public class Translator implements Processor {

    private String InputMsg;
    private String OutpubMsg;
    private String Lang;
    private Requester requester;

    public Translator(String lang) {
        this.Lang = lang;
    }

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

    public void setLang(String lang) {
        this.Lang = lang;
    }

    public String getLang() {
        return Lang;
    }

    @Override
    public String process(String inputMsg) throws IOException {
        try {
            requester = new Requester();
            String prompt = "Translate the following text in " + getLang() + ": " + inputMsg + ". Please return only the translated text.";
            setOutpubMsg(requester.sendRequest(prompt));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OutpubMsg;
    }
}
