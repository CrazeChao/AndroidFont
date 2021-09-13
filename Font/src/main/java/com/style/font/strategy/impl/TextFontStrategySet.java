package com.style.font.strategy.impl;
import android.widget.TextView;
import com.style.font.TextFontCompat;
import com.style.font.TextFontStyle;
import com.style.font.strategy.Language;
import com.style.font.strategy.TextFontStrategy;
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