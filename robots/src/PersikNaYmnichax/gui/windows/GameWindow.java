package PersikNaYmnichax.gui.windows;

import PersikNaYmnichax.gui.LanguageChangeListener;
import PersikNaYmnichax.gui.closingWindows.CloseWindow;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame implements PropertyChangeListener {

    private ResourceBundle appLang;

    public GameWindow(LanguageChangeListener languageChangeListener, CloseWindow closeWindow) {
        super(languageChangeListener.getOldBundle().getString("window.Game"), true, true, true, true);

        languageChangeListener.addPropertyChangeListener(this);

        GameVisualizer visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addInternalFrameListener(closeWindow);
        pack();
    }

    public void setAppLang(ResourceBundle appLang) {
        this.appLang = appLang;
        setTitle(this.appLang.getString("window.Game"));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.appLang = (ResourceBundle) evt.getNewValue();
        setAppLang(appLang);
    }
}