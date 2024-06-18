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
import java.util.concurrent.TimeUnit;

public class Requester
{
    private final OkHttpClient client;
    private final Gson gson;

    public Requester()
    {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        this.gson = new Gson();
    }

    public String sendRequest(String prompt)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode json = mapper.createObjectNode();
            json.put("prompt", prompt);
            json.put("max_length", 1000);
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

            try (Response httpResponse = client.newCall(request).execute())
            {
                if (!httpResponse.isSuccessful())
                {
                    throw new IOException("Unexpected response code: " + httpResponse);
                }
                String response = httpResponse.body().string();
                Responser responser = gson.fromJson(response, Responser.class);
                return responser.getResponse();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error during request", e);
        }
    }
}
