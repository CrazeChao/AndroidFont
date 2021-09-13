package com.style.font;

import android.widget.TextView;

import java.io.File;

/**
 * Created by lizhichao on 2021/9/8
 */
public enum TextFontStyle {
    CeraProBlack("CeraPro-Black"),CeraProBold("CeraPro-Bold"),CeraProMedium("CeraPro-Medium"),CeraProRegular("CeraPro-Regular"),
    AlibabaPuHuiTiRegular("AlibabaPuHuiTi-Regular"),AlibabaPuHuiTiMedium("AlibabaPuHuiTi-Medium"),AlibabaPuHuiTiBold("AlibabaPuHuiTi-Bold"), AlibabaPuHuiTiHeavy("AlibabaPuHuiTi-Heavy"),
    HelveticaNowDisplayBlack("HelveticaNowDisplay-Black"),HelveticaNowDisplayBold("HelveticaNowDisplay-Bold")
    ,HelveticaNowDisplayMedium("HelveticaNowDisplay-Medium")
    ,HelveticaNowDisplayRegular("HelveticaNowDisplay-Regular")//对应文本文件 HelveticaNowDisplay
    ;
    String fontPath;
    TextFontStyle(String fontPath) {
        this.fontPath = "/system/fonts"+ File.separator+fontPath+".otf";
    }
    public void applyTypeface(TextView textView){
        TextFontCompat.applyTypeface(textView,this);
    }
}
