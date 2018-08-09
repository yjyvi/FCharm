package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.MsgListAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.organization.OtherInfoDetailsActivity;
import com.whmnrc.feimei.utils.TestDataUtils;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class MsgActivity extends BaseActivity {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;

    @Override
    protected void initViewData() {

        setTitle("消息");
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mRvBusinessList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.top = getResources().getDimensionPixelOffset(R.dimen.dm_8);
            }
        });
        MsgListAdapter msgListAdapter = new MsgListAdapter(this, R.layout.item_msg_list);
        msgListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvBusinessList.setAdapter(msgListAdapter);
        msgListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                OtherInfoDetailsActivity.start(view.getContext(), "消息详情", "消息详情");
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_business_more;
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MsgActivity.class);
        context.startActivity(starter);
    }
}
