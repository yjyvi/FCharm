package com.whmnrc.feimei.ui.table;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.whmnrc.feimei.R;
import com.whmnrc.feimei.adapter.ProductLibraryListAdapter;
import com.whmnrc.feimei.adapter.ProductLibraryTypeAdapter;
import com.whmnrc.feimei.adapter.recycleViewBaseAdapter.MultiItemTypeAdapter;
import com.whmnrc.feimei.beans.BannerListBean;
import com.whmnrc.feimei.beans.ProductListBean;
import com.whmnrc.feimei.beans.ProductTypeBean;
import com.whmnrc.feimei.presener.GetBannerPresenter;
import com.whmnrc.feimei.presener.GetProductListPresenter;
import com.whmnrc.feimei.presener.GetProductTypePresenter;
import com.whmnrc.feimei.ui.LazyLoadFragment;
import com.whmnrc.feimei.ui.UserManager;
import com.whmnrc.feimei.ui.mine.MineActivity;
import com.whmnrc.feimei.ui.product.SearchProductMoreActivity;
import com.whmnrc.mylibrary.utils.GlideUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author yjyvi
 * @date 2018/1/30
 * 行业产品库
 */

public class ProductLibraryFragment extends LazyLoadFragment implements OnRefreshLoadMoreListener, GetProductListPresenter.GetProductListListener, GetProductTypePresenter.GetProductTypeListener, GetBannerPresenter.GetBannerListener {
    @BindView(R.id.rv_type)
    RecyclerView mRvType;
    @BindView(R.id.rv_product_list)
    RecyclerView mRvProductList;

    @BindView(R.id.vs_empty)
    ViewStub mVsEmpty;
    @BindView(R.id.header_layout)
    LinearLayout headerLayout;
    @BindView(R.id.ll_is_show_all)
    LinearLayout mLlIsShowAll;
    @BindView(R.id.banner)
    Banner mBanner;

    @BindView(R.id.refresh)
    SmartRefreshLayout mRefresh;

    @BindView(R.id.nsv_layout)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.tv_more)
    TextView mTvMore;
    @BindView(R.id.iv_more)
    ImageView mIvMore;
    public ProductLibraryTypeAdapter mProductLibraryTypeAdapter;
    public GetProductTypePresenter mGetProductTypePresenter;
    public GetProductListPresenter mGetProductListPresenter;
    private boolean isShowAllType;
    public ProductLibraryListAdapter mProductLibraryListAdapter;
    private String mName = "";
    private String mCommodityClassId = "";
    private GetBannerPresenter mGetBannerPresenter;


    /**
     * 品牌的一页显示的最大数据
     */

    @Override
    protected int contentViewLayoutID() {
        return R.layout.fragment_product_library;
    }


    @Override
    protected void initViewData() {

        initType();

        initProductList();

        mGetBannerPresenter = new GetBannerPresenter(this);
        mGetBannerPresenter.getBanner(1);

        mGetProductTypePresenter = new GetProductTypePresenter(this);
        mGetProductTypePresenter.getProductType();

        mGetProductListPresenter = new GetProductListPresenter(this);
        mGetProductListPresenter.getProductList(mName, mCommodityClassId);


        mRefresh.setOnRefreshLoadMoreListener(this);


    }

    private void initProductList() {
        mRvProductList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvProductList.setNestedScrollingEnabled(false);
        mProductLibraryListAdapter = new ProductLibraryListAdapter(getActivity(), R.layout.item_product_list);
        mRvProductList.setAdapter(mProductLibraryListAdapter);

        mProductLibraryListAdapter.setGoToDetailsListener(position -> {
            ProductListBean.ResultdataBean.EnterpriseBean enterpriseBean = mProductLibraryListAdapter.getDatas().get(position);
            enterpriseBean.setClickNumber(enterpriseBean.getClickNumber() + 1);
            mProductLibraryListAdapter.notifyDataSetChanged();
        });

    }

    private void initType() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        mRvType.setLayoutManager(gridLayoutManager);
        mRvType.setNestedScrollingEnabled(false);
        DividerItemDecoration divider = new DividerItemDecoration(mRvType.getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(mRvType.getContext(), R.drawable.custom_divider));
        mRvType.addItemDecoration(divider);
        mProductLibraryTypeAdapter = new ProductLibraryTypeAdapter(getActivity(), R.layout.item_organization_chart_type);
        mRvType.setAdapter(mProductLibraryTypeAdapter);
        mProductLibraryTypeAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                ProductTypeBean.ResultdataBean resultdataBean = mProductLibraryTypeAdapter.getDatas().get(position);
                SearchProductMoreActivity.start(view.getContext(), resultdataBean.getID());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }


    /**
     * 初始化实例
     *
     * @return
     */
    public static ProductLibraryFragment newInstance() {
        Bundle bundle = new Bundle();
        ProductLibraryFragment homeFragment = new ProductLibraryFragment();
        homeFragment.setArguments(bundle);
        return homeFragment;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onLoadMore(RefreshLayout refreshLayout) {
        refreshLayout.finishLoadMore();
        mGetProductListPresenter.getProductList(false, "Sort", mName, mCommodityClassId, "desc");
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh();
        refreshLayout.setEnableLoadMore(true);
        mGetProductTypePresenter.getProductType();
        mGetProductListPresenter.getProductList(mName, mCommodityClassId);

    }


    @OnClick({R.id.iv_back, R.id.tv_search, R.id.iv_user_info, R.id.ll_is_show_all})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                break;
            case R.id.tv_search:
                SearchProductMoreActivity.start(view.getContext(), "");
                break;
            case R.id.iv_user_info:
                if (!UserManager.getIsLogin(v.getContext())) {
                    return;
                }
                MineActivity.start(v.getContext());
                break;
            case R.id.ll_is_show_all:
                if (!mIvMore.isSelected()) {
                    mTvMore.setText("收起");
                    mIvMore.setRotation(180);
                    mIvMore.setSelected(true);
                    isShowAllType = true;
                } else {
                    mTvMore.setText("展开全部");
                    mIvMore.setRotation(0);
                    mIvMore.setSelected(false);
                    isShowAllType = false;
                }

                mGetProductTypePresenter.getProductType();

                mProductLibraryTypeAdapter.notifyDataSetChanged();

                break;
            default:
                break;
        }
    }

    @Override
    public void getProductListSuccess(ProductListBean.ResultdataBean bean, boolean isRefresh) {
        if (isRefresh) {
            mProductLibraryListAdapter.setDataArray(bean.getEnterprise());
        } else {
            List<ProductListBean.ResultdataBean.EnterpriseBean> datas = mProductLibraryListAdapter.getDatas();
            if (bean.getPagination().getRecords() == datas.size()) {
                mRefresh.setEnableLoadMore(false);
                return;
            }

            datas.addAll(bean.getEnterprise());
            mProductLibraryListAdapter.setDataArray(datas);
        }

        mProductLibraryListAdapter.notifyDataSetChanged();

        showEmpty(mProductLibraryListAdapter, mVsEmpty);
    }

    @Override
    public void getProductListField() {

    }

    @Override
    public void getProductTypeSuccess(List<ProductTypeBean.ResultdataBean> bean) {
        if (mProductLibraryTypeAdapter == null) {
            return;
        }
        if (isShowAllType) {
            mProductLibraryTypeAdapter.setDataArray(bean);
        } else {
            if (bean.size() != 0) {
                List<ProductTypeBean.ResultdataBean> datas = mProductLibraryTypeAdapter.getDatas();
                if (datas == null) {
                    datas = new ArrayList<>();
                }
                if (datas.size() > 0) {
                    datas.clear();
                }
                for (int i = 0; i < bean.size(); i++) {
                    if (i >= 8) {
                        break;
                    }

                    datas.add(bean.get(i));
                }
                mProductLibraryTypeAdapter.setDataArray(datas);
            }
        }

        mProductLibraryTypeAdapter.notifyDataSetChanged();

    }


    @Override
    public void getBannerSuccess(List<BannerListBean.ResultdataBean> bean) {
        initBanner(bean);
    }


    /**
     * 轮播图
     */
    private void initBanner(List<BannerListBean.ResultdataBean> bannerList) {
        if (mBanner != null) {
            mBanner.setDelayTime(3000);
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            mBanner.setIndicatorGravity(BannerConfig.CENTER);
            mBanner.offsetLeftAndRight(10);
            mBanner.setImages(bannerList).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    final BannerListBean.ResultdataBean resultdataBeans = (BannerListBean.ResultdataBean) path;
                    String bannerUrl = resultdataBeans.getBanner_Url();
                    if (TextUtils.isEmpty(bannerUrl)) {
                        if (!bannerUrl.startsWith("http")) {
                            bannerUrl = getResources().getString(R.string.service_host_address) + bannerUrl;
                        }
                    }
                    GlideUtils.LoadImage(imageView.getContext(), bannerUrl, imageView);
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }).start();
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (mBanner != null) {
            mBanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mBanner != null) {
            mBanner.stopAutoPlay();
        }
    }

}
