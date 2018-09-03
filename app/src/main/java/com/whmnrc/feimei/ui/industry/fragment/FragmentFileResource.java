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
import com.whmnrc.feimei.adapter.ResourceFileListAdapter;
import com.whmnrc.feimei.beans.ResourcesFileBean;
import com.whmnrc.feimei.beans.SearchConditionBean;
import com.whmnrc.feimei.presener.GetLibraryPresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.home.SearchActivity;
import com.whmnrc.feimei.utils.KeyboardUtils;
import com.whmnrc.feimei.utils.evntBusBean.PayEvent;
import com.whmnrc.feimei.utils.evntBusBean.UserInfoEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

/**
 * @author yjyvi
 * @data 2018/7/24.
 * 文库
 */

public class FragmentFileResource extends LazyLoadFragment implements GetLibraryPresenter.GetLibraryListener {

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
    public ResourceFileListAdapter mResourceListAdapter;
    private GetLibraryPresenter mGetLibraryPresenter;

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fagment_resource;
    }

    @Override
    protected void initViewData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        mLlFilter.setVisibility(View.GONE);

        mRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore();
                mGetLibraryPresenter.getLibraryList(false, "","");
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mRefresh.setEnableLoadMore(true);
                refreshLayout.finishRefresh();
                mGetLibraryPresenter.getLibraryList();
            }
        });

        mGetLibraryPresenter = new GetLibraryPresenter(this);
        mGetLibraryPresenter.getLibraryList();

        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mResourceListAdapter = new ResourceFileListAdapter(getActivity(), R.layout.item_library_resource_list);
        mRvProductList.setAdapter(mResourceListAdapter);


        mEtSearchContent.setOnEditorActionListener((view, keyCode, event) -> {
            if (keyCode == EditorInfo.IME_ACTION_SEARCH) {
                // 先隐藏键盘
                KeyboardUtils.hideKeyBoard(getActivity(), mEtSearchContent);
                String mSearchContent = view.getText().toString().trim();

                if (!TextUtils.isEmpty(mSearchContent)) {
                    SearchConditionBean searchConditionBean = new SearchConditionBean();
                    searchConditionBean.setContent(mSearchContent);
                    SearchActivity.start(getActivity(), SearchActivity.SEARCH_FILE, searchConditionBean);
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
    public static FragmentFileResource newInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        FragmentFileResource fragmentResource = new FragmentFileResource();
        fragmentResource.setArguments(bundle);
        return fragmentResource;
    }

    @Override
    public void getReadSuccess(boolean isRefresh, ResourcesFileBean.ResultdataBean bean) {
        if (isRefresh) {
            mResourceListAdapter.setDataArray(bean.getLibrarys());
        } else {
            List<ResourcesFileBean.ResultdataBean.LibrarysBean> datas = mResourceListAdapter.getDatas();
            if (datas.size() == bean.getPagination().getRecords()) {
                mRefresh.setEnableLoadMore(false);
                return;
            }

            datas.addAll(bean.getLibrarys());
            mResourceListAdapter.setDataArray(datas);
        }

        mResourceListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getReadField() {

    }


    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    @Subscribe
    public void payEvent(PayEvent payEvent) {
        if (payEvent.getEventType() == PayEvent.PAY_SUCCESS) {
            if (mGetLibraryPresenter != null) {
                mGetLibraryPresenter.getLibraryList();
            }
        }
    }


    @Subscribe
    public void userChangeEvent(UserInfoEvent userInfoEvent) {
      if (userInfoEvent.getEventType() == UserInfoEvent.UPDATE_USER_INFO) {
            if (mGetLibraryPresenter != null) {
                mGetLibraryPresenter.getLibraryList();
            }
        }

    }
}
