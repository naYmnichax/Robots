package PersikNaYmnichax.localization;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageChangeListener {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public static String descriptionBundle = "the bundle is outdated";

    private ResourceBundle oldBundle;

    public LanguageChangeListener(ResourceBundle appLang){
        oldBundle = appLang;
    }

    public ResourceBundle getOldBundle(){
        return oldBundle;
    }

    public void changeBundle(String lang, Locale locale){
        ResourceBundle newBundle = ResourceBundle.getBundle(lang, locale);

        support.firePropertyChange(descriptionBundle, oldBundle, newBundle);
        oldBundle = newBundle;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(descriptionBundle,pcl);
    }
}
