
public class Meow {
	
	public static void main(String[] args) throws Exception {
		String[] channels = {"#infinitenight"};
		MeowBot bot = new MeowBot("MeowBot");
		
		
		bot.connect("irc.esper.net");
		for(String channel:channels) {
			bot.joinChannel(channel);
			
			bot.sendMessage(channel, "Hi hawkfalcon!");
		}
	}
}
