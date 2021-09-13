package com.style.font;

import android.widget.TextView;

/**
 * Created by lizhichao on 2021/9/8
 */
public enum TextFontWeightStyle {
    Black,Bold,Medium,Regular;
    public void applyTypeface(TextView textView){
        TextFontCompat.applyTypefaceAdaptive(textView,this);
    }
}