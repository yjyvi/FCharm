package com.whmnrc.feimei.utils;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.whmnrc.feimei.R;

/**
 * @author yjyvi
 * @data 2018/8/17.
 */

public class BookFileTypeUtils {


    /**
     * 规格书的类型
     * @param textView
     * @param type
     */
    public static void showBookFileType(TextView textView, int type) {
        if (textView == null) {
            return;
        }
        Drawable drawable;
        switch (type) {
            case 0:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_p);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case 1:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_w);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case 2:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_a);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 文库的类型
     * @param textView
     * @param type
     */
    public static void showFileType(TextView textView, int type) {

        if (textView == null) {
            return;
        }
        Drawable drawable;
        switch (type) {
            case 0:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_x);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case 1:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_p);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case 2:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_w);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case 3:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_a);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;
            case 4:
                drawable = ContextCompat.getDrawable(textView.getContext(), R.mipmap.icon_s);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    textView.setCompoundDrawables(drawable, null, null, null);
                }
                break;

            default:
                break;
        }

    }
}
