package at.meowww.AsukaDiscord;

import at.meowww.AsukaDiscord.utils.Command;
import at.meowww.AsukaDiscord.utils.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class AsukaDiscord extends JavaPlugin {

    public static final Logger logger = Logger.getLogger("Minecraft");

    public static AsukaDiscord INSTANCE;

    public static ConfigManager configManager = null;
    public static at.meowww.AsukaDiscord.BotHandler botHandler = null;

    public static Command command;

    @Override
    public void onEnable () {
        INSTANCE = this;

        configManager = new ConfigManager(this);
        botHandler = new BotHandler();

        configManager.loadConfig();
        botHandler.load(configManager.botConfig);
        botHandler.initBot();

        command = new Command();
        command.setExecutor(this);
    }


    @Override
    public void onDisable() {
        configManager.botConfig = botHandler.save();
    }

}
