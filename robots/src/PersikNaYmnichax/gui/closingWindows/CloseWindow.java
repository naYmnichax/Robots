package PersikNaYmnichax.gui.closingWindows;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.util.ResourceBundle;

public class CloseWindow extends InternalFrameAdapter {

    private ResourceBundle appLang;

    public CloseWindow(ResourceBundle appLang){
        setAppLang(appLang);
    }

    public void setAppLang(ResourceBundle appLang){
        this.appLang = appLang;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent event){
        Object[] options = {appLang.getString("close.yes"), appLang.getString("close.no")};
        int n = JOptionPane
                .showOptionDialog(
                        event.getInternalFrame(),
                        appLang.getString("close.confirmation"),
                        appLang.getString("close.title"),
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);
        if (n == 0){
            event.getInternalFrame().setVisible(false);
            event.getInternalFrame().dispose();
        }
    }
}
