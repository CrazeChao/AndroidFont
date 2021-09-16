package com.style.fontx.view;
import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.style.fontx.style.TextFontWeightStyle;

/**
 * Created by lizhichao on 2021/9/14
 * 使用方式参考 TextFontView
 */
public class TextFontViewHelper{
   TextFontWeightStyle textFontWeightStyle;
   @RequiresApi(api = Build.VERSION_CODES.P)
   public void loadFromTypeFace(TextView textView){
       Typeface typeface = textView.getTypeface();
       if (typeface == null) return;
       int weight = typeface.getWeight();
       switch (weight){
           case 400: textFontWeightStyle = TextFontWeightStyle.Regular; break;
           case 500: textFontWeightStyle = TextFontWeightStyle.Medium; break;
           case 700: textFontWeightStyle = TextFontWeightStyle.Bold; break;
           case 1000: textFontWeightStyle = TextFontWeightStyle.Black; break;
       }
   }
   public void applyTypeface(TextView textView){
       textFontWeightStyle.applyTypeface(textView);
   }
}