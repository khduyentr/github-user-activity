import java.util.HashMap;
import java.util.Map;

public class GitActor {
    private final String id;
    private final String login;
    private final String displayLogin;
    private final String gravatarId;
    private final String url;
    private final String avatarUrl;

    public GitActor(String id, String login, String displayLogin, String gravatarId, String url, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.displayLogin = displayLogin;
        this.gravatarId = gravatarId;
        this.url = url;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getDisplayLogin() {
        return displayLogin;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getUrl() {
        return url;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public static GitActor parser(String json) {
        int actorStart = json.indexOf("\"actor\":");
        if (actorStart == -1) {
            System.out.println("Actor not found");
            return null;
        }

        Map<String, String> actorMap = new HashMap<>();
        int open = json.indexOf('{', actorStart);
        int close = Helper.findMatchingBrace(json, open);

        String jsonActor = json.substring(open + 1, close).trim();

        String[] lines = jsonActor.split(",");
        for (String line: lines) {
            line = line.trim();
            String[] parts = line.split(":", 2);

            if (parts.length == 2) {
                String key = parts[0].trim().replaceAll("\"", "");
                String value = parts[1].trim().replaceAll("^[ \"]+|[ \",]+$", "");

                actorMap.put(key, value);
            }
        }

        String id = actorMap.getOrDefault("id", "-1");
        String login = actorMap.getOrDefault("login", "");
        String displayLogin = actorMap.getOrDefault("display_login", "");
        String gravatarId = actorMap.getOrDefault("gravatar_id", "");
        String url = actorMap.getOrDefault("url", "");
        String avatarUrl = actorMap.getOrDefault("avatar_url", "");

        return new GitActor(id, login, displayLogin, gravatarId, url, avatarUrl);
    }



    @Override
    public String toString() {
        return "GitActor #" + this.getId() + "\n" +
                " - login: " + this.getLogin() + "\n" +
                " - display_login: " + this.getDisplayLogin() + "\n" +
                " - gravatar_id: " + this.getGravatarId() + "\n" +
                " - url: " + this.getUrl() + "\n" +
                " - avatar_url: " + this.getAvatarUrl() + "\n";
    }
}
