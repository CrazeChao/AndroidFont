package com.android.font

import android.widget.TextView
import com.style.font.TextFontCompat
import com.style.font.TextFontStyle
import com.style.font.TextFontWeightStyle
import com.style.font.setTypeface
import com.style.font.strategy.Language
import com.style.font.strategy.TextFontStrategy

/**
 * Created by lizhichao on 2021/9/13
 */
internal class ReadMine {
    fun test(textView: TextView) {
        //主要部分
        /**
         * 方式1 直接给文本设置样式
         * */
        TextFontCompat.applyTypeface(textView,  TextFontStyle.CeraProBlack)
        /**
         * 方式2 根据 🐻 给出的 字重 设置字体样式
         * 字重包含:Black,Bold,Medium,Regular
         * */
        TextFontCompat.applyTypefaceAdaptive(textView, TextFontWeightStyle.Black)
        /**
         * 方式3 字体策略 根据语言环境 去定制相关字体
         * */
        TextFontCompat.applyTypefaceStrategy(textView, object: TextFontStrategy {
            override fun applyTypeface(textView: TextView?) {
                TextFontCompat.applyTypeface(textView,TextFontStyle.AlibabaPuHuiTiBold)
            }

            override fun filter(language: Language?): Boolean {
                return language == Language.chinese;
            }
        })
        //可以不看
        /**
         * 方式4 直接用枚举来设置
         * */
        TextFontStyle.HelveticaNowDisplayRegular.applyTypeface(textView)
        TextFontWeightStyle.Black.applyTypeface(textView)

        /**
         * 方式5 kotlin特性
         * */
        textView.setTypeface(TextFontStyle.AlibabaPuHuiTiBold)
        textView.setTypeface(TextFontWeightStyle.Black)
        textView.setTypeface(object:TextFontStrategy{
            override fun applyTypeface(textView: TextView?) {
                TextFontStyle.AlibabaPuHuiTiBold.applyTypeface(textView)

            }

            override fun filter(language: Language?): Boolean {
                return language == Language.chinese;
            }
        })
    }
}