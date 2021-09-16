package com.android.font

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.style.fontx.style.TextFontStyle
import com.style.fontx.style.TextFontWeightStyle
import com.style.fontx.TextFontCompat
import com.style.fontx.strategy.Language

/**
 * 开线程字体包！
 */
class MainActivity : AppCompatActivity() {
   fun  applicationCreate(){
       /**
        * 可以覆盖TextFontView 指定权重 》 语言环境 下的文本样式
         * */
       var languageConfig =    TextFontCompat.config()
           .getLanguageConfig(TextFontWeightStyle.Black)
       languageConfig.cover(Language.chinese,TextFontStyle.HelveticaNowDisplayRegular)
       languageConfig.cover(Language.japanese,TextFontStyle.HelveticaNowDisplayRegular)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //使用方式
        //方式1
        applyTypeFace(findViewById(R.id.textView1), TextFontStyle.CeraProBlack)
//        applyTypeFace(findViewById(R.id.textView2), TextFontStyle.CeraProBold)
        applyTypeFace(findViewById(R.id.textView3), TextFontStyle.CeraProMedium)
        applyTypeFace(findViewById(R.id.textView4), TextFontStyle.CeraProRegular)
        applyTypeFace(findViewById(R.id.textView5), TextFontStyle.AlibabaPuHuiTiRegular)
        applyTypeFace(findViewById(R.id.textView6), TextFontStyle.AlibabaPuHuiTiMedium)
        applyTypeFace(findViewById(R.id.textView7), TextFontStyle.AlibabaPuHuiTiBold)
        applyTypeFace(findViewById(R.id.textView8), TextFontStyle.AlibabaPuHuiTiHeavy)
        applyTypeFace(findViewById(R.id.textView9), TextFontStyle.HelveticaNowDisplayBlack)
        applyTypeFace(findViewById(R.id.textView10), TextFontStyle.HelveticaNowDisplayBold)
        applyTypeFace(findViewById(R.id.textView11), TextFontStyle.HelveticaNowDisplayMedium)
        applyTypeFace(findViewById(R.id.textView12), TextFontStyle.HelveticaNowDisplayRegular)
        /**
         * 方式2
         * 根据字重设置
         *  TextFontCompat.applyTypefaceAdaptive(textView, textFontStyle)
         */
//        applyTypefaceAdaptive(findViewById(R.id.textView1), TextFontWeightStyle.Black)
//        applyTypefaceAdaptive(findViewById(R.id.textView2), TextFontWeightStyle.Bold)
//        applyTypefaceAdaptive(findViewById(R.id.textView3), TextFontWeightStyle.Medium)
//        applyTypefaceAdaptive(findViewById(R.id.textView4), TextFontWeightStyle.Regular)

        var tv6:TextView = findViewById(R.id.textView1);
        tv6.setOnClickListener{
//            applyTypeFace(tv6, TextFontStyle.HelveticaNowDisplayRegular)
        }
    }

    fun applyTypeFace(textView: TextView, textFontStyle: TextFontStyle) {
        textView.text = textFontStyle.name
        TextFontCompat.applyTypeface(textView, textFontStyle)
    }




    fun applyTypefaceAdaptive(textView: TextView, textFontStyle: TextFontWeightStyle) {
        textView.text = textFontStyle.name
        TextFontCompat.applyTypefaceAdaptive(textView, textFontStyle)
    }
}