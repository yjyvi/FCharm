package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class OrderDetailsActivity extends BaseActivity {
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
    public String mOrderId;

    @Override
    protected void initViewData() {
        setTitle("订单详情");
        mOrderId = getIntent().getStringExtra("orderId");

        int type = new Random().nextInt(2) + 1;
        if (type == 1) {
            mLlTotalPrice.setVisibility(View.GONE);
            mLlResource.setVisibility(View.GONE);
            mLlProduct.setVisibility(View.VISIBLE);
        } else {
            mLlTotalPrice.setVisibility(View.VISIBLE);
            mLlResource.setVisibility(View.VISIBLE);
            mLlProduct.setVisibility(View.GONE);
        }
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


    @OnClick(R.id.ll_commit)
    public void onClick() {
        int type = new Random().nextInt(2) + 1;
        if (type == 1) {
            PayActivity.start(this, PayActivity.ONE_PAY,"12");
        } else {
            CommentActivity.start(this, "");
        }
    }
}
