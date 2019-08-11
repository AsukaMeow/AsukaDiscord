package at.meowww.AsukaDiscord.utils;

import at.meowww.AsukaDiscord.AsukaDiscord;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {

    private static final String[] COMMANDS = {"bot"};
    private AsukaDiscord plugin;

    public Command () {}

    public void setExecutor (AsukaDiscord plugin) {
        this.plugin = plugin;
        this.plugin.getCommand("asuka").setExecutor(this);
        this.plugin.getCommand("asuka").setTabCompleter(this);
    }

    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && commandSender.isOp()) || commandSender instanceof ConsoleCommandSender) {
            if (strings.length > 0) {
                String[] nextPass = Arrays.copyOfRange(strings, 1, strings.length);
                if (strings[0].equalsIgnoreCase("bot")) {
                    return at.meowww.AsukaDiscord.BotCommand.onCommand(this.plugin, commandSender, command, s, nextPass);
                }
            }

            String msg = "";
            msg += "asuka bot ...\n";
            commandSender.sendMessage(msg);
            return true;
        } else {
            commandSender.sendMessage("You are not operator!");
            return true;
        }
    }

    public List<String> onTabComplete(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if ((commandSender instanceof Player && commandSender.isOp()) || commandSender instanceof ConsoleCommandSender) {
            String[] nextPass = Arrays.copyOfRange(strings, 1, strings.length);
            if (nextPass.length > 0) {
                if (strings[0].equalsIgnoreCase("bot")) {
                    return at.meowww.AsukaDiscord.BotCommand.onTabComplete(commandSender, command, s, nextPass);
                }
            }
            final List<String> completions = new ArrayList<>(Arrays.asList(COMMANDS));
            List<String> collections = new ArrayList<>();

            StringUtil.copyPartialMatches(strings.length != 0 ? strings[0] : "", completions, collections);
            Collections.sort(collections);

            return collections;
        } else {
            commandSender.sendMessage("You are not operator!");
            return new ArrayList<>();
        }
    }
}
