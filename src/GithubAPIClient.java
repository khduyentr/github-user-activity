import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GithubAPIClient {
    private static final String GIT_TOKEN = Token.getInstance().getToken();

    public static String fetchUserEventsByUsername(String username, int limit, int page) throws IOException {
        String apiUrl = String.format(
                "https://api.github.com/users/%s/events?page=%d&per_page=%d",
                username, page, limit);

        URL url = new URL(apiUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/vnd.github+json");
        connection.setRequestProperty("Authorization", "Bearer " + GIT_TOKEN);

        int status = connection.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                (status >= 200 && status < 300) ? connection.getInputStream() : connection.getErrorStream()
        ));

        StringBuilder response = new StringBuilder();
        String line;
        // read each line from buffered reader
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();

    }
}
