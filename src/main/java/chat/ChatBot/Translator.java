package chat.ChatBot;

import com.google.gson.Gson;
import javafx.scene.transform.Translate;

public  class Translator implements Processor {

    private String InputMsg;
    private String OutpubMsg;
    private String Lang;

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
    public String getLang(){return Lang;};
    public Translator(String lang) {
        Lang = lang;
    }

    @Override
    public String process(String inputMsg) {
        try {
            String url = "https://aiapi.alowlaomar.de/api/generate";
            String prompt = "Translate the following text in " + getLang() + ": " + inputMsg + ". Please return only the translated text. ";
            String model = "llama3";

            Requester request = new Requester(url, prompt, model);
            Gson gson = new Gson();
            Responser responser = gson.fromJson(request.getResponse(), Responser.class);
            setOutpubMsg(responser.getResponse());
            System.out.println(OutpubMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OutpubMsg;
    }


}
