package com.whmnrc.feimei.ui.mine;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ProductLibraryListAdapter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.utils.TestDataUtils;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/25.
 */

public class MyCollectionProductFragment extends LazyLoadFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRvList;
    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;

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

        int type = getArguments().getInt("type");
        mRvList.setNestedScrollingEnabled(false);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom = getResources().getDimensionPixelOffset(R.dimen.dm_0_5);
            }
        });
        int itemProductList;
        if (type == 1) {
            itemProductList = R.layout.item_product_list;
        } else {
            itemProductList = R.layout.item_collection_resource_list;
        }

        ProductLibraryListAdapter productLibraryListAdapter = new ProductLibraryListAdapter(getActivity(), itemProductList);
        productLibraryListAdapter.setDataArray(TestDataUtils.initTestData(12));
        mRvList.setAdapter(productLibraryListAdapter);
    }
}
