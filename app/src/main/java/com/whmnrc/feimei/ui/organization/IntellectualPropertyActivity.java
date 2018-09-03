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
import com.whmnrc.feimei.adapter.IntellectualPropertyListAdapter;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class IntellectualPropertyActivity extends BaseActivity {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;

    public List<OrganizationDetailsBean.ResultdataBean.CertificateBean> mCertificateBeans;

    @Override
    protected void initViewData() {

        mCertificateBeans = getIntent().getParcelableArrayListExtra("certificateBeans");
        setTitle("知识产权");
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mRvBusinessList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.dm_8);
            }
        });
        IntellectualPropertyListAdapter intellectualPropertyListAdapter = new IntellectualPropertyListAdapter(this, R.layout.item_intellectual_property_list);
        intellectualPropertyListAdapter.setDataArray(mCertificateBeans);
        mRvBusinessList.setAdapter(intellectualPropertyListAdapter);

        showEmpty(intellectualPropertyListAdapter, mVsEmpty);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_intellectual_property;
    }

    public static void start(Context context, List<OrganizationDetailsBean.ResultdataBean.CertificateBean> certificateBeans) {
        Intent starter = new Intent(context, IntellectualPropertyActivity.class);
        starter.putParcelableArrayListExtra("certificateBeans", (ArrayList<? extends Parcelable>) certificateBeans);
        context.startActivity(starter);
    }
}
