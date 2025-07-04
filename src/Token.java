import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Token {
    // Singleton instance
    private static Token instance;

    // Cached token
    private final String token;

    // Private constructor
    private Token() {
        this.token = loadTokenFromFile();
    }

    // Public method to get the singleton instance
    public static Token getInstance() {
        if (instance == null) {
            instance = new Token();
        }
        return instance;
    }

    // Public method to get the token
    public String getToken() {
        return token;
    }

    public static String loadTokenFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("token.txt"))) {
            return reader.readLine().trim();
        } catch (IOException e) {
            System.err.println("Failed to read token: " + e.getMessage());
            return null;
        }
    }
}
