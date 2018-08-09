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
import com.whmnrc.feimei.adapter.OtherListAdapter;
import com.whmnrc.feimei.beans.OrganizationDetailsBean;
import com.whmnrc.feimei.ui.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class BusinessMoreActivity extends BaseActivity {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    public List<OrganizationDetailsBean.ResultdataBean.RelationBean> mRelation;

    @Override
    protected void initViewData() {
        mRelation = getIntent().getParcelableArrayListExtra("relation");
        setTitle(getIntent().getStringExtra("title"));
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mRvBusinessList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.dm_8);
            }
        });
        OtherListAdapter otherListAdapter = new OtherListAdapter(this, R.layout.item_business_more_list);
        otherListAdapter.setDataArray(mRelation);
        mRvBusinessList.setAdapter(otherListAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_business_more;
    }

    public static void start(Context context, List<OrganizationDetailsBean.ResultdataBean.RelationBean> relation, String title) {
        Intent starter = new Intent(context, BusinessMoreActivity.class);
        starter.putExtra("title", title);
        starter.putParcelableArrayListExtra("relation", (ArrayList<? extends Parcelable>) relation);
        context.startActivity(starter);
    }


}
