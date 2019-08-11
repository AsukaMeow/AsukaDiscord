package at.meowww.AsukaDiscord;

import at.meowww.AsukaDiscord.invoke.Filter;
import at.meowww.AsukaDiscord.utils.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class BotListener extends Listener {

    private BotHandler handler;
    protected Filter filter;
    private DiscordBot bot;
    private boolean consoleEnable = false;

    public BotListener (BotHandler handler) {
        this.handler = handler;
    }

    public void initListenerBotPart(DiscordBot bot) {
        this.filter = new Filter(this.handler);
        this.bot = bot;
    }

    @EventHandler
    public void onPlayerChat (AsyncPlayerChatEvent event) {
        String message = String.format("%s : %s", event.getPlayer().getDisplayName(), event.getMessage());
        if (this.filter.canSendMessageToChat(event.getMessage())) {
            this.bot.sendMessage(this.handler.chatChannelName, message);
        }
        if (this.filter.canSendMessageToConsole(event.getMessage())) {
            this.bot.sendMessage(this.handler.consoleChannelName, message);
        }
    }

    @EventHandler
    public void onServerExecuteCommand (ServerCommandEvent event) {
        String message = String.format("Console : %s", event.getCommand());
        this.bot.sendMessage(this.handler.consoleChannelName, message);
    }

    @EventHandler
    public void onPlayerCommand (PlayerCommandPreprocessEvent event) {
        String message = String.format("%s : %s", event.getPlayer().getDisplayName(), event.getMessage());
        if (this.filter.canSendCommandToConsole(event.getMessage())) {
            this.bot.sendMessage(this.handler.consoleChannelName, this.filter.wrapCommand(event.getPlayer(), event.getMessage()));
        }
    }

    @EventHandler
    public void onServerStarted (WorldLoadEvent event) {
        this.consoleEnable = true;
    }

    public void setConsoleEnable (boolean enable) {
        this.consoleEnable = enable;
    }
}
