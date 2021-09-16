package com.style.fontx;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Looper;
import android.widget.TextView;

import com.style.fontx.strategy.Language;
import com.style.fontx.strategy.TextFontStrategy;
import com.style.fontx.strategy.impl.TextFontStrategySet;
import com.style.fontx.style.TextFontStyle;
import com.style.fontx.style.TextFontWeightStyle;
import com.style.fontx.task.ITaskPart;
import com.style.fontx.task.LoadCancel;
import com.style.fontx.task.TextFontLoader;
import com.style.fontx.task.impl.FontITask;
import com.style.fontx.task.impl.FontLoaderController;
import com.style.fontx.task.impl.TextViewFontLoaderController;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by lizhichao on 2021/9/8
 */
public class TextFontCompat {
    private static HashMap<TextFontStyle, Typeface> cacheTypeFace = new HashMap();
    private static HashMap<TextFontStyle, ITaskPart<TextFontLoader>> executeTasks = new HashMap<>();
    private static HashMap<TextFontWeightStyle, TextFontStrategySet> cacheTypeWeightStyle = new HashMap<>();
    private static Executor executor;

    static {
        cacheTypeWeightStyle.put(TextFontWeightStyle.Black, new TextFontStrategySet()
                .put(Language.chinese, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.english, TextFontStyle.HelveticaNowDisplayBlack)

                .put(Language.france, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.german, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.italian, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.japanese, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.korea, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.french, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.germany, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.italy, TextFontStyle.AlibabaPuHuiTiHeavy)
                .put(Language.japan, TextFontStyle.AlibabaPuHuiTiHeavy)

        );
        cacheTypeWeightStyle.put(TextFontWeightStyle.Bold, new TextFontStrategySet()
                .put(Language.chinese, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.english, TextFontStyle.HelveticaNowDisplayBold)

                .put(Language.france, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.german, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.italian, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.japanese, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.korea, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.french, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.germany, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.italy, TextFontStyle.AlibabaPuHuiTiBold)
                .put(Language.japan, TextFontStyle.AlibabaPuHuiTiBold)

        );
        cacheTypeWeightStyle.put(TextFontWeightStyle.Medium, new TextFontStrategySet()
                .put(Language.chinese, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.english, TextFontStyle.HelveticaNowDisplayMedium)

                .put(Language.france, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.german, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.italian, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.japanese, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.korea, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.french, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.germany, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.italy, TextFontStyle.AlibabaPuHuiTiMedium)
                .put(Language.japan, TextFontStyle.AlibabaPuHuiTiMedium)
        );
        cacheTypeWeightStyle.put(TextFontWeightStyle.Regular, new TextFontStrategySet()
                .put(Language.chinese, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.english, TextFontStyle.HelveticaNowDisplayRegular)

                .put(Language.france, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.german, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.italian, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.japanese, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.korea, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.french, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.germany, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.italy, TextFontStyle.AlibabaPuHuiTiRegular)
                .put(Language.japan, TextFontStyle.AlibabaPuHuiTiRegular)
        );
    }

    public static Config config() {
        return new Config(cacheTypeWeightStyle);
    }

    /**
     * 设置调度器：如果没有设置调度器 会使用一个调度器
     *
     * @param executor 调度器
     */
    public static void setExecute(Executor executor) {
        TextFontCompat.executor = executor;
    }

    private static TextFontLoader createTextViewFontLoader(TextView textView) {
        return new TextViewFontLoaderController(textView);
    }

    /**
     * 设置字体样式
     *
     * @param textView  文本
     * @param textStyle 字体样式
     */
    public static void applyTypeface(TextView textView, TextFontStyle textStyle) {
        _applyTypeface(createTextViewFontLoader(textView), textStyle);
    }

    private static void _applyTypeface(TextFontLoader textFontLoader, TextFontStyle textStyle) {
        if (executor == null) {
            setExecute(Executors.newCachedThreadPool());
        }
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new RuntimeException("子线程不支持 设置字体样式");
        }
        Typeface typeface = cacheTypeFace.get(textStyle);

        if (typeface != null) {
            textFontLoader.onLoaded(typeface);
            return;
        }

        ITaskPart<TextFontLoader> taskPart = executeTasks.get(textStyle);
        if (taskPart != null) {
            taskPart.apply(textFontLoader);
            return;
        }

        FontITask fontITask = new FontITask(cacheTypeFace, executeTasks, textStyle);
        fontITask.apply(textFontLoader);
        executor.execute(fontITask);
    }


    /**
     * 加载字体样式
     */
    public static LoadCancel loaderTypeface(TextFontStyle textStyle, OnTypeFaceLoader typeFaceLoader) {
        FontLoaderController textFontLoader = new FontLoaderController(typeFaceLoader);
        _applyTypeface(textFontLoader, textStyle);
        return textFontLoader;
    }

    /**
     * 加载字体样式
     */
    public static LoadCancel loaderTypeface(Context context, TextFontWeightStyle textFontWeightStyle, OnTypeFaceLoader typeFaceLoader) {
        Locale locale = context.getApplicationContext().getResources().getConfiguration().locale;
        TextFontStrategySet textFontStrategySet = cacheTypeWeightStyle.get(textFontWeightStyle);
        Language language = Language.valueOf(locale);
        TextFontStyle typeface = textFontStrategySet.getFontStrategyList().get(language);
        return loaderTypeface(typeface, typeFaceLoader);
    }

    /**
     * 加载字体根据权重
     * */

    /**
     * 根据 媒举的字重 自动匹配相关字体
     *
     * @param textView        文本
     * @param textWeightStyle 字重样式
     */
    public static void applyTypefaceAdaptive(TextView textView, TextFontWeightStyle textWeightStyle) {
        TextFontStrategy textFontStyle = cacheTypeWeightStyle.get(textWeightStyle);
        if (textFontStyle == null) {
            return;
        }

        applyTypefaceStrategy(textView, textFontStyle);
    }

    /**
     * @param textView         文本
     * @param textFontStrategy 字体样式策略
     */
    public static void applyTypefaceStrategy(TextView textView, TextFontStrategy textFontStrategy) {
        Locale locale = textView.getContext().getApplicationContext().getResources().getConfiguration().locale;
        if (textFontStrategy.filter(Language.valueOf(locale))) {
            textFontStrategy.applyTypeface(textView);
        }
    }

    public interface OnTypeFaceLoader {
        void onTypeFaceLoaded(Typeface typeface);
    }

    public static class Config {
        public Config(HashMap<TextFontWeightStyle, TextFontStrategySet> hashMap) {
            this.hashMap = hashMap;
        }

        HashMap<TextFontWeightStyle, TextFontStrategySet> hashMap;

        /**
         * 覆盖字体样式
         */
        public void cover(TextFontWeightStyle weightStyle, Language language, TextFontStyle textFontStyle) {
            hashMap.get(weightStyle).put(language, textFontStyle);
        }

        /**
         * 覆盖指定字体样式
         */
        public LanguageConfig getLanguageConfig(TextFontWeightStyle weightStyle) {
            return new LanguageConfig(hashMap.get(weightStyle));
        }


    }

    public static class LanguageConfig {
        TextFontStrategySet textFontStrategySet;

        public LanguageConfig(TextFontStrategySet textFontStrategySet) {
            this.textFontStrategySet = textFontStrategySet;
        }

        public void cover(Language language, TextFontStyle textFontStyle) {
            textFontStrategySet.put(language, textFontStyle);
        }
    }

}