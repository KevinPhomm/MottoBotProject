package audio;

import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import main.MottoBot;
import manager.GuildMusicManager;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.managers.AudioManager;

public class AudioManagerMotto {
	
	public AudioManagerMotto() {
		
	}

	public void loadAndPlay(final TextChannel channel, final String trackUrl, MottoBot bot) {

		GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild(), bot);

		AudioPlayerManager playerManager = bot.getPlayerManager();
		playerManager.loadItemOrdered(musicManager, trackUrl, new AudioLoadResultHandler() {
			@Override
			public void trackLoaded(AudioTrack track) {
				channel.sendMessage(":musical_note: Ajoutée à la playlist : " + track.getInfo().title).queue();
				play(channel.getGuild(), musicManager, track);
			}

			@Override
			public void playlistLoaded(AudioPlaylist playlist) {
				AudioTrack firstTrack = playlist.getSelectedTrack();

				if (firstTrack == null) {
					firstTrack = playlist.getTracks().get(0);
				}

				channel.sendMessage(":musical_note: Ajoutée à la playlist : " + firstTrack.getInfo().title + " (Titre de la playlist  : "
						+ playlist.getName() + ")").queue();

				playList(channel.getGuild(), musicManager, playlist);
			}

			@Override
			public void noMatches() {
				channel.sendMessage(":musical_note: Rien trouvé avec " + trackUrl).queue();
			}

			@Override
			public void loadFailed(FriendlyException exception) {
				channel.sendMessage(":musical_note: Je ne peux pas jouer : " + exception.getMessage()).queue();
			}
		});
	}
	
	private synchronized static GuildMusicManager getGuildAudioPlayer(Guild guild, MottoBot bot) {
		long guildId = Long.parseLong(guild.getId());
		GuildMusicManager musicManager = bot.getMusicManagers().get(guildId);

		if (musicManager == null) {
			musicManager = new GuildMusicManager(bot.getPlayerManager());
			bot.getMusicManagers().put(guildId, musicManager);
		}

		guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());
		
		return musicManager;
	}

	public void play(Guild guild, GuildMusicManager musicManager, AudioTrack track) {
		connectToFirstVoiceChannel(guild.getAudioManager());

		musicManager.scheduler.queue(track);
	}
	
	public void playList(Guild guild, GuildMusicManager musicManager, AudioPlaylist playlist) {
		connectToFirstVoiceChannel(guild.getAudioManager());

		musicManager.scheduler.queuePlayList(playlist);
	}

	public void skipTrack(TextChannel channel, MottoBot bot, int nbOfSkips) {
		GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild(), bot);
		for(int i = 0; i<nbOfSkips; i++)
		{
			musicManager.scheduler.nextTrack();
		}
		channel.sendMessage(":musical_note: Passer à la prochaine musique : "+musicManager.player.getPlayingTrack().getInfo().title).queue();
	}

	public static void connectToFirstVoiceChannel(AudioManager audioManager) {
		if (!audioManager.isConnected() && !audioManager.isAttemptingToConnect()) {
			for (VoiceChannel voiceChannel : audioManager.getGuild().getVoiceChannels()) {
				audioManager.openAudioConnection(voiceChannel);
				break;
			}
		}
	}
	
	public void clearQueue(TextChannel channel, MottoBot bot) {
		GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild(), bot);
		for(int i = 0; i<musicManager.scheduler.getPlaylist().size(); i++)
			musicManager.scheduler.nextTrack();
	}
	
	public String showPlaylist(TextChannel channel, MottoBot bot, int index) {
		GuildMusicManager musicManager = getGuildAudioPlayer(channel.getGuild(), bot);
		String playlistText = "";
		if(musicManager.player.getPlayingTrack() != null)
		{
			List<String> playlist = musicManager.scheduler.getPlaylist();
			playlistText = "```Playlist :\n0. "+musicManager.player.getPlayingTrack().getInfo().title+"\n";
			if(index>0)
			{
				playlistText = playlistText + "\n";
			}
			if(playlist.size() !=0)
			{
				for(int i = index-1 ; i<playlist.size();i++)
				{
					if((playlistText+(i+1) + ". " + playlist.get(i+1) + "\n").length() >1996)
					{
						playlistText = playlistText + "...";
						break;
					}
					playlistText = playlistText + (i+1) + ". " + playlist.get(i) + "\n";
					
				}
			}
			playlistText = playlistText + "```";
		}
		else
		{
			playlistText = "```No playlist```";
		}
		return playlistText;
	}

}