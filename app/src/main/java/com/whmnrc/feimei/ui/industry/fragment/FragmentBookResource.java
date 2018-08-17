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

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ResourceBookListAdapter;
import com.whmnrc.feimei.beans.RegulationBookListBean;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.presener.GetRegulationBookPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.utils.KeyboardUtils;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 * 规格书
 */

public class FragmentBookResource extends LazyLoadFragment implements GetRegulationBookPresenter.GetBookListener {

    @BindView(R.id.et_search_content)
    EditText mEtSearchContent;
    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.ll_filter)
    LinearLayout mLlFilter;
    public ResourceBookListAdapter mResourceBookListAdapter;
    public GetRegulationBookPresenter mGetRegulationBookPresenter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_resource;
    }

    @Override
    protected void initViewData() {
        mLlFilter.setVisibility(View.GONE);

        mGetRegulationBookPresenter = new GetRegulationBookPresenter(this);
        mGetRegulationBookPresenter.getBookList();

        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mResourceBookListAdapter = new ResourceBookListAdapter(getActivity(), R.layout.item_library_resource_list);
        mRvProductList.setAdapter(mResourceBookListAdapter);


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
    public static FragmentBookResource newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FragmentBookResource fragmentResource = new FragmentBookResource();
        fragmentResource.setArguments(bundle);
        return fragmentResource;
    }

    @Override
    public void getBookSuccess(boolean isRefresh, RegulationBookListBean.ResultdataBean bean) {
        mResourceBookListAdapter.setDataArray(bean.getRead());
        mResourceBookListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getBookField() {

    }
}
