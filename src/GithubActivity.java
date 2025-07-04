import java.io.IOException;
import java.util.List;

public class GithubActivity {
    public static void fetchEvent(String username, int limit, int page) {
        String params = String.format(
                "{page:%d, per_page:%d}", page, limit);
        System.out.println("> FETCHING DATA ...");
        System.out.println("> PARAMS: " + params);
        try {
            String res = GithubAPIClient.fetchUserEventsByUsername(username, limit, page);
            List<GitEvent> list = GitEventListParser.parseEventList(res);

            System.out.println("> FOUND " + list.size() + " EVENTS: ");
            for (GitEvent event: list) {
                System.out.println(event);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args)  {

        if (args.length < 1) {
            System.out.println("Usage: <command> [arguments]");
            System.out.println(" - Get events by username (first 10 events): <username>");
            System.out.println(" - Get events by username (custom limit, page): <username> <limit?> <page?>");
            return;
        }

        final int DEFAULT_LIMIT = 10;
        final int DEFAULT_PAGE = 1;

        String username = args[0];
        int limit = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_LIMIT;
        int page = args.length > 2 ? Integer.parseInt(args[2]) : DEFAULT_PAGE;

        fetchEvent(username, limit, page);
    }
}