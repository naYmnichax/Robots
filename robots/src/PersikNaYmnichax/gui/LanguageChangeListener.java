package PersikNaYmnichax.gui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageChangeListener {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private ResourceBundle oldBundle;

    LanguageChangeListener(ResourceBundle appLang){
        oldBundle = appLang;
    }

    public ResourceBundle getOldBundle(){
        return oldBundle;
    }

    public void changeBundle(String lang, Locale locale){
        ResourceBundle newBundle = ResourceBundle.getBundle(lang, locale);
        String descriptionBundle = "the bundle is outdated";
        support.firePropertyChange(descriptionBundle, oldBundle, newBundle);
        oldBundle = newBundle;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl){
        support.addPropertyChangeListener(pcl);
    }
}
