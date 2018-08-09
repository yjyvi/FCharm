package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.VipPriceAdapter;
import com.whmnrc.feimei.adapter.VipTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.login.LoginActivity;
import com.whmnrc.feimei.utils.TestDataUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.mylibrary.utils.GlideUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/27.
 */

public class PayVipActivity extends BaseActivity {
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

    @Override
    protected void initViewData() {

        if (UserManager.getUser() != null && UserManager.getUser().getMobile() != null && TextUtils.isEmpty(UserManager.getUser().getMobile())) {
            setTitle("开通VIP");
            mTvVipTime.setVisibility(View.GONE);
            mTvLogin.setVisibility(View.VISIBLE);
        } else {
            setTitle("续费");
            if (UserManager.getUserIsVip()) {
                mTvVipTime.setText(String.format("会员至：%s", TimeUtils.getDateToString(Long.parseLong(UserManager.getUser().getVIP()))));
                mTvVipTime.setVisibility(View.VISIBLE);
            } else {
                mTvVipTime.setVisibility(View.GONE);
            }
            mTvLogin.setVisibility(View.GONE);
            mTvNoLogin.setVisibility(View.GONE);
            mTvVipHint.setVisibility(View.GONE);
        }

        if (UserManager.getUser() != null && UserManager.getUser().getHeadImg() != null && !TextUtils.isEmpty(UserManager.getUser().getHeadImg())) {
            GlideUtils.LoadImage(this, UserManager.getUser().getHeadImg(), mIvUserImg);
        }

        mRvList.setLayoutManager(new GridLayoutManager(this, 3));
        VipPriceAdapter adapter = new VipPriceAdapter(this, R.layout.item_vip_price_list);
        adapter.setDataArray(TestDataUtils.initTestData(3));
        mRvList.setAdapter(adapter);
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                selectedView2(view);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRvTypeList.setLayoutManager(new GridLayoutManager(this, 4));
        VipTypeAdapter vipTypeAdapter = new VipTypeAdapter(this, R.layout.item_vip_type_list);
        vipTypeAdapter.setDataArray(TestDataUtils.initTestData(8));
        mRvTypeList.setAdapter(vipTypeAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_pay_vip;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, PayVipActivity.class);
        context.startActivity(starter);
    }



    private View lastView2;

    private void selectedView2(View view) {
        if (lastView2 != null) {
            lastView2.setSelected(false);
        }
        if (!view.isSelected()) {
            view.setSelected(true);
            lastView2 = view;
        } else {
            view.setSelected(false);
        }

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


    @OnClick({R.id.tv_login, R.id.ll_commit, R.id.ll_wx, R.id.ll_zfb})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                LoginActivity.start(view.getContext());
                break;
            case R.id.ll_commit:
                break;
            case R.id.ll_wx:
                selectedView(mIvSelectWx);
                break;
            case R.id.ll_zfb:
                selectedView(mIvSelectZfb);
                break;
            default:
                break;
        }
    }

}
