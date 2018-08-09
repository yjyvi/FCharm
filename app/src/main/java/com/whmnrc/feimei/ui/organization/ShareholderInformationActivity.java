package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ShareholderInformationListAdapter;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class ShareholderInformationActivity extends BaseActivity {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    public List<OrganizationDetailsBean.ResultdataBean.ShareholderBean> mShareholder;

    @Override
    protected void initViewData() {
        mShareholder = getIntent().getParcelableArrayListExtra("shareholder");
        setTitle("股东信息");
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mRvBusinessList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.dm_8);
            }
        });
        ShareholderInformationListAdapter shareholderInformationListAdapter = new ShareholderInformationListAdapter(this, R.layout.item_shareholder_information_list);
        shareholderInformationListAdapter.setDataArray(mShareholder);
        mRvBusinessList.setAdapter(shareholderInformationListAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_shareholder_information;
    }

    public static void start(Context context, List<OrganizationDetailsBean.ResultdataBean.ShareholderBean> shareholder) {
        Intent starter = new Intent(context, ShareholderInformationActivity.class);
        starter.putParcelableArrayListExtra("shareholder", (ArrayList<? extends Parcelable>) shareholder);
        context.startActivity(starter);
    }
}
