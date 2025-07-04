import java.util.HashMap;
import java.util.Map;

public class GitRepo {
    private final String id;
    private final String name;
    private final String url;

    public GitRepo(String id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static GitRepo parser(String json) {
        int repoStart = json.indexOf("\"repo\":");
        if (repoStart == -1) {
            System.out.println("Repo not found");
            return null;
        }

        Map<String, String> repoMap = new HashMap<>();
        int open = json.indexOf('{', repoStart);
        int close = Helper.findMatchingBrace(json, open);

        String jsonRepo = json.substring(open + 1, close).trim();

        String[] lines = jsonRepo.split(",");
        for (String line: lines) {
            line = line.trim();
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                String key = parts[0].trim().replaceAll("\"", "");
                String value = parts[1].trim().replaceAll("^[ \"]+|[ \",]+$", "");

                repoMap.put(key, value);
            }

        }

        String id = repoMap.getOrDefault("id", "-1");
        String name = repoMap.getOrDefault("name", "");
        String url = repoMap.getOrDefault("url", "");

        return new GitRepo(id, name, url);
    }

    @Override
    public String toString() {
        return "GitRepo #" + this.getId() + "\n" +
                " - name: " + this.getName() + "\n" +
                " - url: " + this.getUrl() + "\n";
    }
}
