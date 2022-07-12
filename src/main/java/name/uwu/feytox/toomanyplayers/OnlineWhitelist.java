package name.uwu.feytox.toomanyplayers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import javax.annotation.Nullable;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class OnlineWhitelist {
    public static List<String> onlineList = new ArrayList<>();

    public static boolean reloadWhitelist() {
        return reloadWhitelist(TMPConfig.onlineWhitelistUrl);
    }

    public static boolean reloadWhitelist(String url) {
        List<String> requestedList = getWhitelist(url);
        if (requestedList != null) {
            onlineList = requestedList;
            return true;
        }
        return false;
    }

    @Nullable
    private static List<String> getWhitelist(String url) {
        Gson gson = new Gson();
        try {
            String response = request(url);
            JsonElement root = JsonParser.parseString(response);
            JsonArray jsonArray = root.getAsJsonObject().get("whitelist").getAsJsonArray();
            Type listType = new TypeToken<List<String>>() {}.getType();

            return gson.fromJson(jsonArray, listType);
        } catch (Exception e) {
            return null;
        }
    }

    private static String request(String url) {
        List<String> responseList = new ArrayList<>();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(responseList::add)
                .join();

        return String.join("", responseList);
    }
}
