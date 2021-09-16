package com.style.fontx.strategy.impl;
import android.widget.TextView;

import com.style.fontx.strategy.Language;
import com.style.fontx.strategy.TextFontStrategy;
import com.style.fontx.style.TextFontStyle;

import java.util.HashMap;

/**
 * Created by lizhichao on 2021/9/13
 */
public class TextFontStrategySet implements TextFontStrategy {
    HashMap<Language, TextFontStyle> fontStrategyList = new HashMap<>();
    public TextFontStrategySet put(Language language,TextFontStyle textFontStyle){
        fontStrategyList.put(language,textFontStyle);
        return this;
    }

    public HashMap<Language, TextFontStyle> getFontStrategyList() {
        return fontStrategyList;
    }

    Language currentLau;

    @Override
    public boolean filter(Language language) {
        currentLau = language;
       return fontStrategyList.keySet().contains(language);
    }

    @Override
    public void applyTypeface(TextView textView) {
        TextFontStyle textFontStyle = fontStrategyList.get(currentLau);
        assert textFontStyle != null;
        textFontStyle.applyTypeface(textView);
    }
}