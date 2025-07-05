import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
    public static int findMatchingBrace(String str, int openIndex) {
        int depth = 0;
        for (int i = openIndex; i < str.length(); i++) {
            if (str.charAt(i) == '{') depth++;
            else if (str.charAt(i) == '}') depth--;
            if (depth == 0) return i;
        }
        return -1;
    }

    private static String getValueFromLine(String line) {
        String[] parts = line.split(":");

        if (parts.length < 2) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < parts.length; i++) {
            builder.append(parts[i]);
        }

        return builder.toString().replaceAll("^\"|\"$", "");
    }

    public static String extractStringValue(String json, String key) {
        int startIndex = json.indexOf(key);
        int endIndex = json.indexOf(",", startIndex) == -1 ? json.length() - 2 : json.indexOf(",", startIndex);

        String line = json.substring(startIndex -1, endIndex);

        return getValueFromLine(line);
    }

    public static Boolean extractBooleanValue(String json, String key) {
        int startIdx = json.indexOf(key);
        if (startIdx == -1) return null;
        int colonIdx = json.indexOf(':', startIdx);
        int commaIdx = json.indexOf(',', colonIdx);
        int endIdx = commaIdx != -1 ? commaIdx : json.indexOf('}', colonIdx);
        String value = json.substring(colonIdx + 1, endIdx).trim();
        return value.equals("true");
    }

    public static String formatDateTime(String time) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HHmmssX");
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(time, inputFormatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return zonedDateTime.format(outputFormatter);
    }
}
