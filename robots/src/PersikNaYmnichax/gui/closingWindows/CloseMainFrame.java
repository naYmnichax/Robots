package PersikNaYmnichax.gui.closingWindows;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

public class CloseMainFrame extends WindowAdapter {

    private ResourceBundle appLang;

    public CloseMainFrame(ResourceBundle appLang){
        setAppLang(appLang);
    }

    public void setAppLang(ResourceBundle appLang){
        this.appLang = appLang;
    }

    @Override
    public void windowClosing(WindowEvent event){
        Object[] options = {appLang.getString("close.yes"), appLang.getString("close.no")};
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
        if (n == 0){
            event.getWindow().setVisible(false);
            event.getWindow().dispose();
            System.exit(0);
        }
    }
}
