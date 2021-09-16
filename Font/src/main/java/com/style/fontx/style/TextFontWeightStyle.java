package com.style.fontx.style;

import android.widget.TextView;

import com.style.fontx.TextFontCompat;

/**
 * Created by lizhichao on 2021/9/8
 */
public enum TextFontWeightStyle {
    Black,Bold,Medium,Regular;
    public void applyTypeface(TextView textView){
        TextFontCompat.applyTypefaceAdaptive(textView,this);
    }
}