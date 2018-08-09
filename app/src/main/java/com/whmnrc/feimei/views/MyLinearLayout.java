package com.whmnrc.feimei.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by zhuwenhai on 2017/12/25.
 */

public class MyLinearLayout extends LinearLayout {


    public MyLinearLayout(Context context) {
        super(context,null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}
