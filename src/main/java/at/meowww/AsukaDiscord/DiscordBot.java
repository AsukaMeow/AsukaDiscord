package at.meowww.AsukaDiscord;

import at.meowww.AsukaDiscord.command.Command;
import com.google.common.base.Splitter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

public class DiscordBot extends ListenerAdapter {

    private BotHandler handler;
    private Command command;
    private String token;
    private JDABuilder jdaBuilder;
    private JDA jda = null;

    public DiscordBot(BotHandler handler, String token) {
        this.handler = handler;
        this.command = new Command(this);
        this.token = token;
        this.jdaBuilder = new JDABuilder(AccountType.BOT);
    }

    public void connect() {
        try {
            this.jdaBuilder.setToken(this.token);
            this.jdaBuilder.addEventListener(this);
            this.jda = this.jdaBuilder.buildBlocking();
        } catch (Exception e) {
            if (this.handler.debug) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String channelName, String message) {
        try {
            String msg = message.replaceAll("§.", "");
            if (msg.length() > 2000) {
                for (final String slice : Splitter.fixedLength(2000).split(msg)) {
                    this.getTextChannelByName(channelName).sendMessage(slice).queue();
                }
            } else {
                this.getTextChannelByName(channelName).sendMessage(msg).queue();
            }
        } catch (Exception e) {
            if (this.handler.debug) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            if (!event.getAuthor().isBot()) {
                if (event.getMessage().getContentDisplay().startsWith("!")) {
                    command.onCommand(event.getMessage());
                } else if (event.getChannel().getName().equals(this.handler.chatChannelName)) {
                    Bukkit.broadcastMessage(
                            String.format("[Discord] %s : %s", event.getAuthor().getName(), event.getMessage()));
                } else if (event.getChannel().getName().equals(this.handler.consoleChannelName)) {
                    Bukkit.getScheduler().runTask(AsukaDiscord.INSTANCE, () -> Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), event.getMessage().getContentRaw()));
                }
            }
        } catch (Exception e) {
            if (this.handler.debug) {
                e.printStackTrace();
            }
        }
    }

    public TextChannel getTextChannelByName(String name) {
        for (TextChannel tc : this.jda.getTextChannels()) {
            if (tc.getName().equals(name)) {
                return tc;
            }
        }
        return null;
    }
}