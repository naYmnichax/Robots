package PersikNaYmnichax.gui;

import PersikNaYmnichax.gui.closingWindows.CloseMainFrame;
import PersikNaYmnichax.gui.closingWindows.CloseWindow;
import PersikNaYmnichax.gui.windows.GameWindow;
import PersikNaYmnichax.gui.windows.LogWindow;
import PersikNaYmnichax.log.Logger;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static PersikNaYmnichax.localization.Language.*;
import static PersikNaYmnichax.localization.Language.LANG_LOCALE_LANG_RU;

public class MainApplicationFrame extends JFrame implements PropertyChangeListener {
    private final JDesktopPane desktopPane = new JDesktopPane();

    private final JMenuBar menuBar = new JMenuBar();

    private ResourceBundle appLang = ResourceBundle.getBundle(
            LANGUAGE_RU.getAppLang(),
            new Locale(LANG_LOCALE_LANG_RU.getAppLang(), LANG_LOCALE_COUNTRY_RU.getAppLang())
    );
    private final LanguageChangeListener languageChangeListener = new LanguageChangeListener(appLang);

    private final CloseWindow closeWindow = new CloseWindow(languageChangeListener);
    private final CloseMainFrame closeMainFrame = new CloseMainFrame(languageChangeListener);

    private final GameWindow gameWindow = new GameWindow(languageChangeListener, closeWindow);
    private final LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource(), languageChangeListener, closeWindow);

    public MainApplicationFrame() {
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2
        );

        setContentPane(desktopPane);

        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = createGameWindow();
        addWindow(gameWindow);

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        CloseMainFrame closeMainFrame = new CloseMainFrame(languageChangeListener);
        addWindowListener(closeMainFrame);
        languageChangeListener.addPropertyChangeListener(this);
    }

    protected LogWindow createLogWindow() {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource(), languageChangeListener, closeWindow);
        logWindow.setLocation(10, 10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug(appLang.getString("tests.Protocol"));
        return logWindow;
    }


    protected GameWindow createGameWindow(){
        gameWindow.setLocation(400, 10);
        gameWindow.setSize(400, 400);
        return gameWindow;
    }

    protected void addWindow(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setVisible(true);
    }

    public JMenuBar generateMenuBar() {
        menuBar.add(lookAndFeelMenu());
        menuBar.add(createTestMenu());
        menuBar.add(languageMenu());
        menuBar.add(exitMenu());
        return menuBar;
    }

    private JMenu lookAndFeelMenu() {
        return createMenu(
                appLang.getString("displayMode"),
                appLang.getString("displayMode.textDescription"),
                Arrays.asList(
                        createItem(appLang.getString("displayMode.system"), (event) -> {
                            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            this.invalidate();
                        }),
                        createItem(appLang.getString("displayMode.universal"), (event) -> {
                            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                            this.invalidate();
                        })));
    }

    private JMenu languageMenu() {
        return createMenu(
                appLang.getString("language"),
                appLang.getString("language.textDescription"),
                Arrays.asList(
                        createItem(appLang.getString("language.ru"), (event) -> {
                                    languageChangeListener.changeBundle(
                                            LANGUAGE_RU.getAppLang(),
                                            new Locale(LANG_LOCALE_LANG_RU.getAppLang(),
                                                    LANG_LOCALE_COUNTRY_RU.getAppLang())
                                    );
                                    appLang = languageChangeListener.getOldBundle();
                                    updateMenu();
                                    this.invalidate();
                                }
                        ),
                        createItem(appLang.getString("language.en"), (event)->{
                                    languageChangeListener.changeBundle(
                                            LANGUAGE_EN.getAppLang(),
                                            new Locale(LANG_LOCALE_LANG_EN.getAppLang(),
                                                    LANG_LOCALE_COUNTRY_EN.getAppLang())
                                    );
                                    appLang = languageChangeListener.getOldBundle();
                                    updateMenu();
                                    this.invalidate();
                                }
                        ),
                        createItem(appLang.getString("language.de"), (event)->{
                            languageChangeListener.changeBundle(
                                    LANGUAGE_DE.getAppLang(),
                                    new Locale(LANG_LOCALE_LANG_DE.getAppLang(),
                                            LANG_LOCALE_COUNTRY_DE.getAppLang())
                            );
                            appLang = languageChangeListener.getOldBundle();
                            updateMenu();
                            this.invalidate();
                        }))
        );
    }

    private JMenu exitMenu(){
        return createMenu(
                appLang.getString("close"),
                KeyEvent.VK_T,
                appLang.getString("close.textDescription"),
                createItem(appLang.getString("close.exit"),
                        (event) -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING))
                ));
    }

    private JMenu createTestMenu() {
        return createMenu(
                appLang.getString("tests"),
                KeyEvent.VK_T,
                appLang.getString("tests.textDescription"),
                createItem(appLang.getString("tests.msgInLog"),
                        (event) -> Logger.debug(appLang.getString("tests.newLine"))
                ));
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }
    }

    private JMenuItem createItem(String text, ActionListener actionListener) {
        JMenuItem jMenuItem = new JMenuItem(text, KeyEvent.VK_S);
        jMenuItem.addActionListener(actionListener);
        return jMenuItem;
    }

    private JMenu createMenu(String text, int key, String textDescription, JMenuItem item) {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(key);
        menu.getAccessibleContext().setAccessibleDescription(textDescription);
        menu.add(item);
        return menu;
    }

    private JMenu createMenu(String text, String textDescription, List<JMenuItem> Items) {
        JMenu menu = createMenu(text, KeyEvent.VK_V, textDescription, Items.get(0));
        for (JMenuItem item : Items) {
            menu.add(item);
        }
        return menu;
    }

    private void updateMenu() {
        menuBar.removeAll();
        setJMenuBar(generateMenuBar());
        revalidate();
        repaint();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.appLang = (ResourceBundle) evt.getNewValue();
        updateMenu();
    }
}