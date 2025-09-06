package me.fuji97.API;

import io.github.cdimascio.dotenv.Dotenv;
import me.fuji97.Model.Message;
import okhttp3.*;
import com.google.gson.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

public class GeminiAPI {

    static Dotenv dotenv = Dotenv.load();

    private static final String API_KEY = dotenv.get("GEMINI_APIKEY");
    private static final String ENDPOINT = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + API_KEY;

    private static final OkHttpClient client = new OkHttpClient();

    public static String ask(List<Message> history) throws IOException {
        JsonArray contentsArray = new JsonArray();

        for (Message m : history) {
            JsonObject part = new JsonObject();
            part.addProperty("text", m.text);

            JsonObject content = new JsonObject();
            JsonArray partsArray = new JsonArray();
            partsArray.add(part);
            content.add("parts", partsArray);
            content.addProperty("role", m.role); // "user" หรือ "model"

            contentsArray.add(content);
        }

        JsonObject bodyJson = new JsonObject();
        bodyJson.add("contents", contentsArray);

        JsonObject generationConfig = new JsonObject();
        generationConfig.addProperty("temperature", 0.3);
        generationConfig.addProperty("topK", 40);
        generationConfig.addProperty("topP", 0.95);
        generationConfig.addProperty("maxOutputTokens", 1024);
        bodyJson.add("generationConfig", generationConfig);

        RequestBody body = RequestBody.create(
                bodyJson.toString(),
                MediaType.get("application/json")
        );

        Request request = new Request.Builder()
                .url(ENDPOINT)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful())
                throw new IOException("HTTP Error: " + response.code() + "\n" + response.body().string());

            JsonObject res = JsonParser.parseString(response.body().string()).getAsJsonObject();
            return res.getAsJsonArray("candidates")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("content")
                    .getAsJsonArray("parts")
                    .get(0).getAsJsonObject()
                    .get("text").getAsString();
        }
    }

    @NotNull
    private static JsonObject getJsonObject(String prompt) {
        JsonArray contentsArray = getJsonElements(prompt);

        JsonObject bodyJson = new JsonObject();
        bodyJson.add("contents", contentsArray);
        JsonObject generationConfig = new JsonObject();
        generationConfig.addProperty("temperature", 0.3);
        generationConfig.addProperty("topK", 40);
        generationConfig.addProperty("topP", 0.95);
        generationConfig.addProperty("maxOutputTokens", 1024);
        bodyJson.add("generationConfig", generationConfig);
        return bodyJson;
    }

    @NotNull
    private static JsonArray getJsonElements(String prompt) {

        JsonObject userPart = new JsonObject();
        JsonObject userContent = new JsonObject();
        JsonArray userPartsArray = new JsonArray();
        userPartsArray.add(userPart);
        userContent.add("parts", userPartsArray);
        userContent.addProperty("role", "user");

        JsonArray contentsArray = new JsonArray();
        contentsArray.add(userContent);
        return contentsArray;
    }


}
