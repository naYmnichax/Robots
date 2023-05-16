package PersikNaYmnichax.localization;

import java.util.ListResourceBundle;

public class resources_de extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"displayMode" , "Anzeigemodus"},
                    {"displayMode.system" , "Systemschema"},
                    {"displayMode.universal" , "Universelles Schema"},
                    {"displayMode.textDescription", "Steuern des Anzeigemodus einer Anwendung"},

                    {"tests" , "Tests"},
                    {"tests.msgInLog", "Blogeintrag"},
                    {"tests.newLine", "Neue Zeile"},
                    {"tests.Protocol", "Das Protokoll funktioniert"},
                    {"tests.textDescription", "Testbefehle"},

                    {"language" , "Sprache"},
                    {"language.textDescription", "Ändern der Sprache der Anwendung steuern"},
                    {"language.ru" , "Russisch"},
                    {"language.en" , "Englisch"},
                    {"language.de", "Deutsch"},

                    {"close", "Schließen"},
                    {"close.exit", "Aussteigen"},
                    {"close.yes", "Ja"},
                    {"close.no", "Nein"},
                    {"close.title", "Die Bestätigung"},
                    {"close.confirmation", "Fenster schließen?"},
                    {"close.textDescription", "Angebot zum Beenden des Programms"},

                    {"window.Game", "Spielfeld"},
                    {"window.Log", "Arbeits-Protokoll"}
            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
