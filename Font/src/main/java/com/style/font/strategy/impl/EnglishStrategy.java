package com.style.font.strategy.impl;

import android.widget.TextView;

import com.style.font.TextFontCompat;
import com.style.font.TextFontStyle;
import com.style.font.strategy.Language;
import com.style.font.strategy.TextFontStrategy;

/**
 * Created by lizhichao on 2021/9/13
 * 英语策略
 * //配置策略
 */
public class EnglishStrategy implements TextFontStrategy {
    TextFontStyle textFontStyle;
    public EnglishStrategy(TextFontStyle textFontStyle) {
        this.textFontStyle = textFontStyle;
    }

    @Override
    public boolean filter(Language language) {
        return language == Language.english;
    }

    @Override
    public void applyTypeface(TextView textView) {
        textFontStyle.applyTypeface(textView);
    }
}