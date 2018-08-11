package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.mine.AddressManagerActivity;
import com.whmnrc.feimei.ui.mine.PayActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/31.
 * 确认商品订单
 */

public class ConfirmOrderActivity extends BaseActivity {
    @BindView(R.id.iv_location)
    ImageView mIvLocation;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_tel)
    TextView mTvTel;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.iv_goods_img)
    ImageView mIvGoodsImg;
    @BindView(R.id.tv_goods_name)
    TextView mTvGoodsName;
    @BindView(R.id.tv_source_price)
    TextView mTvSourcePrice;
    @BindView(R.id.ll_product)
    LinearLayout mLlProduct;
    @BindView(R.id.edit_num)
    TextView mEditNum;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.tv_subtotal_price)
    TextView mTvSubtotalPrice;
    @BindView(R.id.tv_subtotal_name)
    TextView mTvSubtotalName;
    @BindView(R.id.tv_total)
    TextView mTvTotal;

    @Override
    protected void initViewData() {
        setTitle("确认订单");


    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_confirm_order;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ConfirmOrderActivity.class);
        context.startActivity(starter);
    }


    @OnClick({R.id.ll_select_address, R.id.iv_minus, R.id.iv_add, R.id.ll_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_select_address:
                AddressManagerActivity.start(view.getContext(), true);
                break;
            case R.id.iv_minus:
                isAdd(false);
                break;
            case R.id.iv_add:
                isAdd(true);
                break;
            case R.id.ll_pay:
                PayActivity.start(view.getContext(), PayActivity.PRODUCT_PAY);
                finish();
                break;
            default:
                break;
        }
    }

    public void isAdd(boolean isAdd) {
        int num = Integer.parseInt(mEditNum.getText().toString().trim());
        if (isAdd) {
            ++num;
        } else {
            if (num > 1) {
                --num;
            }
        }
        mEditNum.setText(String.valueOf(num));
        mTvSubtotalName.setText(String.format("共%s件商品    小计： ", num));
    }


}
