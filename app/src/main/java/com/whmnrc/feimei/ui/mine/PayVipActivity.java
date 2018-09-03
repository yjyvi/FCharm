package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.VipPriceAdapter;
import com.whmnrc.feimei.adapter.VipTypeAdapter;
import com.whmnrc.feimei.beans.RuleDescriptionBean;
import com.whmnrc.feimei.beans.VipTypeListBean;
import com.whmnrc.feimei.network.OKHttpManager;
import com.whmnrc.feimei.presener.CreateOrderPresenter;
import com.whmnrc.feimei.presener.GetVipTypePresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.login.LoginActivity;
import com.whmnrc.feimei.utils.TextSpannableUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.utils.ToastUtils;
import com.whmnrc.feimei.utils.evntBusBean.PayEvent;
import com.whmnrc.feimei.utils.pay.PayUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/27.
 */

public class PayVipActivity extends BaseActivity implements GetVipTypePresenter.GetVipTypeListener, CreateOrderPresenter.CreateOrderListener {
    @BindView(R.id.iv_user_img)
    ImageView mIvUserImg;
    @BindView(R.id.tv_no_login)
    TextView mTvNoLogin;
    @BindView(R.id.tv_vip_time)
    TextView mTvVipTime;
    @BindView(R.id.tv_vip_hint)
    TextView mTvVipHint;
    @BindView(R.id.tv_login)
    TextView mTvLogin;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.rv_type_list)
    RecyclerView mRvTypeList;
    @BindView(R.id.iv_select_wx)
    ImageView mIvSelectWx;
    @BindView(R.id.iv_select_zfb)
    ImageView mIvSelectZfb;
    @BindView(R.id.tv_pay_price)
    TextView mTvPayPrice;
    public PayUtils mPayUtils;
    private int payType = CommonConstant.Common.PAY_METHOD_WX;
    public GetVipTypePresenter mGetVipTypePresenter;
    public VipPriceAdapter mVipPriceAdapter;
    public double mVIpPrice;
    private CreateOrderPresenter mCreateOrderPresenter;
    public String mOtherId;

    @Override
    protected void initViewData() {

        EventBus.getDefault().register(this);

        mCreateOrderPresenter = new CreateOrderPresenter(this);

        mPayUtils = new PayUtils();
        if (UserManager.getUser() != null && UserManager.getUser().getMobile() != null && TextUtils.isEmpty(UserManager.getUser().getMobile())) {
            mTvVipTime.setVisibility(View.GONE);
            mTvLogin.setVisibility(View.VISIBLE);
        } else {
            if (UserManager.getUserIsVip()) {
                setTitle("续费");
                mTvVipTime.setText(String.format("会员至：%s", TimeUtils.getDateToString(Long.parseLong(UserManager.getUser().getVIP()))));
                mTvVipTime.setVisibility(View.VISIBLE);
            } else {
                setTitle("开通VIP");
                mTvVipTime.setVisibility(View.GONE);
            }
            mTvLogin.setVisibility(View.GONE);
            mTvNoLogin.setVisibility(View.GONE);
            mTvVipHint.setVisibility(View.GONE);
        }

        mGetVipTypePresenter = new GetVipTypePresenter(this);
        mGetVipTypePresenter.getVipTypeList(0);
        if (UserManager.getUser() != null && UserManager.getUser().getHeadImg() != null && !TextUtils.isEmpty(UserManager.getUser().getHeadImg())) {
            GlideUtils.LoadImage(this, UserManager.getUser().getHeadImg(), mIvUserImg);
        }

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRvList.setLayoutManager(layoutManager);
        mVipPriceAdapter = new VipPriceAdapter(this, R.layout.item_vip_price_list);
        mRvList.setAdapter(mVipPriceAdapter);

        mVipPriceAdapter.setVipPriceListener(bean -> {
            mVIpPrice = bean.getMoney();
            mTvPayPrice.setText(String.format("共计：￥%2.2f", mVIpPrice));
            mOtherId = bean.getPayType_ID();
            String reslutPirce = mTvPayPrice.getText().toString().trim();
            TextSpannableUtils.changeTextColor(mTvPayPrice, reslutPirce, 4, reslutPirce.length(), ContextCompat.getColor(this, R.color.good_price_red));
        });

        mRvTypeList.setLayoutManager(new GridLayoutManager(this, 4));
        VipTypeAdapter vipTypeAdapter = new VipTypeAdapter(this, R.layout.item_vip_type_list);
        List<RuleDescriptionBean> ruleDescriptionDataList = RuleDescriptionActivity.getRuleDescriptionDataList();
        vipTypeAdapter.setDataArray(ruleDescriptionDataList);
        mRvTypeList.setAdapter(vipTypeAdapter);

        selectedView(mIvSelectWx);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pay_vip;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PayVipActivity.class);
        context.startActivity(starter);
    }


    private View lastView;

    private void selectedView(View view) {
        if (lastView != null) {
            lastView.setSelected(false);
        }
        if (view == null) {
            return;
        }
        if (!view.isSelected()) {
            view.setSelected(true);
            lastView = view;
        } else {
            view.setSelected(false);
        }

    }


    @OnClick({R.id.tv_login, R.id.ll_commit, R.id.ll_wx, R.id.ll_zfb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                LoginActivity.start(view.getContext());
                break;
            case R.id.ll_commit:
                mCreateOrderPresenter.createOrder(1, "", 1, mOtherId);
                isShowDialog(true);
                break;
            case R.id.ll_wx:
                payType = CommonConstant.Common.PAY_METHOD_WX;
                selectedView(mIvSelectWx);
                break;
            case R.id.ll_zfb:
                payType = CommonConstant.Common.PAY_METHOD_ZFB;
                selectedView(mIvSelectZfb);
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
                ToastUtils.showToast(st);
                UserManager.refresh();
                EventBus.getDefault().post(new PayEvent().setEventType(PayEvent.PAY_SUCCESS));
                EventBus.getDefault().post(new PayEvent().setEventType(PayEvent.PAY_VIP_SUCCESS));
                finish();
            }

            @Override
            public void onFailure(int code, String errorMsg) {
                ToastUtils.showToast(errorMsg);
                finish();
            }
        });
    }

    @Override
    public void getVipTypeSuccess(List<VipTypeListBean.ResultdataBean> resultdataBeans) {
        mVipPriceAdapter.setDataArray(resultdataBeans);
        mVipPriceAdapter.notifyDataSetChanged();
    }

    @Override
    public void getVipTypeField() {

    }

    @Override
    public void createOrderSuccess(String orderId) {
        payResult(payType, orderId);
        isShowDialog(false);
    }

    @Override
    public void createOrderField() {
        isShowDialog(false);
    }

    @Override
    protected void onDestroy() {
        mPayUtils = null;
        lastView = null;
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void payEvent(PayEvent payEvent) {

    }
}
