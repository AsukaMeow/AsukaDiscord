package at.meowww.AsukaDiscord.command;

import at.meowww.AsukaDiscord.DiscordBot;
import net.dv8tion.jda.core.entities.Message;

public class Command {
        private DiscordBot bot;

        public Command(DiscordBot bot) {
            this.bot = bot;
        }

        public void onCommand (Message message) {
            String cmd = message.getContentDisplay().substring(1);
            if (cmd.startsWith("IP") || cmd.startsWith("ip")) {
                bot.sendMessage(message.getChannel().getName(), "asuka.meowww.at");
            }
        }

}
