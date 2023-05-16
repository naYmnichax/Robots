package PersikNaYmnichax.localization;

import java.util.ListResourceBundle;

public class resources_ru extends ListResourceBundle {
    private static final Object[][] contents =
            {
                    {"displayMode" , "Режим отображения"},
                    {"displayMode.system" , "Системная схема"},
                    {"displayMode.universal" , "Универсальная схема"},
                    {"displayMode.textDescription", "Управление режимом отображения приложения"},

                    {"tests" , "Тесты"},
                    {"tests.msgInLog", "Сообщение в лог"},
                    {"tests.newLine", "Новая строка"},
                    {"tests.Protocol", "Протокол работает"},
                    {"tests.textDescription", "Тестовые команды"},

                    {"language" , "Язык"},
                    {"language.textDescription", "Управление сменой языка приложения"},
                    {"language.ru" , "Русский"},
                    {"language.en" , "Английский"},
                    {"language.de", "Немецкий"},

                    {"close", "Закрыть"},
                    {"close.exit", "Выйти"},
                    {"close.yes", "Да"},
                    {"close.no", "Нет"},
                    {"close.title", "Подтверждение"},
                    {"close.confirmation", "Закрыть окно?"},
                    {"close.textDescription", "Предложение выхода из программы"},


                    {"window.Game", "Игровое поле"},
                    {"window.Log", "Протокол работы"}
            };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
