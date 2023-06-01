package PersikNaYmnichax.gui.openingWindows;

import PersikNaYmnichax.localization.LanguageChangeListener;
import PersikNaYmnichax.savingPersonalSettings.SettingsChangeListener;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static PersikNaYmnichax.localization.Language.*;

public class OpenMainFrame extends WindowAdapter {

    private final LanguageChangeListener languageChangeListener;
    private final SettingsChangeListener settingsChangeListener;

    public OpenMainFrame(LanguageChangeListener languageChangeListener, SettingsChangeListener settingsChangeListener){
        this.languageChangeListener = languageChangeListener;
        this.settingsChangeListener = settingsChangeListener;

    }

    @Override
    public void windowOpened(WindowEvent event){
        Preferences preferences = Preferences.userNodeForPackage(ResourceBundle.class);

        try {
            preferences.sync();
            String baseName = preferences.get("baseName", LANGUAGE_RU.getAppLang());
            String lang = preferences.get("lang", "ru");

            languageChangeListener.changeBundle(baseName, new Locale(lang, lang.toUpperCase()));

            Object[] options = {languageChangeListener.getOldBundle().getString("yes"), languageChangeListener.getOldBundle().getString("no")};
            int n = JOptionPane
                    .showOptionDialog(
                            event.getWindow(),
                            languageChangeListener.getOldBundle().getString("downloadSettings.confirmation"),
                            languageChangeListener.getOldBundle().getString("downloadSettings.title"),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
            if (n == 0){
                settingsChangeListener.downloadSettings();
            } else {
                languageChangeListener.changeBundle(
                        LANGUAGE_RU.getAppLang(),
                        new Locale(LANG_LOCALE_LANG_RU.getAppLang(),
                                LANG_LOCALE_COUNTRY_RU.getAppLang())
                );
            }
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }
}
