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

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ResourceListAdapter;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.pop.PopInformation;
import com.whmnrc.feimei.presener.GetLibraryPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.utils.KeyboardUtils;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 * 光通通迅
 */

public class FragmentInformationResource extends LazyLoadFragment {

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

    private PopInformation mPopInformation;
    public GetLibraryPresenter mGetLibraryPresenter;
    public ResourceListAdapter mResourceListAdapter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_resource;
    }

    @Override
    protected void initViewData() {


        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mResourceListAdapter = new ResourceListAdapter(getActivity(), R.layout.item_information_resource_list);
        mRvProductList.setAdapter(mResourceListAdapter);

        mEtSearchContent.requestFocus();
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


        mLlFilter.setOnClickListener(v -> {
            if (mPopInformation == null) {
                mPopInformation = new PopInformation(getActivity(), mLlFilter);
                mPopInformation.setPopHintListener(bean -> {
                    mTvTypeName.setText(bean.getTypeName());
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


}
