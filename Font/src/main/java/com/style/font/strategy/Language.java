package com.style.font.strategy;
import java.util.Locale;
import java.util.Objects;

/**
 * Created by lizhichao on 2021/9/13
 */
public enum Language {
    english(Locale.ENGLISH), french(Locale.FRENCH), german(Locale.GERMAN), italian(Locale.ITALIAN), japanese(Locale.JAPANESE), korean(Locale.KOREA), chinese(Locale.CHINESE)
    , france(Locale.FRANCE), germany(Locale.GERMANY), italy(Locale.ITALY), japan(Locale.JAPAN), korea(Locale.KOREA);
    String language;
    Locale locale;

    Language(Locale locale) {
        this.language = locale.getLanguage();
        this.locale = locale;
    }

    public static Language valueOf(Locale locale) {
        Language[] languages = Language.values();
        for (int i = 0; i < languages.length; i++) {
            if (Objects.equals(languages[i].locale.getLanguage(),locale.getLanguage())){
                return languages[i];
            }
        }

        return null;
    }

    public boolean equals(Locale locale) {
        return Objects.equals(locale, this.locale);
    }


}