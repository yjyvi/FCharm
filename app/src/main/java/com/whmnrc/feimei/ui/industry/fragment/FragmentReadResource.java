package com.whmnrc.feimei.ui.industry.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.AuthorResourceListAdapter;
import com.whmnrc.feimei.adapter.ResourceListAdapter;
import com.whmnrc.feimei.beans.ColumnBean;
import com.whmnrc.feimei.beans.ReadListBean;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.presener.GetColumnPresenter;
import com.whmnrc.feimei.presener.GetReadPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.utils.KeyboardUtils;

import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class FragmentReadResource extends LazyLoadFragment implements GetReadPresenter.GetReadListener, GetColumnPresenter.GetColumnListener, OnRefreshLoadMoreListener {

    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.rv_author_list)
    RecyclerView mRvAuthorList;
    @BindView(R.id.et_search_content)
    EditText mEtSearchContent;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    private GetColumnPresenter mGetColumnPresenter;
    private GetReadPresenter mGetReadPresenter;
    public AuthorResourceListAdapter mAuthorResourceListAdapter;
    public ResourceListAdapter mResourceListAdapter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_read_resource;
    }

    @Override
    protected void initViewData() {

        mGetColumnPresenter = new GetColumnPresenter(this);
        mGetColumnPresenter.getColumnList();

        mGetReadPresenter = new GetReadPresenter(this);
        mGetReadPresenter.getReadList(true);


        mRefresh.setOnRefreshLoadMoreListener(this);

        mRvAuthorList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvAuthorList.setNestedScrollingEnabled(false);
        mAuthorResourceListAdapter = new AuthorResourceListAdapter(getActivity(), R.layout.item_auther_resource_list);
        mRvAuthorList.setAdapter(mAuthorResourceListAdapter);


        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mResourceListAdapter = new ResourceListAdapter(getActivity(), R.layout.item_resource_list);
        mRvProductList.setAdapter(mResourceListAdapter);

        mEtSearchContent.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                KeyboardUtils.hideKeyBoard(getActivity(), mEtSearchContent);
                String mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {
                    SearchConditionBean searchConditionBean = new SearchConditionBean();
                    searchConditionBean.setContent(mSearchContent);
                    SearchActivity.start(getActivity(), SearchActivity.SEARCH_RESOURCE, searchConditionBean);
                    mEtSearchContent.setText("");
                    return true;
                }
            }
            return false;
        });
    }

    /**
     * 初始化实例
     *
     * @return
     */
    public static FragmentReadResource newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FragmentReadResource fragmentResource = new FragmentReadResource();
        fragmentResource.setArguments(bundle);
        return fragmentResource;
    }

    @Override
    public void getReadSuccess(boolean isRefresh, ReadListBean.ResultdataBean bean) {
        if (isRefresh) {
            mResourceListAdapter.setDataArray(bean.getRead());
        } else {
            List<ReadListBean.ResultdataBean.ReadBean> datas = mResourceListAdapter.getDatas();
            if (bean.getPagination().getRecords() == datas.size()) {
                mRefresh.setEnableLoadMore(false);
            }
            datas.addAll(bean.getRead());
            mResourceListAdapter.setDataArray(datas);
        }
        mResourceListAdapter.notifyDataSetChanged();

        showEmpty(mResourceListAdapter, mVsEmpty);
    }

    @Override
    public void getReadField() {

    }

    @Override
    public void getColumnSuccess(List<ColumnBean.ResultdataBean> resultdataBean) {
        mAuthorResourceListAdapter.setDataArray(resultdataBean);
        mAuthorResourceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getColumnField() {

    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
        mGetReadPresenter.getReadList(false);
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
        mRefresh.setEnableLoadMore(true);
        mGetReadPresenter.getReadList(true);
        mGetColumnPresenter.getColumnList();

    }
}
