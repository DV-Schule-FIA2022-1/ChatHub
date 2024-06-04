package chat.ChatBot;

import com.google.gson.Gson;
import javafx.scene.transform.Translate;

public  class Translator implements Processor {

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
    public Translator(String inputMsg) {
        InputMsg = inputMsg;
    }

    @Override
    public String process(String inputMsg) {
        return OutpubMsg;
    }
}
