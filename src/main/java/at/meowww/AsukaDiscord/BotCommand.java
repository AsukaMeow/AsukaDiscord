package at.meowww.AsukaDiscord;

import org.bukkit.command.CommandSender;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BotCommand {

    private static final String[] COMMANDS = {
            "status", "toggle"
    };

    public static List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS));
        List<String> collections = new ArrayList<>();

        StringUtil.copyPartialMatches(strings[0], completions, collections);
        Collections.sort(collections);

        return collections;
    }

    public static boolean onCommand(AsukaDiscord plugin, CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (strings.length > 0) {
            BotHandler handler = AsukaDiscord.botHandler;
            if (strings[0].equalsIgnoreCase("status") || strings[0].equalsIgnoreCase("s")) {
                commandSender.sendMessage(String.valueOf(handler.enable));
            } else if (strings[0].equalsIgnoreCase("toggle") || strings[0].equalsIgnoreCase("t")) {
                handler.enable = !handler.enable;
                handler.toggleRegister();
                commandSender.sendMessage("Enable change to " + String.valueOf(handler.enable));
            }
        } else {
            String msg = "";
            msg += "asuka bot (status|s)\n";
            msg += "asuka bot (toggle|t)\n";
            commandSender.sendMessage(msg);
        }
        return true;
    }
}