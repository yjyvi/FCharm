package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.SpecialInformationListAdapter;
import com.whmnrc.feimei.beans.GetRecruitBean;
import com.whmnrc.feimei.presener.GetRecruitPresenter;
import com.whmnrc.feimei.ui.BaseActivity;

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
    private GetRecruitPresenter mGetRecruitPresenter;
    public SpecialInformationListAdapter mSpecialInformationListAdapter;

    @Override
    protected void initViewData() {
        isShowDialog(true);
        String otherId = getIntent().getStringExtra("otherId");
        mGetRecruitPresenter = new GetRecruitPresenter(this);
        mGetRecruitPresenter.getRecruit(true, "", "", "", otherId, "", "", "");

        setTitle("关联岗位");
        mRvBusinessList.setLayoutManager(new LinearLayoutManager(this));
        mRvBusinessList.setNestedScrollingEnabled(false);
        mSpecialInformationListAdapter = new SpecialInformationListAdapter(this, R.layout.item_recruitment_list);
//        specialInformationListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvBusinessList.setAdapter(mSpecialInformationListAdapter);
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
        mSpecialInformationListAdapter.setDataArray(bean.getRecruit());
        mSpecialInformationListAdapter.notifyDataSetChanged();
        showEmpty(mSpecialInformationListAdapter, mVsEmpty);
        isShowDialog(false);
    }

    @Override
    public void getRecruitField() {

    }
}
