package at.meowww.AsukaDiscord;

import at.meowww.AsukaDiscord.command.Command;
import at.meowww.AsukaDiscord.command.InvokeSender;
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
            this.getTextChannelByName(channelName).sendMessage(message.replaceAll("§.", "")).queue();
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
                    Bukkit.getScheduler().callSyncMethod(AsukaDiscord.INSTANCE, () ->
                            Bukkit.dispatchCommand(new InvokeSender(this.handler, Bukkit.getConsoleSender()), event.getMessage().getContentDisplay())
                    );
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