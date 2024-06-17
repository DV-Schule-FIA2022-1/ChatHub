package chat.chatBot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

public class Requester {

    private final String response;

    public Requester(String prompt) throws IOException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("prompt", prompt);
        json.put("max_length", 100);
        json.put("n", 1);
        json.put("model", "llama3");
        json.put("stream", false);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(json.toString(), mediaType);

        Request request = new Request.Builder()
                .url("https://aiapi.alowlaomar.de/api/generate")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response httpResponse = client.newCall(request).execute()) {
            if (!httpResponse.isSuccessful()) {
                throw new IOException("Unexpected response code: " + httpResponse);
            }
            response = httpResponse.body().string();
        } catch (IOException e) {
            throw new RuntimeException("Error making HTTP request", e);
        }
    }

    public String getResponse() {
        return response;
    }
    public String sendRequest(String prompt) {
        try {
            Requester requester = new Requester(prompt);
            Gson gson = new Gson();
            Responser responser = gson.fromJson(requester.getResponse(), Responser.class);
            return responser.getResponse();
        } catch (IOException e) {
            throw new RuntimeException("Error during request", e);
        }
    }


}
