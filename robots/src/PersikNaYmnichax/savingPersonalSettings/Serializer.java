package PersikNaYmnichax.savingPersonalSettings;

import javax.swing.*;
import java.beans.PropertyVetoException;
import java.util.prefs.Preferences;

public class Serializer {

    public static void serialize(JInternalFrame window){
        String name = window.getClass().toString();
        Preferences pref = Preferences.userNodeForPackage(window.getClass());

        pref.putInt(name + "width", window.getWidth());
        pref.putInt(name + "height", window.getHeight());
        pref.putInt(name + "x", window.getX());
        pref.putInt(name + "y", window.getY());
        pref.putBoolean(name + "icon", window.isIcon());
    }

    public static void deserialize(JInternalFrame window){
        Preferences preferences = Preferences.userNodeForPackage(window.getClass());
        String name = window.getClass().toString();

        window.setSize(
                preferences.getInt(name + "width", 0),
                preferences.getInt(name + "height", 0)
        );
        window.setLocation(
                preferences.getInt(name + "x", 0),
                preferences.getInt(name + "y", 0)
        );
        try {
            window.setIcon(preferences.getBoolean(name + "icon", false));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
}
