package com.whmnrc.feimei.ui.industry;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.ReadDetailsBean;
import com.whmnrc.feimei.beans.ResourcesFileBean;
import com.whmnrc.feimei.beans.VipTypeListBean;
import com.whmnrc.feimei.pop.PopAppreciate;
import com.whmnrc.feimei.pop.PopReadComment;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.presener.AddOrDelCollectionPresenter;
import com.whmnrc.feimei.presener.GetLibraryPresenter;
import com.whmnrc.feimei.presener.GetReadDetailsPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.ui.mine.PayVipActivity;
import com.whmnrc.feimei.ui.product.ProductSpecificationsActivity;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.CollectionEvent;
import com.whmnrc.feimei.utils.evntBusBean.PayEvent;
import com.whmnrc.feimei.views.MyVideoPlayer;
import com.whmnrc.mylibrary.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * @author yjyvi
 * @data 2018/8/2.
 */

public class IndustryDetailsActivity extends BaseActivity implements GetReadDetailsPresenter.GetReadDetailsListener, AddOrDelCollectionPresenter.AddOrDelCollectionListener, GetLibraryPresenter.GetLibraryListener {
    @BindView(R.id.web_content)
    WebView mWebContent;

    @BindView(R.id.tv_industry_title)
    TextView mTvIndustryTitle;
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
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.ll_tool)
    LinearLayout mLlTool;
    @BindView(R.id.my_video)
    MyVideoPlayer mMyVideoPlayer;

    public PopShare mPopShare;
    public PopAppreciate mPopAppreciate;
    public GetReadDetailsPresenter mGetReadDetailsPresenter;
    public String mReadId;

    private ReadDetailsBean.ResultdataBean mReadDetailsBean;
    /**
     * 阅读
     */
    public static final int READ_DETAILS_TYPE = 1;
    /**
     * 文库
     */
    public static final int FILE_DETAILS_TYPE = 2;
    public int mType;
    public PopReadComment mPopReadComment;
    public AddOrDelCollectionPresenter mAddOrDelCollectionPresenter;
    public ResourcesFileBean.ResultdataBean.LibrarysBean mLibrarysBean;
    private String downloadUrl;
    private String downloadFileName;
    private int mPosition;

    private String mShareTitle;
    private String mShareDesc;
    private String mShareImg = "";
    private String mShareUrl;
    public int mCommentCount;
    private GetLibraryPresenter mGetLibraryPresenter;


    @Override
    protected void initViewData() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        isShowDialog(true);
        mTvIndustryTitle.setText("行业资源");

        mType = getIntent().getIntExtra("type", -1);
        mPosition = getIntent().getIntExtra("position", -1);

        mAddOrDelCollectionPresenter = new AddOrDelCollectionPresenter(this);


        if (UserManager.getUserIsVip()) {
            mTvJoinVip.setVisibility(View.GONE);
        } else {
            mTvJoinVip.setVisibility(View.VISIBLE);
        }

        mReadId = getIntent().getStringExtra("readId");

        if (mType == FILE_DETAILS_TYPE) {
            mGetLibraryPresenter = new GetLibraryPresenter(this);
            mGetLibraryPresenter.getLibraryList(true, "", mReadId);
            mTvDownloadCount.setVisibility(View.VISIBLE);
        } else {
            mMyVideoPlayer.setVisibility(View.GONE);
            mTvDownloadCount.setVisibility(View.GONE);
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

    public static void start(Context context, String readId, int type, int position) {
        Intent starter = new Intent(context, IndustryDetailsActivity.class);
        starter.putExtra("readId", readId);
        starter.putExtra("type", type);
        starter.putExtra("position", position);
        context.startActivity(starter);
    }


    @OnClick({R.id.rl_right, R.id.iv_zan, R.id.tv_join_vip, R.id.tv_download_count, R.id.tv_comment_count, R.id.tv_collection, R.id.tv_price})
    public void onClick(final View view) {

        switch (view.getId()) {
            case R.id.rl_right:
                if (mPopShare == null) {
                    mPopShare = new PopShare(IndustryDetailsActivity.this, mShareTitle, mShareImg, "", mShareDesc);
                }
                mPopShare.show();
                break;
            case R.id.iv_zan:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                zanPop(view);
                break;
            case R.id.tv_join_vip:
                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                if (!UserManager.getUserIsVip()) {
                    PayVipActivity.start(view.getContext());
                }
                break;
            case R.id.tv_download_count:
                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                if (mType == READ_DETAILS_TYPE) {
                    return;
                }


                if (UserManager.getUserIsVip() || mLibrarysBean.getIsPay() == 1) {
                    mTvDownloadCount.setEnabled(false);
                    mTvDownloadCount.post(() -> ProductSpecificationsActivity.showDownloadPop(IndustryDetailsActivity.this, mTvDownloadCount, downloadUrl, downloadFileName, 1, mReadId, new ProductSpecificationsActivity.DownloadListener() {
                        @Override
                        public void downloadSuccess() {
                            mTvDownloadCount.setText(String.valueOf(mLibrarysBean.getDownloadNumber() + 1));
                            mTvDownloadCount.setEnabled(true);
                        }

                        @Override
                        public void downloadField() {
                            mTvDownloadCount.setEnabled(true);
                        }

                        @Override
                        public void downloading() {

                        }
                    }));
                }
                break;
            case R.id.tv_comment_count:
                if (mPopReadComment == null) {
                    mPopReadComment = new PopReadComment(IndustryDetailsActivity.this, mReadId);
                }

                mPopReadComment.setPopHintListener(() -> mTvCommentCount.setText(String.valueOf(mCommentCount + 1)));
                mPopReadComment.show();

                break;
            case R.id.tv_collection:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                int isCollection = 0;
                int collectionType = -1;
                if (mReadDetailsBean != null) {
                    isCollection = mReadDetailsBean.getIsCollection();
                    collectionType = AddOrDelCollectionPresenter.READ_COLLECTION;
                }

                if (mLibrarysBean != null) {
                    isCollection = mLibrarysBean.getIsCollection();
                    collectionType = AddOrDelCollectionPresenter.FILE_COLLECTION;
                }

                if (isCollection == 1) {
                    ArrayList<String> readIds = new ArrayList<>();
                    readIds.add(mReadId);
                    mAddOrDelCollectionPresenter.delCollection(readIds);
                } else {
                    mAddOrDelCollectionPresenter.addCollection(mReadId, collectionType);
                }
                break;
            case R.id.tv_price:

                if (!UserManager.getIsLogin(IndustryDetailsActivity.this)) {
                    return;
                }

                if (UserManager.getUserIsVip() || mReadDetailsBean != null && mReadDetailsBean.getIsPay() == 1 || mLibrarysBean != null && mLibrarysBean.getIsPay() == 1) {
                    zanPop(view);
                } else {
                    if (mType == FILE_DETAILS_TYPE) {
                        PayActivity.startFileResource(view.getContext(), PayActivity.RESOURCE_PAY, mLibrarysBean);
                    } else {
                        PayActivity.startRead(view.getContext(), PayActivity.COLUMN_PAY, mReadDetailsBean.getRead());
                    }
                }
                break;
            default:
                break;
        }
    }


    /**
     * 赞赏弹窗
     *
     * @param view
     */
    private void zanPop(View view) {
        if (mPopAppreciate == null) {
            mPopAppreciate = new PopAppreciate(IndustryDetailsActivity.this);
        }
        mPopAppreciate.show();
        mPopAppreciate.setPopHintListener((VipTypeListBean.ResultdataBean bean) ->
                PayActivity.start(view.getContext(), PayActivity.ONE_PAY, String.valueOf(bean.getMoney()), bean.getPayType_ID(), mReadId)
        );
    }

    @Override
    public void getReadDetailsSuccess(ReadDetailsBean.ResultdataBean readDetailsBean) {
        this.mReadDetailsBean = readDetailsBean;
        if (readDetailsBean != null) {
            String url;
            if (readDetailsBean.getIsPay() == 1) {
                url = readDetailsBean.getRead().getChargeConten();
                mTvReadPageHint.setVisibility(View.GONE);
                mLlTool.setVisibility(View.GONE);
                mTvPrice.setText("赞赏");
            } else {
                mTvReadPageHint.setVisibility(View.VISIBLE);
                url = readDetailsBean.getRead().getFreeConten();
                mTvPrice.setText(String.format("￥%s", readDetailsBean.getRead().getPrice()));
                mLlTool.setVisibility(View.VISIBLE);
            }


            mShareTitle = mReadDetailsBean.getRead().getTitle();
            mShareDesc = mReadDetailsBean.getRead().getSubtitle();
            mShareUrl = mReadDetailsBean.getRead().getFreeConten();

            loadDetailsUrl(mWebContent, url);
            mTvTitleName.setText(String.format("[%s]", readDetailsBean.getRead().getName()));
            mTvTitle.setText(readDetailsBean.getRead().getTitle());
            mTvTimeBrowse.setText(String.format("%s 阅读%s", TimeUtils.getDateToString(Long.parseLong(readDetailsBean.getRead().getCreateTime())), readDetailsBean.getRead().getClickNumber()));
            mCommentCount = readDetailsBean.getCommentCount();
            mTvCommentCount.setText(String.valueOf(mCommentCount));


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


//        //去除WebView的焦点事件
//        webContent.setFocusableInTouchMode(false);
//
//        webContent.setOnTouchListener((v, event) -> false);
//
//        //去掉超连接事件
        webContent.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mWebContent.canGoBack()) {
                mWebContent.goBack();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

//    @Override
//    protected void back() {
//        if (mWebContent.canGoBack()) {
//            mWebContent.goBack();
//        } else {
//            super.back();
//        }
//    }

    @Override
    public void collectionSuccess(boolean isAdd) {
        mTvCollection.setSelected(isAdd);
        mTvCollection.setText(isAdd ? "已收藏" : "收藏");

        if (!isAdd) {
            EventBus.getDefault().post(new CollectionEvent().setEventType(CollectionEvent.CANCEL_COLLECTION));
        }

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.goOnPlayOnPause();
    }

    private boolean isPay;

    /**
     * 获取当前播放的时间
     */
    private void getPlayCurrentTime() {
        CharSequence text1 = mMyVideoPlayer.currentTimeTextView.getText();
        if (!TextUtils.isEmpty(text1)) {
            String m = text1.toString().split(":")[0];
            String s = text1.toString().split(":")[1];

            int inM = Integer.parseInt(m);
            int inS = Integer.parseInt(s);

            if (inM > 0) {
                inM = 60 * inM;
            }

            long resultTime = inM + inS;
            if (resultTime >= mLibrarysBean.getChargePage()) {
                if (!isPay) {
                    ToastUtils.showToast(String.format("非VIP用户或未购买只能观看%s秒", mLibrarysBean.getChargePage()));
                }
                JZVideoPlayer.goOnPlayOnPause();
                JZVideoPlayer.backPress();
                mMyVideoPlayer.progressBar.setVisibility(View.INVISIBLE);
                isPay = true;
            }
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            getPlayCurrentTime();

            mMyVideoPlayer.postDelayed(runnable, 1000);
        }
    };

    @Override
    public void getReadSuccess(boolean isRefresh, ResourcesFileBean.ResultdataBean bean) {
        if (bean.getLibrarys() == null) {
            return;
        }

        if (bean.getLibrarys().size() <= 0) {
            return;
        }

        mLibrarysBean = bean.getLibrarys().get(0);
        if (mLibrarysBean != null) {
            String url;
            mShareTitle = mLibrarysBean.getTitle();
            mShareDesc = mLibrarysBean.getSubtitle();
            mShareUrl = mLibrarysBean.getFreeConten();

            if (mLibrarysBean.getType() == 4) {
                mMyVideoPlayer.setVisibility(View.VISIBLE);
                //初始化视频播放器
                mMyVideoPlayer.setUp(mLibrarysBean.getFilePath(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL);
                mMyVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mMyVideoPlayer.titleTextView.setText(mLibrarysBean.getTitle());
                JZVideoPlayer.SAVE_PROGRESS = false;

                GlideUtils.LoadImage(this, mLibrarysBean.getImg(), mMyVideoPlayer.thumbImageView);

                mMyVideoPlayer.startButton.setOnClickListener(v -> {
                    if (isPay) {
                        JZVideoPlayer.backPress();
                        ToastUtils.showToast(String.format("非VIP用户或未购买只能观看%s秒", mLibrarysBean.getChargePage()));
                        return;
                    }
                    if (mMyVideoPlayer.currentState == JZVideoPlayer.CURRENT_STATE_PAUSE) {
                        JZVideoPlayer.goOnPlayOnResume();
                    } else  if (mMyVideoPlayer.currentState == JZVideoPlayer.CURRENT_STATE_PLAYING){
                        JZVideoPlayer.goOnPlayOnPause();
                    }
                });

                mMyVideoPlayer.progressBar.setVisibility(View.VISIBLE);

//                mMyVideoPlayer.progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                        if (!UserManager.getUserIsVip() || mLibrarysBean.getIsPay() == 0) {
//                            mMyVideoPlayer.postDelayed(runnable, 1000);
//                        }
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//
//                    }
//                });


                if (mLibrarysBean.getIsPay() == 0) {
                    mMyVideoPlayer.postDelayed(runnable, 1000);
                    if (mLibrarysBean.getChargePage() <= 0.0d) {
                        isPay = true;
                    }
                }
                if (!isPay) {
                    mMyVideoPlayer.startVideo();
                }
            } else {
                mMyVideoPlayer.setVisibility(View.GONE);
            }

            if (mLibrarysBean.getIsPay() == 1) {
                Drawable drawable = ContextCompat.getDrawable(mTvDownloadCount.getContext(), R.mipmap.icon_read_download2);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvDownloadCount.setCompoundDrawables(drawable, null, null, null);
                }

                mTvPrice.setText("赞赏");
                mLlTool.setVisibility(View.GONE);


            } else {
                Drawable drawable = ContextCompat.getDrawable(mTvDownloadCount.getContext(), R.mipmap.icon_read_download);
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mTvDownloadCount.setCompoundDrawables(drawable, null, null, null);
                }
                mLlTool.setVisibility(View.VISIBLE);
                mTvPrice.setText(String.format("￥%s", mLibrarysBean.getPrice()));
            }


            mReadId = mLibrarysBean.getID();
            downloadUrl = mLibrarysBean.getFilePath();
            downloadFileName = mLibrarysBean.getName();
            if (UserManager.getUserIsVip() || mLibrarysBean.getIsPay() == 1) {
                mTvReadPageHint.setVisibility(View.GONE);
                url = mLibrarysBean.getChargeConten();
            } else {
                mTvReadPageHint.setVisibility(View.VISIBLE);
                url = mLibrarysBean.getFreeConten();
            }

            loadDetailsUrl(mWebContent, url);
            mTvTitleName.setText(String.format("[%s]", mLibrarysBean.getName()));
            mTvTitle.setText(mLibrarysBean.getTitle());
            mTvTimeBrowse.setText(String.format("%s 阅读%s", TimeUtils.getDateToString(Long.parseLong(mLibrarysBean.getCreateTime())), mLibrarysBean.getClickNumber()));
            mTvDownloadCount.setText(String.valueOf(mLibrarysBean.getDownloadNumber()));
            mCommentCount = mLibrarysBean.getCommentCount();
            mTvCommentCount.setText(String.valueOf(mCommentCount));

            mTvCollection.setSelected(mLibrarysBean.getIsCollection() == 1);
            mTvCollection.setText(mLibrarysBean.getIsCollection() == 1 ? "已收藏" : "收藏");

            mTvReadPageHint.setText(String.format("剩余%s页未读！继续阅读需付费。", mLibrarysBean.getChargePage()));
        }
    }

    @Override
    public void getReadField() {

    }

    public interface CollectionListener {
        void collectionSuccess(boolean isCollection);
    }


    @Override
    protected void onDestroy() {
        JZVideoPlayer.releaseAllVideos();
        runnable = null;
        mWebContent.destroy();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void payEvent(PayEvent payEvent) {
        if (payEvent.getEventType() == PayEvent.PAY_SUCCESS) {

            if (TextUtils.isEmpty(mReadId)) {
                return;
            }
            if (mLibrarysBean != null) {
                if (mGetLibraryPresenter != null) {
                    mGetLibraryPresenter.getLibraryList(true, "", mReadId);
                }
            } else {
                if (mGetReadDetailsPresenter != null) {
                    mGetReadDetailsPresenter.getReadDetails(mReadId);
                }
            }


        }
    }
}
