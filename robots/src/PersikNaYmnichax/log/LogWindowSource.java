package PersikNaYmnichax.log;

import java.util.ArrayList;
import java.util.Collections;

public class LogWindowSource
{
    private int queueLength;
    
    private ArrayList<LogEntry> messages;
    private final ArrayList<LogChangeListener> listeners;
    private volatile LogChangeListener[] activeListener;
    
    public LogWindowSource(int iQueueLength) 
    {
        queueLength = iQueueLength;
        messages = new ArrayList<LogEntry>(iQueueLength);
        listeners = new ArrayList<LogChangeListener>();
    }
    
    public void registerListener(LogChangeListener listener)
    {
        synchronized(listeners)
        {
            listeners.add(listener);
            activeListener = null;
        }
    }
    
    public void unregisterListener(LogChangeListener listener)
    {
        synchronized(listeners)
        {
            listeners.remove(listener);
            activeListener = null;
        }
    }
    
    public void append(LogLevel logLevel, String strMessage)
    {
        LogEntry entry = new LogEntry(logLevel, strMessage);
        messages.add(entry);
        LogChangeListener [] activeListeners = activeListener;
        if (activeListeners == null)
        {
            synchronized (listeners)
            {
                if (activeListener == null)
                {
                    activeListeners = listeners.toArray(new LogChangeListener [0]);
                    activeListener = activeListeners;
                }
            }
        }
        for (LogChangeListener listener : activeListeners)
        {
            listener.onLogChanged();
        }
    }
    
    public int size()
    {
        return messages.size();
    }

    public Iterable<LogEntry> range(int startFrom, int count)
    {
        if (startFrom < 0 || startFrom >= messages.size())
        {
            return Collections.emptyList();
        }
        int indexTo = Math.min(startFrom + count, messages.size());
        return messages.subList(startFrom, indexTo);
    }

    public Iterable<LogEntry> all()
    {
        return messages;
    }
}
