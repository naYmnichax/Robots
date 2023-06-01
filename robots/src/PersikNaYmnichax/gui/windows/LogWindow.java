package PersikNaYmnichax.gui.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.TextArea;

import javax.swing.*;

import PersikNaYmnichax.localization.LanguageChangeListener;
import PersikNaYmnichax.gui.closingWindows.CloseWindow;
import PersikNaYmnichax.log.LogChangeListener;
import PersikNaYmnichax.log.LogEntry;
import PersikNaYmnichax.log.LogWindowSource;
import PersikNaYmnichax.savingPersonalSettings.SettingsChangeListener;

public class LogWindow extends GeneralWindowSettings implements LogChangeListener {
    private final LogWindowSource logSource;
    private final TextArea logContent;

    public LogWindow(LogWindowSource logSource, LanguageChangeListener languageChangeListener, SettingsChangeListener settingsChangeListener, CloseWindow closeWindow) {
        super(languageChangeListener, settingsChangeListener, "window.Log",  closeWindow);

        this.logSource = logSource;
        logSource.registerListener(this);
        logContent = new TextArea("");
        logContent.setSize(200, 500);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(logContent, BorderLayout.CENTER);
        getContentPane().add(panel);

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

    @Override
    public void doDefaultCloseAction() {
        logSource.unregisterListener(this);
        super.doDefaultCloseAction();
    }

    @Override
    public void onLogChanged() {
        EventQueue.invokeLater(this::updateLogContent);
    }


}