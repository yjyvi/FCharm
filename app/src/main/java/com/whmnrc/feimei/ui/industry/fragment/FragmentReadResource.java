package com.whmnrc.feimei.ui.industry.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.AuthorResourceListAdapter;
import com.whmnrc.feimei.adapter.ResourceListAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.ui.industry.ColumnActivity;
import com.whmnrc.feimei.ui.mine.PayActivity;
import com.whmnrc.feimei.utils.KeyboardUtils;
import com.whmnrc.feimei.utils.TestDataUtils;

import java.util.Random;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 */

public class FragmentReadResource extends LazyLoadFragment {

    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;
    @BindView(R.id.rv_author_list)
    RecyclerView mRvAuthorList;
    @BindView(R.id.et_search_content)
    EditText mEtSearchContent;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_read_resource;
    }

    @Override
    protected void initViewData() {
        mRvAuthorList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mRvAuthorList.setNestedScrollingEnabled(false);
        AuthorResourceListAdapter authorResourceListAdapter = new AuthorResourceListAdapter(getActivity(), R.layout.item_auther_resource_list);
        authorResourceListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvAuthorList.setAdapter(authorResourceListAdapter);
        authorResourceListAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                int type = new Random().nextInt(2) + 1;
                if (type == 1) {
                    PayActivity.start(view.getContext(), PayActivity.COLUMN_PAY,"123");
                } else {
                    ColumnActivity.start(view.getContext());
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });


        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        ResourceListAdapter resourceListAdapter = new ResourceListAdapter(getActivity(), R.layout.item_resource_list);
        resourceListAdapter.setDataArray(TestDataUtils.initTestData(15));
        mRvProductList.setAdapter(resourceListAdapter);

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
}
