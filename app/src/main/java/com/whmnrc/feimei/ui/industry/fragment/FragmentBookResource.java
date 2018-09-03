package com.whmnrc.feimei.ui.industry.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ResourceBookListAdapter;
import com.whmnrc.feimei.beans.RegulationBookListBean;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.presener.AddOrDelCollectionPresenter;
import com.whmnrc.feimei.presener.GetRegulationBookPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.utils.KeyboardUtils;
import com.whmnrc.feimei.utils.evntBusBean.CollectionEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 * 规格书
 */

public class FragmentBookResource extends LazyLoadFragment implements GetRegulationBookPresenter.GetBookListener, AddOrDelCollectionPresenter.AddOrDelCollectionListener {

    @BindView(R.id.et_search_content)
    EditText mEtSearchContent;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    public ResourceBookListAdapter mResourceBookListAdapter;
    public GetRegulationBookPresenter mGetRegulationBookPresenter;
    private AddOrDelCollectionPresenter mAddOrDelCollectionPresenter;
    private int mCollectionPosition;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_resource;
    }

    @Override
    protected void initViewData() {
        mLlFilter.setVisibility(View.GONE);

        mGetRegulationBookPresenter = new GetRegulationBookPresenter(this);
        mGetRegulationBookPresenter.getBookList();

        mAddOrDelCollectionPresenter = new AddOrDelCollectionPresenter(this);

        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mResourceBookListAdapter = new ResourceBookListAdapter(getActivity(), R.layout.item_library_resource_list);
        mRvProductList.setAdapter(mResourceBookListAdapter);

        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                mGetRegulationBookPresenter.getBookList(false, "", "","");
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mRefresh.setEnableLoadMore(true);
                refreshLayout.finishRefresh();
                mGetRegulationBookPresenter.getBookList();
            }
        });

        mResourceBookListAdapter.setBookCollectionListener(position -> {

            if (!UserManager.getIsLogin(getActivity())) {
                return;
            }

            mCollectionPosition = position;
            RegulationBookListBean.ResultdataBean.ReadBean readBean = mResourceBookListAdapter.getDatas().get(position);
            if (readBean.getIsCollection() == 1) {
                ArrayList<String> readIds = new ArrayList<>();
                readIds.add(readBean.getID());
                mAddOrDelCollectionPresenter.delCollection(readIds);
            } else {
                mAddOrDelCollectionPresenter.addCollection(readBean.getID(), AddOrDelCollectionPresenter.SPECIFICATION_COLLECTION);
            }
        });

        mEtSearchContent.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                KeyboardUtils.hideKeyBoard(getActivity(), mEtSearchContent);
                String mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {
                    SearchConditionBean searchConditionBean = new SearchConditionBean();
                    searchConditionBean.setContent(mSearchContent);
                    SearchActivity.start(getActivity(), SearchActivity.SEARCH_BOOK, searchConditionBean);
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
    public static FragmentBookResource newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FragmentBookResource fragmentResource = new FragmentBookResource();
        fragmentResource.setArguments(bundle);
        return fragmentResource;
    }

    @Override
    public void getBookSuccess(boolean isRefresh, RegulationBookListBean.ResultdataBean bean) {
        if (isRefresh) {
            mResourceBookListAdapter.setDataArray(bean.getRead());
        } else {
            List<RegulationBookListBean.ResultdataBean.ReadBean> datas = mResourceBookListAdapter.getDatas();
            if (datas.size() == bean.getPagination().getRecords()) {
                mRefresh.setEnableLoadMore(false);
                return;
            }
            datas.addAll(bean.getRead());
            mResourceBookListAdapter.setDataArray(datas);
        }

        mResourceBookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBookField() {

    }

    @Override
    public void collectionSuccess(boolean isAdd) {
        mResourceBookListAdapter.getDatas().get(mCollectionPosition).setIsCollection(isAdd ? 1 : 0);
        mResourceBookListAdapter.notifyItemChanged(mCollectionPosition);

        if (!isAdd) {
            EventBus.getDefault().post(new CollectionEvent().setEventType(CollectionEvent.CANCEL_COLLECTION));
        }
    }

    @Override
    public void collectionCodeField() {

    }


}
