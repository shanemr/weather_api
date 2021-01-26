
// Main class that runs bot
public class MyBotMain {

    public static void main(String[] args) throws Exception {
        // Creating object instance of MyBot
        MyBot bot = new MyBot();

        // Enabling debugging output.
        bot.setVerbose(true);

        // Connecting to the IRC server.
        bot.connect("irc.freenode.net");

        // Joining the #mychannel channel.
        bot.joinChannel("#mychannel");
    }
}