package PersikNaYmnichax.log;

public enum LogLevel
{
    Trace(0),
    Debug(1),
    Info(2),
    Warning(3),
    Error(4),
    Fatal(5);
    
    private int Level;
    
    private LogLevel(int Level)
    {
        this.Level = Level;
    }
    
    public int level()
    {
        return Level;
    }
}

