package at.meowww.AsukaDiscord.utils;

import at.meowww.AsukaDiscord.AsukaDiscord;

import static org.bukkit.Bukkit.getServer;

public class Listener implements org.bukkit.event.Listener {

    public Listener () {}

    public void register () {
        getServer().getPluginManager().registerEvents(this, AsukaDiscord.INSTANCE);
    }

}