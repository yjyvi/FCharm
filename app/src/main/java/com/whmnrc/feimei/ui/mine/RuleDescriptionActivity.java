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
import com.whmnrc.feimei.adapter.VipTypeAdapter;
import com.whmnrc.feimei.beans.RuleDescriptionBean;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.utils.TimeUtils;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;
import com.whmnrc.mylibrary.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @data 2018/5/21.
 */

public class RuleDescriptionActivity extends BaseActivity {

    @BindView(R.id.iv_user_img)
    ImageView mIvUserImg;
    @BindView(R.id.tv_vip_time)
    TextView mTvVipTime;
    @BindView(R.id.rv_list)
    RecyclerView mRvList;

    @Override
    protected void initViewData() {
        UserManager.refresh();
        EventBus.getDefault().register(this);
        setTitle("规则说明");

        mRvList.setLayoutManager(new GridLayoutManager(this, 4));
        VipTypeAdapter adapter = new VipTypeAdapter(this, R.layout.item_vip_type_list);
        adapter.setDataArray(getRuleDescriptionDataList());
        mRvList.setAdapter(adapter);

    }

    /**
     * VIP权益数据
     * @return
     */
    public static List<RuleDescriptionBean> getRuleDescriptionDataList() {
        List<RuleDescriptionBean> ruleDescriptionBeans = new ArrayList<>();
        ruleDescriptionBeans.add(new RuleDescriptionBean(1, R.mipmap.icon_table_organization_chart_select, "企业名录"));
        ruleDescriptionBeans.add(new RuleDescriptionBean(2, R.mipmap.icon_table_industry_resources_select, "行业资源"));
        ruleDescriptionBeans.add(new RuleDescriptionBean(3,R.mipmap.icon_table_product_library_select, "行业产品库"));
        ruleDescriptionBeans.add(new RuleDescriptionBean(4, R.mipmap.icon_table_special_information_select, "特聘信息"));
        return ruleDescriptionBeans;
    }


    private void showUserData() {
        if (UserManager.getUser() != null && UserManager.getUser().getHeadImg() != null && !TextUtils.isEmpty(UserManager.getUser().getHeadImg())) {
            GlideUtils.LoadImage(this, UserManager.getUser().getHeadImg(), mIvUserImg);
        }


        if (UserManager.getUserIsVip()) {
            mTvVipTime.setVisibility(View.VISIBLE);
            mTvVipTime.setText(String.format("会员至：%s", TimeUtils.getDateToString(Long.parseLong(UserManager.getUser().getVIP()))));
        } else {
            mTvVipTime.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_vip_rule_description;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, RuleDescriptionActivity.class);
        context.startActivity(starter);
    }


    @OnClick({R.id.tv_pay_vip})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pay_vip:
                PayVipActivity.start(view.getContext());
                break;
            default:
                break;
        }
    }


    @Subscribe
    public void userChangeEvent(UserInfoEvent userInfoEvent) {
        if (UserInfoEvent.UPDATE_USER_INFO == userInfoEvent.getEventType()) {
            showUserData();
        }
    }
}
