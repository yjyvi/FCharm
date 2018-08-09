package com.whmnrc.feimei.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * @author yjyvi
 * @data 2018/6/12.
 */

public class MyWebView extends WebView {

    public MyWebView(Context context) {
        super(context, null);
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //首先DOWN事件，无论怎样都会传递到WebView中，这时
            //可以调用requestDisallowInterceptTouchEvent，让Scroll
            //View不拦截MOVE事件
            case MotionEvent.ACTION_DOWN:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                //在MOVE事件中，我们确定两种情况Scroll是需要ScrollView来执行
                //如果WebView中的内容滑到顶部，这时就由ScrollView来执行
                //Scroll动作。如果WebView中的内容滑到底部，这时就由
                //ScrollView来执行Scroll动作。其他情况Scroll动作都由WebView
                //来执行。
                boolean scroll = true;
                //是否滑到顶部
                if (isTop()) {
                    scroll = false;
                    //是否滑到底部
                } else if (isBottom()) {
                    scroll = false;
                }
                getParent().getParent().requestDisallowInterceptTouchEvent(scroll);

                break;
            case MotionEvent.ACTION_UP:
                getParent().getParent().requestDisallowInterceptTouchEvent(false);
            default:
                break;
        }

        return super.onTouchEvent(event);
    }

    private boolean isBottom() {
        float htmlHeight = getContentHeight() * getScale();
        float measuredHeight = getMeasuredHeight();
        float currentheight = getHeight() + getScrollY();
        Log.d("xuchun", htmlHeight + ", " + measuredHeight + ", " + getHeight() + ", " + getScrollY());
        return htmlHeight == currentheight;
    }

    private boolean isTop() {
        return getScrollY() == 0;
    }
}
