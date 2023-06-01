package PersikNaYmnichax.gui.windows;

import PersikNaYmnichax.localization.LanguageChangeListener;
import PersikNaYmnichax.gui.closingWindows.CloseWindow;
import PersikNaYmnichax.savingPersonalSettings.SettingsChangeListener;

import java.awt.*;
import javax.swing.JPanel;

public class GameWindow extends GeneralWindowSettings {

    public GameWindow(LanguageChangeListener languageChangeListener, SettingsChangeListener settingsChangeListener, CloseWindow closeWindow) {
        super(languageChangeListener, settingsChangeListener, "window.Game", closeWindow);
        GameVisualizer visualizer = new GameVisualizer();

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);

        pack();
    }
}