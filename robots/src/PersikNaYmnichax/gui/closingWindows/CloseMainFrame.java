package PersikNaYmnichax.gui.closingWindows;

import PersikNaYmnichax.localization.LanguageChangeListener;
import PersikNaYmnichax.savingPersonalSettings.SettingsChangeListener;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ResourceBundle;


public class CloseMainFrame extends WindowAdapter implements PropertyChangeListener {

    private ResourceBundle appLang;
    private final SettingsChangeListener settingsChangeListener;


    public CloseMainFrame(LanguageChangeListener languageChangeListener, SettingsChangeListener settingsChangeListener) {
        this.settingsChangeListener = settingsChangeListener;

        languageChangeListener.addPropertyChangeListener(this);
        this.settingsChangeListener.addPropertyChangeListener(this);

        setAppLang(languageChangeListener.getOldBundle());
    }

    public void setAppLang(ResourceBundle appLang) {
        this.appLang = appLang;
    }

    @Override
    public void windowClosing(WindowEvent event) {
        Object[] options = {appLang.getString("yes"), appLang.getString("no")};
        int n = JOptionPane
                .showOptionDialog(
                        event.getWindow(),
                        appLang.getString("close.confirmation"),
                        appLang.getString("close.title"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
        if (n == 0) {
            settingsChangeListener.saveSettings();

            event.getWindow().setVisible(false);
            event.getWindow().dispose();
            System.exit(0);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(LanguageChangeListener.descriptionBundle)) {
            this.appLang = (ResourceBundle) evt.getNewValue();
            setAppLang(appLang);
        }
    }
}
