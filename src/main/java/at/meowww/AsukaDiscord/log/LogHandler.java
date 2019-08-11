package at.meowww.AsukaDiscord.log;

import at.meowww.AsukaDiscord.BotHandler;
import at.meowww.AsukaDiscord.DiscordBot;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;

public class LogHandler extends ConsoleHandler {

    private BotHandler handler;
    private DiscordBot bot = null;

    public LogHandler(BotHandler handler, DiscordBot bot) {
        this.handler = handler;
        this.bot = bot;
        setLevel(Level.ALL);
        setFormatter(new Formatter());
    }

    @Override
    public void publish(LogRecord record) {
        this.bot.sendMessage(this.handler.consoleChannelName, getFormatter().formatMessage(record));
    }

    @Override
    public void flush() {

    }

    @Override
    public void close() throws SecurityException {

    }
    
}
