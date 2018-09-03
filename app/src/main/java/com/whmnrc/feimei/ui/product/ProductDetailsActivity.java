package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ProductDetailsCommentAdapter;
import com.whmnrc.feimei.beans.ProductDetailsBean;
import com.whmnrc.feimei.pop.PopServerInfo;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.presener.AddOrDelCollectionPresenter;
import com.whmnrc.feimei.presener.GetProductDetailsPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.CommentActivity;
import com.whmnrc.feimei.ui.organization.AllCommentActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.feimei.utils.TextSpannableUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.CollectionEvent;
import com.whmnrc.feimei.views.BackGroundColorSpan;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class ProductDetailsActivity extends BaseActivity implements GetProductDetailsPresenter.GetProductDetailsListener, AddOrDelCollectionPresenter.AddOrDelCollectionListener {


    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.tv_showpiece)
    TextView mTvShowpiece;
    @BindView(R.id.tv_browse)
    TextView mTvBrowse;
    @BindView(R.id.tv_sales)
    TextView mTvSales;
    @BindView(R.id.tv_product_specifications)
    TextView mTvProductSpecifications;
    @BindView(R.id.tv_organization_name)
    TextView mTvOrganizationName;
    @BindView(R.id.v_line1)
    View mVLine1;
    @BindView(R.id.ll_account_product_details)
    LinearLayout mLlAccountProductDetails;
    @BindView(R.id.v_line2)
    View mVLine2;
    @BindView(R.id.ll_comment)
    LinearLayout mLlComment;
    @BindView(R.id.wb_product_details)
    WebView mWbProductDetails;
    @BindView(R.id.v_line6)
    View mVLine6;
    @BindView(R.id.tv_all_comment)
    TextView mTvAllComment;
    @BindView(R.id.rv_comment_list)
    RecyclerView mRvCommentList;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R.id.ll_all_comment)
    LinearLayout mLlAllComment;
    @BindView(R.id.ll_advisory)
    LinearLayout mLlAdvisory;
    @BindView(R.id.ll_collection)
    LinearLayout mLlCollection;
    @BindView(R.id.tv_send)
    TextView mTvSend;
    @BindView(R.id.ll_pay)
    LinearLayout mLlPay;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_share)
    ImageView mIvShare;
    @BindView(R.id.iv_msg)
    ImageView mIvMsg;
    @BindView(R.id.iv_collection)
    ImageView mIvCollection;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.dsl_layout)
    NestedScrollView mDslLayout;
    @BindView(R.id.tv_collection)
    TextView mTvCollection;

    private PopShare mPopShare;
    public PopServerInfo mPopServerInfo;
    public GetProductDetailsPresenter mGetProductDetailsPresenter;
    public String mProductId;
    public ProductDetailsCommentAdapter mOrganizationCommentAdapter;
    private ProductDetailsBean.ResultdataBean mProductDetailsBean;
    public AddOrDelCollectionPresenter mAddOrDelCollectionPresenter;


    @Override
    protected void initViewData() {

        EventBus.getDefault().register(this);

        showEmpty(true, mVsEmpty);
        mProductId = getIntent().getStringExtra("productId");
        isAccountLogin(true);
        mGetProductDetailsPresenter = new GetProductDetailsPresenter(this);
        mGetProductDetailsPresenter.getProductDetails(mProductId);

        mAddOrDelCollectionPresenter = new AddOrDelCollectionPresenter(this);


        mRvCommentList.setNestedScrollingEnabled(false);
        mRvCommentList.setLayoutManager(new LinearLayoutManager(this));
        mOrganizationCommentAdapter = new ProductDetailsCommentAdapter(this, R.layout.item_organization_comment);
        mRvCommentList.setAdapter(mOrganizationCommentAdapter);

    }


    /**
     * 加载网页
     */
    private void loadUrl(String url) {
        mWbProductDetails.post(() -> {
            mWbProductDetails.loadUrl(TextUtils.isEmpty(url) ? "http://www.whmnx.com" : url);
            mWbProductDetails.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return true;
                }
            });
            WebSettings settings = mWbProductDetails.getSettings();
            settings.setFixedFontFamily("monospace");
            settings.setJavaScriptEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setDefaultTextEncodingName("utf-8");
//            settings.setTextZoom(200);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setLoadWithOverviewMode(true);
            //去除WebView的焦点事件
            mWbProductDetails.setFocusableInTouchMode(false);
            //取消长按复制事件
            mWbProductDetails.setOnLongClickListener(v -> true);
            mWbProductDetails.setOnTouchListener((v, event) -> false);

            //去掉超连接事件
            mWbProductDetails.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return true;
                }
            });
        });

    }

    /**
     * 轮播图
     */
    private void initBanner(List<String> bannerList) {
        if (mBanner != null) {
            mBanner.setDelayTime(3000);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.offsetLeftAndRight(10);
            mBanner.setImages(bannerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    final String resultdataBeans = (String) path;
                    GlideUtils.LoadImage(imageView.getContext(), resultdataBeans, imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }).start();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product_details;
    }


    public static void start(Context context, String productId) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
        starter.putExtra("productId", productId);
        context.startActivity(starter);
    }


    @OnClick({R.id.ll_account_product_details,
            R.id.ll_comment,
            R.id.ll_advisory,
            R.id.ll_collection,
            R.id.tv_product_specifications,
            R.id.tv_organization_name,
            R.id.ll_pay,
            R.id.iv_msg,
            R.id.ll_all_comment,
            R.id.iv_back,
            R.id.iv_share})
    public void onClick(View view) {

        if (mProductDetailsBean == null) {
            finish();
            return;
        }

        switch (view.getId()) {
            case R.id.ll_account_product_details:
                isAccountLogin(true);
                break;
            case R.id.ll_comment:
                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                isAccountLogin(false);
                int bottom = mWbProductDetails.getBottom();
                mDslLayout.setScrollY(bottom);

                CommentActivity.start(view.getContext(), mProductId);
                CommentActivity.setCommentListener(() -> mGetProductDetailsPresenter.getProductDetails(mProductId));
                break;
            case R.id.ll_advisory:

                if (mPopServerInfo == null) {
                    mPopServerInfo = new PopServerInfo(this, mProductDetailsBean.getCommodity());
                }
                mPopServerInfo.show();

                break;
            case R.id.ll_collection:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                if (mProductDetailsBean.getIsCollection() == 1) {
                    ArrayList<String> readIds = new ArrayList<>();
                    readIds.add(mProductDetailsBean.getCommodity().getID());
                    mAddOrDelCollectionPresenter.delCollection(readIds);
                } else {
                    mAddOrDelCollectionPresenter.addCollection(mProductDetailsBean.getCommodity().getID(), AddOrDelCollectionPresenter.PRODUCT_COLLECTION);
                }

                break;
            case R.id.tv_product_specifications:
                if (TextUtils.isEmpty(mProductDetailsBean.getCommodity().getRegulationBookID())) {
                    ToastUtils.showToast("该产品没有规格书");
                    return;
                }
                ProductSpecificationsActivity.start(view.getContext(), mProductDetailsBean.getCommodity().getRegulationBookID());
                break;
            case R.id.tv_organization_name:
                OrganizationDetailsActivity.start(view.getContext(), mProductDetailsBean.getCommodity().getEnterprise_ID());
                break;
            case R.id.ll_all_comment:
                if (mProductDetailsBean.getComment().size() > 0) {
                    AllCommentActivity.start(view.getContext(), mProductDetailsBean.getCommodity().getID());
                }
                break;
            case R.id.ll_pay:

                if (mProductDetailsBean.getCommodity().getPrice() <= 0.00d) {
                    return;
                }
                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }


                ConfirmOrderActivity.start(view.getContext(), mProductDetailsBean.getCommodity());

                break;
            case R.id.iv_msg:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                MsgActivity.start(view.getContext());
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                mPopShare = new PopShare(ProductDetailsActivity.this,
                        mProductDetailsBean.getCommodity().getName(),
                        mProductDetailsBean.getCommodity().getImg(),
                        "", mProductDetailsBean.getCommodity().getSalesman());
                mPopShare.show();
                break;
            default:
                break;
        }
    }

    private void isAccountLogin(boolean isAccountProductDetails) {
        mVLine1.setBackgroundResource(isAccountProductDetails ? R.color.normal_blue_text_color : 0);
        mLlAccountProductDetails.setSelected(isAccountProductDetails);

        mVLine2.setBackgroundResource(!isAccountProductDetails ? R.color.normal_blue_text_color : 0);
        mLlComment.setSelected(!isAccountProductDetails);

    }


    @Override
    public void getProductDetailsSuccess(ProductDetailsBean.ResultdataBean bean) {
        mProductDetailsBean = bean;
        ProductDetailsBean.ResultdataBean.CommodityBean commodity = bean.getCommodity();
        initBanner(commodity.getImgAdd());
        loadUrl(commodity.getConten());

        String productName = commodity.getName() + " " + commodity.getLabel();

        SpannableStringBuilder spannableString = new SpannableStringBuilder(productName);
        int bgColor = Color.parseColor("#ff0000");
        int textColor = Color.parseColor("#ffffff");
        BackGroundColorSpan span = new BackGroundColorSpan(bgColor, textColor, 10);
        spannableString.setSpan(span, productName.length() - commodity.getLabel().length(), productName.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvTitle.setText(spannableString);
        mTvTitle.setMovementMethod(LinkMovementMethod.getInstance());


        if (commodity.getPrice() <= 0.00d) {
            mTvPrice.setVisibility(View.GONE);
            mTvSales.setVisibility(View.GONE);
            mTvShowpiece.setVisibility(View.VISIBLE);
            mLlPay.setBackgroundColor(ContextCompat.getColor(this, R.color.normal_gray));
            mLlPay.setEnabled(false);
        } else {
            mLlPay.setBackgroundColor(ContextCompat.getColor(this, R.color.normal_blue_text_color));
            mLlPay.setEnabled(true);
            mTvShowpiece.setVisibility(View.GONE);
            mTvSales.setVisibility(View.VISIBLE);
            mTvPrice.setVisibility(View.VISIBLE);
            mTvPrice.setText(String.format("￥%2.2f", commodity.getPrice()));
            mTvSales.setText(String.format("销量%s", commodity.getSales()));
        }

//        mTvCollection.setText(bean.getIsCollection() == 1 ? "已收藏" : "收藏");
        mIvCollection.setSelected(bean.getIsCollection() == 1);
        mTvBrowse.setText(String.format("浏览量%s", commodity.getClickNumber()));
        mTvOrganizationName.setText(commodity.getEnterpriseName());

        if (bean.getCommentCount() > 0) {
            mTvCommentCount.setText(String.format("共%s条", bean.getCommentCount()));
            mTvAllComment.setText(String.format("全部评价(%s)", bean.getCommentCount()));
            String textContent = mTvAllComment.getText().toString().trim();
            TextSpannableUtils.changeTextColor(mTvAllComment, textContent, 4, textContent.length(), ContextCompat.getColor(this, R.color.good_price_red));

        }
        mOrganizationCommentAdapter.setDataArray(bean.getComment());
        mOrganizationCommentAdapter.notifyDataSetChanged();
        isShowDialog(false);
        showEmpty(false, mVsEmpty);
    }

    @Override
    public void getProductDetailsField() {
        isShowDialog(false);
    }

    @Override
    public void collectionSuccess(boolean isAdd) {
        mIvCollection.setSelected(isAdd);
        mProductDetailsBean.setIsCollection(isAdd ? 1 : 0);
        if (!isAdd) {
            EventBus.getDefault().post(new CollectionEvent().setEventType(CollectionEvent.CANCEL_COLLECTION));
        }
    }

    @Override
    public void collectionCodeField() {

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Subscribe
    public void collectionEvent(CollectionEvent collectionEvent) {

    }
}
