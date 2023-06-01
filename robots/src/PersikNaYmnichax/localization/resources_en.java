package PersikNaYmnichax.localization;

import java.util.ListResourceBundle;

public class resources_en extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"displayMode" , "Display Mode"},
                    {"displayMode.system" , "System Diagram"},
                    {"displayMode.universal" , "Universal Scheme"},
                    {"displayMode.textDescription", "Managing the application display mode"},

                    {"tests" , "Tests"},
                    {"tests.msgInLog", "Message to the log"},
                    {"tests.newLine", "New line"},
                    {"tests.Protocol", "The protocol works"},
                    {"tests.textDescription", "Test commands"},

                    {"language" , "Language"},
                    {"language.textDescription", "Managing the application language change"},
                    {"language.ru" , "Russian"},
                    {"language.en" , "English"},
                    {"language.de", "German"},

                    {"close", "Close"},
                    {"close.exit", "Exit"},
                    {"yes", "Yes"},
                    {"no", "No"},
                    {"close.title", "Confirmation"},
                    {"close.confirmation", "Close the window?"},
                    {"close.textDescription", "Offer to exit the program"},

                    {"window.Game", "Playing field"},
                    {"window.Log", "Work protocol"},

                    {"downloadSettings.confirmation", "Do you want to restore the last state?"},
                    {"downloadSettings.title", "Loading Settings"}
            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
