package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.OrganizationCommentAdapter;
import com.whmnrc.feimei.pop.PopServerInfo;
import com.whmnrc.feimei.pop.PopShare;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.PayVipActivity;
import com.whmnrc.feimei.ui.organization.AllCommentActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class ProductDetailsActivity extends BaseActivity {


    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
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
    private PopShare mPopShare;
    public PopServerInfo mPopServerInfo;

    @Override
    protected void initViewData() {
        isAccountLogin(true);

        mRvCommentList.setNestedScrollingEnabled(false);
        mRvCommentList.setLayoutManager(new LinearLayoutManager(this));
        OrganizationCommentAdapter organizationCommentAdapter = new OrganizationCommentAdapter(this, R.layout.item_organization_comment);
//        organizationCommentAdapter.setDataArray(TestDataUtils.initTestData(6));
        mRvCommentList.setAdapter(organizationCommentAdapter);
        loadUrl();
    }


    /**
     * 加载网页
     */
    private void loadUrl() {
        mWbProductDetails.post(new Runnable() {
            @Override
            public void run() {
                mWbProductDetails.loadUrl("http://www.whmnx.com");
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
            }
        });

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_product_details;
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, ProductDetailsActivity.class);
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
                        mPopServerInfo = new PopServerInfo(this);
                    }
                } else {
                    PayVipActivity.start(view.getContext());
                }
                mPopServerInfo.show();
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
                OrganizationDetailsActivity.start(view.getContext(), "");
                break;
            case R.id.ll_all_comment:
                AllCommentActivity.start(view.getContext(), "");
                break;
            case R.id.ll_pay:

                if (!UserManager.getIsLogin(view.getContext())) {
                    return;
                }

                ConfirmOrderActivity.start(view.getContext());

                break;
            case R.id.iv_msg:
                MsgActivity.start(view.getContext());
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_share:
                mPopShare = new PopShare(ProductDetailsActivity.this, "1", "1", "1", "1");
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


}
