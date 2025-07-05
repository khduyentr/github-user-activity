public class GitEvent {
    private final String id;
    private final String type;
    private final Boolean isPublished;
    private final String createdAt;

    private final GitActor actor;
    private final GitRepo repo;
    private final GitPayload payload;

    public GitEvent(String id, String type, Boolean isPublished, String createdAt, GitActor actor, GitRepo repo, GitPayload payload) {
        this.id = id;
        this.type = type;
        this.isPublished = isPublished;
        this.createdAt = createdAt;
        this.actor = actor;
        this.repo = repo;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public GitActor getActor() {
        return actor;
    }

    public GitRepo getRepo() {
        return repo;
    }

    public GitPayload getPayload() {
        return payload;
    }

    public static GitEvent parse(String event) {
        // parse the id, type, public, created_at property
        String eventId = Helper.extractStringValue(event, "id");
        String eventType = Helper.extractStringValue(event, "type");
        Boolean eventPublished = Helper.extractBooleanValue(event, "public");
        String eventCreatedAt = Helper.extractStringValue(event, "created_at");

        // parse the actor by property inside actor object: id, login, display_login, gravatar_id, url, avatar_url
        GitActor actor = GitActor.parser(event);
        // parse the repo by property inside repo object: id, name, url
        GitRepo repo = GitRepo.parser(event);
        // parse the payload by property inside payload object: ref_type, description, master_branch, pusher_type
        GitPayload payload = GitPayload.parser(event);
        // create object for actor, repo, payload
        // use that for the git event constructor
        return new GitEvent(eventId, eventType, eventPublished, eventCreatedAt, actor, repo, payload);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (GitCommit commit: this.getPayload().getCommits()) {
            builder.append(commit);
        }
        return "[" + Helper.formatDateTime(this.getCreatedAt()) + "] User \"" + this.getActor().getLogin() + "\" (ID: " + this.getActor().getId() + ")\n"
                + "- Event ID: " + this.getId() + "\n"
                + "- Profile: " + this.getActor().getUrl() + "\n"
                + "- Action: " + this.getType() + "\n"
                + "- Repository: " + this.getRepo().getName() + "\n"
                + "- Repo URL: " + this.getRepo().getUrl() + "\n"
                + "\n" + builder + "\n";
    }
}
