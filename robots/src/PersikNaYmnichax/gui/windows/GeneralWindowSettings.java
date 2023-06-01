package PersikNaYmnichax.gui.windows;

import PersikNaYmnichax.gui.closingWindows.CloseWindow;
import PersikNaYmnichax.localization.LanguageChangeListener;
import PersikNaYmnichax.savingPersonalSettings.Serializer;
import PersikNaYmnichax.savingPersonalSettings.SettingsChangeListener;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

public class GeneralWindowSettings extends JInternalFrame implements PropertyChangeListener {

    private final String textWindow;

    public GeneralWindowSettings(LanguageChangeListener languageChangeListener, SettingsChangeListener settingsChangeListener, String textWindow, CloseWindow closeWindow){
        super(languageChangeListener.getOldBundle().getString(textWindow), true, true, true, true);

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(closeWindow);

        this.textWindow = textWindow;

        languageChangeListener.addPropertyChangeListener(this);
        settingsChangeListener.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(LanguageChangeListener.descriptionBundle)) {
            ResourceBundle appLang = (ResourceBundle) evt.getNewValue();
            setTitle(appLang.getString(textWindow));
        }
        if (evt.getPropertyName().equals(SettingsChangeListener.descriptionOfSave)) {
            Serializer.serialize(this);
        }
        else if (evt.getPropertyName().equals(SettingsChangeListener.descriptionOfDownload)){
            Serializer.deserialize(this);
        }
    }
}
