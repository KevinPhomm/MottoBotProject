package commandes;

import java.util.ArrayList;
import java.util.List;

import main.Main;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CmdClear implements Commande {

	@Override
	public String getName() {
		return "mottoclear";
	}

	@Override
	public List<String> getAliases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean run(Main bot, MessageReceivedEvent e, String arguments) {
		List<Message> temp = new ArrayList<Message>();
		int nbMessageInitial = 0;
		bot.addMsg(e.getMessage());
		while (bot.getMsgTab().size() != 0)
		{
			Message messageToDelete = bot.getMsgTab().get(bot.getMsgTab().size() - 1);
			if(messageToDelete.getChannel().equals(e.getMessage().getChannel()))
			{
				messageToDelete.delete().queue();
				nbMessageInitial++;
			}
			else
			{
				temp.add(messageToDelete);
			}
			bot.getMsgTab().remove(bot.getMsgTab().size() - 1); 
		}
		bot.setMsgTab(temp);
		
		if (nbMessageInitial > 1)
		{
			e.getChannel().sendMessage(nbMessageInitial + " messages effacés.").queue();
		}
		return true;
	}

}
