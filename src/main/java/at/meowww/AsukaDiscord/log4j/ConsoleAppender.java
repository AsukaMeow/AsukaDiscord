package at.meowww.AsukaDiscord.log4j;

import at.meowww.AsukaDiscord.AsukaDiscord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.AbstractAppender;

public class ConsoleAppender extends AbstractAppender {

    public ConsoleAppender () {
        super(AsukaDiscord.INSTANCE.getName(), null, null, false);
    }


    @Override
    public void start() {
        Logger rootLogger = (Logger) LogManager.getRootLogger();
        rootLogger.addAppender(this);

        super.start();
    }

    @Override
    public void append(LogEvent logEvent) {
        String line = logEvent.getMessage().getFormattedMessage();
        AsukaDiscord.botHandler.bot.sendMessage(AsukaDiscord.botHandler.consoleChannelName, line);
    }

}
