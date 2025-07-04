import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Token {
    public static String getToken() {
        Path path = Paths.get(System.getProperty("user.dir"), "token.txt");

        System.out.println(path.toFile());
        try (BufferedReader reader = new BufferedReader(new FileReader("token.txt"))) {
            return reader.readLine().trim();
        } catch (IOException e) {
            System.err.println("Failed to read token: " + e.getMessage());
            return null;
        }
    }
}
