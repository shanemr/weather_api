
import java.util.Map;
import java.util.ArrayList;

// Dad jokes class that is used to hold and display joke data from api call.
public class DadJokes {
    // ArrayList used to hold json information.
    private ArrayList<Map<String, String>> body;

    // This method parses the json and displays a random joke.
    public String getJoke(){
        return this.body.get(0).get("setup") + " " + this.body.get(0).get("punchline");
    }
}
