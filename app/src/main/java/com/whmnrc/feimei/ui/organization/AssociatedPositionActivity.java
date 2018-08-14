package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.SpecialInformationListAdapter;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.presener.GetRecruitPresenter;
import com.whmnrc.feimei.ui.BaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class AssociatedPositionActivity extends BaseActivity implements GetRecruitPresenter.GetRecruitListener {


    @BindView(R.id.rv_business_list)
    RecyclerView mRvBusinessList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private GetRecruitPresenter mGetRecruitPresenter;
    public SpecialInformationListAdapter mSpecialInformationListAdapter;
    public String mOtherId;

    @Override
    protected void initViewData() {
        refresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                refreshLayout.setEnableLoadMore(true);
                getData(true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                getData(false);
            }
        });
        isShowDialog(true);
        mOtherId = getIntent().getStringExtra("otherId");
        mGetRecruitPresenter = new GetRecruitPresenter(this);
        getData(true);

        setTitle("关联岗位");
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mSpecialInformationListAdapter = new SpecialInformationListAdapter(this, R.layout.item_recruitment_list);
        mRvBusinessList.setAdapter(mSpecialInformationListAdapter);
    }

    private void getData(boolean isRefresh) {
        mGetRecruitPresenter.getRecruit(isRefresh, "", "", "", mOtherId, "", "", "");
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_business_more;
    }


    public static void start(Context context, String otherId) {
        Intent starter = new Intent(context, AssociatedPositionActivity.class);
        starter.putExtra("otherId", otherId);
        context.startActivity(starter);
    }

    @Override
    public void getRecruitSuccess(GetRecruitBean.ResultdataBean bean, boolean isRefresh) {
        if (isRefresh) {
            mSpecialInformationListAdapter.setDataArray(bean.getRecruit());
        } else {
            List<GetRecruitBean.ResultdataBean.RecruitBean> datas = mSpecialInformationListAdapter.getDatas();
            if (bean.getPagination().getRecords() == datas.size()) {
                refresh.setEnableLoadMore(false);
                return;
            }
            datas.addAll(bean.getRecruit());
            mSpecialInformationListAdapter.setDataArray(datas);
        }
        mSpecialInformationListAdapter.notifyDataSetChanged();
        showEmpty(mSpecialInformationListAdapter, mVsEmpty);
        isShowDialog(false);
    }

    @Override
    public void getRecruitField() {

    }
}
