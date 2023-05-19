package PersikNaYmnichax.gui.windows;

import PersikNaYmnichax.gui.RestCaller;
import PersikNaYmnichax.gui.closingWindows.CloseWindow;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame implements PropertyChangeListener {

    private ResourceBundle appLang;

    public GameWindow(RestCaller restCaller, CloseWindow closeWindow) {
        super(restCaller.getOldBundle().getString("window.Game"), true, true, true, true);

        restCaller.addPropertyChangeListener(this);

        GameVisualizer visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addInternalFrameListener(closeWindow);
        pack();
    }

    public void setAppLang(ResourceBundle appLang){
        this.appLang = appLang;
        setTitle(this.appLang.getString("window.Game"));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.appLang = (ResourceBundle) evt.getNewValue();
        setAppLang(appLang);
    }
}
