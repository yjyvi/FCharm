package com.whmnrc.feimei.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

/**
 * @author yjyvi
 * @data 2018/5/14.
 */

public class TextSpannableUtils {

    /**
     * 改变字体颜色
     *
     * @param textView
     * @param text
     * @param start
     * @param end
     */
    public static void changeTextColor(TextView textView, String text, int start, int end, int color) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        //设置指定位置文字的颜色
        style.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        textView.setText(style);
    }

    public static void changeTextSize(TextView textView, String text, int start, int end, int size) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        //设置指定位置文字的颜色
        AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(size);
        style.setSpan(absoluteSizeSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(style);
    }


    public static void changeTextBg(TextView textView, String text, int start, int end, int color) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        //设置指定位置文字背景图片

        BackgroundColorSpan span = new BackgroundColorSpan(color);

        style.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(style);
    }
}
