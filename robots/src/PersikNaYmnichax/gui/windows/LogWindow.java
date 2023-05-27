package PersikNaYmnichax.gui.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ResourceBundle;

import javax.swing.*;

import PersikNaYmnichax.gui.LanguageChangeListener;
import PersikNaYmnichax.gui.closingWindows.CloseWindow;
import PersikNaYmnichax.log.LogChangeListener;
import PersikNaYmnichax.log.LogEntry;
import PersikNaYmnichax.log.LogWindowSource;

public class LogWindow extends JInternalFrame implements LogChangeListener, PropertyChangeListener {
    private final LogWindowSource logSource;
    private final TextArea logContent;
    private ResourceBundle appLang;

    public LogWindow(LogWindowSource logSource, LanguageChangeListener languageChangeListener, CloseWindow closeWindow) {
        super(languageChangeListener.getOldBundle().getString("window.Log"), true, true, true, true);

        languageChangeListener.addPropertyChangeListener(this);

        this.logSource = logSource;
        logSource.registerListener(this);
        logContent = new TextArea("");
        logContent.setSize(200, 500);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(logContent, BorderLayout.CENTER);
        getContentPane().add(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addInternalFrameListener(closeWindow);

        pack();
        updateLogContent();
    }

    private void updateLogContent() {
        StringBuilder content = new StringBuilder();
        for (LogEntry entry : logSource.all()) {
            content.append(entry.getMessage()).append("\n");
        }
        logContent.setText(content.toString());
        logContent.invalidate();
    }

    public void setAppLang(ResourceBundle appLang){
        this.appLang = appLang;
        setTitle(this.appLang.getString("window.Game"));
    }

    @Override
    public void doDefaultCloseAction() {
        logSource.unregisterListener(this);
        super.doDefaultCloseAction();
    }

    @Override
    public void onLogChanged() {
        EventQueue.invokeLater(this::updateLogContent);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.appLang = (ResourceBundle) evt.getNewValue();
        setAppLang(appLang);
    }
}