import java.util.Arrays;

import org.jibble.pircbot.PircBot;


public class MeowBot extends PircBot{
	
	public MeowBot(String name) {
		this.setName(name);
	}
	
	public void onMessage(String channel, String sender, 
			String login, String hostname, String message) {
		if(message.startsWith("!awesome")) {
			this.sendMessage(channel, "I am awesome");
		}
	}
	
	public void onPrivateMessage(String sender, String login, String hostname, String message)  {
		if(sender.contains("gomeow")) {
			String[] args = message.split(" ");
			if(args.length > 1) {
				if(args[0].equalsIgnoreCase("send")) {
					if(Arrays.asList(this.getChannels()).contains(args[1])) {
						this.sendMessage(args[1], message.substring(6+args[1].length()));
						return;
					}
				}
			}
			if(args.length == 2) {
				if(args[0].equalsIgnoreCase("join")) {
					if(!Arrays.asList(this.getChannels()).contains(args[1])) {
						this.joinChannel(args[1]);
						this.sendMessage(sender, "Joined!");
						return;
					}
					else {
						this.sendMessage(sender, "I am already in that channel!");
						return;
					}
				}
				if(args[0].equalsIgnoreCase("leave")) {
					if(Arrays.asList(this.getChannels()).contains(args[1])) {
						this.partChannel(args[1], "Gomeow told me too");
						this.sendMessage(sender, "Success!");
						return;
					}
					else {
						this.sendMessage(sender, "I am not in that channel!");
						return;
					}
				}
			}
			else {
				this.sendMessage(sender, "ERROR!!!!");
			}
		}
		else {
			this.sendMessage(sender, "You're not gomeow! I hate you!");
		}
	}
	
	public void onJoin(String channel, String sender, String login, String hostname) {
		if(sender.equalsIgnoreCase(this.getNick())) {
			this.sendMessage(channel, "Hello, I am SmartBot, and I have come to this channel to loiter...");
			this.sendMessage(channel, "I am smart also, ask me a question.");
		}
	}
}
