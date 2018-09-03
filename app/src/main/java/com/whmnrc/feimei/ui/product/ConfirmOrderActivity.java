package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.beans.AddressBean;
import com.whmnrc.feimei.beans.ProductDetailsBean;
import com.whmnrc.feimei.presener.AddressListPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.mine.AddressManagerActivity;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.AddressEvent;
import com.whmnrc.feimei.utils.pay.MoneyUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/31.
 * 确认商品订单
 */

public class ConfirmOrderActivity extends BaseActivity implements AddressListPresenter.AddressListListener {
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
    @BindView(R.id.tv_no_address)
    TextView mTvNoAddress;
    @BindView(R.id.rl_address)
    RelativeLayout mRlAddress;
    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    public ProductDetailsBean.ResultdataBean.CommodityBean mCommodityBean;
    public int mNum = 1;
    private String addressId;
    private AddressBean.ResultdataBean addressEventData;
    private AddressListPresenter mAddressListPresenter;

    @Override
    protected void initViewData() {
        setTitle("确认订单");

        EventBus.getDefault().register(this);

        mCommodityBean = getIntent().getParcelableExtra("commodity");

        mAddressListPresenter = new AddressListPresenter(this);
        mAddressListPresenter.getAddressList();


        if (mCommodityBean != null) {

            mTvGoodsName.setText(mCommodityBean.getName());
            GlideUtils.LoadImage(this, mCommodityBean.getImg(), mIvGoodsImg);

            MoneyUtils.showRMB(true, mTvSourcePrice, mCommodityBean.getPrice());
            MoneyUtils.showRMB(false, mTvSubtotalPrice, mCommodityBean.getPrice());
            MoneyUtils.showRMB(false, mTvTotal, mCommodityBean.getPrice());

        }

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_confirm_order;
    }

    public static void start(Context context, ProductDetailsBean.ResultdataBean.CommodityBean commodity) {
        Intent starter = new Intent(context, ConfirmOrderActivity.class);
        starter.putExtra("commodity", commodity);
        context.startActivity(starter);
    }

    @Subscribe
    public void addressEvent(AddressEvent addressEvent) {
        if (addressEvent.getEventType() == AddressEvent.ORDER_SELECT_ADDRESS) {
            addressEventData = (AddressBean.ResultdataBean) addressEvent.getData();
            if (addressEventData != null) {
                initAddress(addressEventData);
            } else {
                mAddressListPresenter.getAddressList();
            }
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
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

                if (TextUtils.isEmpty(addressId)) {
                    ToastUtils.showToast("请选择收货地址");
                    return;
                }

                PayActivity.startProduct(view.getContext(),
                        PayActivity.PRODUCT_PAY, mCommodityBean,
                        mTvTotal.getText().toString().trim(),
                        mNum,
                        addressId, mEtRemark.getText().toString().trim());
                finish();
                break;
            default:
                break;
        }
    }

    public void isAdd(boolean isAdd) {
        mNum = Integer.parseInt(mEditNum.getText().toString().trim());
        if (isAdd) {
            ++mNum;
        } else {
            if (mNum > 1) {
                --mNum;
            }
        }
        mEditNum.setText(String.valueOf(mNum));
        mTvSubtotalName.setText(String.format("共%s件商品    小计： ", mNum));

        MoneyUtils.showRMB(false, mTvSubtotalPrice, (mCommodityBean.getPrice() * mNum));
        MoneyUtils.showRMB(false, mTvTotal, (mCommodityBean.getPrice() * mNum));
    }


    @Override
    public void getListSuccess(List<AddressBean.ResultdataBean> resultdataBeans) {
        if (resultdataBeans.size() == 0) {
            addressId = "";
            mTvNoAddress.setVisibility(View.VISIBLE);
            mTvName.setText("");
            mTvTel.setText("");
            mTvAddress.setText("");
        } else {
            for (AddressBean.ResultdataBean resultdataBean : resultdataBeans) {
                if (resultdataBean.getIsDefault() == 1) {
                    initAddress(resultdataBean);
                    break;
                }
            }
        }

    }

    /**
     * 显示地址信息
     *
     * @param resultdataBean
     */
    private void initAddress(AddressBean.ResultdataBean resultdataBean) {

        mRlAddress.setVisibility(View.VISIBLE);
        mTvNoAddress.setVisibility(View.GONE);
        addressId = resultdataBean.getID();
        mTvName.setText(String.format("收货人：%s", resultdataBean.getName()));
        mTvTel.setText(resultdataBean.getMobile());
        mTvAddress.setText(String.format("收货地址：%s", resultdataBean.getProvice() + resultdataBean.getCity() + resultdataBean.getRegion() + resultdataBean.getDetail()));

    }

    @Override
    public void getAddressListField() {

    }
}
