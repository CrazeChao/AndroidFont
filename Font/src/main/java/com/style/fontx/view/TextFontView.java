package com.style.fontx.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by lizhichao on 2021/9/14
 * <p></p>
 * <h1>字重</h1>
 * <br>400: Regular;</br>
 * <br>500: Medium;</br>
 * <br>700: Bold;</br>
 * <br>1000: Black;</br>
 */
public class TextFontView extends AppCompatTextView {
    TextFontViewHelper textFontViewHelper;
    public TextFontView(@NonNull Context context) {
        super(context);
    }
    public TextFontView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public TextFontView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textFontViewHelper = new TextFontViewHelper();
        textFontViewHelper.loadFromTypeFace(this);
    }
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        textFontViewHelper.applyTypeface(this);
    }

    @Override
    public void setTypeface(@Nullable Typeface tf) {
        super.setTypeface(tf);
    }
}