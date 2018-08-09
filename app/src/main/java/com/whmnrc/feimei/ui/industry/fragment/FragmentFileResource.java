package com.whmnrc.feimei.ui.industry.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ResourceListAdapter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.utils.TestDataUtils;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class FragmentFileResource extends LazyLoadFragment {

    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_resource;
    }

    @Override
    protected void initViewData() {
        mLlFilter.setVisibility(View.GONE);

        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        ResourceListAdapter resourceListAdapter = new ResourceListAdapter(getActivity(), R.layout.item_library_resource_list);
        resourceListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvProductList.setAdapter(resourceListAdapter);

    }

    /**
     * 初始化实例
     *
     * @return
     */
    public static FragmentFileResource newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FragmentFileResource fragmentResource = new FragmentFileResource();
        fragmentResource.setArguments(bundle);
        return fragmentResource;
    }
}
