package com.style.fontx.task.impl;
import android.graphics.Typeface;

import com.style.fontx.style.TextFontStyle;
import com.style.fontx.task.ITask;
import com.style.fontx.task.ITaskPart;
import com.style.fontx.task.TextFontLoader;
import com.style.fontx.task.TextFontRegister;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by lizhichao on 2021/9/8
 * 一个FontTask 对应一个字体
 */
public class FontITask implements ITask, ITaskPart<TextFontLoader>,Runnable {
    Queue <ViewLifeAdapter> orders = new LinkedList<>();
    TextFontStyle textFontStyle;
    HashMap<TextFontStyle, ITaskPart<TextFontLoader>> executeTasks;
    HashMap<TextFontStyle, Typeface> typefaceHashMap;
    public FontITask(HashMap<TextFontStyle, Typeface> typefaceHashMap, HashMap<TextFontStyle, ITaskPart<TextFontLoader>> partHashMap, TextFontStyle textFontStyle) {
        this.textFontStyle = textFontStyle;
        this.executeTasks = partHashMap;
        this.typefaceHashMap = typefaceHashMap;
        partHashMap.put(textFontStyle,this);
    }

    @Override
    public void apply(TextFontLoader textFontLoader){
        ViewLifeAdapter adapter = new ViewLifeAdapter(this.orders,typefaceHashMap,textFontStyle);
        adapter.bind(textFontLoader);
    }

    @Override
    public void run() {
        Typeface typeface = Typeface.createFromFile(textFontStyle.getTextFontPath());
        while (!orders.isEmpty()){
            ViewLifeAdapter adapter = orders.poll();
            assert adapter != null;
            adapter.setTypeface(typeface);
        }
        executeTasks.remove(textFontStyle);
    }

    public static class ViewLifeAdapter implements TextFontRegister {
        Queue<ViewLifeAdapter> adapterHashMap;
        HashMap<TextFontStyle, Typeface> typefaceHashMap;
        TextFontStyle textFontStyle;
        TextFontRegister textFontRegister;
        TextFontLoader textFontLoader;

        public ViewLifeAdapter(Queue<ViewLifeAdapter> queue, HashMap<TextFontStyle, Typeface> typefaceHashMap,  TextFontStyle textFontStyle) {
            this.adapterHashMap = queue;
            this.typefaceHashMap = typefaceHashMap;
            this.textFontStyle = textFontStyle;
        }
          public void bind(TextFontLoader loader){
              adapterHashMap.offer(this);
              loader.addRegister(this);
              textFontLoader = loader;
          }

        @Override
        public void addRegister(TextFontRegister register) {
            textFontRegister = register;//建立双向绑定
        }

        @Override
        public void unRegister(TextFontRegister register) {
            adapterHashMap.remove(this);
            textFontRegister.unRegister(this);
        }

        public void setTypeface(Typeface typeface) {
            textFontLoader.post(new Runnable() {
                @Override
                public void run() {
                    typefaceHashMap.put(textFontStyle,typeface);
                    textFontLoader.onLoaded(typeface);
                }
            });
        }
    }

}