package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whmnrc.feimei.R;


/**
 * Created by guox on 2017/6/2 0002.
 * 评级规则弹窗
 */

public class PopRatingRule {

    private PopupWindow mPopupWindow;
    private Context mContext;


    public PopRatingRule(Context context, String title, String content) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_rating_rule, null);
        LinearLayout ll_layout = (LinearLayout) view.findViewById(R.id.ll_layout);
        TextView tvTitle = view.findViewById(R.id.tv_title);
        TextView tvMsg = view.findViewById(R.id.tv_msg);
        ImageView ivClose = view.findViewById(R.id.iv_close);

        tvTitle.setText(title);
        tvMsg.setText(content);


        // 设置popwindow弹出大小
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ivClose.setOnClickListener(v -> {
            if (mPopupWindow != null) {
                mPopupWindow.dismiss();
            }
        });
        // 使其聚集
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);

        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.PopupWindow);
        // 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public void show() {

         PopUtils.setBackgroundAlpha((Activity) mContext, 0.5f);

        // 设置弹出位置
        mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        // 刷新状态
        mPopupWindow.update();

        mPopupWindow.setOnDismissListener(() -> PopUtils.setBackgroundAlpha((Activity) mContext, 1f));
    }


}
