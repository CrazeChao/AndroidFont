package com.style.font;
import android.graphics.Typeface;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Created by lizhichao on 2021/9/8
 * 一个FontTask 对应一个字体
 */
public class FontITask implements ITask, ITaskPart,Runnable {
    Queue <ViewLifeAdapter> orders = new LinkedList<>();
    TextFontStyle textFontStyle;
    HashMap<TextFontStyle, ITaskPart> partHashMap;
    HashMap<TextFontStyle, Typeface> typefaceHashMap;
    public FontITask(HashMap<TextFontStyle, Typeface> typefaceHashMap,HashMap<TextFontStyle, ITaskPart> partHashMap,  TextFontStyle textFontStyle) {
        this.textFontStyle = textFontStyle;
        this.partHashMap = partHashMap;
        this.typefaceHashMap = typefaceHashMap;
        partHashMap.put(textFontStyle,this);
    }

    @Override
    public void apply(TextView view){
        ViewLifeAdapter adapter = new ViewLifeAdapter(this.orders,typefaceHashMap,textFontStyle);
        adapter.bind(view);
    }

    @Override
    public void run() {
        Typeface typeface = Typeface.createFromFile(textFontStyle.fontPath);
        while (!orders.isEmpty()){
            ViewLifeAdapter adapter = orders.poll();
            assert adapter != null;
            adapter.setTypeface(typeface);
        }
        partHashMap.remove(textFontStyle);
    }

    public static class ViewLifeAdapter implements OnAttachStateChangeListener{
        Queue<ViewLifeAdapter> adapterHashMap;
        HashMap<TextFontStyle, Typeface> typefaceHashMap;
        TextFontStyle textFontStyle;
        public ViewLifeAdapter(Queue<ViewLifeAdapter> queue, HashMap<TextFontStyle, Typeface> typefaceHashMap,  TextFontStyle textFontStyle) {
            this.adapterHashMap = queue;
            this.typefaceHashMap = typefaceHashMap;
            this.textFontStyle = textFontStyle;
        }
        WeakReference<TextView> viewWeakReference;
          public void bind(TextView view){
              viewWeakReference = new WeakReference<>(view);
              view.addOnAttachStateChangeListener(this);
              adapterHashMap.offer(this);
          }
        @Override
        public void onViewAttachedToWindow(View v) {
        }

        @Override
        public void onViewDetachedFromWindow(View v) {
            v.removeOnAttachStateChangeListener(this);
            adapterHashMap.remove(this);
        }

        public void setTypeface(Typeface typeface) {
            TextView view = viewWeakReference.get();
            if (view == null) return;
            view.post(() -> {
                view.setTypeface(typeface);
                onViewDetachedFromWindow(view);
                typefaceHashMap.put(textFontStyle,typeface);
            });

        }
    }

}