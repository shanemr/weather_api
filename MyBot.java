
import org.jibble.pircbot.*;
import java.io.IOException;

// MyBot class that holds all code to run bot in MyBotMain
public class MyBot extends PircBot {
    // Default constructor that automatically sets the bots nick.
    public MyBot() {
        this.setAutoNickChange(true);
    }

    // Creating REST-API object to connect to weather api.
    RestApi weatherAPI = new RestApi();
    // Creating REST-API object to connect to jokes api.
    RestApi jokesAPI = new RestApi();


    // This Displays a welcome message when a user enters the chat room.
    @Override
    protected void onJoin(String channel, String sender, String login, String hostname) {
        sendMessage(channel, "Hello. Type the word \"commands\" for suggested queries.");
    }

    // This handles all incoming commands from the user and displays the appropriate response to user.
    public void onMessage(String channel, String sender, String login, String hostname, String message) {

        if (message.toLowerCase().contains("commands")){
            // Displays the list of valid commands the user can use.
            sendMessage(channel , "You can ask me for weather information by typing \"weather\" and the desired zip code.");
            sendMessage(channel, "I can tell you a hilarious joke if you would like. All you have to do ask me for a \"joke\".");
            sendMessage(channel,"I can tell you the current date and time if you ask me for the \"date\" and/or \"time\".");
            sendMessage(channel, "If you wish to end the chat, simply type \"close bot\" and I'll disconnect.");

        } else if(message.toLowerCase().contains("time") || message.toLowerCase().contains("date")) {
            // Displays current date and time.
            String time = new java.util.Date().toString();
            sendMessage(channel, ": The date and time is  " + time);

        } else if (message.toLowerCase().contains("weather")) {
            try {
                // Calls the weather api and displays weather information to user.
                sendMessage(channel, weatherAPI.getWeatherInfo(message));
            } catch (IOException e) {
                // Lets user know the weather request was not in a valid format.
                sendMessage(channel, "Invalid weather search. Make sure to include a proper zip code.");
            } catch (InterruptedException e) {
                // Lets user know the weather request was not in a valid format.
                sendMessage(channel, "Invalid weather search. Make sure to include a proper zip code.");
            } catch (NullPointerException e) {
                // Lets user know the weather request was not in a valid format.
                sendMessage(channel, "Invalid weather search. Make sure to include a proper zip code.");
            }

        } else if (message.toLowerCase().contains("joke")) {
            try {
                // Calls jokes api and displays a joke to the user .
                sendMessage(channel, jokesAPI.getDadJokes());
            } catch (IOException e) {
                // Displays joke limit reached when max api calls have been reached.
                sendMessage(channel, "Sorry. I'm all out of jokes today :( . Ask me again tomorrow!");
            } catch (InterruptedException e) {
                // Displays joke limit reached when max api calls have been reached.
                sendMessage(channel, "Sorry. I'm all out of jokes today :( . Ask me again tomorrow!");
            } catch (NullPointerException e) {
                // Displays joke limit reached when max api calls have been reached.
                sendMessage(channel, "Sorry. I'm all out of jokes today :( . Ask me again tomorrow!");
            }

        } else if(message.toLowerCase().contains("close bot")){
                // Says goodbye to the user and disconnects bot.
                sendMessage(channel,"Goodbye. Thanks for chatting!");
                // Delays disconnect method so goodbye message will display before disconnecting the bot.
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    disconnect();
                }
                // Disconnects bot from server.
                disconnect();

        } else{
            // If the user enters a non valid command this lets them know they made an error.
            sendMessage(channel, "Invalid input. Check spelling and reenter or type \"commands\" to see valid queries. ");
        }
    }
}
