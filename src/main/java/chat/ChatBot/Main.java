package chat.ChatBot;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        try {
            String url = "https://aiapi.alowlaomar.de/api/generate";
            String prompt = "Why%20is%20the%20sky%20blue?";
            String model = "llama3";

            Requester request = new Requester(url, prompt, model);
            Gson gson = new Gson();
            Responser responser = gson.fromJson(request.getResponse(), Responser.class);

            System.out.println(responser.getResponse());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
