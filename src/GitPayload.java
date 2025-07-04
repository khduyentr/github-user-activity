import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GitPayload {
    private final Optional<String> refType;
    private final Optional<String> description;
    private final String masterBranch;
    private final String pusherType;

    public GitPayload(Optional<String> refType, Optional<String> description, String masterBranch, String pusherType) {
        this.refType = refType;
        this.description = description;
        this.masterBranch = masterBranch;
        this.pusherType = pusherType;
    }

    public Optional<String> getRefType() {
        return refType;
    }

    public Optional<String> getDescription() {
        return description;
    }

    public String getMasterBranch() {
        return masterBranch;
    }

    public String getPusherType() {
        return pusherType;
    }

    public static GitPayload parser(String json) {
        int payloadStart = json.indexOf("\"payload\":");
        if (payloadStart == -1) {
            System.out.println("Payload not found");
            return null;
        }

        Map<String, String> payloadMap = new HashMap<>();
        int open = json.indexOf('{', payloadStart);
        int close = Helper.findMatchingBrace(json, open);

        String jsonActor = json.substring(open + 1, close).trim();

        String[] lines = jsonActor.split(",");
        for (String line: lines) {
            line = line.trim();
            String[] parts = line.split(":", 2);
            if (parts.length == 2) {
                String key = parts[0].trim().replaceAll("\"", "");
                String value = parts[1].trim().replaceAll("^[ \"]+|[ \",]+$", "");

                payloadMap.put(key, value);
            }
        }

        String pusherType = payloadMap.getOrDefault("pusher_type", "");
        String masterBranch = payloadMap.getOrDefault("master_branch", "");
        String refType = payloadMap.getOrDefault("ref_type", "");
        String description = payloadMap.getOrDefault("description", "");

        return new GitPayload(Optional.ofNullable(refType), Optional.ofNullable(description), masterBranch, pusherType);
    }

    @Override
    public String toString() {
        return "GitPayload" + "\n" +
                " - ref_type: " + this.getRefType() + "\n" +
                " - master_branch: " + this.getMasterBranch() + "\n" +
                " - description: " + this.getDescription() + "\n" +
                " - pusher_type: " + this.getDescription() + "\n";
    }
}
