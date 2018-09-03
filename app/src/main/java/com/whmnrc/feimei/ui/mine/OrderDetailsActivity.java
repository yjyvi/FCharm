package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.OrderDetailsBean;
import com.whmnrc.feimei.presener.OrderCancelOrRefundPresenter;
import com.whmnrc.feimei.presener.OrderDetailsPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.industry.IndustryDetailsActivity;
import com.whmnrc.feimei.ui.organization.OrganizationDetailsActivity;
import com.whmnrc.feimei.ui.product.ProductDetailsActivity;
import com.whmnrc.feimei.utils.CodeTimeUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.utils.evntBusBean.PayEvent;
import com.whmnrc.feimei.views.AlertDialog;
import com.whmnrc.mylibrary.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class OrderDetailsActivity extends BaseActivity implements OrderDetailsPresenter.OrderDetailsListener, OrderCancelOrRefundPresenter.OrderOperationListener {
    @BindView(R.id.tv_order_state)
    TextView mTvOrderState;
    @BindView(R.id.tv_order_time)
    TextView mTvOrderTime;
    @BindView(R.id.iv_order_state)
    ImageView mIvOrderState;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_tel)
    TextView mTvTel;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_order_total_price)
    TextView mTvOrderTotalPrice;
    @BindView(R.id.tv_pay_price)
    TextView mTvPayPrice;
    @BindView(R.id.tv_order_no)
    TextView mTvOrderNo;
    @BindView(R.id.tv_pay_no)
    TextView mTvPayNo;
    @BindView(R.id.tv_crate_time)
    TextView mTvCrateTime;
    @BindView(R.id.tv_pay_time)
    TextView mTvPayTime;
    @BindView(R.id.tv_refund_time)
    TextView mTvRefundTime;
    @BindView(R.id.ll_refund)
    LinearLayout mLlRefund;
    @BindView(R.id.ll_total_price)
    LinearLayout mLlTotalPrice;
    @BindView(R.id.ll_resource)
    LinearLayout mLlResource;
    @BindView(R.id.ll_product)
    LinearLayout mLlProduct;
    @BindView(R.id.rl_address)
    RelativeLayout mRlAddress;
    public String mOrderId;
    public OrderDetailsPresenter mOrderDetailsPresenter;
    @BindView(R.id.iv_goods_img)
    ImageView mIvGoodsImg;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_good_price)
    TextView mTvGoodPrice;
    @BindView(R.id.tv_goods_num)
    TextView mTvGoodsNum;
    @BindView(R.id.iv_resource_img)
    ImageView mIvResourceImg;
    @BindView(R.id.iv_pay_or_comment)
    ImageView mIvPayOrComment;
    @BindView(R.id.tv_resource_title)
    TextView mTvResourceTitle;
    @BindView(R.id.tv_resource_desc)
    TextView mTvResourceDesc;
    @BindView(R.id.tv_confirm_refund)
    TextView mTvConfirmRefund;
    @BindView(R.id.tv_order_refund)
    TextView mTvOrderRefund;
    @BindView(R.id.tv_pay_or_comment)
    TextView mTvPayOrComment;
    @BindView(R.id.ll_commit)
    LinearLayout mLlCommit;
    private OrderDetailsBean.ResultdataBean mOrderDetailsBean;
    public OrderCancelOrRefundPresenter mOrderCaneclOrRefundPresenter;
    private boolean mIsFirst = true;

    @Override
    protected void initViewData() {

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        isShowDialog(true);

        setTitle("订单详情");
        mOrderId = getIntent().getStringExtra("orderId");

        mOrderDetailsPresenter = new OrderDetailsPresenter(this);
        mOrderDetailsPresenter.getOrderDetails(mOrderId);

        mOrderCaneclOrRefundPresenter = new OrderCancelOrRefundPresenter(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_order_details;
    }

    public static void start(Context context, String orderId) {
        Intent starter = new Intent(context, OrderDetailsActivity.class);
        starter.putExtra("orderId", orderId);
        context.startActivity(starter);
    }


    @OnClick({R.id.ll_commit, R.id.tv_order_refund})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_commit:

                if (mOrderDetailsBean == null) {
                    return;
                }

                if (mOrderDetailsBean.getState() == 0) {
                    PayActivity.startOrderPay(this, PayActivity.ONE_PAY, String.valueOf(mOrderDetailsBean.getMoney()), mOrderId);
                } else if (mOrderDetailsBean.getState() == 1) {
                    CommentActivity.start(this, mOrderDetailsBean.getOtherID());
                    CommentActivity.setCommentListener(() -> mOrderDetailsPresenter.getOrderDetails(mOrderId));
                }

                break;
            case R.id.tv_order_refund:
                new AlertDialog(this).builder().setTitle("提示!")
                        .setMsg("确定要退款吗?")
                        .setCancelable(true)
                        .setPositiveButton("确定", view -> {
                            isShowDialog(true);
                            mOrderCaneclOrRefundPresenter.refundOrder(mOrderId);
                        })
                        .setNegativeButton("取消", view -> {

                        }).show();

                break;
            default:
                break;
        }


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!mIsFirst && mOrderDetailsBean != null && mOrderDetailsBean.getState() == 0) {
            showPayTIme();
        }
    }

    @Override
    public void getOrderDetailsSuccess(OrderDetailsBean.ResultdataBean bean) {
        this.mOrderDetailsBean = bean;
        mIsFirst = false;

        if (mOrderDetailsBean == null) {
            return;
        }

        if (mOrderDetailsBean.getOrderType() == 0) {
            mRlAddress.setVisibility(View.VISIBLE);
            mLlTotalPrice.setVisibility(View.VISIBLE);
            mLlResource.setVisibility(View.GONE);
            mLlProduct.setVisibility(View.VISIBLE);

            mTvName.setText(String.format("收货人：%s", bean.getAddressName()));
            mTvAddress.setText(String.format("收货地址：%s", bean.getAddressProvice() + bean.getAddressCity() + bean.getAddressRegion() + bean.getAddressDetail()));
            mTvTel.setText(bean.getAddressMobile());

            GlideUtils.LoadImage(this, mOrderDetailsBean.getImg(), mIvGoodsImg);
            mTvGoodsName.setText(mOrderDetailsBean.getName());
            mTvGoodPrice.setText(String.format("￥%s", mOrderDetailsBean.getUnitPrice()));
            mTvGoodsNum.setText(String.format("X%s", mOrderDetailsBean.getNumber()));

            mIvGoodsImg.setOnClickListener(v -> {
                        switch (bean.getType()) {
                            case 0:
                                ProductDetailsActivity.start(v.getContext(), mOrderDetailsBean.getOtherID());
                                break;
                            case 3:
                                OrganizationDetailsActivity.start(v.getContext(), mOrderDetailsBean.getOtherID());
                                break;
                            case 4:
                                IndustryDetailsActivity.start(v.getContext(), mOrderDetailsBean.getOtherID(), IndustryDetailsActivity.READ_DETAILS_TYPE);
                                break;
                            case 5:
                                IndustryDetailsActivity.start(v.getContext(), mOrderDetailsBean.getOtherID(), IndustryDetailsActivity.FILE_DETAILS_TYPE);
                                break;
                            default:
                                break;
                        }

                    }
            );

        } else {
            mRlAddress.setVisibility(View.GONE);
            mLlTotalPrice.setVisibility(View.GONE);
            mLlResource.setVisibility(View.VISIBLE);
            mLlProduct.setVisibility(View.GONE);

            if (TextUtils.isEmpty(mOrderDetailsBean.getImg())) {
                mIvResourceImg.setVisibility(View.GONE);
            } else {
                mIvResourceImg.setVisibility(View.VISIBLE);
                GlideUtils.LoadImage(this, mOrderDetailsBean.getImg(), mIvResourceImg);
            }

            mTvResourceTitle.setText(mOrderDetailsBean.getName());
            mTvResourceDesc.setText(mOrderDetailsBean.getSubTitle());
        }

        mTvOrderTotalPrice.setText(String.format("￥%s", mOrderDetailsBean.getMoney()));
        mTvPayPrice.setText(String.format("￥%s", mOrderDetailsBean.getMoney()));
        mTvOrderNo.setText(String.format("订单编号：%s", mOrderDetailsBean.getOrderNo()));
        mTvCrateTime.setText(String.format("下单时间：%s", TimeUtils.getDateToString(mOrderDetailsBean.getCreateTime(), "yyyy-MM-dd HH:mm:ss")));
        mTvPayTime.setText(String.format("付款时间：%s", TimeUtils.getDateToString(mOrderDetailsBean.getPayTime(), "yyyy-MM-dd HH:mm:ss")));

        if (mOrderDetailsBean.getState() != 3) {
            mTvConfirmRefund.setVisibility(View.GONE);
            mTvRefundTime.setVisibility(View.GONE);

        }

        switch (mOrderDetailsBean.getState()) {
            case 0:
                mLlCommit.setVisibility(View.VISIBLE);
                mTvPayTime.setVisibility(View.GONE);
                mTvOrderState.setText("您的订单正在等待付款");
                mIvOrderState.setImageResource(R.mipmap.icon_order_details_1);
                mTvOrderTime.setVisibility(View.VISIBLE);
                showPayTIme();
                mTvOrderRefund.setVisibility(View.GONE);
                mTvPayOrComment.setText("确认支付");
                mIvPayOrComment.setImageResource(R.mipmap.icon_order_pay);
                break;
            case 1:
                mTvOrderTime.setVisibility(View.GONE);
                mLlCommit.setVisibility(View.VISIBLE);
                mTvOrderRefund.setVisibility(View.VISIBLE);
                mTvOrderState.setText("您的订单已付款");
                mIvOrderState.setImageResource(R.mipmap.icon_order_details_3);
                mTvPayOrComment.setText("去评价");
                mIvPayOrComment.setImageResource(R.mipmap.icon_comment_release);
                break;
            case 2:
                mTvOrderTime.setVisibility(View.GONE);
                mLlCommit.setVisibility(View.GONE);
                mTvOrderRefund.setVisibility(View.GONE);
                break;
            case 3:
                mTvOrderTime.setVisibility(View.GONE);
                mLlCommit.setVisibility(View.GONE);
                mTvOrderRefund.setVisibility(View.GONE);
                mTvConfirmRefund.setVisibility(View.VISIBLE);
                mTvRefundTime.setText(String.format("申请退款时间：%s", TimeUtils.getDateToString(mOrderDetailsBean.getRefundableTime(), "yyyy-MM-dd HH:mm:ss")));
                mTvOrderState.setText("您的订单已申请退款");
                mIvOrderState.setImageResource(R.mipmap.icon_order_details_4);
                break;
            case 4:
                mTvOrderTime.setVisibility(View.GONE);
                mLlCommit.setVisibility(View.GONE);
                mTvOrderRefund.setVisibility(View.GONE);
                mTvConfirmRefund.setVisibility(View.VISIBLE);
                mTvRefundTime.setText(String.format("申请退款时间：%s", TimeUtils.getDateToString(mOrderDetailsBean.getRefundableTime(), "yyyy-MM-dd HH:mm:ss")));
                mTvOrderState.setText("您的订单已退款");
                mIvOrderState.setImageResource(R.mipmap.icon_order_details_4);
                break;
            default:
                break;
        }

        isShowDialog(false);
    }

    /**
     * 显示支付订单到计时
     */
    private void showPayTIme() {
        if (mOrderDetailsBean == null) {
            return;
        }
        CodeTimeUtils.payOrderTime(mTvOrderTime, Long.parseLong(mOrderDetailsBean.getCreateTime()) * 1000, () -> {
            mLlCommit.setEnabled(false);
            mLlCommit.setBackgroundColor(ContextCompat.getColor(this, R.color.normal_gray));
        });
    }

    @Override
    public void getOrderDetailsField() {
        isShowDialog(false);
    }

    @Override
    public void cancelSuccess() {

    }

    @Override
    public void refundSuccess() {
        mOrderDetailsPresenter.getOrderDetails(mOrderId);
        UserManager.refresh();
        isShowDialog(false);
    }

    @Override
    public void cancelField() {

    }

    @Override
    public void refundField() {
        isShowDialog(false);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        CodeTimeUtils.cancelTimer();
        mOrderDetailsPresenter = null;
        super.onDestroy();
    }


    @Subscribe
    public void payEvent(PayEvent payEvent) {
        if (payEvent.getEventType() == PayEvent.PAY_SUCCESS) {
            if (mOrderDetailsPresenter != null) {
                mOrderDetailsPresenter.getOrderDetails(mOrderId);
            }
        }
    }
}
