package com.whmnrc.feimei.ui.product;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.luck.picture.lib.permissions.RxPermissions;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.RegulationBookListBean;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.pop.PopUtils;
import com.whmnrc.feimei.presener.DownloadFilePresenter;
import com.whmnrc.feimei.presener.GetRegulationBookPresenter;
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

public class ProductSpecificationsActivity extends BaseActivity implements GetRegulationBookPresenter.GetBookListener {
    @BindView(R.id.web_content)
    WebView mWbContent;
    public RegulationBookListBean.ResultdataBean.ReadBean mReadBean;
    @BindView(R.id.ll_commit)
    LinearLayout mLlCommit;


    private PopShare mPopShare;
    public int mType;
    private GetRegulationBookPresenter mGetRegulationBookPresenter;

//    @Override
//    protected void back() {
//        if (mWbContent.canGoBack()) {
//            mWbContent.goBack();
//        } else {
//            finish();
//        }
//    }


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

    @Override
    protected void initViewData() {


        isShowDialog(true);
        mReadBean = getIntent().getParcelableExtra("readBean");
        mType = getIntent().getIntExtra("type", 0);
        setTitle("行业资源");
        rightVisible(R.mipmap.icon_share);
        if (mReadBean == null) {
            String bookId = getIntent().getStringExtra("bookId");
            mGetRegulationBookPresenter = new GetRegulationBookPresenter(this);
            mGetRegulationBookPresenter.getBook(bookId);
        } else {
            mWbContent.post(() -> loadDetailsUrl());
        }

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

    /**
     * 商品详情查看规格书
     *
     * @param context
     * @param bookId
     */
    public static void start(Context context, String bookId) {
        Intent starter = new Intent(context, ProductSpecificationsActivity.class);
        starter.putExtra("bookId", bookId);
        context.startActivity(starter);
    }


    @OnClick({R.id.rl_right, R.id.ll_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_right:
                mPopShare = new PopShare(ProductSpecificationsActivity.this,
                        mReadBean.getName(),
                        "",
                        "", mReadBean.getSubtitle());
                mPopShare.show();
                break;
            case R.id.ll_commit:
                mLlCommit.post(() -> {
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

                                @Override
                                public void downloading() {
                                    mLlCommit.setEnabled(false);
                                    mLlCommit.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.normal_gray));
                                }
                            });
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
        RxPermissions rxPermissions = new RxPermissions((Activity) context);
        rxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).subscribe(aBoolean -> {
            if (aBoolean) {
                download((Activity) context, contentView, url, name, fileType, fileId, downloadListener);
            } else {
                ToastUtils.showToast("请开启存储权限，否则无法下载！");
            }
        });

    }

    private static void download(Activity context, View contentView, String url, String name, int fileType, String fileId, DownloadListener downloadListener) {
        if (TextUtils.isEmpty(url)) {
            ToastUtils.showToast("文件下载地址为空");
            downloadListener.downloadField();
            return;
        }


        final Activity activity = context;
        View popView = LayoutInflater.from(activity).inflate(com.whmnrc.mylibrary.R.layout.download_pop, null);
        final PopupWindow popupWindow = new PopupWindow(popView, LinearLayout.LayoutParams
                .MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        popView.setPadding(30, 30, 30, 30);
        //设置SelectPicPopupWindow弹出窗体可点击
        PopUtils.showPoPNormal(context, contentView, popupWindow, Gravity.BOTTOM, null);


        TextView tvPopDownload = (TextView) popView.findViewById(com.whmnrc.mylibrary.R.id.tv_pop_download);
        TextView tvPopCancel = (TextView) popView.findViewById(com.whmnrc.mylibrary.R.id.tv_pop_cancel);
        tvPopDownload.setOnClickListener(v -> {

            String filePath = Environment.getExternalStoragePublicDirectory(Environment.
                    DIRECTORY_DOWNLOADS) + "/FeiMeiDownload/";
            String fileName = name + getFileNameNoEx(url);

            if (new File(fileName).exists()) {
                ToastUtils.showToast(String.format("文件已下载到：%s", Environment.DIRECTORY_DOWNLOADS + "/FeiMeiDownload/"));
                return;
            }

            ToastUtils.showToast("开始下载");

            if (url.contains("http")) {

                OkHttpUtils.get().url(url).build().execute(new FileCallBack(filePath, fileName) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast("下载失败");
                        downloadListener.downloadField();
                    }

                    @Override
                    public void onResponse(File response, int id) {
                        ToastUtils.showToast(String.format("下载成功,文件已下载到：%s", Environment.DIRECTORY_DOWNLOADS + "/FeiMeiDownload/"));
                        downloadListener.downloadSuccess();
                        DownloadFilePresenter downloadFilePresenter = new DownloadFilePresenter(() -> {

                        });
                        downloadFilePresenter.downloadFileReq(fileType, fileId);

                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        downloadListener.downloading();
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
            downloadListener.downloadField();
            PopUtils.setBackgroundAlpha(context, 1F);
        });
    }

    @Override
    public void getBookSuccess(boolean isRefresh, RegulationBookListBean.ResultdataBean bean) {
        if (bean.getRead() != null && bean.getRead().size() > 0) {
            mReadBean = bean.getRead().get(0);
            mWbContent.post(() -> loadDetailsUrl());
        }
        isShowDialog(false);
    }

    @Override
    public void getBookField() {
        isShowDialog(false);
    }


    public interface DownloadListener {
        void downloadSuccess();

        void downloadField();

        void downloading();
    }


    private static String suffixes = "avi|mpeg|3gp|mp3|mp4|wav|jpeg|gif|jpg|png|apk|exe|pdf|rar|zip|docx|doc|xls|ppt|pptx|xlsx";

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
