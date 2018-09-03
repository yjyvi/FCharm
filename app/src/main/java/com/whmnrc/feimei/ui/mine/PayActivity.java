package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.beans.ProductDetailsBean;
import com.whmnrc.feimei.beans.ReadDetailsBean;
import com.whmnrc.feimei.beans.ResourcesFileBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.presener.CreateOrderPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.CodeTimeUtils;
import com.whmnrc.feimei.utils.TextSpannableUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.PayEvent;
import com.whmnrc.feimei.utils.pay.PayUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * @author yjyvi
 * @data 2018/7/27.
 */

public class PayActivity extends BaseActivity implements CreateOrderPresenter.CreateOrderListener {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.common_title_back)
    RelativeLayout mCommonTitleBack;
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
    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.rl_one_pay)
    RelativeLayout mRlOnePay;
    @BindView(R.id.tv_product_end_time)
    TextView mTvProductEndTime;
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
    @BindView(R.id.rl_video)
    RelativeLayout mRlVideo;
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
    public static final int ONE_PAY = 2;
    /**
     * 产品库支付
     */
    public static final int PRODUCT_PAY = 0;
    /**
     * 企业名录支付
     */
    public static final int ORG_PAY = 3;
    /**
     * 行业资源支付
     */
    public static final int RESOURCE_PAY = 5;
    /**
     * 专栏支付
     */
    public static final int COLUMN_PAY = 4;

    public int mProductType;

    private PayUtils mPayUtils;
    private int payType = CommonConstant.Common.PAY_METHOD_WX;
    private String totalPrice;
    private CreateOrderPresenter mCreateOrderPresenter;
    private int mProductCount = 1;
    public String mAddressId;
    private String mOtherId;
    private String mRemark;
    private String mOrderId;

    @Override
    protected void initViewData() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        mCreateOrderPresenter = new CreateOrderPresenter(this);

        mPayUtils = new PayUtils();
        mProductType = getIntent().getIntExtra("productType", -1);
        setTitle("付费");
        selectedView(mIvSelectWx);
        showTypeView();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        mPayUtils = null;
        CodeTimeUtils.cancelTimer();
        super.onDestroy();
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
                totalPrice = getIntent().getStringExtra("totalPrice");
                mOtherId = getIntent().getStringExtra("otherId");
                mRemark = getIntent().getStringExtra("remark");
                mOrderId = getIntent().getStringExtra("orderId");
                String onePrice = String.format("支付￥%s", totalPrice);
                TextSpannableUtils.changeTextSize(mTvMoney, onePrice, 3, onePrice.length(), getResources().getDimensionPixelSize(R.dimen.dm_24));
                break;
            case PRODUCT_PAY:
                mRlProductPay.setVisibility(View.VISIBLE);
                mLlOrgInfo.setVisibility(View.GONE);
                mRlOnePay.setVisibility(View.GONE);
                mRlOrderInfo.setVisibility(View.GONE);
                mLlResourcePay.setVisibility(View.GONE);

                ProductDetailsBean.ResultdataBean.CommodityBean mCommodityBean = getIntent().getParcelableExtra("payData");
                mProductCount = getIntent().getIntExtra("number", 1);
                totalPrice = getIntent().getStringExtra("totalPrice");
                mAddressId = getIntent().getStringExtra("addressId");
                mRemark = getIntent().getStringExtra("remark");
                if (mCommodityBean != null) {
                    mOtherId = mCommodityBean.getID();
                    CodeTimeUtils.payOrderTime(mTvProductEndTime, 0, () -> {
                        ToastUtils.showToast("支付超时");
                        mLlCommit.setEnabled(false);
                        mLlCommit.setBackgroundColor(ContextCompat.getColor(this, R.color.normal_gray));
                    });
                    mTvMoney2.setText(totalPrice);
                    String ProductPrice = String.format("￥%s", totalPrice);
                    TextSpannableUtils.changeTextSize(mTvMoney2, ProductPrice, 1, ProductPrice.length(), getResources().getDimensionPixelSize(R.dimen.dm_24));
                    mTvProductDesc.setText(mCommodityBean.getName());
                    mTvPay.setText(String.format("确认支付￥%s", totalPrice));
                }

                break;
            case ORG_PAY:
                mLlOrgInfo.setVisibility(View.VISIBLE);
                mRlOnePay.setVisibility(View.VISIBLE);
                mRlOrderInfo.setVisibility(View.VISIBLE);
                mRlProductPay.setVisibility(View.GONE);
                mLlResourcePay.setVisibility(View.GONE);
                OrganizationDetailsBean.ResultdataBean.EnterpriseBean mEnterpriseBean = getIntent().getParcelableExtra("payData");
                if (mEnterpriseBean != null) {
                    mOtherId = mEnterpriseBean.getID();
                    String orgPrice = String.format("支付￥%2.2f", mEnterpriseBean.getPrice());
                    TextSpannableUtils.changeTextSize(mTvMoney, orgPrice, 3, orgPrice.length(), getResources().getDimensionPixelSize(R.dimen.dm_24));
                    mTvOrgTitle.setText(mEnterpriseBean.getName());
                    mTvOrgDesc.setText(mEnterpriseBean.getSubtitle());
                    mIvImg.setVisibility(View.GONE);
                    mTvOrgTime.setText(TimeUtils.getDateToString(mEnterpriseBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                }
                break;
            case COLUMN_PAY:
                mLlOrgInfo.setVisibility(View.VISIBLE);
                mRlOnePay.setVisibility(View.VISIBLE);
                mRlOrderInfo.setVisibility(View.VISIBLE);
                mRlProductPay.setVisibility(View.GONE);
                mLlResourcePay.setVisibility(View.GONE);
                ReadDetailsBean.ResultdataBean.ReadBean mReadBean = getIntent().getParcelableExtra("payData");
                if (mReadBean != null) {
                    mOtherId = mReadBean.getID();
                    mTvOrgTitle.setText(mReadBean.getTitle());
                    mTvOrgDesc.setText(mReadBean.getSubtitle());
                    mIvImg.setVisibility(View.VISIBLE);
                    GlideUtils.LoadImage(this, mReadBean.getImg(), mIvImg);
                    mTvOrgTime.setText(TimeUtils.getDateToString(mReadBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    String columnPrice = String.format("支付￥%2.2f", mReadBean.getPrice());
                    TextSpannableUtils.changeTextSize(mTvMoney, columnPrice, 3, columnPrice.length(), getResources().getDimensionPixelSize(R.dimen.dm_24));

                }
                break;
            case RESOURCE_PAY:
                ResourcesFileBean.ResultdataBean.LibrarysBean mLibrarysBean = getIntent().getParcelableExtra("payData");
                if (mLibrarysBean != null) {
                    mOtherId = mLibrarysBean.getID();
                    mTvDesc.setText(mLibrarysBean.getSubtitle());

                    if (mLibrarysBean.getType() == 4) {
                        mRlVideo.setVisibility(View.VISIBLE);
                        GlideUtils.LoadImage(this, mLibrarysBean.getImg(), mIvVideoImg);
                    } else {
                        mRlVideo.setVisibility(View.GONE);
                    }

                    mTvTime.setText(TimeUtils.getDateToString(mLibrarysBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                    mTvDownloadCount.setText(String.format("%s次下载", mLibrarysBean.getDownloadNumber()));
                    String resourcePrice = String.format("支付：￥%2.2f", mLibrarysBean.getPrice());
                    mTvPrice.setText(resourcePrice);

                    totalPrice = String.valueOf(mLibrarysBean.getPrice());
                    TextSpannableUtils.changeTextSize(mTvMoney, resourcePrice, 4, resourcePrice.length(), getResources().getDimensionPixelSize(R.dimen.dm_24));

                }
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

    /**
     * 企业名录支付界面数据
     *
     * @param context
     * @param productType
     * @param bean
     */
    public static void startOrg(Context context, int productType, OrganizationDetailsBean.ResultdataBean.EnterpriseBean bean) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        starter.putExtra("payData", bean);
        context.startActivity(starter);
    }

    /**
     * 产品支付界面数据
     *
     * @param context
     * @param productType
     * @param bean
     */
    public static void startProduct(Context context, int productType, ProductDetailsBean.ResultdataBean.CommodityBean bean, String totalPrice, int number, String addressId, String remark) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        starter.putExtra("totalPrice", totalPrice);
        starter.putExtra("number", number);
        starter.putExtra("addressId", addressId);
        starter.putExtra("remark", remark);
        starter.putExtra("payData", bean);
        context.startActivity(starter);
    }

    /**
     * 阅读付费
     *
     * @param context
     * @param productType
     * @param bean
     */
    public static void startRead(Context context, int productType, ReadDetailsBean.ResultdataBean.ReadBean bean) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        starter.putExtra("payData", bean);
        context.startActivity(starter);
    }

    /**
     * 文库的支付
     *
     * @param context
     * @param productType
     * @param bean
     */
    public static void startFileResource(Context context, int productType, ResourcesFileBean.ResultdataBean.LibrarysBean bean) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        starter.putExtra("payData", bean);
        context.startActivity(starter);
    }


    /**
     * 赞赏支付
     *
     * @param context
     * @param productType
     * @param totalPrice
     * @param otherId
     * @param remark
     */
    public static void start(Context context, int productType, String totalPrice, String otherId, String remark) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        starter.putExtra("totalPrice", totalPrice);
        starter.putExtra("otherId", otherId);
        starter.putExtra("remark", remark);
        context.startActivity(starter);
    }

    /**
     * 订单支付
     *
     * @param context
     * @param productType
     * @param totalPrice
     * @param orderId
     */
    public static void startOrderPay(Context context, int productType, String totalPrice, String orderId) {
        Intent starter = new Intent(context, PayActivity.class);
        starter.putExtra("productType", productType);
        starter.putExtra("totalPrice", totalPrice);
        starter.putExtra("orderId", orderId);
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
                payType = CommonConstant.Common.PAY_METHOD_WX;
                selectedView(mIvSelectWx);
                break;
            case R.id.ll_zfb:
                payType = CommonConstant.Common.PAY_METHOD_ZFB;
                selectedView(mIvSelectZfb);
                break;
            case R.id.ll_commit:

                if (!UserManager.getIsLogin(this)) {
                    return;
                }

                mLlCommit.setEnabled(false);
                if (TextUtils.isEmpty(mOrderId)) {
                    isShowDialog(true);
                    mCreateOrderPresenter.createOrder(mProductCount, mAddressId, mProductType, mOtherId, mRemark);
                } else {
                    payResult(payType, mOrderId);
                }
                break;
            default:
                break;
        }
    }


    /**
     * 支付结果
     *
     * @param
     * @param order
     */
    private void payResult(int payType, final String order) {

        if (mPayUtils == null) {
            return;
        }
        mPayUtils.playPay(this, payType, order, new OKHttpManager.ObjectCallback() {
            @Override
            public void onSuccess(String st) {
                mLlCommit.setEnabled(true);
                ToastUtils.showToast(st);
                if (TextUtils.isEmpty(mRemark)) {
                    PayResultActivity.start(PayActivity.this, order, false);
                }

                UserManager.refresh();

                EventBus.getDefault().post(new PayEvent().setEventType(PayEvent.PAY_SUCCESS));
                finish();
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                ToastUtils.showToast(errorMsg);
                mLlCommit.setEnabled(true);
                finish();
            }
        });

        isShowDialog(false);
    }


    @Override
    public void createOrderSuccess(String orderId) {
        payResult(payType, orderId);
        isShowDialog(false);
    }

    @Override
    public void createOrderField() {
        isShowDialog(false);
        mLlCommit.setEnabled(true);
    }


    @Subscribe
    public void payEvent(PayEvent payEvent) {

    }


}
