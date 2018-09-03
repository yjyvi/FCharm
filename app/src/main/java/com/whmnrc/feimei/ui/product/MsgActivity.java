package com.whmnrc.feimei.ui.product;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.MsgListAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.MsgListBean;
import com.whmnrc.feimei.presener.GetMsgPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.ui.CommonH5WebView;

import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class MsgActivity extends BaseActivity implements GetMsgPresenter.GetMsgListener {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    public GetMsgPresenter mGetMsgPresenter;
    public MsgListAdapter mMsgListAdapter;
    public int currentPosition;


    @Override
    protected void initViewData() {

        isShowDialog(true);

        mGetMsgPresenter = new GetMsgPresenter(this);
        mGetMsgPresenter.getMsgList(true);
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
        mMsgListAdapter = new MsgListAdapter(this, R.layout.item_msg_list);

        mRvBusinessList.setAdapter(mMsgListAdapter);
        mMsgListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {


            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                MsgListBean.ResultdataBean.PushBean pushBean = mMsgListAdapter.getDatas().get(position);
                CommonH5WebView.startCommonH5WebView(view.getContext(), pushBean.getConten(), pushBean.getTitle());
                if (pushBean.getIsRead() == 0) {
                    mGetMsgPresenter.readMsg(pushBean.getID());
                    currentPosition = position;
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mGetMsgPresenter.getMsgList(false);
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mRefresh.setEnableLoadMore(true);
                refreshLayout.finishRefresh();
                mGetMsgPresenter.getMsgList(true);
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

    @Override
    public void getMsgSuccess(boolean isRefresh, MsgListBean.ResultdataBean bean) {
        if (isRefresh) {

            mMsgListAdapter.setDataArray(bean.getPush());
        } else {
            List<MsgListBean.ResultdataBean.PushBean> datas = mMsgListAdapter.getDatas();

            if (bean.getPagination().getRecords() <= datas.size()) {
                mRefresh.setEnableLoadMore(false);
            }

            if (bean.getPush().size() > 0) {
                datas.addAll(bean.getPush());
                mMsgListAdapter.setDataArray(datas);
            }
        }

        mMsgListAdapter.notifyDataSetChanged();

        showEmpty(mMsgListAdapter, mVsEmpty);

        isShowDialog(false);
    }

    @Override
    public void getMsgField() {
        isShowDialog(false);
    }

    @Override
    public void readMsgSuccess() {
        mMsgListAdapter.getDatas().get(currentPosition).setIsRead(1);
        mMsgListAdapter.notifyItemChanged(currentPosition);
    }
}
