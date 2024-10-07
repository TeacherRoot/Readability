import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Readability {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.openai.com/v1/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer YOUR_API_KEY"); // Replace with your API key
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            
            String inputJson = "{\n" +
                "  \"model\": \"text-davinci-003\",\n" +
                "  \"prompt\": \"Hello, world!\",\n" +
                "  \"max_tokens\": 100\n" +
                "}";

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = inputJson.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (Scanner scanner = new Scanner(connection.getInputStream(), "UTF-8")) {
                String response = scanner.useDelimiter("\\A").next();
                System.out.println(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
