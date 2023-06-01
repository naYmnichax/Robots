package PersikNaYmnichax.savingPersonalSettings;


import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;


public class SettingsChangeListener {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public static String descriptionOfSave = "the status of saving settings";
    public static String descriptionOfDownload = "loading status of settings";

    private ResourceBundle bundle;

    public SettingsChangeListener(ResourceBundle appLang){
        bundle = appLang;
    }

    public void setAppLang(ResourceBundle appLang){
        bundle = appLang;
    }

    public void saveSettings(){
        Preferences preferences = Preferences.userNodeForPackage(ResourceBundle.class);
        preferences.put("baseName", bundle.getBaseBundleName());
        preferences.put("lang", bundle.getLocale().toString());
        support.firePropertyChange(descriptionOfSave,  null, null);
    }

    public void downloadSettings(){
        support.firePropertyChange(descriptionOfDownload, null, null);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(descriptionOfSave, pcl);
        support.addPropertyChangeListener(descriptionOfDownload, pcl);
    }
}
