package com.style.fontx.task;
import android.graphics.Typeface;

public interface TextFontLoader extends TextFontRegister {
    void onLoaded(Typeface typeface);
    void post(Runnable runnable);
}