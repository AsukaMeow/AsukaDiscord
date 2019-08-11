package at.meowww.AsukaDiscord;

import at.meowww.AsukaDiscord.log.LogHandler;
import at.meowww.AsukaDiscord.utils.Handler;
import org.bukkit.configuration.MemoryConfiguration;
import org.bukkit.configuration.MemorySection;

import java.util.logging.Level;

public class BotHandler extends Handler {


    protected LogHandler logHander;
    protected BotListener listener;

    public boolean debug;
    protected String token = null;
    public DiscordBot bot = null;
    public String chatChannelName = null, consoleChannelName = null;


    public BotHandler () {
        this.listener = new BotListener(this);
    }

    @Override
    public void load (MemorySection config) {
        this.config = config;
        this.enable = config.getBoolean("Enable");
        this.debug = config.getBoolean("Debug");

        boolean loadResult = this.discordConfigLoad((MemorySection) config.get("Discord"));

        if (!this.enable) {
            AsukaDiscord.logger.info("Bot enable is off, bot init will not function.");
        } else {
            if (loadResult) {
                this.bot = new DiscordBot(this, this.token);
                this.listener.initListenerBotPart(bot);
            }
            this.listener.register();
        }
    }

    @Override
    public MemorySection save () {
        MemorySection config = new MemoryConfiguration();
        config.set("Enable", this.enable);
        config.set("Debug", this.debug);
        config.set("Discord", this.discordConfigSave());
        return config;
    }

    public void initBot () {
        if (this.enable) {
            this.bot.connect();
            if (!this.chatChannelName.equals("")) {
                logHander = new LogHandler(this, this.bot);
                AsukaDiscord.logger.setLevel(Level.ALL);
                AsukaDiscord.logger.addHandler(logHander);
            }
        }
    }

    public boolean discordConfigLoad (MemorySection config) {
        try {
            this.token = config.getString("Token");
            this.chatChannelName = config.getString("ChatChannelName");
            this.consoleChannelName = config.getString("ConsoleChannelName");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public MemorySection discordConfigSave () {
        MemorySection config = new MemoryConfiguration();
        config.set("Token", this.token);
        config.set("ChatChannelName", this.chatChannelName);
        config.set("ConsoleChannelName", this.consoleChannelName);
        return config;
    }

}
