package com.android.font

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.font.R
import android.widget.TextView
import com.style.font.TextFontStyle
import com.style.font.TextFontWeightStyle
import com.style.font.TextFontCompat
import com.style.font.setTypeface
import com.style.font.strategy.Language
import com.style.font.strategy.TextFontStrategy

/**
 * 开线程字体包！
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //使用方式
        //方式1
//        applyTypeFace(findViewById(R.id.textView1), TextFontStyle.CeraProBlack)
//        applyTypeFace(findViewById(R.id.textView2), TextFontStyle.CeraProBold)
//        applyTypeFace(findViewById(R.id.textView3), TextFontStyle.CeraProMedium)
//        applyTypeFace(findViewById(R.id.textView4), TextFontStyle.CeraProRegular)
//        applyTypeFace(findViewById(R.id.textView5), TextFontStyle.AlibabaPuHuiTiRegular)
//        applyTypeFace(findViewById(R.id.textView6), TextFontStyle.AlibabaPuHuiTiMedium)
//        applyTypeFace(findViewById(R.id.textView7), TextFontStyle.AlibabaPuHuiTiBold)
//        applyTypeFace(findViewById(R.id.textView8), TextFontStyle.AlibabaPuHuiTiHeavy)
//        applyTypeFace(findViewById(R.id.textView9), TextFontStyle.HelveticaNowDisplayBlack)
//        applyTypeFace(findViewById(R.id.textView10), TextFontStyle.HelveticaNowDisplayBold)
//        applyTypeFace(findViewById(R.id.textView11), TextFontStyle.HelveticaNowDisplayMedium)
//        applyTypeFace(findViewById(R.id.textView12), TextFontStyle.HelveticaNowDisplayRegular)

        /**
         * 方式2
         * 根据字重设置
         *  TextFontCompat.applyTypefaceAdaptive(textView, textFontStyle)
         */
        applyTypefaceAdaptive(findViewById(R.id.textView1), TextFontWeightStyle.Black)
        applyTypefaceAdaptive(findViewById(R.id.textView2), TextFontWeightStyle.Bold)
        applyTypefaceAdaptive(findViewById(R.id.textView3), TextFontWeightStyle.Medium)
        applyTypefaceAdaptive(findViewById(R.id.textView4), TextFontWeightStyle.Regular)



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