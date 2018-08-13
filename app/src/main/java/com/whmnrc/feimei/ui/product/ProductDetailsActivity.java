package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.whmnrc.feimei.presener.GetProductDetailsPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.PayVipActivity;
import com.whmnrc.feimei.ui.organization.AllCommentActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class ProductDetailsActivity extends BaseActivity implements GetProductDetailsPresenter.GetProductDetailsListener {


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
    private PopShare mPopShare;
    public PopServerInfo mPopServerInfo;
    public GetProductDetailsPresenter mGetProductDetailsPresenter;
    public String mProductId;
    public ProductDetailsCommentAdapter mOrganizationCommentAdapter;
    private ProductDetailsBean.ResultdataBean mProductDetailsBean;

    @Override
    protected void initViewData() {
        showEmpty(true, mVsEmpty);
        mProductId = getIntent().getStringExtra("productId");
        isAccountLogin(true);
        mGetProductDetailsPresenter = new GetProductDetailsPresenter(this);
        mGetProductDetailsPresenter.getProductDetails(mProductId);
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
            settings.setJavaScriptEnabled(false);
            settings.setUseWideViewPort(true);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            settings.setLoadWithOverviewMode(true);
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
                    //轮播图跳转
                    imageView.setOnClickListener(view -> {
//

                    });
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
        switch (view.getId()) {
            case R.id.ll_account_product_details:
                isAccountLogin(true);
                break;
            case R.id.ll_comment:
                isAccountLogin(false);
                break;
            case R.id.ll_advisory:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                if (UserManager.getUserIsVip()) {
                    if (mPopServerInfo == null) {
                        mPopServerInfo = new PopServerInfo(this, mProductDetailsBean.getCommodity());
                    }
                    mPopServerInfo.show();
                } else {
                    PayVipActivity.start(view.getContext());
                }
                break;
            case R.id.ll_collection:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }
                mIvCollection.setSelected(!mIvCollection.isSelected());
                break;
            case R.id.tv_product_specifications:
                ProductSpecificationsActivity.start(view.getContext());
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

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                ConfirmOrderActivity.start(view.getContext(),mProductDetailsBean.getCommodity());

                break;
            case R.id.iv_msg:
                MsgActivity.start(view.getContext());
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                mPopShare = new PopShare(ProductDetailsActivity.this,
                        mProductDetailsBean.getCommodity().getName(),
                        mProductDetailsBean.getCommodity().getImgAdd().get(0),
                        mProductDetailsBean.getCommodity().getConten(), mProductDetailsBean.getCommodity().getSalesman());
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

        mTvTitle.setText(commodity.getName());
        if (commodity.getPrice() <= 0) {
            mTvPrice.setVisibility(View.GONE);
            mTvSales.setVisibility(View.GONE);
            mTvShowpiece.setVisibility(View.VISIBLE);
        } else {
            mTvShowpiece.setVisibility(View.GONE);
            mTvSales.setVisibility(View.VISIBLE);
            mTvPrice.setVisibility(View.VISIBLE);
            mTvPrice.setText(String.format("￥%s", commodity.getPrice()));
            mTvSales.setText(String.format("销量%s", commodity.getSales()));
        }
        mTvBrowse.setText(String.format("浏览量%s", commodity.getClickNumber()));
        mTvOrganizationName.setText(commodity.getEnterpriseName());
        mTvCommentCount.setText(String.format("共%s条", bean.getCommentCount()));

        mOrganizationCommentAdapter.setDataArray(bean.getComment());
        mOrganizationCommentAdapter.notifyDataSetChanged();
        isShowDialog(false);
        showEmpty(false, mVsEmpty);
    }

    @Override
    public void getProductDetailsField() {
        isShowDialog(false);
    }
}
