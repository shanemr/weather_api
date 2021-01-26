
import com.google.gson.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

// Rest api class that calls api sources to get data for user.
public class RestApi {
    // Default constructor to be used for API calls in MyBot class.
    public RestApi() {}

    // This method gets the weather info for a desired location from api.
    public String getWeatherInfo(String city) throws IOException, InterruptedException {

        // Calling method to correct string format of argument of getWeatherInfo method for url api call.
        String cityZip = zipCode(city);

        // Creating new instance of HttpClient object.
        HttpClient client = HttpClient.newHttpClient();

        // Building a new request to connect to api source.
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?zip=" + cityZip + "&units=imperial&appid=e06b2db9b4d61a4660827a98dc4b9c52"))
                .timeout(Duration.ofSeconds(5))
                .header("accept", "application/json")
                .build();

        // Sending request to api source and storing that response in a string type.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Creating new Gson object.
        Gson gson = new Gson();

        // Parses and maps the Json to myWeather object.
        Weather myWeather = gson.fromJson(response.body(), Weather.class);

        // Returns weather information for given zip code.
        return myWeather.getWeather();
    }


    // This method is used to get random jokes from api.
    public String getDadJokes() throws IOException, InterruptedException {

        // Creating new HttpClient object.
        HttpClient client = HttpClient.newHttpClient();

        // Building a new request to connect to api source.
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://dad-jokes.p.rapidapi.com/random/joke"))
                .timeout(Duration.ofSeconds(5))
                .header("x-rapidapi-host", "dad-jokes.p.rapidapi.com")
                .header("x-rapidapi-key", "86ad62c053msh741d7c820ed9c8bp1d6acbjsn14eac50ec840")
                .build();

        // Sending request to api source and storing that response in a string type.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Creating new Gson object.
        Gson gson = new Gson();

        // Mapping Json to myDadJoke object.
        DadJokes myDadJoke = gson.fromJson(response.body(), DadJokes.class);

        // Returns a random joke.
        return myDadJoke.getJoke();
    }

        /* This method parses the city argument of the getWeather method and deletes everything except numbers
           so it can be correctly used in the api url call.
        */
        public String zipCode (String a){
            String zip = a.replaceAll("[^0-9]", "");
            return zip;
        }
}

