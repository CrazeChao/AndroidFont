package com.style.fontx

import android.widget.TextView
import com.style.fontx.strategy.TextFontStrategy
import com.style.fontx.style.TextFontStyle
import com.style.fontx.style.TextFontWeightStyle


/**
 * Created by lizhichao on 2021/9/13
 */

fun TextView.setTypeface(style: TextFontStyle):TextView{
    TextFontCompat.applyTypeface(this, style)
    return this
}
fun TextView.setTypeface(styleWeightStyle: TextFontWeightStyle):TextView{
    TextFontCompat.applyTypefaceAdaptive(this, styleWeightStyle)
    return this
}
fun TextView.setTypeface(textFontStrategy: TextFontStrategy):TextView{
    TextFontCompat.applyTypefaceStrategy(this, textFontStrategy)
    return this
}