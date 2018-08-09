package com.whmnrc.feimei.ui;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.whmnrc.feimei.R;

import butterknife.BindView;


/**
 * @author yjyvi
 * @date 2017/10/8
 * 公用H5页面
 */


public class CommonH5WebView extends BaseActivity {

    @BindView(R.id.wb_content)
    WebView wb_content;

    @BindView(R.id.pb_loading)
    ProgressBar pb_loading;

    public String mTitle;
    public String mH5Url;


    @Override
    protected void back() {
        if (wb_content.canGoBack()) {
            wb_content.goBack();
        } else {
            finish();
        }
    }

    public static void startCommonH5WebView(Context activity, String h5Url, String title) {
        Intent intent = new Intent(activity, CommonH5WebView.class);
        intent.putExtra("h5Url", h5Url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_my_binbei;
    }

    @Override
    public void initViewData() {

        mTitle = getIntent().getStringExtra("title");
        mH5Url = getIntent().getStringExtra("h5Url");

        setTitle(mTitle);
        wb_content.post(new Runnable() {
            @Override
            public void run() {
                wb_content.loadUrl(mH5Url);
                WebSettings settings = wb_content.getSettings();
                settings.setFixedFontFamily("monospace");
                settings.setJavaScriptEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                settings.setLoadWithOverviewMode(true);


                //去除WebView的焦点事件
                wb_content.setFocusableInTouchMode(false);

                wb_content.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });

                //去掉超连接事件
                wb_content.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return true;
                    }
                });

                //取消长按复制事件
                wb_content.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        return true;
                    }
                });
                settings.setDefaultTextEncodingName("utf-8");
                wb_content.setWebChromeClient(new WebChromeClient() {

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);

                        if (newProgress == 100) {
                            //加载完网页进度条消失
                            pb_loading.setVisibility(View.GONE);
                        } else {
                            //开始加载网页时显示进度条
                            pb_loading.setVisibility(View.VISIBLE);
                            //设置进度值
                            pb_loading.setProgress(newProgress);
                        }
                    }
                });
            }
        });


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (wb_content.canGoBack()) {
                wb_content.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
