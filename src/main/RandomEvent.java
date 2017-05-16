package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageBulkDeleteEvent;
import net.dv8tion.jda.core.events.message.MessageDeleteEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.user.UserOnlineStatusUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import pokemon.Pokemon;
import pokemon.PokemonSpecies;

public class RandomEvent extends ListenerAdapter {
	protected enum FoodEmotes {
		APPLE("🍏"),
		BREAD("🍞"),
		CHEESE("🧀"),
		MEAT("🍖"),
		COOKIE("🍪"),
		KIWI("🥝"),
		CARROT("🥕"),
		RICE_BALL("🍙");
		public final String unicode;
		private FoodEmotes(String unicode) {
			this.unicode=unicode;
		}
	}

	private Message eventMessage;
	private boolean finished;
	private Guild guild;
	private Random RNG;
	private MottoBot bot;
	private String targetId;
	
	public RandomEvent(Guild g, MottoBot bot) {
		this.eventMessage = null;
		this.guild = g;
		this.finished = false;
		this.bot = bot;
		this.RNG = new Random();
		this.targetId = null;
	}

	public boolean hasEnded() {
		return this.finished;
	}

	public void run() {
		EmbedBuilder eb = new EmbedBuilder();
		eb.setImage("http://www.pokepedia.fr/images/8/82/Sprite_4_d_115.png");
		Pokemon wildPokemon = new Pokemon(PokemonSpecies.randomEncounter(this.RNG));
		List<Member> eligibleMembers = new ArrayList<Member>();
		for(Member m : this.guild.getMembers()) {
			if(m.getUser().isBot() || m.getUser().isFake())
				continue;
			if(m.getOnlineStatus().equals(OnlineStatus.ONLINE))
				eligibleMembers.add(m);
		}
		this.targetId = eligibleMembers.get(this.RNG.nextInt(eligibleMembers.size())).getUser().getId();
		eb.appendDescription("<@"+this.targetId+"> est attaqué par un "+wildPokemon.species.name);
		MessageEmbed message = eb.build();
		this.guild.getTextChannels().get(this.RNG.nextInt(this.guild.getTextChannels().size())).sendMessage(message).queue(new Consumer<Message>() {
			@Override
			public void accept(Message t) {
				RandomEvent.this.eventMessage = t;
				t.addReaction(FoodEmotes.values()[RandomEvent.this.RNG.nextInt(FoodEmotes.values().length)].unicode).queue();
				t.addReaction("💣").queue();
				t.addReaction("🎊").queue();
				RandomEvent.this.start();
			}
		});
	}
	protected void start() {
		this.bot.getJda().addEventListener(this);
	}

	
    public void onMessageDelete(MessageDeleteEvent e) {
    	if(e.getMessageId().equals(this.eventMessage.getId())) {
    		this.end();
    	}
    }
    
    public void onMessageBulkDelete(MessageBulkDeleteEvent e) {
    	if (e.getMessageIds().contains(this.eventMessage.getId())) {
    		this.end();
    	}
    }
    
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
    	if(e.getUser().isBot())
    		return;
    	
    	if(e.getMessageId().equals(this.eventMessage.getId())) {
	    	System.out.println(e.getUser().getName());
	    	e.getReaction().removeReaction(e.getUser()).queue();
    	}
    }

    public void onUserOnlineStatusUpdate(UserOnlineStatusUpdateEvent e) {
    	if(e.getUser().getId().equals(this.targetId)) {
    		this.end();
    	}
    }

	private void end() {
		this.eventMessage.delete().queue();
    	this.bot.getJda().removeEventListener(this);
    	this.finished = true;
	}
}
