package com.whmnrc.feimei.pop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
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
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzoneShare;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.tauth.Tencent;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.WxShareUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


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
    private ImageView iv_wechat, iv_weibo, iv_cancel, iv_qq, iv_qq_zone;

    private SharePopListener mListener;
    public WxShareUtils mWxShareUtils;
    private Bitmap sBitmap;
    public Tencent mTencent;
    public String mPathname;

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
        iv_qq = (ImageView) view.findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);
        iv_qq_zone = (ImageView) view.findViewById(R.id.iv_qq_zone);
        iv_qq_zone.setOnClickListener(this);

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

        mWxShareUtils = WxShareUtils.getInstance(context.getApplicationContext());

        mTencent = Tencent.createInstance("1107709037", mContext.getApplicationContext());

        //通过Glide加载封面图片URL --获取图片
        if (!TextUtils.isEmpty(coverImgUrl)) {

            Glide.with(context).asBitmap().load(coverImgUrl).into(new SimpleTarget<Bitmap>() {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    sBitmap = resource;
                }
            });
        } else {
            sBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher);
            try {
                saveBitmap(sBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (TextUtils.isEmpty(mDesc)) {
            mDesc = "更多招聘信息尽在菲魅App";
        }

        if (TextUtils.isEmpty(mUrl)) {
            mUrl = "https://www.optic-female.cn";
        }


    }

    public void show() {
        PopUtils.setBackgroundAlpha((Activity) mContext, 0.5f);
        // 设置弹出位置
        mPopupWindow.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
        // 刷新状态
        mPopupWindow.update();

        mPopupWindow.setOnDismissListener(() -> PopUtils.setBackgroundAlpha((Activity) mContext, 1f));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_wechat:
                if (!WxShareUtils.isApplicationAvilible(mContext, "com.tencent.mm")) {
                    ToastUtils.showToast("没有安装微信");
                    return;
                }
                ToastUtils.showToast("分享到微信好友");
                mWxShareUtils.shareUrl(mUrl, mTitle, sBitmap, mDesc, SendMessageToWX.Req.WXSceneSession);
                dismiss();
                break;
            case R.id.iv_circle:
                if (!WxShareUtils.isApplicationAvilible(mContext, "com.tencent.mm")) {
                    ToastUtils.showToast("没有安装微信");
                    return;
                }
                ToastUtils.showToast("分享到朋友圈");
                mWxShareUtils.shareUrl(mUrl, mTitle, sBitmap, mDesc, SendMessageToWX.Req.WXSceneTimeline);
                dismiss();
                break;
            case R.id.iv_qq:
                if (!WxShareUtils.isApplicationAvilible(mContext, "com.tencent.mobileqq")) {
                    ToastUtils.showToast("没有安装QQ");
                    return;
                }
                shareToQQ((Activity) mContext, QQShare.SHARE_TO_QQ_TYPE_DEFAULT, mTitle, mDesc, mUrl, mCoverImgUrl, new BaseActivity.MyQQShareListener());
                dismiss();
                break;
            case R.id.iv_qq_zone:
                if (!WxShareUtils.isApplicationAvilible(mContext, "com.tencent.mobileqq")) {
                    ToastUtils.showToast("没有安装QQ");
                    return;
                }
                shareToQQ((Activity) mContext, QzoneShare.SHARE_TO_QZONE_TYPE_APP, mTitle, mDesc, mUrl, mCoverImgUrl, new BaseActivity.MyQQShareListener());
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
//        if (sBitmap != null) {
//            sBitmap.recycle();
//        }
    }


    private Bundle params;

    private void shareToQQ(Activity activity, int shareType, String title, String desc, String url, String coverImgUrl, BaseActivity.MyQQShareListener mIUiListener) {
        params = new Bundle();
        if (QzoneShare.SHARE_TO_QZONE_TYPE_APP == shareType) {
            params.putInt(QzoneShare.SHARE_TO_QZONE_KEY_TYPE, shareType);
            // 标题
            params.putString(QzoneShare.SHARE_TO_QQ_TITLE, title);
            // 摘要
            params.putString(QzoneShare.SHARE_TO_QQ_SUMMARY, desc);
            // 内容地址
            params.putString(QzoneShare.SHARE_TO_QQ_TARGET_URL, url);

            if (TextUtils.isEmpty(coverImgUrl)) {
                coverImgUrl = mPathname;
            }
            ArrayList<String> imageUrls = new ArrayList<>();
            imageUrls.add(coverImgUrl);
            params.putStringArrayList(QzoneShare.SHARE_TO_QQ_IMAGE_URL, imageUrls);
            params.putInt(QzoneShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);

            // 分享操作要在主线程中完成
            activity.runOnUiThread(() -> {
                if (mTencent != null) {
                    mTencent.shareToQzone(activity, params, mIUiListener);
                }
            });

        } else {
            params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, shareType);
            // 标题
            params.putString(QQShare.SHARE_TO_QQ_TITLE, title);
            // 摘要
            params.putString(QQShare.SHARE_TO_QQ_SUMMARY, desc);
            // 内容地址
            params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, url);
            // 网络图片地址　　
            params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, coverImgUrl);

            // 分享操作要在主线程中完成
            activity.runOnUiThread(() -> {
                if (mTencent != null) {
                    mTencent.shareToQQ(activity, params, mIUiListener);
                }
            });
        }


    }


    private void saveBitmap(Bitmap bitmap) throws IOException {
        mPathname = Environment.getExternalStoragePublicDirectory(Environment.
                DIRECTORY_DOWNLOADS) + "/FeiMeiDownload/" + "QZoneShareImg.png";
        File file = new File(mPathname);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
