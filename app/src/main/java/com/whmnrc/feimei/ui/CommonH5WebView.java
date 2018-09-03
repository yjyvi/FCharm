package com.whmnrc.feimei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.pop.PopShare;

import butterknife.BindView;


/**
 * @author yjyvi
 * @date 2017/10/8
 * 公用H5页面
 */


public class CommonH5WebView extends BaseActivity {

    @BindView(R.id.wb_content)
    WebView mWbContent;

    @BindView(R.id.pb_loading)
    ProgressBar mPbLoading;

    public String mTitle;
    public String mH5Url;
    public PopShare mPopShare;
    public boolean mIsSpecial;

//
//    @Override
//    protected void back() {
//        if (mWbContent.canGoBack()) {
//            mWbContent.goBack();
//        } else {
//            finish();
//        }
//    }

    public static void startCommonH5WebView(Context activity, String h5Url, String title) {
        Intent intent = new Intent(activity, CommonH5WebView.class);
        intent.putExtra("h5Url", h5Url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    public static void startCommonH5WebView(Context activity, String h5Url, String title, boolean isSpecial) {
        Intent intent = new Intent(activity, CommonH5WebView.class);
        intent.putExtra("h5Url", h5Url);
        intent.putExtra("title", title);
        intent.putExtra("isSpecial", isSpecial);
        activity.startActivity(intent);
    }


    @Override
    public int setLayoutId() {
        return R.layout.activity_my_binbei;
    }

    @Override
    public void initViewData() {

        rightVisible(R.mipmap.icon_share);

        isShowDialog(true);
        mTitle = getIntent().getStringExtra("title");
        mH5Url = getIntent().getStringExtra("h5Url");
        mIsSpecial = getIntent().getBooleanExtra("isSpecial", false);


        findViewById(R.id.rl_right).setOnClickListener(v -> {
            if (mPopShare == null) {
                if (!mIsSpecial) {
                    mH5Url = "";
                }
                mPopShare = new PopShare(CommonH5WebView.this, mTitle, "", mH5Url, "");
            }
            mPopShare.show();
        });

        setTitle(mTitle);
        mWbContent.post(new Runnable() {
            @Override
            public void run() {
                mWbContent.loadUrl(mH5Url);
                WebSettings settings = mWbContent.getSettings();
                settings.setFixedFontFamily("monospace");
                settings.setJavaScriptEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setDefaultTextEncodingName("utf-8");
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                settings.setLoadWithOverviewMode(true);


                //去除WebView的焦点事件
//                mWbContent.setFocusableInTouchMode(false);

//                mWbContent.setOnTouchListener((v, event) -> false);

                //去掉超连接事件
                mWbContent.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });

                //取消长按复制事件
                mWbContent.setOnLongClickListener(v -> true);

                mWbContent.setWebChromeClient(new WebChromeClient() {

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);

                        if (newProgress >= 100) {
                            //加载完网页进度条消失
//                            mPbLoading.setVisibility(View.GONE);
                            isShowDialog(false);
                        } else {
//                            //开始加载网页时显示进度条
//                            mPbLoading.setVisibility(View.VISIBLE);
//                            //设置进度值
//                            mPbLoading.setProgress(newProgress);
                        }
                    }
                });
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mWbContent.canGoBack()) {
                mWbContent.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
