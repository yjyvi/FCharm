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
 * 确认或取消弹窗
 */

public class PopHintInfo {

    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;


    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }

    public PopHintInfo(Context context,  String content) {
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_pay_info, null);
        LinearLayout ll_layout = (LinearLayout) view.findViewById(R.id.ll_layout);
        TextView tvMsg = view.findViewById(R.id.tv_msg);
        TextView tvCancel = view.findViewById(R.id.tv_cancel);
        TextView tvConfirm = view.findViewById(R.id.tv_confirm);
        ImageView ivClose = view.findViewById(R.id.tv_close);

        tvMsg.setText(content);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
            }
        });

        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }

                if (mPopHintListener != null) {
                    mPopHintListener.confirm();
                }
            }
        });

        // 设置popwindow弹出大小
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.PopupWindow);
        // 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 使其聚集
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);


    }

    public void show() {
         PopUtils.setBackgroundAlpha((Activity) mContext, 0.5f);
        // 设置弹出位置
        mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        // 刷新状态
        mPopupWindow.update();

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setBackgroundAlpha((Activity) mContext, 1f);
            }
        });
    }


    public interface PopHintListener {
        void confirm();
    }


}
