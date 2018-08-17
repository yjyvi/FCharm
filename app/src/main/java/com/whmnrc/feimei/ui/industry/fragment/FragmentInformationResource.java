package com.whmnrc.feimei.ui.industry.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ResourceNewsAdapter;
import com.whmnrc.feimei.beans.NewsListBean;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.pop.PopInformation;
import com.whmnrc.feimei.presener.AddOrDelCollectionPresenter;
import com.whmnrc.feimei.presener.GetNewsPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.utils.KeyboardUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 * 光通通迅
 */

public class FragmentInformationResource extends LazyLoadFragment implements GetNewsPresenter.GetNewsListener, AddOrDelCollectionPresenter.AddOrDelCollectionListener {

    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.et_search_content)
    EditText mEtSearchContent;
    @BindView(R.id.tv_type_name)
    TextView mTvTypeName;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    private PopInformation mPopInformation;
    public ResourceNewsAdapter mResourceNewsAdapter;
    public GetNewsPresenter mGetNewsPresenter;
    public int mCollectionPosition;
    private AddOrDelCollectionPresenter mAddOrDelCollectionPresenter;
    public int currentNewType = -1;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_resource;
    }

    @Override
    protected void initViewData() {

        mGetNewsPresenter = new GetNewsPresenter(this);
        mGetNewsPresenter.getNewsList();

        mAddOrDelCollectionPresenter = new AddOrDelCollectionPresenter(this);

        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                mGetNewsPresenter.getNewsList(false, "", -1);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mGetNewsPresenter.getNewsList(true, "", currentNewType);
                refreshLayout.setEnableLoadMore(true);
                refreshLayout.finishRefresh();
            }
        });

        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mResourceNewsAdapter = new ResourceNewsAdapter(getActivity(), R.layout.item_information_resource_list);
        mRvProductList.setAdapter(mResourceNewsAdapter);

        mResourceNewsAdapter.setNewsCollectionListener(position -> {

            if (!UserManager.getIsLogin(getActivity())) {
                return;
            }

            mCollectionPosition = position;
            NewsListBean.ResultdataBean.NewsBean readBean = mResourceNewsAdapter.getDatas().get(position);
            if (readBean.getIsCollection() == 1) {
                ArrayList<String> readIds = new ArrayList<>();
                readIds.add(readBean.getID());
                mAddOrDelCollectionPresenter.delCollection(readIds);
            } else {
                mAddOrDelCollectionPresenter.addCollection(readBean.getID(), AddOrDelCollectionPresenter.INFORMATION_COLLECTION);
            }
        });

        mResourceNewsAdapter.setGoToDetailsListener(position -> {
            NewsListBean.ResultdataBean.NewsBean newsBean = mResourceNewsAdapter.getDatas().get(position);
            newsBean.setClickNumber(newsBean.getClickNumber() + 1);
            mResourceNewsAdapter.notifyItemChanged(position);
        });

        mEtSearchContent.requestFocus();
        mEtSearchContent.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                KeyboardUtils.hideKeyBoard(getActivity(), mEtSearchContent);
                String mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {
                    SearchConditionBean searchConditionBean = new SearchConditionBean();
                    searchConditionBean.setContent(mSearchContent);
                    searchConditionBean.setCurrentNewType(currentNewType);
                    SearchActivity.start(getActivity(), SearchActivity.SEARCH_INFORMATION, searchConditionBean);
                    mEtSearchContent.setText("");
                    return true;
                }
            }
            return false;
        });


        mLlFilter.setOnClickListener(v -> {
            if (mPopInformation == null) {
                mPopInformation = new PopInformation(getActivity(), mLlFilter);
                mPopInformation.setPopHintListener(bean -> {
                    mTvTypeName.setText(bean.getTypeName());
                    currentNewType = bean.getType();
                    mGetNewsPresenter.getNewsList(true, "", currentNewType);
                });
            }

            mPopInformation.show();
        });
    }

    /**
     * 初始化实例
     *
     * @return
     */
    public static FragmentInformationResource newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FragmentInformationResource fragmentResource = new FragmentInformationResource();
        fragmentResource.setArguments(bundle);
        return fragmentResource;
    }


    @Override
    public void getNewsSuccess(boolean isRefresh, NewsListBean.ResultdataBean bean) {
        if (isRefresh) {
            mResourceNewsAdapter.setDataArray(bean.getNews());
        } else {
            List<NewsListBean.ResultdataBean.NewsBean> datas = mResourceNewsAdapter.getDatas();

            if (datas.size() == bean.getPagination().getRecords()) {
                mRefresh.setEnableLoadMore(false);
            }

            datas.addAll(bean.getNews());
            mResourceNewsAdapter.setDataArray(datas);
        }
        mResourceNewsAdapter.notifyDataSetChanged();

        showEmpty(mResourceNewsAdapter, mVsEmpty);

    }

    @Override
    public void getNewsField() {

    }

    @Override
    public void collectionSuccess(boolean isAdd) {
        mResourceNewsAdapter.getDatas().get(mCollectionPosition).setIsCollection(isAdd ? 1 : 0);
        mResourceNewsAdapter.notifyItemChanged(mCollectionPosition);
    }

    @Override
    public void collectionCodeField() {

    }
}
