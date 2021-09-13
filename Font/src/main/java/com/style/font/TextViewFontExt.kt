package com.style.font

import android.widget.TextView
import com.style.font.strategy.TextFontStrategy

/**
 * Created by lizhichao on 2021/9/13
 */

fun TextView.setTypeface(style:TextFontStyle):TextView{
    TextFontCompat.applyTypeface(this,style)
    return this
}
fun TextView.setTypeface(styleWeightStyle: TextFontWeightStyle):TextView{
    TextFontCompat.applyTypefaceAdaptive(this,styleWeightStyle)
    return this
}
fun TextView.setTypeface(textFontStrategy: TextFontStrategy):TextView{
    TextFontCompat.applyTypefaceStrategy(this,textFontStrategy)
    return this
}