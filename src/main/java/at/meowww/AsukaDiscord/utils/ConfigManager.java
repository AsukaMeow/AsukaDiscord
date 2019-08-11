package at.meowww.AsukaDiscord.utils;

import at.meowww.AsukaDiscord.AsukaDiscord;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfiguration;

;

public class ConfigManager {

    private AsukaDiscord plugin;
    private FileConfiguration config;

    public MemorySection botConfig;

    public ConfigManager(AsukaDiscord plugin) {
        this.plugin = plugin;
    }

    public void loadConfig () {
        this.config = this.plugin.getConfig();
        this.config.options().copyDefaults(true);
        this.plugin.saveConfig();
        this.botConfig = (MemorySection) this.config.get("Bot");
    }

    public void reloadConfig () {
        this.loadConfig();
    }

    public void saveConfig () {
        this.config.set("Bot", this.botConfig);
        this.plugin.saveConfig();
    }
}