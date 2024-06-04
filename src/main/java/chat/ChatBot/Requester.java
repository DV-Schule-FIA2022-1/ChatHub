package chat.ChatBot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class Requester {

    private final String response;

    public Requester(String url, String prompt, String model) throws IOException {
        OkHttpClient client = new OkHttpClient();

        // Convert JSON payload
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("prompt", prompt);
        json.put("max_length", 100);
        json.put("n", 1);
        json.put("model", model);
        json.put("stream", false);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(json.toString(), mediaType);

        Request request = new Request.Builder()
                .url(url)
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
}
