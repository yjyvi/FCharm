package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whmnrc.feimei.R;



/**
 * Created by yjyvi on 2017/9/21.
 */

public class PopUtils {

    /**
     * 默认提示弹窗
     *
     * @param context
     * @param contentView
     */
    public static void showNotify(final Activity context, View contentView, String msg, String commitContent, final NormalNotifyPopListener clickListener) {

        View layoutView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_normal_notify, null);
        final PopupWindow sharePopupWindow = new PopupWindow(layoutView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView tv_msg = (TextView) layoutView.findViewById(R.id.tv_msg);
        TextView tv_commit = (TextView) layoutView.findViewById(R.id.tv_commit);

        tv_msg.setText(msg);
        tv_commit.setText(commitContent);

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.commitClick();
                sharePopupWindow.dismiss();
            }
        });


        showPoPNormal(context, contentView, sharePopupWindow, Gravity.CENTER, null);
    }



    /**
     * 下载更新提示弹窗
     *
     * @param context
     * @param contentView
     */
    public static void showVersionNotify(final Activity context, View contentView, String msg, String commitContent, final NormalNotifyPopListener clickListener) {

        View layoutView = LayoutInflater.from(context.getApplicationContext()).inflate(R.layout.item_normal_notify, null);
        final PopupWindow sharePopupWindow = new PopupWindow(layoutView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        TextView tv_msg = (TextView) layoutView.findViewById(R.id.tv_msg);
        TextView tv_commit = (TextView) layoutView.findViewById(R.id.tv_commit);

        tv_msg.setText(msg);
        tv_commit.setText(commitContent);

        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.commitClick();
            }
        });
        sharePopupWindow.showAtLocation(contentView, Gravity.CENTER, 0, 0);
    }







    /**
     * 公共参数
     *
     * @param context
     * @param contentView
     * @param popupWindow
     */
    private static void showPoPNormal(final Activity context, View contentView, PopupWindow popupWindow, int location, final NormalNotifyPopListener clickListener) {
        //设置SelectPicPopupWindow弹出窗体可点击
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置SelectPicPopupWindow弹出窗体动画效果
        setBackgroundAlpha(context, 0.5F);
        popupWindow.showAtLocation(contentView, location, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha(context, 1F);
                if (clickListener != null) {
                    clickListener.dissmiss();
                }
            }
        });
    }


    public interface NormalNotifyPopListener {
        void commitClick();

        void dissmiss();
    }


    /**
     * 设置页面的透明度
     *
     * @param bgAlpha 1表示不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            //不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            //此行代码主要是解决在华为手机上半透明效果无效的bug
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }

        activity.getWindow().setAttributes(lp);
    }



    public static int getNavigationBarHeight(Activity activity) {
        Resources resources = activity.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height",
                "dimen", "android");
        //获取NavigationBar的高度
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }
}
