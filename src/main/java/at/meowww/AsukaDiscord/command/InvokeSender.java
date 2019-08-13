package at.meowww.AsukaDiscord.command;

import at.meowww.AsukaDiscord.BotHandler;
import org.bukkit.Server;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class InvokeSender  implements ConsoleCommandSender {

    private BotHandler handler;
    private ConsoleCommandSender cs;

    public InvokeSender(BotHandler handler, ConsoleCommandSender sender) {
        this.handler = handler;
        this.cs = sender;
    }

    @Override
    public void sendMessage(String s) {
        cs.sendMessage(s);
        handler.bot.sendMessage(handler.consoleChannelName, s);
    }

    @Override
    public void sendMessage(String[] strings) {
        cs.sendMessage(strings);
        String str = "";
        for (String s : strings) {
            str += s + "\n";
        }
        handler.bot.sendMessage(handler.consoleChannelName, str);
    }

    @Override
    public void sendRawMessage(String s) {
        cs.sendRawMessage(s);
        handler.bot.sendMessage(handler.consoleChannelName, s);
    }

    @Override
    public Server getServer() {
        return cs.getServer();
    }

    @Override
    public String getName() {
        return cs.getName();
    }

    @Override
    public Spigot spigot() {
        return cs.spigot();
    }

    @Override
    public boolean isPermissionSet(String s) {
        return cs.isPermissionSet(s);
    }

    @Override
    public boolean isPermissionSet(Permission permission) {
        return cs.isPermissionSet(permission);
    }

    @Override
    public boolean hasPermission(String s) {
        return cs.hasPermission(s);
    }

    @Override
    public boolean hasPermission(Permission permission) {
        return cs.hasPermission(permission);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b) {
        return cs.addAttachment(plugin, s, b);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin) {
        return cs.addAttachment(plugin);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, String s, boolean b, int i) {
        return cs.addAttachment(plugin, s, b, i);
    }

    @Override
    public PermissionAttachment addAttachment(Plugin plugin, int i) {
        return cs.addAttachment(plugin, i);
    }

    @Override
    public void removeAttachment(PermissionAttachment permissionAttachment) {
        cs.removeAttachment(permissionAttachment);
    }

    @Override
    public void recalculatePermissions() {
        cs.recalculatePermissions();
    }

    @Override
    public Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return cs.getEffectivePermissions();
    }

    @Override
    public boolean isOp() {
        return cs.isOp();
    }

    @Override
    public void setOp(boolean b) {
        cs.setOp(b);
    }

    @Override
    public boolean isConversing() {
        return cs.isConversing();
    }

    @Override
    public void acceptConversationInput(String s) {
        cs.acceptConversationInput(s);
    }

    @Override
    public boolean beginConversation(Conversation conversation) {
        return cs.beginConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation) {
        cs.abandonConversation(conversation);
    }

    @Override
    public void abandonConversation(Conversation conversation, ConversationAbandonedEvent conversationAbandonedEvent) {
        cs.abandonConversation(conversation, conversationAbandonedEvent);
    }

}
