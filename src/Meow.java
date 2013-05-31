import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.InviteEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

@SuppressWarnings("rawtypes")
public class Meow extends ListenerAdapter implements Listener {

	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByExtension("js");
	
	
	@Override
	public void onPrivateMessage(PrivateMessageEvent event) {
		String message = event.getMessage();
		String[] args = message.split(" ");
		if(args[0].equals("!join")) {
			String channel = args[1];
			if(!channel.startsWith("#")) {
				channel = "#"+channel;
			}
			event.getBot().joinChannel(channel);
		}
		if(args[0].equalsIgnoreCase("!leave")) {
			event.getBot().partChannel(event.getBot().getChannel(args[1]), "Gomeow told me too");
			event.getUser().sendMessage("Left!");
			return;
		}
	}
	
	@Override
	public void onInvite(InviteEvent event) {
		event.getBot().joinChannel(event.getChannel());
	}
	
	@Override
	public void onMessage(MessageEvent event) throws Exception {
		String message = event.getMessage();
		String[] args = message.split(" ");
		if(args[0].equalsIgnoreCase("!awesome")) {
			event.getBot().sendMessage(event.getChannel(),"I am awesome");
		}
		else if(args[0].equalsIgnoreCase("!cookie")) {
			if(args.length == 1) {
				event.getBot().sendAction(event.getChannel(), "gives everyone a cookie!");
			}
			else if(args.length == 2) {
				event.getBot().sendAction(event.getChannel(), "gives "+args[1]+" a cookie!");
			}
			else {
				StringBuilder sb = new StringBuilder();
				for(String arg:args) {
					if(!arg.equalsIgnoreCase(args[0])) {
						sb.append(arg).append(", ");
					}
				}
				event.getBot().sendAction(event.getChannel(), "gives "+sb.toString()+" cookies!");
			}
		}
		else if(args[0].equalsIgnoreCase("!ping")) {
			event.getBot().sendMessage(event.getChannel(), "PONG!");
		}
		else if(args[0].equalsIgnoreCase("!eval")) {
			if(message.contains("while")) {
				event.getBot().sendMessage(event.getChannel(), "While loops are not allowed!");
				return;
			}
			String js = message.substring(5);
			try {
				Object o = engine.eval(js);
				event.getBot().sendMessage(event.getChannel(), o.toString());
			} catch (Exception e) {
				if(!(e instanceof NullPointerException)) {
					e.printStackTrace();
					event.getBot().sendMessage(event.getChannel(), "Error!");
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		String[] channels = {"#drtshock", "#gomeow"};
		PircBotX bot = new PircBotX();
		bot.setName("Meow");
		bot.setVerbose(true);
		bot.getListenerManager().addListener(new Meow());
		bot.connect("irc.esper.net");
		for(String c:channels) {
			bot.joinChannel(c);
		}
	}
}