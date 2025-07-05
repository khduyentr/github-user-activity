import java.util.ArrayList;
import java.util.List;

public class GitEventListParser {
    public static List<GitEvent> parseEventList(String response) {
        List<GitEvent> eventList = new ArrayList<>();


        // 1. emit the [] bracket at the end and the start of response\
        response = response.substring(1, response.length() - 1);
        // 2. split the response by "},{"
        String[] events = response.split("},\\{\"id\"");
        // 3. loop through the list of event as string to parse each item
        for (String event: events) {
            // add { } to the start and the end of each object
            if (!event.startsWith("\\{\"id\"")) event = "\\{\"id\"" + event;
            if (!event.endsWith("}")) event = event + "}";

            GitEvent gitEvent = GitEvent.parse(event);
            eventList.add(gitEvent);
        }

        return eventList;
    }
}
