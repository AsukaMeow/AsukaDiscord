package at.meowww.AsukaDiscord;

import org.bukkit.configuration.MemorySection;

public class ConfigManager extends at.meowww.AsukaMeow.util.ConfigManager {

    public MemorySection botConfig;

    public ConfigManager(AsukaDiscord plugin) {
        super(plugin);
    }

    @Override
    public void loadConfig () {
        super.loadConfig();
        this.botConfig = (MemorySection) this.config.get("Bot");
    }

    @Override
    public void saveConfig () {
        this.config.set("Bot", this.botConfig);
        super.saveConfig();
    }
}