package PersikNaYmnichax.localization;

import java.util.Locale;

public enum Language {
    LANGUAGE_RU("PersikNaYmnichax.localization.resources_ru"),
    LANG_LOCALE_LANG_RU("ru"),
    LANG_LOCALE_COUNTRY_RU("RU"),
    LANGUAGE_EN("PersikNaYmnichax.localization.resources_en"),
    LANG_LOCALE_LANG_EN("en"),
    LANG_LOCALE_COUNTRY_EN("EN"),
    LANGUAGE_DE("PersikNaYmnichax.localization.resources_de"),
    LANG_LOCALE_LANG_DE("de"),
    LANG_LOCALE_COUNTRY_DE("DE");

    private final String appLang;

    Language(String appLang) {
        this.appLang = appLang;
    }

    public String getAppLang(){
        return String.valueOf(appLang);
    }
}
