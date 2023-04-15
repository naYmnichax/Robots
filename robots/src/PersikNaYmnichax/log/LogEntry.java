package PersikNaYmnichax.log;

public class LogEntry {
    private final LogLevel logLevel;
    private final String strMessage;

    public LogEntry(LogLevel logLevel, String strMessage) {
        this.strMessage = strMessage;
        this.logLevel = logLevel;
    }

    public String getMessage() {
        return strMessage;
    }

    public LogLevel getLevel() {
        return logLevel;
    }
}

