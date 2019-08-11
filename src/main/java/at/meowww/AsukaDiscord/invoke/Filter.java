package at.meowww.AsukaDiscord.invoke;

import at.meowww.AsukaDiscord.BotHandler;
import org.bukkit.entity.Player;

public class Filter {

    public BotHandler handler;

    public Filter (BotHandler handler) {
        this.handler = handler;
    }

    public String wrapCommand (Player player, String command) {
        return String.format("%s : %s", player.getDisplayName(), command);
    }

    public boolean canSendMessageToChat (String message) {
        if (message.startsWith("login") || message.startsWith("register")) {
            return false;
        }
        return true;
    }

    public boolean canSendCommandToConsole (String command) {
        if (command.startsWith("/login") || command.startsWith("/register")) {
            return false;
        }
        return true;
    }

    public boolean canSendMessageToConsole (String message) {
        if (message.startsWith("login") || message.startsWith("register")) {
            return false;
        }
        return true;
    }
}
