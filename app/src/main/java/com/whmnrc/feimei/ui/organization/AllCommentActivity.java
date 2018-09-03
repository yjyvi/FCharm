package com.whmnrc.feimei.ui.organization;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewStub;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.AllCommentAdapter;
import com.whmnrc.feimei.beans.CommentListBean;
import com.whmnrc.feimei.presener.GetCommentPresenter;
import com.whmnrc.feimei.ui.BaseActivity;
import com.whmnrc.feimei.utils.TextSpannableUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/26.
 */

public class AllCommentActivity extends BaseActivity implements GetCommentPresenter.GetCommentListener {

    @BindView(R.id.tv_all_comment)
    TextView mTvAllComment;
    @BindView(R.id.rv_business_list)
    RecyclerView mRvCommentList;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    public GetCommentPresenter mGetCommentPresenter;
    public String mOtherId;
    public AllCommentAdapter mOrganizationCommentAdapter;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    @Override
    protected void initViewData() {
        isShowDialog(true);
        mOtherId = getIntent().getStringExtra("otherId");
        mGetCommentPresenter = new GetCommentPresenter(this);
        mGetCommentPresenter.getComment(mOtherId, true);

        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
                mRefresh.setEnableLoadMore(true);
                mGetCommentPresenter.getComment(mOtherId, true);
            }

            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                mGetCommentPresenter.getComment(mOtherId, false);
            }
        });
        setTitle("全部评价");
        mRvCommentList.setNestedScrollingEnabled(false);
        mRvCommentList.setLayoutManager(new LinearLayoutManager(this));
        mOrganizationCommentAdapter = new AllCommentAdapter(this, R.layout.item_organization_comment);
        mRvCommentList.setAdapter(mOrganizationCommentAdapter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_all_comment;
    }

    public static void start(Context context, String otherId) {
        Intent starter = new Intent(context, AllCommentActivity.class);
        starter.putExtra("otherId", otherId);
        context.startActivity(starter);
    }

    @Override
    public void getCommentListSuccess(CommentListBean.ResultdataBean beans, boolean isRefresh) {

        if (isRefresh) {
            mOrganizationCommentAdapter.setDataArray(beans.getComment());

            mTvAllComment.setText(String.format("全部评价(%s)", beans.getPagination().getRecords()));
            String textContent = mTvAllComment.getText().toString().trim();
            TextSpannableUtils.changeTextColor(mTvAllComment, textContent, 4, textContent.length(), ContextCompat.getColor(this, R.color.good_price_red));

        } else {
            if (beans.getPagination().getRecords() == mOrganizationCommentAdapter.getDatas().size()) {
                mRefresh.setEnableLoadMore(false);
            }

            List<CommentListBean.ResultdataBean.CommentBean> datas = mOrganizationCommentAdapter.getDatas();
            datas.addAll(beans.getComment());
            mOrganizationCommentAdapter.setDataArray(datas);
        }
        mOrganizationCommentAdapter.notifyDataSetChanged();

        showEmpty(mOrganizationCommentAdapter, mVsEmpty);

        isShowDialog(false);
    }

    @Override
    public void getCommentListField() {

    }


}
