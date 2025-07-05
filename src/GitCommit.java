import java.util.*;

public class GitCommit {
    private final String sha;
    private final String author;
    private final String message;
    private final String url;

    public GitCommit(String sha, String author, String message, String url) {
        this.sha = sha;
        this.author = author;
        this.message = message;
        this.url = url;
    }

    public String getSha() {
        return sha;
    }

    public String getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        /*return "    [ #" + this.getSha() + "]: " + this.getMessage() + ")\n"
                + " - Author: " + this.getAuthor() + "\n"
                + " - Url: " + this.getUrl() + "\n";*/
        return String.format(
                "> Commit: %s\n  [Author]: %s\n  [Message]: %s\n  [URL]: %s\n",
                sha, author, message, url
        );
    }

    public static List<GitCommit> listParser(String json) {
        List<GitCommit> list = new ArrayList<>();

        int commitStart = json.indexOf("\"commits\":");
        if (commitStart == -1) {
            System.out.println("No commit found");
            return list;
        }

        int open = json.indexOf('[', commitStart);
        int close = json.indexOf(']', open);

        String jsonCommitArray = json.substring(open + 1, close).trim();
        String[] commitArray = jsonCommitArray.split("},\\{");

        for (String item: commitArray) {
            if (!item.startsWith("{")) item = "{" + item;
            if (!item.endsWith("}")) item = item + "}";

            GitCommit commit = parser(item);
            list.add(commit);
        }

        return list;
    }

    public static GitCommit parser(String json) {
        String[] lines = json.split(",");
        Map<String, String> commitMap = new HashMap<>();

        for (String line: lines) {
            line = line.trim();
            String[] parts = line.split(":", 2);

            if (parts.length == 2) {
                String key = parts[0].trim().replaceAll("\"", "");
                String value = parts[1].trim().replaceAll("^[ \"]+|[ \",]+$", "");

                commitMap.put(key, value);
            }
        }

        String email = Helper.extractStringValue(json, "email");
        String name = Helper.extractStringValue(json, "name").replace("}", "").replace("\"", "");
        String author = name + "(" + email + ")";
        String sha = commitMap.getOrDefault("sha", "");
        String message = commitMap.getOrDefault("message", "");
        String url = commitMap.getOrDefault("url", "");

        return new GitCommit(sha, author, message, url);
    }
}
