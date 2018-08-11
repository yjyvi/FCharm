package com.whmnrc.feimei.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 * @author yjyvi
 * @data 2018/8/10.
 */

public class ViewRoUtils {

    public static void roView(final View view,float value){
        view.animate().rotationBy(value).setDuration(500).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                view.setEnabled(true);
            }
        }).start();
    }
}
