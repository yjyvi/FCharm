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
import com.whmnrc.feimei.beans.ProductDetailsBean;


/**
 * Created by guox on 2017/6/2 0002.
 * 咨询弹窗
 */

public class PopServerInfo {

    private  ProductDetailsBean.ResultdataBean.CommodityBean mCommdity;
    private PopupWindow mPopupWindow;
    private Context mContext;

    private PopHintListener mPopHintListener;


    public void setPopHintListener(PopHintListener popHintListener) {
        mPopHintListener = popHintListener;
    }

    public PopServerInfo(Context context, ProductDetailsBean.ResultdataBean.CommodityBean commodity) {
        mContext = context;
        this.mCommdity  =commodity;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_server_info, null);
        LinearLayout ll_layout = (LinearLayout) view.findViewById(R.id.ll_layout);
        TextView tvName = view.findViewById(R.id.tv_name);
        TextView tvTel = view.findViewById(R.id.tv_tel);
        TextView tvMail = view.findViewById(R.id.tv_mail);

        tvName.setText(String.format("销售人员：%s",mCommdity.getSalesman()));
        tvTel.setText(String.format("邮箱：%s",mCommdity.getMail()));
        tvMail.setText(String.format("电话：%s",mCommdity.getPhone()));

        ImageView ivClose = view.findViewById(R.id.iv_close);

        ivClose.setOnClickListener(v -> {
            if (mPopupWindow != null) {
                mPopupWindow.dismiss();
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

        mPopupWindow.setOnDismissListener(() -> PopUtils.setBackgroundAlpha((Activity) mContext, 1f));
    }


    public interface PopHintListener {
        void confirm();
    }


}
