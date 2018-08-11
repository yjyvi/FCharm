package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.TimeUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author yjyvi
 * @data 2018/7/27.
 */

public class PayActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.common_title_back)
    RelativeLayout mCommonTitleBack;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.common_title)
    RelativeLayout mCommonTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.rl_right)
    RelativeLayout mRlRight;
    @BindView(R.id.iv_right_title)
    TextView mIvRightTitle;
    @BindView(R.id.rl_right_title)
    RelativeLayout mRlRightTitle;
    @BindView(R.id.fl_title_bar)
    FrameLayout mFlTitleBar;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.rl_one_pay)
    RelativeLayout mRlOnePay;
    @BindView(R.id.tv_product_end_time)
    TextView mTvProductEndTime;
    @BindView(R.id.tv_product_name)
    TextView mTvProductName;
    @BindView(R.id.tv_money2)
    TextView mTvMoney2;
    @BindView(R.id.tv_product_desc)
    TextView mTvProductDesc;
    @BindView(R.id.rl_product_pay)
    LinearLayout mRlProductPay;
    @BindView(R.id.v_line)
    View mVLine;
    @BindView(R.id.rl_order_info)
    RelativeLayout mRlOrderInfo;
    @BindView(R.id.iv_img)
    CircleImageView mIvImg;
    @BindView(R.id.tv_org_title)
    TextView mTvOrgTitle;
    @BindView(R.id.tv_org_desc)
    TextView mTvOrgDesc;
    @BindView(R.id.tv_org_time)
    TextView mTvOrgTime;
    @BindView(R.id.ll_org_info)
    LinearLayout mLlOrgInfo;
    @BindView(R.id.iv_video_img)
    ImageView mIvVideoImg;
    @BindView(R.id.tv_desc)
    TextView mTvDesc;
    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.tv_download_count)
    TextView mTvDownloadCount;
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    @BindView(R.id.ll_resource_pay)
    LinearLayout mLlResourcePay;
    @BindView(R.id.v_line2)
    View mVLine2;
    @BindView(R.id.iv_select_wx)
    ImageView mIvSelectWx;
    @BindView(R.id.ll_wx)
    LinearLayout mLlWx;
    @BindView(R.id.iv_select_zfb)
    ImageView mIvSelectZfb;
    @BindView(R.id.ll_zfb)
    LinearLayout mLlZfb;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.ll_commit)
    LinearLayout mLlCommit;


    /**
     * 单次支付 ---赞赏
     */
    public static final int ONE_PAY = 1;
    /**
     * 产品库支付
     */
    public static final int PRODUCT_PAY = 2;
    /**
     * 企业名录支付
     */
    public static final int ORG_PAY = 3;
    /**
     * 行业资源支付
     */
    public static final int RESOURCE_PAY = 4;
    /**
     * 专栏支付
     */
    public static final int COLUMN_PAY = 5;
    public int mProductType;
    public OrganizationDetailsBean.ResultdataBean.EnterpriseBean mEnterpriseBean;


    @Override
    protected void initViewData() {
        mProductType = getIntent().getIntExtra("productType", -1);
        setTitle("付费");
        selectedView(mIvSelectWx);
        showTypeView();


        if (mProductType == ORG_PAY) {
            mEnterpriseBean = getIntent().getParcelableExtra("enterprise");
            if (mEnterpriseBean != null) {
                mTvMoney.setText(String.valueOf(mEnterpriseBean.getPrice()));
                mTvOrgTitle.setText(mEnterpriseBean.getName());
                mTvOrgDesc.setText(mEnterpriseBean.getExplain());
                mIvImg.setVisibility(View.GONE);
                mTvOrgTime.setText(TimeUtils.getDateToString(mEnterpriseBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            }
        }
    }


    /**
     * 根据支付的产品类型显示布局
     */
    private void showTypeView() {
        switch (mProductType) {
            case ONE_PAY:
                mRlOnePay.setVisibility(View.VISIBLE);
                mLlOrgInfo.setVisibility(View.GONE);
                mRlProductPay.setVisibility(View.GONE);
                mRlOrderInfo.setVisibility(View.GONE);
                mLlResourcePay.setVisibility(View.GONE);
                break;
            case PRODUCT_PAY:
                mRlProductPay.setVisibility(View.VISIBLE);
                mLlOrgInfo.setVisibility(View.GONE);
                mRlOnePay.setVisibility(View.GONE);
                mRlOrderInfo.setVisibility(View.GONE);
                mLlResourcePay.setVisibility(View.GONE);
                break;
            case ORG_PAY:
            case COLUMN_PAY:
                mLlOrgInfo.setVisibility(View.VISIBLE);
                mRlOnePay.setVisibility(View.VISIBLE);
                mRlOrderInfo.setVisibility(View.VISIBLE);
                mRlProductPay.setVisibility(View.GONE);
                mLlResourcePay.setVisibility(View.GONE);
                break;
            case RESOURCE_PAY:
                mLlResourcePay.setVisibility(View.VISIBLE);
                mRlOnePay.setVisibility(View.VISIBLE);
                mRlOrderInfo.setVisibility(View.VISIBLE);
                mRlProductPay.setVisibility(View.GONE);
                mLlOrgInfo.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pay;
    }

    public static void start(Context context, int productType, OrganizationDetailsBean.ResultdataBean.EnterpriseBean enterprise) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        starter.putExtra("enterprise", enterprise);
        context.startActivity(starter);
    }

    public static void start(Context context, int productType) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        context.startActivity(starter);
    }


    private View lastView;

    private void selectedView(View view) {
        if (lastView != null) {
            lastView.setSelected(false);
        }
        if (!view.isSelected()) {
            view.setSelected(true);
            lastView = view;
        } else {
            view.setSelected(false);
        }

    }


    @OnClick({R.id.ll_wx, R.id.ll_zfb, R.id.ll_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_wx:
                selectedView(mIvSelectWx);
                break;
            case R.id.ll_zfb:
                selectedView(mIvSelectZfb);
                break;
            case R.id.ll_commit:
                if (mProductType == PRODUCT_PAY) {
                    PayResultActivity.start(view.getContext(), "123");
                }
                finish();
                break;
            default:
                break;
        }
    }



}
