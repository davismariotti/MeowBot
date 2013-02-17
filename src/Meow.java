
public class Meow {
	
	public static void main(String[] args) throws Exception {
		String[] channels = {"#gomeow"};
		MeowBot bot = new MeowBot("MeowBot");
		
		bot.setVerbose(true);
		
		bot.connect("irc.esper.net");
		bot.identify("superduperpassword");
		for(String channel:channels) {
			bot.joinChannel(channel);
		}
	}
}
