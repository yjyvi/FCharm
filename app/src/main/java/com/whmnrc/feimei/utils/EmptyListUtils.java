package com.whmnrc.feimei.utils;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.whmnrc.feimei.R;


/**
 * @author yjyvi
 * @data 2018/4/16.
 * 显示空布局
 */

public class EmptyListUtils {

    public static void loadEmpty(boolean flag,  ViewStub viewStub) {
        if (viewStub.getParent() != null) {
            View view = viewStub.inflate();
            TextView textView = view.findViewById(R.id.tv_empty);
            ImageView imageView = view.findViewById(R.id.img_empty);
            imageView.setImageResource(R.mipmap.icon_no_data);
            textView.setText(view.getContext().getResources().getString(R.string.empty_hint));
            textView.setTextSize(12);
            textView.setTextColor(ContextCompat.getColor(viewStub.getContext(), R.color.normal_gray));
        }

        if (flag) {
            viewStub.setVisibility(View.VISIBLE);
        } else {
            viewStub.setVisibility(View.GONE);
        }

    }

    /**
     * 显示空布局
     *
     * @param flag
     * @param textContent
     * @param emptyImg
     * @param viewStub
     */
    public static void loadEmpty(boolean flag, String textContent, int emptyImg, ViewStub viewStub) {
        if (viewStub.getParent() != null) {
            View view = viewStub.inflate();
            TextView textView = view.findViewById(R.id.tv_empty);
            ImageView imageView = view.findViewById(R.id.img_empty);
            imageView.setImageResource(emptyImg);
            textView.setText(textContent);
            textView.setTextSize(12);
            textView.setTextColor(ContextCompat.getColor(viewStub.getContext(), R.color.normal_gray));
        }

        if (flag) {
            viewStub.setVisibility(View.VISIBLE);
        } else {
            viewStub.setVisibility(View.GONE);
        }
    }


}
