package com.whmnrc.feimei.ui.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ProductCollectionListAdapter;
import com.whmnrc.feimei.adapter.ResourceFileCollectionListAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.CommonAdapter;
import com.whmnrc.feimei.beans.ProductCollectionListBean;
import com.whmnrc.feimei.presener.AddOrDelCollectionPresenter;
import com.whmnrc.feimei.presener.GetCollectionListPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.utils.EmptyListUtils;
import com.whmnrc.feimei.utils.evntBusBean.CollectionEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class MyCollectionProductFragment extends LazyLoadFragment implements GetCollectionListPresenter.GetCollectionListListener, AddOrDelCollectionPresenter.AddOrDelCollectionListener {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    public GetCollectionListPresenter mGetCollectionListPresenter;
    public ProductCollectionListAdapter mProductLibraryListAdapter;
    public int mType;
    private ResourceFileCollectionListAdapter mResourceListAdapter;
    public AddOrDelCollectionPresenter mAddOrDelCollectionPresenter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_my_collection_product;
    }

    /**
     * 初始化实例
     *
     * @return
     */
    public static MyCollectionProductFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        MyCollectionProductFragment homeFragment = new MyCollectionProductFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    protected void initViewData() {

        mAddOrDelCollectionPresenter = new AddOrDelCollectionPresenter(this);

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        mGetCollectionListPresenter = new GetCollectionListPresenter(this);
        mType = getArguments().getInt("type");
        loadData();

        mRvList.setNestedScrollingEnabled(false);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));


        if (mType == 0) {
            mProductLibraryListAdapter = new ProductCollectionListAdapter(getActivity(), R.layout.item_collection_product_list);
            mRvList.setAdapter(mProductLibraryListAdapter);

            mProductLibraryListAdapter.setGoToDetailsListener((isSelect) -> {
                        boolean isSelectResult = false;
                        for (ProductCollectionListBean.ResultdataBean resultdataBean : mProductLibraryListAdapter.getDatas()) {
                            if (resultdataBean.isSelect()) {
                                isSelectResult = resultdataBean.isSelect();
                            }
                        }
                        EventBus.getDefault().post(new CollectionEvent().setEventType(CollectionEvent.SELECT).setData(isSelectResult));
                    }
            );
        } else {
            mResourceListAdapter = new ResourceFileCollectionListAdapter(getActivity(), R.layout.item_collection_resource_list);
            mRvList.setAdapter(mResourceListAdapter);

            mResourceListAdapter.setGoToDetailsListener((isSelect) -> {
                        boolean isSelectResult = false;
                        for (ProductCollectionListBean.ResultdataBean resultdataBean : mResourceListAdapter.getDatas()) {
                            if (resultdataBean.isSelect()) {
                                isSelectResult = resultdataBean.isSelect();
                            }
                        }
                        EventBus.getDefault().post(new CollectionEvent().setEventType(CollectionEvent.SELECT).setData(isSelectResult));
                    }
            );
        }


        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                mGetCollectionListPresenter.getProductCollectionList(mType, false);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mRefresh.setEnableLoadMore(true);
                refreshLayout.finishRefresh();
                isShowEdit(false);
                loadData();
                EventBus.getDefault().post(new CollectionEvent().setEventType(CollectionEvent.SELECT).setData(null));
            }
        });
    }

    @Override
    public void getProductCollectionSuccess(List<ProductCollectionListBean.ResultdataBean> beans, boolean isRefresh) {
        if (mType == 0) {
            if (isRefresh) {
                mProductLibraryListAdapter.setDataArray(beans);
            } else {
                if (beans.size() == 0) {
                    mRefresh.setEnableLoadMore(false);
                    return;
                }
                List<ProductCollectionListBean.ResultdataBean> datas = mProductLibraryListAdapter.getDatas();
                datas.addAll(beans);
                mProductLibraryListAdapter.setDataArray(datas);
            }
            mProductLibraryListAdapter.notifyDataSetChanged();
            showEmpty(mProductLibraryListAdapter, mVsEmpty);
        } else {
            if (isRefresh) {
                mResourceListAdapter.setDataArray(beans);
            } else {
                if (beans.size() == 0) {
                    mRefresh.setEnableLoadMore(false);
                    return;
                }
                List<ProductCollectionListBean.ResultdataBean> datas = mResourceListAdapter.getDatas();
                datas.addAll(beans);
                mResourceListAdapter.setDataArray(datas);
            }
            mResourceListAdapter.notifyDataSetChanged();
            isShowEmpty(mResourceListAdapter, mVsEmpty);
        }

    }


    public void isShowEmpty(CommonAdapter adapter, ViewStub mVsEmpty) {
        if (adapter != null && adapter.getDatas().size() == 0) {
            EmptyListUtils.loadEmpty(true, "没有收藏", R.mipmap.no_collection, mVsEmpty);
        } else {
            if (mVsEmpty != null) {
                mVsEmpty.setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void getCollectionField() {

    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Subscribe
    public void collectionEvent(CollectionEvent collectionEvent) {
        if (collectionEvent.getEventType() == CollectionEvent.CANCEL_COLLECTION) {
            loadData();
        } else if (CollectionEvent.SHOW_EDIT == collectionEvent.getEventType()) {
            isShowEdit(true);
        } else if (collectionEvent.getEventType() == CollectionEvent.HIDE_EDIT) {
            isShowEdit(false);
        } else if (collectionEvent.getEventType() == CollectionEvent.DEL) {
            delCollection();
        }
    }


    /**
     * 删除选中的
     */
    private void delCollection() {

        ArrayList<String> readIds = new ArrayList<>();

        if (mType == 0) {
            List<ProductCollectionListBean.ResultdataBean> datas = mProductLibraryListAdapter.getDatas();
            for (ProductCollectionListBean.ResultdataBean data : datas) {
                if (data.isSelect()) {
                    readIds.add(data.getID());
                }
            }
        } else {
            List<ProductCollectionListBean.ResultdataBean> datas = mResourceListAdapter.getDatas();
            for (ProductCollectionListBean.ResultdataBean data : datas) {
                if (data.isSelect()) {
                    readIds.add(data.getID());
                }
            }
        }
        if (readIds.size() > 0) {
            mAddOrDelCollectionPresenter.delCollection(readIds);
        }
    }

    /**
     * 显示复选按扭
     *
     * @param isShow
     */
    private void isShowEdit(boolean isShow) {
        if (mType == 0) {
            List<ProductCollectionListBean.ResultdataBean> datas = mProductLibraryListAdapter.getDatas();
            for (ProductCollectionListBean.ResultdataBean data : datas) {
                data.setShowEdit(isShow);
            }

            mProductLibraryListAdapter.notifyDataSetChanged();

        } else {
            List<ProductCollectionListBean.ResultdataBean> datas = mResourceListAdapter.getDatas();
            for (ProductCollectionListBean.ResultdataBean data : datas) {
                data.setShowEdit(isShow);
            }
            mResourceListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void collectionSuccess(boolean isAdd) {
        if (mGetCollectionListPresenter != null) {
            isShowEdit(false);
            loadData();
            EventBus.getDefault().post(new CollectionEvent().setEventType(CollectionEvent.SELECT).setData(null));
        }
    }

    public void loadData() {
        mGetCollectionListPresenter.getProductCollectionList(mType, true);
    }

    @Override
    public void collectionCodeField() {
    }
}
