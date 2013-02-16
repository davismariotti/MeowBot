
public class Meow {
	
	public static void main(String[] args) throws Exception {
		String[] channels = {"#gomeow"};
		MeowBot bot = new MeowBot("SmartBot");
		
		bot.setVerbose(true);
		
		bot.connect("irc.esper.net");
		for(String channel:channels) {
			bot.joinChannel(channel);
		}
	}
}
