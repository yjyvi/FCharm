package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.WxShareUtils;


/**
 * Created by guox on 2017/6/2 0002.
 * 分享弹窗
 */

public class PopShare implements View.OnClickListener {

    private String mTitle;
    private String mCoverImgUrl;
    private int res;
    private String mUrl;
    private String mDesc;
    private PopupWindow mPopupWindow;
    private View mView;
    private Context mContext;
    private ImageView iv_wechat, iv_weibo, iv_cancel;

    private SharePopListener mListener;
    public WxShareUtils mWxShareUtils;
    private Bitmap sBitmap;

    public void setListener(SharePopListener listener) {
        mListener = listener;
    }

    public PopShare(Context context, String title, String coverImgUrl, String url, String desc) {
        mContext = context;
        mTitle = title;
        mCoverImgUrl = coverImgUrl;
        mUrl = url;
        mDesc = desc;
        View view = LayoutInflater.from(context).inflate(R.layout.pop_share, null);
        LinearLayout ll_layout = (LinearLayout) view.findViewById(R.id.ll_layout);
        ll_layout.setOnClickListener(this);
        iv_cancel = (ImageView) view.findViewById(R.id.iv_cancel);
        iv_cancel.setOnClickListener(this);
        iv_wechat = (ImageView) view.findViewById(R.id.iv_wechat);
        iv_wechat.setOnClickListener(this);
        iv_weibo = (ImageView) view.findViewById(R.id.iv_circle);
        iv_weibo.setOnClickListener(this);

        // 设置popwindow弹出大小
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置允许在外点击消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setAnimationStyle(R.style.PopupWindow);
        // 设置PopupWindow以外部分的背景颜色  有一种变暗的效果
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        PopUtils.setBackgroundAlpha((Activity) mContext, 0.5f);
        // 使其聚集
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);

        mWxShareUtils = WxShareUtils.getInstance(context.getApplicationContext());
        //通过Glide加载封面图片URL --获取图片
        Glide.with(context).asBitmap().load(coverImgUrl).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                sBitmap = resource;
            }
        });

    }

    public void show() {
        // 设置弹出位置
        mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        // 刷新状态
        mPopupWindow.update();

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setBackgroundAlpha((Activity) mContext, 1f);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wechat:
                if (!WxShareUtils.isApplicationAvilible(mContext,"com.tencent.mm")) {
                    return;
                }
                ToastUtils.showToast("分享到微信好友");
                mWxShareUtils.shareUrl(mUrl,mTitle,sBitmap,mDesc, SendMessageToWX.Req.WXSceneSession);
                dismiss();
                break;
            case R.id.iv_circle:
                if (!WxShareUtils.isApplicationAvilible(mContext,"com.tencent.mm")) {
                    return;
                }
                ToastUtils.showToast("分享到朋友圈");
                mWxShareUtils.shareUrl(mUrl,mTitle,sBitmap,mDesc, SendMessageToWX.Req.WXSceneTimeline);
                dismiss();
                break;
            case R.id.iv_cancel:
                dismiss();
                break;
            default:
                break;
        }

    }

    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }


    public interface SharePopListener {
        void onWeChatClick();

        void onQQClick();

        void onCircleClick();
    }

    /**
     * 隐藏菜单
     */
    public void dismiss() {
        mPopupWindow.dismiss();
    }


}
