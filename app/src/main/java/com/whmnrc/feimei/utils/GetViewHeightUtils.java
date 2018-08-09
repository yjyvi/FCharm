package com.whmnrc.feimei.utils;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author yjyvi
 * @data 2018/5/2.
 */

public class GetViewHeightUtils {

    public static void changeViewHeight(View imageView , int dimen){
        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
        layoutParams.width = dimen;
        layoutParams.height = dimen;
        imageView.setLayoutParams(layoutParams);
    }
}
