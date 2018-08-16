package com.whmnrc.feimei.ui.industry;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ReadDetailsBean;
import com.whmnrc.feimei.pop.PopAppreciate;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.presener.GetReadDetailsPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.ui.mine.PayVipActivity;
import com.whmnrc.feimei.utils.TimeUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class IndustryDetailsActivity extends BaseActivity implements GetReadDetailsPresenter.GetReadDetailsListener {
    @BindView(R.id.web_content)
    WebView mWebContent;
    public PopShare mPopShare;
    public PopAppreciate mPopAppreciate;
    public GetReadDetailsPresenter mGetReadDetailsPresenter;
    public String mReadId;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_title_name)
    TextView mTvTitleName;
    @BindView(R.id.tv_time_browse)
    TextView mTvTimeBrowse;
    @BindView(R.id.tv_download_count)
    TextView mTvDownloadCount;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R.id.tv_collection)
    TextView mTvCollection;
    @BindView(R.id.tv_read_page_hint)
    TextView mTvReadPageHint;
    @BindView(R.id.tv_join_vip)
    TextView mTvJoinVip;
    private ReadDetailsBean.ResultdataBean mReadDetailsBean;

    @Override
    protected void initViewData() {
        setTitle("行业资源");
        rightVisible(R.mipmap.icon_share);
        mReadId = getIntent().getStringExtra("readId");

        mGetReadDetailsPresenter = new GetReadDetailsPresenter(this);
        mGetReadDetailsPresenter.getReadDetails(mReadId);

        if (UserManager.getUserIsVip()) {
            mTvJoinVip.setVisibility(View.GONE);
        } else {
            mTvJoinVip.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_industry_details;
    }

    public static void start(Context context, String readId) {
        Intent starter = new Intent(context, IndustryDetailsActivity.class);
        starter.putExtra("readId", readId);
        context.startActivity(starter);
    }


    @OnClick({R.id.rl_right, R.id.iv_zan, R.id.tv_join_vip, R.id.tv_download_count, R.id.tv_comment_count, R.id.tv_collection, R.id.tv_price})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.rl_right:
                if (mPopShare == null) {
                    mPopShare = new PopShare(IndustryDetailsActivity.this, "1", "1", "1", "1");
                }
                mPopShare.show();
                break;
            case R.id.iv_zan:
                if (mPopAppreciate == null) {
                    mPopAppreciate = new PopAppreciate(IndustryDetailsActivity.this);
                }
                mPopAppreciate.show();
                mPopAppreciate.setPopHintListener(() -> PayActivity.start(view.getContext(), PayActivity.ONE_PAY, "20"));
                break;
            case R.id.tv_join_vip:
                if (!UserManager.getUserIsVip()) {
                    PayVipActivity.start(view.getContext());
                }
                break;
            case R.id.tv_download_count:
                break;
            case R.id.tv_comment_count:

                break;
            case R.id.tv_collection:
                mTvCollection.setSelected(!mTvCollection.isSelected());
                break;
            case R.id.tv_price:
                PayActivity.start(view.getContext(), PayActivity.RESOURCE_PAY, String.valueOf(mReadDetailsBean.getRead().getPrice()));
                break;
            default:
                break;
        }
    }

    @Override
    public void getReadDetailsSuccess(ReadDetailsBean.ResultdataBean readDetailsBean) {
        this.mReadDetailsBean = readDetailsBean;
        if (readDetailsBean != null) {
            String url;

            if (UserManager.getUserIsVip()) {
                url = readDetailsBean.getRead().getChargeConten();
            } else {
                url = readDetailsBean.getRead().getFreeConten();
            }

            loadDetailsUrl(mWebContent, url);
            mTvTitleName.setText(String.format("[%s]", readDetailsBean.getRead().getName()));
            mTvTitle.setText(readDetailsBean.getRead().getTitle());
            mTvTimeBrowse.setText(String.format("%s 阅读%s", TimeUtils.getDateToString(Long.parseLong(readDetailsBean.getRead().getCreateTime())), readDetailsBean.getRead().getClickNumber()));
            mTvCommentCount.setText(String.valueOf(readDetailsBean.getCommentCount()));
//            mTvDownloadCount.setText(String.valueOf(readDetailsBean.getRead().getd));

            mTvCollection.setSelected(readDetailsBean.getIsCollection() == 1);

            mTvReadPageHint.setText(String.format("剩余%s页未读！继续阅读需付费。", readDetailsBean.getRead().getChargePage()));
        }
    }

    @Override
    public void getReadDetailsField() {

    }


    /**
     * 加载Url
     */
    private void loadDetailsUrl(WebView webContent, String url) {
        webContent.loadUrl(url);
        WebSettings settings = webContent.getSettings();
        settings.setFixedFontFamily("monospace");
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setDefaultTextEncodingName("utf-8");
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);


        //去除WebView的焦点事件
        webContent.setFocusableInTouchMode(false);

        webContent.setOnTouchListener((v, event) -> false);

        //去掉超连接事件
        webContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });

        //取消长按复制事件
        webContent.setOnLongClickListener(v -> true);

        webContent.setWebChromeClient(new WebChromeClient() {

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
}
