package at.meowww.AsukaDiscord.command;

import at.meowww.AsukaDiscord.BotHandler;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class InvokeSender  implements CommandSender {

    private BotHandler handler;
    private CommandSender cs;

    public InvokeSender(BotHandler handler, CommandSender sender) {
        this.handler = handler;
        this.cs = sender;
    }

    public void sendMessage(String s) {
        cs.sendMessage(s);
        handler.bot.sendMessage(handler.consoleChannelName, s);
    }

    public void sendMessage(String[] strings) {
        cs.sendMessage(strings);
        String str = "";
        for (String s : strings) {
            str += s + "\n";
        }
        handler.bot.sendMessage(handler.consoleChannelName, str);
    }

    public Server getServer() {
        return cs.getServer();
    }

    public String getName() {
        return cs.getName();
    }

    public Spigot spigot() {
        return cs.spigot();
    }

    public boolean isPermissionSet(String s) {
        return cs.isPermissionSet(s);
    }

    public boolean isPermissionSet(Permission permission) {
        return cs.isPermissionSet(permission);
    }

    public boolean hasPermission(String s) {
        return cs.hasPermission(s);
    }

    public boolean hasPermission(Permission permission) {
        return cs.hasPermission(permission);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return cs.addAttachment(plugin, s, b);
    }

    public PermissionAttachment addAttachment(Plugin plugin) {
        return cs.addAttachment(plugin);
    }

    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return cs.addAttachment(plugin, s, b, i);
    }

    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return cs.addAttachment(plugin, i);
    }

    public void removeAttachment(PermissionAttachment permissionAttachment) {
        cs.removeAttachment(permissionAttachment);
    }

    public void recalculatePermissions() {
        cs.recalculatePermissions();
    }

    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return cs.getEffectivePermissions();
    }

    public boolean isOp() {
        return cs.isOp();
    }

    public void setOp(boolean b) {
        cs.setOp(b);
    }
}
