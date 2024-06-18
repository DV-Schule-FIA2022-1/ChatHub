package chat.chatBot;

public class Improver implements Processor {
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


    @Override
    public String process(String inputMsg) {
        try {
            String prompt = "Bitte verbessere den folgenden Text grammatikalisch " +
                    "und gib nur den verbesserten Text zurück. " +
                    "Die Ausgabe soll ausschließlich auf Deutsch sein und ohne anführungszeichen. " +
                    "Text: \"" + inputMsg + "\"";
            setOutpubMsg(requester.sendRequest(prompt));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OutpubMsg;
    }
}
