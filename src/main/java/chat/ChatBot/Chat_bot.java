package chat.ChatBot;

import com.google.gson.Gson;

public class Chat_bot implements Processor{
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

    public Chat_bot() {
    }

    @Override
    public String process(String inputMsg) {
        try {
            String url = "https://aiapi.alowlaomar.de/api/generate";
            String model = "llama3";

            Requester request = new Requester(url, inputMsg , model);
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
