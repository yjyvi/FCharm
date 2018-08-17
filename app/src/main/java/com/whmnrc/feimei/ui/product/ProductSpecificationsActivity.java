package com.whmnrc.feimei.ui.product;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
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
import com.whmnrc.feimei.beans.RegulationBookListBean;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.pop.PopUtils;
import com.whmnrc.feimei.presener.DownloadFilePresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * @author yjyvi
 * @data 2018/8/10.
 */

public class ProductSpecificationsActivity extends BaseActivity implements DownloadFilePresenter.DownloadFileListener {
    @BindView(R.id.web_content)
    WebView mWbContent;
    public RegulationBookListBean.ResultdataBean.ReadBean mReadBean;
    @BindView(R.id.ll_commit)
    LinearLayout mLlCommit;


    private PopShare mPopShare;
    public static DownloadFilePresenter mDownloadFilePresenter;
    public int mType;

    @Override
    protected void initViewData() {
        mDownloadFilePresenter = new DownloadFilePresenter(this);

        isShowDialog(true);
        mReadBean = getIntent().getParcelableExtra("readBean");
        mType = getIntent().getIntExtra("type", -1);
        setTitle("行业资源");
        rightVisible(R.mipmap.icon_share);
        if (mReadBean == null) {
            return;
        }
        mWbContent.post(() -> loadDetailsUrl());

    }

    /**
     * 加载Url
     */
    private void loadDetailsUrl() {
        mWbContent.loadUrl(mReadBean.getConten());
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

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product_specifications;
    }

    public static void start(Context context, RegulationBookListBean.ResultdataBean.ReadBean readBean, int type) {
        Intent starter = new Intent(context, ProductSpecificationsActivity.class);
        starter.putExtra("readBean", readBean);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }


    @OnClick({R.id.rl_right, R.id.ll_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_right:
                mPopShare = new PopShare(ProductSpecificationsActivity.this,
                        mReadBean.getName(),
                        "",
                        mReadBean.getConten(), mReadBean.getSubtitle());
                mPopShare.show();
                break;
            case R.id.ll_commit:
                mLlCommit.setEnabled(false);
                mLlCommit.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.normal_gray));
                showDownloadPop(ProductSpecificationsActivity.this, mWbContent, mReadBean.getFilePath(), mReadBean.getName(), mType, mReadBean.getID(), new DownloadListener() {
                    @Override
                    public void downloadSuccess() {
                        mLlCommit.setEnabled(true);
                        mLlCommit.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.normal_blue_text_color));
                    }

                    @Override
                    public void downloadField() {
                        mLlCommit.setEnabled(true);
                        mLlCommit.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.normal_blue_text_color));
                    }
                });
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
    public static void showDownloadPop(final Context context, View contentView, final String url, String name, int fileType, String fileId, DownloadListener downloadListener) {
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
            ToastUtils.showToast("开始下载");
            String filePath = Environment.getExternalStorageDirectory() + "/FeiMeiDownload/";
            String fileName = name + getFileNameNoEx(url);
            if (url.contains("http")) {

                OkHttpUtils.get().url(url).build().execute(new FileCallBack(filePath, fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast("下载失败");
                        downloadListener.downloadField();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        ToastUtils.showToast(String.format("下载成功,文件已下载到：%s", filePath));
                        downloadListener.downloadSuccess();
                        mDownloadFilePresenter.downloadFileReq(fileType, fileId);
                    }
                });
            }
            popupWindow.dismiss();
        });
        tvPopCancel.setOnClickListener(v -> {
            downloadListener.downloadField();
            popupWindow.dismiss();
        });

        popupWindow.setOnDismissListener(() -> {
            PopUtils.setBackgroundAlpha((Activity) context, 1F);
            downloadListener.downloadField();
        });
    }

    @Override
    public void downloadFileSuccess() {

    }


    public interface DownloadListener {
        void downloadSuccess();

        void downloadField();
    }


    private static String suffixes = "avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|pdf|rar|zip|docx|doc";

    /**
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        //正则判断
        Pattern pat = Pattern.compile("[\\w]+[\\.](" + suffixes + ")");
        //条件匹配
        Matcher mc = pat.matcher(filename);
        while (mc.find()) {
            //截取文件名后缀名
            filename = mc.group();
            Log.e("substring:", filename);
        }

        return filename;
    }
}
