package com.whmnrc.feimei.ui.industry;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ReadDetailsBean;
import com.whmnrc.feimei.beans.ResourcesFileBean;
import com.whmnrc.feimei.pop.PopAppreciate;
import com.whmnrc.feimei.pop.PopReadComment;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.presener.AddOrDelCollectionPresenter;
import com.whmnrc.feimei.presener.GetReadDetailsPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.ui.mine.PayVipActivity;
import com.whmnrc.feimei.ui.product.ProductSpecificationsActivity;
import com.whmnrc.feimei.utils.TimeUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class IndustryDetailsActivity extends BaseActivity implements GetReadDetailsPresenter.GetReadDetailsListener, AddOrDelCollectionPresenter.AddOrDelCollectionListener {
    @BindView(R.id.web_content)
    WebView mWebContent;

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

    public PopShare mPopShare;
    public PopAppreciate mPopAppreciate;
    public GetReadDetailsPresenter mGetReadDetailsPresenter;
    public String mReadId;

    private ReadDetailsBean.ResultdataBean mReadDetailsBean;
    /**
     * 阅读
     */
    public static final int READ_DETAILS_TYPE = 1;
    public static final int FILE_DETAILS_TYPE = 2;
    public int mType;
    public PopReadComment mPopReadComment;
    public AddOrDelCollectionPresenter mAddOrDelCollectionPresenter;
    public ResourcesFileBean.ResultdataBean.LibrarysBean mLibrarysBean;
    private String downloadUrl;
    private String downloadFileName;
    private int mPosition;


    @Override
    protected void initViewData() {
        isShowDialog(true);
        setTitle("行业资源");
        rightVisible(R.mipmap.icon_share);

        mType = getIntent().getIntExtra("type", -1);
        mPosition = getIntent().getIntExtra("position", -1);

        mAddOrDelCollectionPresenter = new AddOrDelCollectionPresenter(this);

        if (UserManager.getUserIsVip()) {
            mTvJoinVip.setVisibility(View.GONE);
        } else {
            mTvJoinVip.setVisibility(View.VISIBLE);
        }


        if (mType == FILE_DETAILS_TYPE) {
            mTvDownloadCount.setVisibility(View.VISIBLE);

            mLibrarysBean = getIntent().getParcelableExtra("librarysBean");
            if (mLibrarysBean != null) {
                String url;

                if (UserManager.getUserIsVip() || mLibrarysBean.getIsPay() == 1) {
                    Drawable drawable = ContextCompat.getDrawable(mTvDownloadCount.getContext(), R.mipmap.icon_read_download2);
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        mTvDownloadCount.setCompoundDrawables(drawable, null, null, null);
                    }
                } else {
                    Drawable drawable = ContextCompat.getDrawable(mTvDownloadCount.getContext(), R.mipmap.icon_read_download);
                    if (drawable != null) {
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        mTvDownloadCount.setCompoundDrawables(drawable, null, null, null);
                    }
                }


                mReadId = mLibrarysBean.getID();
                downloadUrl = mLibrarysBean.getFilePath();
                downloadFileName = mLibrarysBean.getName();
                if (UserManager.getUserIsVip()) {
                    url = mLibrarysBean.getChargeConten();
                } else {
                    url = mLibrarysBean.getFreeConten();
                }

                loadDetailsUrl(mWebContent, url);
                mTvTitleName.setText(String.format("[%s]", mLibrarysBean.getName()));
                mTvTitle.setText(mLibrarysBean.getTitle());
                mTvTimeBrowse.setText(String.format("%s 阅读%s", TimeUtils.getDateToString(Long.parseLong(mLibrarysBean.getCreateTime())), mLibrarysBean.getClickNumber()));
                mTvDownloadCount.setText(String.valueOf(mLibrarysBean.getDownloadNumber()));
//                mTvCommentCount.setText(String.valueOf(mLibrarysBean.getCommentCount()));

                mTvCollection.setSelected(mLibrarysBean.getIsCollection() == 1);
                mTvCollection.setText(mLibrarysBean.getIsCollection() == 1 ? "已收藏" : "收藏");

                mTvReadPageHint.setText(String.format("剩余%s页未读！继续阅读需付费。", mLibrarysBean.getChargePage()));
            }
        } else {
            mTvDownloadCount.setVisibility(View.GONE);

            mReadId = getIntent().getStringExtra("readId");
            mGetReadDetailsPresenter = new GetReadDetailsPresenter(this);
            mGetReadDetailsPresenter.getReadDetails(mReadId);
        }

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_industry_details;
    }

    public static void start(Context context, String readId, int type) {
        Intent starter = new Intent(context, IndustryDetailsActivity.class);
        starter.putExtra("readId", readId);
        starter.putExtra("type", type);
        context.startActivity(starter);
    }

    public static void startFielDetails(Context context, ResourcesFileBean.ResultdataBean.LibrarysBean librarysBean, int type, int position) {
        Intent starter = new Intent(context, IndustryDetailsActivity.class);
        starter.putExtra("librarysBean", librarysBean);
        starter.putExtra("type", type);
        starter.putExtra("position", position);
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
                if (mType == READ_DETAILS_TYPE) {
                    return;
                }

                if (UserManager.getUserIsVip() || mLibrarysBean.getIsPay() == 1) {
                    mTvDownloadCount.setEnabled(false);
                    ProductSpecificationsActivity.showDownloadPop(IndustryDetailsActivity.this, mTvDownloadCount, downloadUrl, downloadFileName, 1, mReadId, new ProductSpecificationsActivity.DownloadListener() {
                        @Override
                        public void downloadSuccess() {
                            mTvDownloadCount.setEnabled(true);
                        }

                        @Override
                        public void downloadField() {
                            mTvDownloadCount.setEnabled(true);
                        }
                    });
                }
                break;
            case R.id.tv_comment_count:
                if (mPopReadComment == null) {
                    mPopReadComment = new PopReadComment(IndustryDetailsActivity.this, mReadId);
                }

                mPopReadComment.show();

                break;
            case R.id.tv_collection:
                int isCollection = 0;

                if (mReadDetailsBean != null) {
                    isCollection = mReadDetailsBean.getIsCollection();
                }

                if (mLibrarysBean != null) {
                    isCollection = mLibrarysBean.getIsCollection();
                }

                if (isCollection == 1) {
                    ArrayList<String> readIds = new ArrayList<>();
                    readIds.add(mReadId);
                    mAddOrDelCollectionPresenter.delCollection(readIds);
                } else {
                    mAddOrDelCollectionPresenter.addCollection(mReadId, AddOrDelCollectionPresenter.FILE_COLLECTION);
                }
                break;
            case R.id.tv_price:

                if (!UserManager.getIsLogin(IndustryDetailsActivity.this)) {
                    return;
                }
                if (mType == FILE_DETAILS_TYPE) {
                    PayActivity.startFileResource(view.getContext(), PayActivity.RESOURCE_PAY, mLibrarysBean);
                } else {
                    PayActivity.startRead(view.getContext(), PayActivity.COLUMN_PAY, mReadDetailsBean.getRead());
                }
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


            mTvCollection.setSelected(readDetailsBean.getIsCollection() == 1);
            mTvCollection.setText(readDetailsBean.getIsCollection() == 1 ? "已收藏" : "收藏");

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

    @Override
    public void collectionSuccess(boolean isAdd) {
        mTvCollection.setSelected(isAdd);
        mTvCollection.setText(isAdd ? "已收藏" : "收藏");
        if (mReadDetailsBean != null) {
            mReadDetailsBean.setIsCollection(isAdd ? 1 : 0);
        }

        if (mLibrarysBean != null) {
            mLibrarysBean.setIsCollection(isAdd ? 1 : 0);
        }

        if (mCollectionListener != null) {
            mCollectionListener.collectionSuccess(isAdd);
        }
    }

    @Override
    public void collectionCodeField() {

    }


    public static CollectionListener mCollectionListener;

    public static void setCollectionListener(CollectionListener collectionListener) {
        mCollectionListener = collectionListener;
    }

    public interface CollectionListener {
        void collectionSuccess(boolean isCollection);
    }
}
