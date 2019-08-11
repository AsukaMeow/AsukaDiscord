package at.meowww.AsukaDiscord.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogRecord;

public class Formatter extends java.util.logging.Formatter {

    private static final DateFormat df = new SimpleDateFormat("[hh:mm:ss ");

    @Override
    public String format(LogRecord record) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append(df.format(new Date(record.getMillis()))).append(record.getLevel());
        sb.append(" ]").append(record.getMessage());
        return sb.toString();
    }

}
