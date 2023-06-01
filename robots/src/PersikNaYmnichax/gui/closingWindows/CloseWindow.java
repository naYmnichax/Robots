package PersikNaYmnichax.gui.closingWindows;

import PersikNaYmnichax.localization.LanguageChangeListener;

import javax.swing.*;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

public class CloseWindow extends InternalFrameAdapter implements PropertyChangeListener {

    private ResourceBundle appLang;

    public CloseWindow(LanguageChangeListener languageChangeListener){
        languageChangeListener.addPropertyChangeListener(this);
        setAppLang(languageChangeListener.getOldBundle());
    }

    public void setAppLang(ResourceBundle appLang){
        this.appLang = appLang;
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent event){
        Object[] options = {appLang.getString("yes"), appLang.getString("no")};
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals(LanguageChangeListener.descriptionBundle)) {
            this.appLang = (ResourceBundle) evt.getNewValue();
            setAppLang(appLang);
        }
    }
}
