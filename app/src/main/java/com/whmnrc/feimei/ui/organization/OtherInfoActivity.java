package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.OtherInfoAdapter;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class OtherInfoActivity extends BaseActivity {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    public List<OrganizationDetailsBean.ResultdataBean.EnterpriseOtherBean> mEnterpriseOther;
    public OtherInfoAdapter mOtherListAdapter;

    @Override
    protected void initViewData() {
        refresh.setEnableLoadMore(false);
        refresh.setEnableRefresh(false);
        mEnterpriseOther = getIntent().getParcelableArrayListExtra("enterpriseOther");

        setTitle("其它信息");
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mOtherListAdapter = new OtherInfoAdapter(this, R.layout.item_other_info);
        mOtherListAdapter.setDataArray(mEnterpriseOther);
        mRvBusinessList.setAdapter(mOtherListAdapter);

        showEmpty(mOtherListAdapter, mVsEmpty);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_business_more;
    }

    public static void start(Context context, List<OrganizationDetailsBean.ResultdataBean.EnterpriseOtherBean> enterpriseOther) {
        Intent starter = new Intent(context, OtherInfoActivity.class);
        starter.putParcelableArrayListExtra("enterpriseOther", (ArrayList<? extends Parcelable>) enterpriseOther);
        context.startActivity(starter);
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, OtherInfoActivity.class);
        context.startActivity(starter);
    }
}
