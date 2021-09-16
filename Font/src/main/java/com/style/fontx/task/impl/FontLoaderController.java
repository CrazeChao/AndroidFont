package com.style.fontx.task.impl;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;

import com.style.fontx.TextFontCompat;
import com.style.fontx.task.LoadCancel;
import com.style.fontx.task.TextFontLoader;
import com.style.fontx.task.TextFontRegister;

/**
 * Created by lizhichao on 2021/9/15
 */
public class FontLoaderController implements TextFontLoader, TextFontCompat.OnTypeFaceLoader, LoadCancel {
    Handler handler;
    TextFontRegister register;
    TextFontCompat.OnTypeFaceLoader onTypeFaceLoader;

    public FontLoaderController(TextFontCompat.OnTypeFaceLoader onTypeFaceLoader) {
        this.onTypeFaceLoader = onTypeFaceLoader;
        handler = new Handler(Looper.getMainLooper());
    }


    @Override
    public void onTypeFaceLoaded(Typeface typeface) {
        onTypeFaceLoader.onTypeFaceLoaded(typeface);
    }

    @Override
    public void onLoaded(Typeface typeface) {
        onTypeFaceLoaded(typeface);
    }

    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

    @Override
    public void addRegister(TextFontRegister register) {
        this.register =register;//建立双向链接
    }

    @Override
    public void unRegister(TextFontRegister register) {
        handler = null;
    }
    @Override
    public void cancel(){
        if (register == null)return;
        register.unRegister(this);
    }

}