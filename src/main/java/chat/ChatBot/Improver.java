package chat.ChatBot;

import com.google.gson.Gson;

public class Improver implements Processor {
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


    @Override
    public String process(String inputMsg) {
        try {
            String url = "https://aiapi.alowlaomar.de/api/generate";
            String prompt = "verbessere den text Gramatikalisch ,  Bitte nur den text zurück geben !!! Text:|" + inputMsg + "|";
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
