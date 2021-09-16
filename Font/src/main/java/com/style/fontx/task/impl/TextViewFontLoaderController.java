package com.style.fontx.task.impl;

import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.style.fontx.task.TextFontLoader;
import com.style.fontx.task.TextFontRegister;

import java.lang.ref.WeakReference;

/**
 * Created by lizhichao on 2021/9/15
 */
public class TextViewFontLoaderController implements TextFontLoader {
    WeakReference<TextView> textViewWeakReference;
    public TextViewFontLoaderController(TextView textView) {
        this.textViewWeakReference = new WeakReference<>(textView);
    }

    @Override
    public void onLoaded(Typeface typeface) {
       TextView textView =  textViewWeakReference.get();
       if (textView == null)return;
        textView.setTypeface(typeface);
    }

    @Override
    public void post(Runnable runnable) {
        TextView textView =  textViewWeakReference.get();
        if (textView == null)return;
        textView.post(runnable);
    }

    /**
     * 任务注册
     * */
    @Override
    public void addRegister(TextFontRegister register) {
        this.register = register;
        TextView textView =  textViewWeakReference.get();
        if (textView == null)return;
        textView.addOnAttachStateChangeListener(onAttachStateChangeListener);
        register.addRegister(this);//建立双向绑定
    }
    TextFontRegister register;
    View.OnAttachStateChangeListener onAttachStateChangeListener =  new View.OnAttachStateChangeListener() {
        @Override
        public void onViewAttachedToWindow(View v) {
        }

        @Override
        public void onViewDetachedFromWindow(View v) {
            //远程接触注册
            register.unRegister(TextViewFontLoaderController.this);
        }
    };

    @Override
    public void unRegister(TextFontRegister register) {
        TextView textView =  textViewWeakReference.get();
        if (textView == null)return;
        textView.removeOnAttachStateChangeListener(onAttachStateChangeListener);
    }
}