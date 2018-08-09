package com.whmnrc.feimei.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @author yjyvi
 * @data 2018/7/2.
 */

public class MyRelativelayout extends RelativeLayout {

    public MyRelativelayout(Context context) {
        super(context,null);
    }

    public MyRelativelayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyRelativelayout(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
