package PersikNaYmnichax.gui;

import PersikNaYmnichax.log.Logger;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;

public class BarMenu {
    MainApplicationFrame mainFrame;

    public BarMenu(MainApplicationFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    //    protected JMenuBar createMenuBar() {
//        JMenuBar menuBar = new JMenuBar();
//
//        //Set up the lone menu.
//        JMenu menu = new JMenu("Document");
//        menu.setMnemonic(KeyEvent.VK_D);
//        menuBar.add(menu);
//
//        //Set up the first menu item.
//        JMenuItem menuItem = new JMenuItem("New");
//        menuItem.setMnemonic(KeyEvent.VK_N);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("new");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
//
//        //Set up the second menu item.
//        menuItem = new JMenuItem("Quit");
//        menuItem.setMnemonic(KeyEvent.VK_Q);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("quit");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
//
//        return menuBar;
//    }

    public JMenuBar generateMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(lookAndFeelMenu());
        menuBar.add(createTestMenu());
        return menuBar;
    }
    private JMenu lookAndFeelMenu() {
        return createMenu("Режим отображения",
                KeyEvent.VK_V,
                "Управление режимом отображения приложения",
                Arrays.asList(
                        createItem("Системная схема", KeyEvent.VK_S, (event) -> {
                            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            mainFrame.invalidate();
                        }),
                        createItem("Универсальная схема", KeyEvent.VK_S, (event) -> {
                            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                            mainFrame.invalidate();
                        })));
    }

    private JMenu createTestMenu() {
        return createMenu("Тесты",
                KeyEvent.VK_T,
                "Тестовые команды",
                createItem("Сообщение в лог", KeyEvent.VK_S, (event)
                        -> Logger.debug("Новая строка")));
    }

    private void setLookAndFeel(String className) {
        try {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(mainFrame);
        } catch (ClassNotFoundException | InstantiationException
                 | IllegalAccessException | UnsupportedLookAndFeelException ignored) {
        }
    }

    private JMenuItem createItem(String text, int key, ActionListener actionListener) {
        JMenuItem jMenuItem = new JMenuItem(text, key);
        jMenuItem.addActionListener(actionListener);
        return jMenuItem;
    }

    private JMenu createMenu(String text, int key, String textDescription, JMenuItem item) {
        JMenu menu = new JMenu(text);
        menu.setMnemonic(key);
        menu.getAccessibleContext()
                .setAccessibleDescription(textDescription);
        menu.add(item);
        return menu;
    }

    private JMenu createMenu(String text, int key, String textDescription, List<JMenuItem> items) {
        JMenu menu = createMenu(text, key, textDescription, items.get(0));
        for (int i = 1; i < items.size(); i++) {
            menu.add(items.get(i));
        }
        return menu;
    }
}
