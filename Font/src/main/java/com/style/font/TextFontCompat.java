package com.style.font;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Looper;
import android.widget.TextView;

import com.style.font.strategy.Language;
import com.style.font.strategy.TextFontStrategy;
import com.style.font.strategy.impl.EnglishStrategy;
import com.style.font.strategy.impl.TextFontStrategySet;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/**
 * Created by lizhichao on 2021/9/8
 */
public class TextFontCompat {
    private static HashMap<TextFontStyle, Typeface> cacheTypeFace = new HashMap();
    private static HashMap<TextFontStyle, ITaskPart> executeTasks = new HashMap<>();
    private static HashMap<TextFontWeightStyle, TextFontStrategySet> cacheTypeWeightStyle = new HashMap<>();
    private static Executor executor;
    static {
        cacheTypeWeightStyle.put(TextFontWeightStyle.Black,new TextFontStrategySet()
                .put(Language.chinese,TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.english,TextFontStyle.HelveticaNowDisplayBlack)
        );
        cacheTypeWeightStyle.put(TextFontWeightStyle.Bold,new TextFontStrategySet()
                 .put(Language.chinese,TextFontStyle.AlibabaPuHuiTiBold)
                 .put(Language.english,TextFontStyle.HelveticaNowDisplayBold)
        );
        cacheTypeWeightStyle.put(TextFontWeightStyle.Medium,new TextFontStrategySet()
                .put(Language.chinese,TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.english,TextFontStyle.HelveticaNowDisplayMedium)
        );
        cacheTypeWeightStyle.put(TextFontWeightStyle.Regular,new TextFontStrategySet()
                .put(Language.chinese,TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.english,TextFontStyle.HelveticaNowDisplayRegular)
        );
    }
    /**
     * 设置调度器：如果没有设置调度器 会使用一个调度器
     * @param executor 调度器
     * */
    public static void setExecute(Executor executor) {
        TextFontCompat.executor = executor;
    }


    /**
     * 设置字体样式
     * @param textView 文本
     * @param  textStyle  字体样式
     * */
    public static void applyTypeface(TextView textView, TextFontStyle textStyle) {
        if (executor == null){
            setExecute(Executors.newCachedThreadPool());
        }
        if (Thread.currentThread() != Looper.getMainLooper().getThread()){
            throw new RuntimeException("子线程不支持 设置字体样式");
        }
        Typeface typeface = cacheTypeFace.get(textStyle);

        if (typeface != null) {
            textView.setTypeface(typeface);
            return;
        }

        ITaskPart taskPart = executeTasks.get(textStyle);
        if (taskPart != null) {
            taskPart.apply(textView);
            return;
        }

        FontITask fontITask = new FontITask(cacheTypeFace, executeTasks, textStyle);
        fontITask.apply(textView);
        executor.execute(fontITask);
    }

    /**
     * 根据 媒举的字重 自动匹配相关字体
     * @param textView 文本
     * @param textWeightStyle 字重样式
     * */
    public static void applyTypefaceAdaptive(TextView textView, TextFontWeightStyle textWeightStyle){
        TextFontStrategy textFontStyle = cacheTypeWeightStyle.get(textWeightStyle);
        if (textFontStyle == null){
           return;
        }
        applyTypefaceStrategy(textView,textFontStyle);
    }

    /**
     * @param textView 文本
     * @param textFontStrategy 字体样式策略
     * */
    public static void applyTypefaceStrategy(TextView textView, TextFontStrategy textFontStrategy){
        Locale locale = textView.getContext().getApplicationContext().getResources().getConfiguration().locale;
        if (textFontStrategy.filter(Language.valueOf(locale))){
             textFontStrategy.applyTypeface(textView);
        }
    }

}