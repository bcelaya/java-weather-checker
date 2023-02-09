import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import org.json.JSONObject;

public class WeatherChecker {
    private static final String API_KEY = "your_api_key_here";

    public static void main(String[] args) {
        try {
            // Construct URL to retrieve weather data for Benicarló
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Benicarl%C3%B3,ES&appid=" + API_KEY);

            // Open connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Read the response from the API
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            // Parse the JSON response from the API
            JSONObject weatherData = new JSONObject(response.toString());
            JSONObject mainData = weatherData.getJSONObject("main");
            double temperature = mainData.getDouble("temp");
            JSONObject weather = weatherData.getJSONArray("weather").getJSONObject(0);
            String conditions = weather.getString("main");

            // Display the temperature and weather conditions
            System.out.println("Temperature: " + temperature + "°C");
            System.out.println("Conditions: " + conditions);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
