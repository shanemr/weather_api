
import java.util.Map;
import java.io.IOException;

// Weather class that is used to hold and display weather data from api call
public class Weather {
    //Instance variables used to hold json information.
    private String name;
    private Map<String, Double> main;

    // This method parses the json and displays the weather information for a given city.
    public String getWeather() throws IOException, InterruptedException{
        return  "The current temperature in " + name +  " is " + this.main.get("temp") + "\u2109" + " with a high of " + this.main.get("temp_max") + "\u2109"  +" and a low of " + this.main.get("temp_min") + "\u2109" + ".";

    }
}