package com.whmnrc.feimei.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.whmnrc.feimei.CommonConstant;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.SPUtils;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;
import com.whmnrc.feimei.pop.PopHintInfo;
import com.whmnrc.mylibrary.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/7/24.
 * 我的界面
 */

public class MineActivity extends BaseActivity {
    @BindView(R.id.iv_header_img)
    ImageView mIvHeaderImg;
    @BindView(R.id.tv_nickname)
    TextView mTvNickname;
    @BindView(R.id.iv_is_vip)
    ImageView mIvIsVip;
    @BindView(R.id.tv_vip_time)
    TextView mTvVipTime;
    private PopHintInfo mPopHintInfo;

    @Override
    protected void initViewData() {
        UserManager.refresh();
        EventBus.getDefault().register(this);
        showUserInfo();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_mine;
    }


    public static void start(Context context) {
        Intent starter = new Intent(context, MineActivity.class);
        context.startActivity(starter);
    }


    @OnClick({R.id.rl_all_order,R.id.ll_all_order, R.id.rl_no_pay_order, R.id.rl_pay_order, R.id.ll_collection, R.id.ll_address_manager, R.id.ll_free_back, R.id.ll_setting, R.id.ll_login_out, R.id.iv_is_vip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_all_order:
            case R.id.ll_all_order:
                OrderListActivity.start(view.getContext(), 0);
                break;
            case R.id.rl_no_pay_order:
                OrderListActivity.start(view.getContext(), 1);
                break;
            case R.id.rl_pay_order:
                OrderListActivity.start(view.getContext(), 2);
                break;
            case R.id.ll_collection:
                MyCollectionActivity.start(view.getContext());
                break;
            case R.id.ll_address_manager:
                AddressManagerActivity.start(view.getContext());
                break;
            case R.id.ll_free_back:
                FreeBackActivity.start(view.getContext());
                break;
            case R.id.ll_setting:
                SettingActivity.start(view.getContext());
                break;
            case R.id.ll_login_out:
                if (mPopHintInfo == null) {
                    mPopHintInfo = new PopHintInfo(MineActivity.this, "亲，确定退出登录吗？");
                }
                mPopHintInfo.show();
                mPopHintInfo.setPopHintListener(new PopHintInfo.PopHintListener() {
                    @Override
                    public void confirm() {
                        UserManager.clearUser();
                        SPUtils.put(MineActivity.this, CommonConstant.Common.LAST_LOGIN_ID, "");
                        finish();
                    }
                });
                break;
            case R.id.iv_is_vip:
                RuleDescriptionActivity.start(view.getContext());
                break;
            default:
                break;
        }
    }

    @Subscribe
    public void userChangeEvent(UserInfoEvent userInfoEvent) {
        if (userInfoEvent.getEventType() == UserInfoEvent.LOGIN_OUT) {
            finish();
        }else  if (userInfoEvent.getEventType() == UserInfoEvent.UPDATE_USER_INFO) {
            showUserInfo();
        }

    }

    private void showUserInfo() {
        if (UserManager.getUser() != null && UserManager.getUser().getHeadImg() != null && !TextUtils.isEmpty(UserManager.getUser().getHeadImg())) {
            GlideUtils.LoadImage(this, UserManager.getUser().getHeadImg(), mIvHeaderImg);
        }

        if (UserManager.getUserIsVip()) {
            mIvIsVip.setVisibility(View.VISIBLE);
            mTvVipTime.setVisibility(View.VISIBLE);
            mTvVipTime.setText(String.format("会员至：%s", TimeUtils.getDateToString(Long.parseLong(UserManager.getUser().getVIP()))));
        } else {
            mIvIsVip.setVisibility(View.GONE);
            mTvVipTime.setVisibility(View.INVISIBLE);
        }

        if (UserManager.getUser() != null && UserManager.getUser().getNickName() != null && !TextUtils.isEmpty(UserManager.getUser().getNickName())) {
            mTvNickname.setText(UserManager.getUser().getNickName());
        }
    }
}
