package com.whmnrc.feimei.ui.product;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ProductDetailsBean;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.pop.PopUtils;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/8/10.
 */

public class ProductSpecificationsActivity extends BaseActivity {
    @BindView(R.id.web_content)
    WebView mWbContent;
    public ProductDetailsBean.ResultdataBean.CommodityBean mCommodity;
    @BindView(R.id.ll_commit)
    LinearLayout mLlCommit;


    private PopShare mPopShare;

    @Override
    protected void initViewData() {


        isShowDialog(true);
        mCommodity = getIntent().getParcelableExtra("commodity");
        setTitle("行业资源");
        rightVisible(R.mipmap.icon_share);
        if (mCommodity == null) {
            return;
        }
        mWbContent.post(new Runnable() {
            @Override
            public void run() {
                mWbContent.loadUrl(mCommodity.getRegulationBookID());
                WebSettings settings = mWbContent.getSettings();
                settings.setFixedFontFamily("monospace");
                settings.setJavaScriptEnabled(true);
                settings.setUseWideViewPort(true);
                settings.setDefaultTextEncodingName("utf-8");
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                settings.setLoadWithOverviewMode(true);


                //去除WebView的焦点事件
                mWbContent.setFocusableInTouchMode(false);

                mWbContent.setOnTouchListener((v, event) -> false);

                //去掉超连接事件
                mWbContent.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return true;
                    }
                });

                //取消长按复制事件
                mWbContent.setOnLongClickListener(v -> true);

                mWbContent.setWebChromeClient(new WebChromeClient() {

                    @Override
                    public void onProgressChanged(WebView view, int newProgress) {
                        super.onProgressChanged(view, newProgress);

                        if (newProgress == 100) {
                            //加载完网页进度条消失
                            isShowDialog(false);
                        }
                    }
                });
            }
        });

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product_specifications;
    }

    public static void start(Context context, ProductDetailsBean.ResultdataBean.CommodityBean commodity) {
        Intent starter = new Intent(context, ProductSpecificationsActivity.class);
        starter.putExtra("commodity", commodity);
        context.startActivity(starter);
    }


    @OnClick({R.id.rl_right, R.id.ll_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_right:
                mPopShare = new PopShare(ProductSpecificationsActivity.this,
                        mCommodity.getName(),
                        mCommodity.getImg(),
                        mCommodity.getRegulationBookID(), " ");
                mPopShare.show();
                break;
            case R.id.ll_commit:
                showDownloadPop(ProductSpecificationsActivity.this, mWbContent, mCommodity.getRegulationBookID(), mCommodity.getName());
                break;
            default:
                break;
        }
    }


    /**
     * 显示下载弹窗
     *
     * @param context
     * @param contentView
     * @param url
     */
    public static void showDownloadPop(final Context context, View contentView, final String url, String name) {
        if (TextUtils.isEmpty(url)) {
            return;
        }


        final Activity activity = (Activity) context;
        View popView = LayoutInflater.from(activity).inflate(com.whmnrc.mylibrary.R.layout.download_pop, null);
        final PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams
                .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popView.setPadding(30, 30, 30, 30);
        //设置SelectPicPopupWindow弹出窗体可点击
        PopUtils.showPoPNormal((Activity) context, contentView, popupWindow, Gravity.BOTTOM, null);



        TextView tvPopDownload = (TextView) popView.findViewById(com.whmnrc.mylibrary.R.id.tv_pop_download);
        TextView tvPopCancel = (TextView) popView.findViewById(com.whmnrc.mylibrary.R.id.tv_pop_cancel);
        tvPopDownload.setOnClickListener(v -> {
            String filePath = Environment.getExternalStorageDirectory() + "/FeiMeiDownload/";
            String fileName = name + getFileNameNoEx(url);
            if (url.contains("http")) {
                OkHttpUtils.get().url(url).build().execute(new FileCallBack(filePath, fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast("下载失败");
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        ToastUtils.showToast("下载成功");
                    }
                });
            }
            popupWindow.dismiss();
        });
        tvPopCancel.setOnClickListener(v -> popupWindow.dismiss());
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }
}
