package PersikNaYmnichax.gui;

import PersikNaYmnichax.gui.closingWindows.CloseWindow;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame {

    public GameWindow(ResourceBundle appLang, CloseWindow closeWindow) {
        super(appLang.getString("window.Game"), true, true, true, true);
        GameVisualizer visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addInternalFrameListener(closeWindow);
        pack();
    }
}
